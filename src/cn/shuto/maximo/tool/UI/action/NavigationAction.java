package cn.shuto.maximo.tool.UI.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cn.shuto.maximo.tool.UI.MaximoToolUI;
import cn.shuto.maximo.tool.UI.panel.MaximoToolModule;

public class NavigationAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	MaximoToolUI maximoToolUI;
	
	MaximoToolModule module;

	/**
	 * Instantiates a new switch to demo action.
	 *
	 */
	public NavigationAction(MaximoToolUI maximoToolUI, MaximoToolModule module) {
		super(module.getName(),module.getIcon());
		this.maximoToolUI = maximoToolUI;
		this.module = module;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
	 * ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		maximoToolUI.setMaximoToolModule(module);
	}
}