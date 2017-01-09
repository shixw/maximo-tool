package cn.shuto.maximo.tool.UI.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.MissingResourceException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.widget.N9ComponentFactory;

import cn.shuto.maximo.tool.UI.MaximoToolUI;
import cn.shuto.maximo.tool.system.SystemEnvironmental;

/**
 * A generic SwingSet2 demo module.
 *
 * @version 1.23 11/17/05
 * @author Jeff Dinkins
 */
public class MaximoToolModule extends JApplet {

	private static final long serialVersionUID = 1L;

	// 系统环境变量
	protected SystemEnvironmental systemEnvironmental = SystemEnvironmental.getInstance();

	// The preferred size of the demo
	/** The PREFERRE d_ width. */
	private int PREFERRED_WIDTH = 680;

	/** The PREFERRE d_ height. */
	private int PREFERRED_HEIGHT = 600;

	/** The lowered border. */
	Border loweredBorder = // new CompoundBorder(new
							// SoftBevelBorder(SoftBevelBorder.LOWERED),
			new EmptyBorder(15, 10, 5, 10);// );

	// Premade convenience dimensions, for use wherever you need 'em.
	/** The HGA p2. */
	public static Dimension HGAP2 = new Dimension(2, 1);

	/** The VGA p2. */
	public static Dimension VGAP2 = new Dimension(1, 2);

	/** The HGA p5. */
	public static Dimension HGAP5 = new Dimension(5, 1);

	/** The VGA p5. */
	public static Dimension VGAP5 = new Dimension(1, 5);

	/** The HGA p10. */
	public static Dimension HGAP10 = new Dimension(10, 1);

	/** The VGA p10. */
	public static Dimension VGAP10 = new Dimension(1, 10);

	/** The HGA p15. */
	public static Dimension HGAP15 = new Dimension(15, 1);

	/** The VGA p15. */
	public static Dimension VGAP15 = new Dimension(1, 15);

	/** The HGA p20. */
	public static Dimension HGAP20 = new Dimension(20, 1);

	/** The VGA p20. */
	public static Dimension VGAP20 = new Dimension(1, 20);

	/** The HGA p25. */
	public static Dimension HGAP25 = new Dimension(25, 1);

	/** The VGA p25. */
	public static Dimension VGAP25 = new Dimension(1, 25);

	/** The HGA p30. */
	public static Dimension HGAP30 = new Dimension(30, 1);

	/** The VGA p30. */
	public static Dimension VGAP30 = new Dimension(1, 30);

	/** The MaximoToolUI. */
	private MaximoToolUI maximoToolUI = null;

	/** The panel. */
	private JPanel panel = null;

	/** The resource name. */
	private String resourceName = null;

	/** The icon path. */
	private String iconPath = null;



	/**
	 * Instantiates a new demo module.
	 *
	 */
	public MaximoToolModule(MaximoToolUI maximoToolUI) {
		this(maximoToolUI, null, null);
	}

	/**
	 * Instantiates a new demo module.
	 *
	 * @param swingset
	 *            the swingset
	 * @param resourceName
	 *            the resource name
	 * @param iconPath
	 *            the icon path
	 */
	public MaximoToolModule(MaximoToolUI maximoToolUI, String resourceName, String iconPath) {
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());

		this.resourceName = resourceName;
		this.iconPath = iconPath;
		this.maximoToolUI = maximoToolUI;

	}

	/**
	 * Gets the resource name.
	 *
	 * @return the resource name
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Gets the demo panel.
	 *
	 * @return the demo panel
	 */
	public JPanel getMaximoToolModulePanel() {
		return panel;
	}

	public MaximoToolUI getMaximoToolUI() {
		return maximoToolUI;
	}

	/**
	 * Creates the image icon.
	 *
	 * @param filename
	 *            the filename
	 * @param description
	 *            the description
	 * @return the image icon
	 */
	public ImageIcon createImageIcon(String filename, String description) {
		if (getMaximoToolUI() != null) {
			return getMaximoToolUI().createImageIcon(filename, description);
		} else {
			String path = "/resources/images/" + filename;
			return new ImageIcon(getClass().getResource(path), description);
		}
	}

	   /**
     * Gets the string.
     *
     * @param key the key
     * @return the string
     */
    public String getString(String key) {
	String value = "no....";
	try {
	    value = systemEnvironmental.getResource2String(key);
	} catch (MissingResourceException e) {
	    System.out.println("java.util.MissingResourceException: Couldn't find value for: " + key);
	}
	return value;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#getName()
	 */
	public String getName() {
		return systemEnvironmental.getResource2String(getResourceName() + ".name");
	};

	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public Icon getIcon() {
		return createImageIcon(iconPath, getResourceName() + ".name");
	};

	/**
	 * Gets the tool tip.
	 *
	 * @return the tool tip
	 */
	public String getToolTip() {
		return systemEnvironmental.getResource2String(getResourceName() + ".tooltip");
	};

	/**
	 * Main impl.
	 */
	public void mainImpl() {
		JFrame frame = new JFrame(getName());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(getMaximoToolModulePanel(), BorderLayout.CENTER);
		getMaximoToolModulePanel().setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Creates the horizontal panel.
	 *
	 * @param threeD
	 *            the three d
	 * @return the j panel
	 */
	public JPanel createHorizontalPanel(boolean threeD) {
		JPanel p = N9ComponentFactory.createPanel_style1(null).setDrawBg(threeD);// modified
																					// by
																					// jb2011
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setAlignmentY(TOP_ALIGNMENT);
		p.setAlignmentX(LEFT_ALIGNMENT);
		if (threeD) {
			p.setBorder(loweredBorder);
		}
		// 因背景是白色N9图，这里设置它不填充默认背景好看一点，要不然灰色背景出来就不好看了
		p.setOpaque(false);// add by jb2011 2012-08-24
		return p;
	}

	/**
	 * Creates the vertical panel.
	 *
	 * @param threeD
	 *            the three d
	 * @return the j panel
	 */
	public JPanel createVerticalPanel(boolean threeD) {
		JPanel p = N9ComponentFactory.createPanel_style1(null).setDrawBg(threeD);// modified
																					// by
																					// jb2011
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setAlignmentY(TOP_ALIGNMENT);
		p.setAlignmentX(LEFT_ALIGNMENT);
		if (threeD) {
			p.setBorder(loweredBorder);
		}
		return p;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		MaximoToolModule demo = new MaximoToolModule(null);
		demo.mainImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#init()
	 */
	public void init() {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getMaximoToolModulePanel(), BorderLayout.CENTER);
	}

	/**
	 * Update drag enabled.
	 *
	 * @param dragEnabled
	 *            the drag enabled
	 */
	void updateDragEnabled(boolean dragEnabled) {
	}

}
