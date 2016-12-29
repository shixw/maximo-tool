package cn.shuto.maximo.tool;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import cn.shuto.maximo.tool.migration.dbconfig.DBConfigMigration;
import cn.shuto.maximo.tool.migration.dbconfig.bean.MaxAttributeCfg;
import cn.shuto.maximo.tool.migration.dbconfig.bean.MaxObjectCfg;
import cn.shuto.maximo.tool.util.DBUtil;
import cn.shuto.maximo.tool.util.SerializeUtil;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger _log = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
//		List<MaxObjectCfg> list = SerializeUtil.readObjectForList(new File("F:\\Workspace\\Eclipse\\workspace-maximo\\maximo-tool\\package\\dbconfig\\BDConfig.mtep"));
//		List<MaxAttributeCfg> maxAttributeCfgs = list.get(0).getMaxAttributeCfgs();
//		for (MaxAttributeCfg maxAttributeCfg : maxAttributeCfgs) {
//			System.out.println(maxAttributeCfg.toInsertSql());
//		}
//		
		//注册系统退出事件，退出系统时关闭数据库连接
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				_log.info("------------系统退出,释放资源!--------------");
				DBUtil.getInstance().closeConnection();
			}
		});

		DBConfigMigration dbcm = new DBConfigMigration(args[0], args[1]);
		dbcm.exportDBConfig(args[2]);
	}
}
