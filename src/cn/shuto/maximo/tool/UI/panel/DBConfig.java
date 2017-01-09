package cn.shuto.maximo.tool.UI.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import cn.shuto.maximo.tool.UI.MaximoToolUI;

public class DBConfig extends MaximoToolModule implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		DBConfig demo = new DBConfig(null);
		demo.mainImpl();
	}

	public DBConfig(MaximoToolUI maximoToolUI) {
		super(maximoToolUI, "DBConfig", "toolbar/JTable.gif");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
