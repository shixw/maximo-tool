package cn.shuto.maximo.tool.migration.dbconfig;

import java.sql.Connection;
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

	public DBConfigMigration(String maximoPath) {
		this.MAXIMOPATH = maximoPath;
		conn = DBUtil.getInstance().getMaximoConnection(maximoPath);
	}

	/**
	 * 导出数据库配置
	 * 
	 * @param exportObjects
	 *            导出数据库配置的多个对象，以逗号分开
	 */
	public void exportDBConfig(String exportObjects) {
		_log.info("----需要导出的数据库配置的对象为:"+exportObjects);
		//需要导出的对象数组
		String[] objects = buildExportObjects(exportObjects);
		//遍历所有需要迁移的对象
		for (String object : objects) {
			exportObject(object);
		}
	}
	
	/**
	 * 根据对象名称导出数据库配置的对象
	 * 需要导出的表结构包括:
	 * 	maxobjectcfg
	 * 	maxtablecfg
	 * 	maxattributecfg
	 * 	maxsysindexes
	 * 	maxsyskeys
	 * 	maxrelationship
	 * 	maxsequence
	 * 	autokey
	 * 	
	 * @param objectName
	 */
	private void exportObject(String objectName){
		
	}
	
	/**
	 * 以 “,” 为分隔符构建需要导出的对象数组
	 * @param exportObjects
	 * @return
	 */
	private String[] buildExportObjects(String exportObjects){
		return exportObjects.split(",");
	}
	 
	
	
	
	
	
}
