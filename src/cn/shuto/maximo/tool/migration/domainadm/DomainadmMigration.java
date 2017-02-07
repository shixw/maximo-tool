package cn.shuto.maximo.tool.migration.domainadm;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cn.shuto.maximo.tool.migration.domainadm.bean.ALNDomain;
import cn.shuto.maximo.tool.migration.domainadm.bean.MaxDomain;
import cn.shuto.maximo.tool.system.SystemEnvironmental;
import cn.shuto.maximo.tool.util.CommonUtil;
import cn.shuto.maximo.tool.util.DBUtil;
import cn.shuto.maximo.tool.util.SerializeUtil;

public class DomainadmMigration {
	private static Logger _log = Logger.getLogger(DomainadmMigration.class.getName());
	private String DOMAINADMFILEPATH = "\\package\\domainadm\\Domainadm.mtep";

	private Connection conn = null;

	private static final String SELECTMAXDOMAIN = "select DOMAINID, DESCRIPTION, DOMAINTYPE, MAXTYPE, LENGTH, SCALE, MAXDOMAINID, INTERNAL from MAXDOMAIN WHERE DOMAINID = ?";
	private static final String SELECTALNDOMAIN = "select DOMAINID, VALUE, DESCRIPTION, SITEID, ORGID, ALNDOMAINID, VALUEID from ALNDOMAIN where domainid  = ?";

	PreparedStatement maxdomainST = null;
	PreparedStatement alndomainST = null;

	Statement insertSt = null;

