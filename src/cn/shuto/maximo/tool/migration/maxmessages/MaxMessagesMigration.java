package cn.shuto.maximo.tool.migration.maxmessages;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cn.shuto.maximo.tool.migration.maxmessages.bean.MaxMessages;
import cn.shuto.maximo.tool.system.SystemEnvironmental;
import cn.shuto.maximo.tool.util.CommonUtil;
import cn.shuto.maximo.tool.util.DBUtil;
import cn.shuto.maximo.tool.util.SerializeUtil;

public class MaxMessagesMigration {
	private static Logger _log = Logger.getLogger(MaxMessagesMigration.class.getName());
	private String MAXMESSAGESFILEPATH = "\\package\\maxmessages\\maxmessages.mtep";

	private Connection conn = null;

	private static final String SELECTMESSAGES = "select MSGKEY, MSGGROUP, VALUE, TITLE, DISPLAYMETHOD, OPTIONS, BUTTONTEXT, MAXMESSAGESID, MSGID, EXPLANATION, ADMINRESPONSE, OPERATORRESPONSE, SYSTEMACTION, PREFIX from maxmessages where msgid = ?";

	PreparedStatement maxmessagesST = null;

	Statement importST = null;

	public MaxMessagesMigration() {
		conn = DBUtil.getInstance()
				.getMaximoConnection(SystemEnvironmental.getInstance().getStringParam("-maximopath"));
		if (conn != null) {
			try {
				maxmessagesST = conn.prepareStatement(SELECTMESSAGES);

				importST = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导入消息信息
	 */
	public void importMaxMessages() {
		try {
			List<MaxMessages> list = SerializeUtil.readObjectForList(
					new File(SystemEnvironmental.getInstance().getStringParam("-importpath") + MAXMESSAGESFILEPATH));
			for (MaxMessages maxMessage : list) {
				clearMaxMessage(maxMessage);
				_log.info("---导入表MaxMessages--数据 ：" + maxMessage.toInsertSql());
				importST.addBatch(maxMessage.toInsertSql());
			}

			importST.executeBatch();
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

	private static final String DELETEMAXMESSAGES = "delete from maxmessages where msgid='%s'";

	/**
	 * 导入前首先清理 消息 对应的数据
	 */
	private void clearMaxMessage(MaxMessages maxMessage) {

		try {
			String deleteFormatSql = String.format(DELETEMAXMESSAGES, maxMessage.getMsgid());
			_log.info("-----清理消息数据:----------" + deleteFormatSql);
			importST.addBatch(deleteFormatSql);

			// importST.executeBatch();
			// conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void exportMaxMessages(String exportMaxMessages) {
		_log.info("---------- 需要导出的消息为:" + exportMaxMessages);
		// 需要的消息的 MSGID 的数组
		exportMaxMessages(CommonUtil.buildExportParames(exportMaxMessages));

	}

	/**
	 * 导出消息数据
	 * 
	 * @param exportMaxMessages
	 */
	public void exportMaxMessages(String[] exportMaxMessages) {
		List<MaxMessages> list = new ArrayList<MaxMessages>();
		try {
			for (String msgid : exportMaxMessages) {
				list.add(exportMaxMessage(msgid.toUpperCase()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}

		// 将导出的集合进行Java序列化
		SerializeUtil.writeObject(list,
				new File(SystemEnvironmental.getInstance().getStringParam("-packagepath") + MAXMESSAGESFILEPATH));

	}

	/**
	 * 导出 消息 maxmessages 表及相关表数据
	 * 
	 * @param domainid
	 * @return
	 * @throws SQLException
	 */
	private MaxMessages exportMaxMessage(String msgid) throws SQLException {
		_log.info("--导出消息--消息的ID为：" + msgid);
		// 导出maxmessages表
		return exportMaxMessageToJavaBean(msgid);
	}

	/**
	 * 导出Maxmessages表为JavaBean
	 * 
	 * @param domainid
	 * @return
	 * @throws SQLException
	 */
	private MaxMessages exportMaxMessageToJavaBean(String msgid) throws SQLException {
		maxmessagesST.setString(1, msgid);
		ResultSet rs = maxmessagesST.executeQuery();
		if (rs.next()) {
			return new MaxMessages(CommonUtil.NULLTOEMPTY(rs.getString(1)), CommonUtil.NULLTOEMPTY(rs.getString(2)),
					CommonUtil.NULLTOEMPTY(rs.getString(3)), CommonUtil.NULLTOEMPTY(rs.getString(4)),
					CommonUtil.NULLTOEMPTY(rs.getString(5)), rs.getInt(6), CommonUtil.NULLTOEMPTY(rs.getString(7)),
					CommonUtil.NULLTOEMPTY(rs.getString(9)), CommonUtil.NULLTOEMPTY(rs.getString(10)),
					CommonUtil.NULLTOEMPTY(rs.getString(10)), CommonUtil.NULLTOEMPTY(rs.getString(12)),
					CommonUtil.NULLTOEMPTY(rs.getString(13)), rs.getInt(14));
		}
		rs.close();
		return null;
	}

	/**
	 * 关闭打开的资源
	 */
	private void closeResource() {
		try {
			if (maxmessagesST != null)
				maxmessagesST.close();

			if (importST != null)
				importST.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
