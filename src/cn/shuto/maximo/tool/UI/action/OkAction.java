package cn.shuto.maximo.tool.UI.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;



class OkAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	/** 关于 . */
	JDialog aboutBox;

	/**
	 * 实例化一个新的 action .
	 *
	 * @param aboutBox 
	 */
	protected OkAction(JDialog aboutBox) {
		super("OkAction");
		this.aboutBox = aboutBox;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
	 * ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		aboutBox.setVisible(false);
	}
}