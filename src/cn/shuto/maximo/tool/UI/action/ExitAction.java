package cn.shuto.maximo.tool.UI.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cn.shuto.maximo.tool.UI.MaximoToolUI;

public class ExitAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	/** The swingset. */
	MaximoToolUI maximoToolUI;

	/**
	 * Instantiates a new exit action.
	 *
	 * @param swingset
	 *            the swingset
	 */
	public ExitAction(MaximoToolUI maximoToolUI) {
		super("ExitAction");
		this.maximoToolUI = maximoToolUI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
	 * ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
