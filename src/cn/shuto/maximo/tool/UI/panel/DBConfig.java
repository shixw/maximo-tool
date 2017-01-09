package cn.shuto.maximo.tool.UI.panel;

import java.awt.Button;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.shuto.maximo.tool.UI.MaximoToolUI;

public class DBConfig extends MaximoToolModule implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		DBConfig demo = new DBConfig(null);
		demo.mainImpl();
	}

	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c) {
		Button button = new Button(name);
		gridbag.setConstraints(button, c);
		add(button);
	}

	public DBConfig(MaximoToolUI maximoToolUI) {
		super(maximoToolUI, "DBConfig", "toolbar/JTable.gif");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		makebutton("Button1", gridbag, c);
		makebutton("Button2", gridbag, c);
		makebutton("Button3", gridbag, c);

		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		makebutton("Button4", gridbag, c);

		c.weightx = 0.0; // reset to the default
		makebutton("Button5", gridbag, c); // another row

		c.gridwidth = GridBagConstraints.RELATIVE; // next-to-last in row
		makebutton("Button6", gridbag, c);

		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		makebutton("Button7", gridbag, c);

		c.gridwidth = 1; // reset to the default
		c.gridheight = 2;
		c.weighty = 1.0;
		makebutton("Button8", gridbag, c);

		c.weighty = 0.0; // reset to the default
		c.gridwidth = GridBagConstraints.REMAINDER; // end row
		c.gridheight = 1; // reset to the default
		makebutton("Button9", gridbag, c);
		makebutton("Button10", gridbag, c);

		setSize(300, 100);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
