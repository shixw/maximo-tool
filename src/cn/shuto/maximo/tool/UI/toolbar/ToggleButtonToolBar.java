package cn.shuto.maximo.tool.UI.toolbar;

import javax.swing.Action;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class ToggleButtonToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new toggle button tool bar.
	 */
	public ToggleButtonToolBar() {
		super();
		this.setFloatable(true);
		// this.putClientProperty("ToolBar.isPaintPlainBackground",
		// Boolean.TRUE);
	}

	/**
	 * Adds the toggle button.
	 *
	 * @param a
	 *            the a
	 * @return the j toggle button
	 */
	public JToggleButton addToggleButton(Action a) {
		JToggleButton tb = new JToggleButton((String) a.getValue(Action.NAME), null
		// (Icon)a.getValue(Action.SMALL_ICON)
		);
		// tb.setMargin(zeroInsets);
		// tb.setText(null);
		tb.setEnabled(a.isEnabled());
		tb.setToolTipText((String) a.getValue(Action.SHORT_DESCRIPTION));
		tb.setAction(a);
		add(tb);
		return tb;
	}
}