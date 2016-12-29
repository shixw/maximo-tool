package cn.shuto.maximo.tool.migration.dbconfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import cn.shuto.maximo.tool.util.DBUtil;

/**
 * 数据库配置迁移
 * 
 * @author shixw
 *
 */
public class DBConfigMigration {
	private static Logger _log = Logger.getLogger(DBConfigMigration.class.getName());
	private String MAXIMOPATH = null;

	private Connection conn = null;

	private static final String SELECTMAXOBJECTCFG = "select OBJECTNAME, CLASSNAME, DESCRIPTION, EAUDITENABLED, EAUDITFILTER, ENTITYNAME, ESIGFILTER, EXTENDSOBJECT, IMPORTED, ISVIEW, PERSISTENT, SERVICENAME, SITEORGTYPE, USERDEFINED, CHANGED, MAINOBJECT, INTERNAL, MAXOBJECTID, TEXTDIRECTION from maxobjectcfg where objectname = ?";
	private static final String INSERTMAXOBJECTCFG = "insert into maxobjectcfg ( OBJECTNAME, CLASSNAME, DESCRIPTION, EAUDITENABLED, EAUDITFILTER, ENTITYNAME, ESIGFILTER, EXTENDSOBJECT, IMPORTED, ISVIEW, PERSISTENT, SERVICENAME, SITEORGTYPE, USERDEFINED, CHANGED, MAINOBJECT, INTERNAL, MAXOBJECTID, TEXTDIRECTION) values ( '%s', '%s', '%s', %s , '%s', '%s', '%s', '%s', %s , %s , %s , '%s', '%s', %s ,'I', %s , %s , MAXOBJECTCFGSEQ.nextval, '%s');";
	PreparedStatement maxobjectcfgST = null;

	public DBConfigMigration(String maximoPath) {
		this.MAXIMOPATH = maximoPath;
		conn = DBUtil.getInstance().getMaximoConnection(maximoPath);
		if (conn != null) {
			try {
				maxobjectcfgST = conn.prepareStatement(SELECTMAXOBJECTCFG);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导出数据库配置
	 * 
	 * @param exportObjects
	 *            导出数据库配置的多个对象，以逗号分开
	 */
	public void exportDBConfig(String exportObjects) {
		_log.info("---------- 需要导出的数据库配置的对象为:" + exportObjects);
		// 需要导出的对象数组
		String[] objects = buildExportObjects(exportObjects);
		// 遍历所有需要迁移的对象
		for (String object : objects) {
			try {
				exportObject(object);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据对象名称导出数据库配置的对象 需要导出的表结构包括: maxobjectcfg maxtablecfg maxattributecfg
	 * maxsysindexes maxsyskeys maxrelationship maxsequence autokey
	 * 
	 * @param objectName
	 * @throws SQLException
	 */
	private void exportObject(String objectName) throws SQLException {
		// 导出maxobjectcfg表
		String insertsql = exportMaxobjectcfg(objectName);
		System.out.println(insertsql);
	}

	public String exportMaxobjectcfg(String objectName) throws SQLException {

		maxobjectcfgST.setString(1, objectName);
		ResultSet rs = maxobjectcfgST.executeQuery();
		if (rs.next()) {
			return String.format(INSERTMAXOBJECTCFG, rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13),
					rs.getInt(14), rs.getString(16), rs.getString(17), rs.getString(19));
		}
		return null;
	}

	/**
	 * 以 “,” 为分隔符构建需要导出的对象数组
	 * 
	 * @param exportObjects
	 * @return
	 */
	private String[] buildExportObjects(String exportObjects) {
		return exportObjects.split(",");
	}

}
