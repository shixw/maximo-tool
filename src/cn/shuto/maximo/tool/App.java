package cn.shuto.maximo.tool;

import java.awt.GraphicsEnvironment;
import java.util.logging.Logger;

import cn.shuto.maximo.tool.UI.MaximoToolUI;
import cn.shuto.maximo.tool.migration.app.AppMigration;
import cn.shuto.maximo.tool.migration.dbconfig.DBConfigMigration;
import cn.shuto.maximo.tool.migration.domainadm.DomainadmMigration;
import cn.shuto.maximo.tool.migration.maxmessages.MaxMessagesMigration;
import cn.shuto.maximo.tool.migration.systemxml.SystemXMLMigration;
import cn.shuto.maximo.tool.system.SystemEnvironmental;
import cn.shuto.maximo.tool.util.DBUtil;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger _log = Logger.getLogger(App.class.getName());

	public static void main(String[] argv) {
		// 注册系统退出事件，退出系统时关闭数据库连接
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				_log.info("------------系统退出,释放资源!--------------");
				DBUtil.getInstance().closeConnection();
			}
		});

		SystemEnvironmental systemEnvironmental = SystemEnvironmental.getInstance();
		// 解析参数
		for (int i = 0; i < argv.length; i++) {
			String[] paramArray = argv[i].split("=");
			systemEnvironmental.putParam(paramArray[0], paramArray[1]);
		}
		// 获取操作
		String option = systemEnvironmental.getStringParam("-option");
		if (option != null && !"".equals(option)) {
			if ("loadui".equals(option)) {
				new MaximoToolUI(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
						.getDefaultConfiguration());
			}
			if ("exportdbconfig".equals(option)) {
				DBConfigMigration dbcm = new DBConfigMigration();
				dbcm.exportDBConfig(systemEnvironmental.getStringParam("-exportobjects"));
			}
			if ("importdbconfig".equals(option)) {
				DBConfigMigration dbcm = new DBConfigMigration();
				dbcm.importDBConfig();
			}
			if ("exportdomainadm".equals(option)) {
				DomainadmMigration dm = new DomainadmMigration();
				dm.exportDomainadm(systemEnvironmental.getStringParam("-exportdomainids"));
			}
			if ("importdomainadm".equals(option)) {
				DomainadmMigration dm = new DomainadmMigration();
				dm.importDomainadm();
			}
			if ("exportapp".equals(option)) {
				AppMigration am = new AppMigration();
				am.exportApp(systemEnvironmental.getStringParam("-exportmodules"),
						systemEnvironmental.getStringParam("-exportapps"));
			}
			if ("importapp".equals(option)) {
				AppMigration am = new AppMigration();
				am.importApp();
			}
			if ("exportmaxmessages".equals(option)) {
				MaxMessagesMigration mmm = new MaxMessagesMigration();
				mmm.exportMaxMessages(systemEnvironmental.getStringParam("-exportmaxmessages"));
			}
			if ("importmaxmessages".equals(option)) {
				MaxMessagesMigration mmm = new MaxMessagesMigration();
				mmm.importMaxMessages();
			}
			if ("exportlookups".equals(option)) {
				SystemXMLMigration sxmlm = new SystemXMLMigration();
				sxmlm.exportLookups(systemEnvironmental.getStringParam("-exportlookupsids"));
			}
			if ("importlookups".equals(option)) {
				SystemXMLMigration sxmlm = new SystemXMLMigration();
				sxmlm.importLookups();
			}
		}

	}
}