	public DomainadmMigration() {
		conn = DBUtil.getInstance()
				.getMaximoConnection(SystemEnvironmental.getInstance().getStringParam("-maximopath"));
		if (conn != null) {
			try {
				if (SystemEnvironmental.getInstance().getStringParam("-option").startsWith("export")) {
					maxdomainST = conn.prepareStatement(SELECTMAXDOMAIN);
					alndomainST = conn.prepareStatement(SELECTALNDOMAIN);
				} else {
					insertSt = conn.createStatement();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导入域信息
	 */
	public void importDomainadm() {
		try {
			List<MaxDomain> list = SerializeUtil.readObjectForList(
					new File(SystemEnvironmental.getInstance().getStringParam("-importpath") + DOMAINADMFILEPATH));
			for (MaxDomain maxDomain : list) {
				clearDomainadm(maxDomain);
				_log.info("---导入表MaxDomain--数据 ：" + maxDomain.toInsertSql());
				insertSt.addBatch(maxDomain.toInsertSql());
				String domaintype = maxDomain.getDomaintype();
				// 如果为字母数字域 导入 ALNDomain 表
				if ("字母数字".equals(domaintype)) {
					_log.info("---域为字母数字域，导入表 --alndomain-- ");
					List<ALNDomain> alnDomains = maxDomain.getAlnDomains();
					if (alnDomains != null && alnDomains.size() > 0) {
						for (ALNDomain alnDomain : alnDomains) {
							_log.info("---导入表alnDomain--数据 ：" + alnDomain.toInsertSql());
							insertSt.addBatch(alnDomain.toInsertSql());
						}
					}
				}
			}

			insertSt.executeBatch();
			// 提交事务
			conn.commit();
		} catch (SQLException e) {
			try {
				// 回滚
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	private static final String DELETEMAXDOMAIN = "delete from MAXDOMAIN where DOMAINID='%s'";
	private static final String DELETEALNDOMAIN = "delete from ALNDOMAIN where DOMAINID='%s'";

	private void clearDomainadm(MaxDomain maxDomain) {
		String deleteFormatSql = String.format(DELETEMAXDOMAIN, maxDomain.getDomainid());
		_log.info("-----清理域数据:----------" + deleteFormatSql);
		try {
			insertSt.addBatch(deleteFormatSql);
			String domaintype = maxDomain.getDomaintype();
			// 如果为字母数字域 导入 ALNDomain 表
			if ("字母数字".equals(domaintype)) {
				_log.info("---域为字母数字域，清理表 --alndomain-- ");
				String deleteAlnDomainFormatSql = String.format(DELETEALNDOMAIN, maxDomain.getDomainid());
				_log.info("-----清理字母数据域数据:----------" + deleteAlnDomainFormatSql);
				insertSt.addBatch(deleteAlnDomainFormatSql);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void exportDomainadm(String exportObjects) {
		_log.info("---------- 需要导出的域为:" + exportObjects);
		// 需要导出的对象数组
		exportDomainadm(CommonUtil.buildExportParames(exportObjects));

	}

	/**
	 * 导出域数据
	 * 
	 * @param exportdomains
	 */
	public void exportDomainadm(String[] exportdomains) {
		List<MaxDomain> list = new ArrayList<MaxDomain>();
		try {
			for (String domain : exportdomains) {
				list.add(exportMaxDomain(domain.toUpperCase()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}

		// 将导出的集合进行Java序列化
		SerializeUtil.writeObject(list,
				new File(SystemEnvironmental.getInstance().getStringParam("-packagepath") + DOMAINADMFILEPATH));

	}

	/**
	 * 导出 MaxDomain 表及相关表数据
	 * 
	 * @param domainid
	 * @return
	 * @throws SQLException
	 */
	private MaxDomain exportMaxDomain(String domainid) throws SQLException {
		_log.info("--导出域--：" + domainid);
		// 导出maxdomain表
		MaxDomain maxDomain = exportMaxDomainToJavaBean(domainid);
		String domaintype = maxDomain.getDomaintype();
		// 如果为字母数字域 导出 ALNDomain 表
		if ("字母数字".equals(domaintype)) {
			_log.info("--域为字母数字域---导出表 ALNDomain-----");
			maxDomain.setAlnDomains(exportALNDomainToJavaBean(domainid));
		}
		return maxDomain;
	}

	/**
	 * 导出 字母数字域 对应的 ALNDomain 表的数据
	 * 
	 * @param domainid
	 * @return
	 * @throws SQLException
	 */
	private List<ALNDomain> exportALNDomainToJavaBean(String domainid) throws SQLException {
		List<ALNDomain> list = new ArrayList<ALNDomain>();
		alndomainST.setString(1, domainid);
		ResultSet rs = alndomainST.executeQuery();
		while (rs.next()) {
			list.add(new ALNDomain(CommonUtil.NULLTOEMPTY(rs.getString(1)), CommonUtil.NULLTOEMPTY(rs.getString(2)),
					CommonUtil.NULLTOEMPTY(rs.getString(3)), CommonUtil.NULLTOEMPTY(rs.getString(4)),
					CommonUtil.NULLTOEMPTY(rs.getString(5)), CommonUtil.NULLTOEMPTY(rs.getString(7))));
		}
		rs.close();
		return list;
	}

	/**
	 * 导出maxdomain表为JavaBean
	 * 
	 * @param domainid
	 * @return
	 * @throws SQLException
	 */
	private MaxDomain exportMaxDomainToJavaBean(String domainid) throws SQLException {
		maxdomainST.setString(1, domainid);
		ResultSet rs = maxdomainST.executeQuery();
		if (rs.next()) {
			return new MaxDomain(CommonUtil.NULLTOEMPTY(rs.getString(1)), CommonUtil.NULLTOEMPTY(rs.getString(2)),
					CommonUtil.NULLTOEMPTY(rs.getString(3)), CommonUtil.NULLTOEMPTY(rs.getString(4)), rs.getInt(5),
					rs.getInt(6), rs.getInt(8));
		}
		rs.close();
		return null;
	}

	/**
	 * 关闭打开的资源
	 */
	private void closeResource() {
		try {
			if (maxdomainST != null)
				maxdomainST.close();
			if (alndomainST != null)
				alndomainST.close();
			if (insertSt != null)
				insertSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
