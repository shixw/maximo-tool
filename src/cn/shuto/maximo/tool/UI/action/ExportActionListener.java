package cn.shuto.maximo.tool.UI.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.shuto.maximo.tool.UI.MaximoToolUI;
import cn.shuto.maximo.tool.migration.dbconfig.DBConfigMigration;
import cn.shuto.maximo.tool.system.SystemEnvironmental;

public class ExportActionListener implements ActionListener {

	// 系统环境变量
	protected SystemEnvironmental systemEnvironmental = SystemEnvironmental.getInstance();
	MaximoToolUI maximoToolUI;

	public ExportActionListener(MaximoToolUI maximoToolUI) {
		this.maximoToolUI = maximoToolUI;
	}

	public void actionPerformed(ActionEvent e) {
		DBConfigMigration dbcm = new DBConfigMigration();
		dbcm.exportDBConfig(systemEnvironmental.getStringParam("-exportobjects"));
	}
}
