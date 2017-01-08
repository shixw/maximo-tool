package cn.shuto.maximo.tool.UI.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import cn.shuto.maximo.tool.UI.MaximoToolUI;
import cn.shuto.maximo.tool.UI.panel.AboutPanel;
import cn.shuto.maximo.tool.system.SystemEnvironmental;

public class AboutAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	// 系统运行环境变量
	private SystemEnvironmental systemEnvironmental = SystemEnvironmental.getInstance();
	/** 界面 . */
	MaximoToolUI maximoToolUI;
	// 关于菜单对应的弹出窗口
	private JDialog aboutBox = null;

	/**
	 * Instantiates a new about action.
	 *
	 * @param swingset
	 *            the swingset
	 */
	public AboutAction(MaximoToolUI maximoToolUI) {
		super("AboutAction");
		this.maximoToolUI = maximoToolUI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
	 * ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (aboutBox == null) {
			// JPanel panel = new JPanel(new BorderLayout());
			JPanel panel = new AboutPanel(maximoToolUI);
			panel.setLayout(new BorderLayout());

			aboutBox = new JDialog(maximoToolUI.getFrame(), systemEnvironmental.getResource2String("AboutBox.title"),
					false);
			aboutBox.setResizable(false);
			aboutBox.getContentPane().add(panel, BorderLayout.CENTER);

			// JButton button = new
			// JButton(getString("AboutBox.ok_button_text"));
			JPanel buttonpanel = new JPanel();
			buttonpanel.setBorder(new javax.swing.border.EmptyBorder(0, 0, 3, 0));
			buttonpanel.setOpaque(false);
			JButton button = (JButton) buttonpanel
					.add(new JButton(systemEnvironmental.getResource2String("AboutBox.ok_button_text")));
			panel.add(buttonpanel, BorderLayout.SOUTH);

			button.addActionListener(new OkAction(aboutBox));
		}
		aboutBox.pack();
		aboutBox.setLocationRelativeTo(maximoToolUI.getFrame());
		aboutBox.setVisible(true);
	}
}
