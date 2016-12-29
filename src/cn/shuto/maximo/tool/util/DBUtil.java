package cn.shuto.maximo.tool.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil {

	private Connection conn = null;
	
	public Connection getMaximoConnection(String maximoPath) {
		if(conn!=null){
			return conn;
		}
		try {
			Properties config = PropertyReader.getProperties(maximoPath + "\\properties\\maximo.properties");
			Class.forName(config.getProperty("mxe.db.driver"));
			conn = DriverManager.getConnection(config.getProperty("mxe.db.url"),
					config.getProperty("mxe.db.user"), config.getProperty("mxe.db.password"));
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static class SingletonHolder {
		private static DBUtil instance = new DBUtil();
	}

	private DBUtil() {
	}

	public static DBUtil getInstance() {
		return SingletonHolder.instance;
	}
}
