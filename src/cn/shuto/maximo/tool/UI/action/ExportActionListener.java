package cn.shuto.maximo.tool.UI.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cn.shuto.maximo.tool.AntOperation;
import cn.shuto.maximo.tool.UI.MaximoToolUI;
import cn.shuto.maximo.tool.system.SystemEnvironmental;

public class ExportActionListener implements ActionListener {

	// 系统环境变量
	protected SystemEnvironmental systemEnvironmental = SystemEnvironmental.getInstance();
	MaximoToolUI maximoToolUI;

	public ExportActionListener(MaximoToolUI maximoToolUI) {
		this.maximoToolUI = maximoToolUI;
	}

	public void actionPerformed(ActionEvent e) {
		AntOperation ao = new AntOperation(systemEnvironmental.getStringParam("-maximopath"));
		maximoToolUI.setStatus("正在导出数据库配置...");
		maximoToolUI.updateUI();
		ao.exportdbconfig(systemEnvironmental.getStringParam("-packagepath"),
				systemEnvironmental.getStringParam("-exportobjects"));
		maximoToolUI.setStatus("正在导出域配置...");
		ao.exportdomaindm(systemEnvironmental.getStringParam("-packagepath"),
				systemEnvironmental.getStringParam("-exportdomainids"));
		
		///弹出提醒 
		JOptionPane.showMessageDialog(maximoToolUI,
				systemEnvironmental.getResource2String("SystemConfig.export.success.message")
						+ systemEnvironmental.getStringParam("-packagepath"));
		maximoToolUI.setStatus(null);
	}
}
