package cn.shuto.maximo.tool.migration.systemxml;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import cn.shuto.maximo.tool.system.SystemEnvironmental;
import cn.shuto.maximo.tool.util.CommonUtil;
import cn.shuto.maximo.tool.util.DBUtil;
import cn.shuto.maximo.tool.util.SerializeUtil;
import cn.shuto.maximo.tool.util.XMLUtil;

public class SystemXMLMigration {
	private static Logger _log = Logger.getLogger(SystemXMLMigration.class.getName());
	private String LOOKUPSFILEPATH = "\\package\\systemxml\\lookups.mtep";

	private Connection conn = null;

	private static final String SELECTLOOKUPS = "select presentation from maxpresentation where app = 'LOOKUPS'";

	PreparedStatement lookupsST = null;

	public SystemXMLMigration() {
		conn = DBUtil.getInstance()
				.getMaximoConnection(SystemEnvironmental.getInstance().getStringParam("-maximopath"));
		if (conn != null) {
			try {
				if (SystemEnvironmental.getInstance().getStringParam("-option").startsWith("export")) {
					lookupsST = conn.prepareStatement(SELECTLOOKUPS);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导入Lookups
	 */
	public void importLookups() {
		_log.info("---------- 导入的lookups.XML中的信息 :");
		try {
			Map<String, String> lookupsMap = SerializeUtil.readObject(
					new File(SystemEnvironmental.getInstance().getStringParam("-importpath") + LOOKUPSFILEPATH));
			Document d = getLookups2Document();
			if (lookupsMap != null && !lookupsMap.keySet().isEmpty()) {
				// 遍历map中的元素
				Set<String> keySet = lookupsMap.keySet();
				for (String key : keySet) {
					_log.info("---------- 导入的lookups.XML中的信息 :" + key);
					Node newNode = XMLUtil.String2XML(lookupsMap.get(key));
					Node oldNode = XMLUtil.selectSingleNode("/systemlib/table[@id='" + key + "']", d);
					Node importNode = d.importNode(newNode.getFirstChild(), true);
					if (oldNode != null) {
						d.getFirstChild().replaceChild(importNode, oldNode);
					} else {
						d.getFirstChild().appendChild(importNode);
					}

				}
				_log.info("---------- 将新的 lookups.XML 更新到数据库中--------------------");
				DBUtil.getInstance().updateClob(SELECTLOOKUPS + " for update", XMLUtil.XML2String(d));
			}
		} catch (DOMException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库中的 lookups.xml
	 * 
	 * @return
	 */
	private Document getLookups2Document() {
		try {
			ResultSet rs = lookupsST.executeQuery();
			if (rs.next()) {
				// 获取XML字符串
				String lookupsxmlString = rs.getString(1);
				// 将字符串转换为 Document
				return XMLUtil.String2XML(lookupsxmlString);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return null;
	}

	/**
	 * 导出lookups.xml
	 * 
	 * @param lookupsids
	 */
	public void exportLookups(String lookupsids) {
		_log.info("---------- 需要导出的lookups.XML 为:" + lookupsids);
		// 需要的消息的 MSGID 的数组
		String[] exportlookupsids = CommonUtil.buildExportParames(lookupsids);
		if (exportlookupsids != null)
			exportLookups(exportlookupsids);

	}

	/**
	 * 导出系统XML中的lookups
	 * 
	 * @param exportMaxMessages
	 */
	private void exportLookups(String[] exportlookupsids) {
		Map<String, String> lookupsMap = new HashMap<String, String>();
		try {
			Document d = getLookups2Document();
			// 从document中查找对应ID的table元素
			for (String lookupid : exportlookupsids) {
				Node node = XMLUtil.selectSingleNode("/systemlib/table[@id='" + lookupid + "']", d);
				// 将查找到XML节点转换为字符串存储的map中
				lookupsMap.put(lookupid, XMLUtil.XML2String(node));
			}
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

		// 将导出的集合进行Java序列化
		SerializeUtil.writeObject(lookupsMap,
				new File(SystemEnvironmental.getInstance().getStringParam("-packagepath") + LOOKUPSFILEPATH));

	}

	/**
	 * 关闭打开的资源
	 */
	private void closeResource() {
		try {
			if (lookupsST != null)
				lookupsST.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
