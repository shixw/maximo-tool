package cn.shuto.maximo.tool.UI.panel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cn.shuto.maximo.tool.UI.MaximoToolUI;

public class AboutPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** The aboutimage. */
	ImageIcon aboutimage = null;

	/** The swingset. */
	MaximoToolUI maximoToolUI = null;

	/**
	 * Instantiates a new about panel.
	 *
	 * @param swingset
	 *            the swingset
	 */
	public AboutPanel(MaximoToolUI maximoToolUI) {
		this.maximoToolUI = maximoToolUI;
		aboutimage = maximoToolUI.createImageIcon("About.jpg", "AboutBox.accessible_description");
		setOpaque(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		aboutimage.paintIcon(this, g, 0, 0);
		super.paint(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	public Dimension getPreferredSize() {
		return new Dimension(aboutimage.getIconWidth(), aboutimage.getIconHeight());
	}
}
