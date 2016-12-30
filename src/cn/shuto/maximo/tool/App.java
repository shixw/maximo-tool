package cn.shuto.maximo.tool;

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
		//注册系统退出事件，退出系统时关闭数据库连接
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				_log.info("------------系统退出,释放资源!--------------");
				DBUtil.getInstance().closeConnection();
			}
		});
		String option = args[0];
		if(option!=null&&!"".equals(option)){
			if("-exportdbconfig".equals(option)){
				DBConfigMigration dbcm = new DBConfigMigration(args[1], args[2]);
				dbcm.exportDBConfig(args[3]);
			}
			if("-importdbconfig".equals(option)){
				DBConfigMigration dbcm = new DBConfigMigration(args[1], args[2]);
				dbcm.importDBConfig();
			}
		}
		
	}
}
