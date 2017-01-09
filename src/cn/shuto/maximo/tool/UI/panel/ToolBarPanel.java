package cn.shuto.maximo.tool.UI.panel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JPanel;

public class ToolBarPanel extends JPanel implements ContainerListener {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#contains(int, int)
	 */
	public boolean contains(int x, int y) {
		Component c = getParent();
		if (c != null) {
			Rectangle r = c.getBounds();
			return (x >= 0) && (x < r.width) && (y >= 0) && (y < r.height);
		} else {
			return super.contains(x, y);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ContainerListener#componentAdded(java.awt.event.
	 * ContainerEvent)
	 */
	public void componentAdded(ContainerEvent e) {
		Container c = e.getContainer().getParent();
		if (c != null) {
			c.getParent().validate();
			c.getParent().repaint();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ContainerListener#componentRemoved(java.awt.event.
	 * ContainerEvent)
	 */
	public void componentRemoved(ContainerEvent e) {
		Container c = e.getContainer().getParent();
		if (c != null) {
			c.getParent().validate();
			c.getParent().repaint();
		}
	}
}