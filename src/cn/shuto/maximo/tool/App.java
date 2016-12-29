package cn.shuto.maximo.tool;

import java.sql.Connection;
import java.util.logging.Logger;

import cn.shuto.maximo.tool.migration.dbconfig.DBConfigMigration;
import cn.shuto.maximo.tool.util.DBUtil;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger _log = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				_log.info("------------系统退出,释放资源!--------------");
				DBUtil.getInstance().closeConnection();
			}
		});
		
		DBConfigMigration dbcm = new DBConfigMigration(args[0],args[1]);
		dbcm.exportDBConfig(args[2]);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
