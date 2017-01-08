package cn.shuto.maximo.tool.UI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SingleSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.widget.N9ComponentFactory;

import cn.shuto.maximo.tool.UI.action.AboutAction;
import cn.shuto.maximo.tool.UI.action.ExitAction;
import cn.shuto.maximo.tool.system.SystemEnvironmental;

public class MaximoToolUI extends JPanel {
	private static final long serialVersionUID = 1L;

	// 系统环境变量
	private SystemEnvironmental systemEnvironmental = SystemEnvironmental.getInstance();
	/** frame 窗体,此处为工具界面的主窗口. */
	private JFrame frame = null;
	/** 窗口宽. */
	private static final int PREFERRED_WIDTH = 720;
	/** 窗口高. */
	private static final int PREFERRED_HEIGHT = 640;
	/** 工具栏菜单. */
	private ToggleButtonToolBar toolbar = null;
	// /** 选项卡式面板. */
	// private JTabbedPane tabbedPane = null;
	/** 菜单栏. */
	private JMenuBar menuBar = null;
	/** 主面板. */
	JPanel mainPanel = null;

	/** The demos. */
	String[] topNavigation = { "系统配置信息", "数据库配置", "应用程序" };

	/**
	 * 构造器函数,
	 * 
	 * @param gc
	 *            图形目标（如打印机或监视器）的特征
	 */
	public MaximoToolUI(GraphicsConfiguration gc) {
		// 创建界面主窗体
		frame = createFrame(gc);
		// 设置布局
		setLayout(new BorderLayout());

		// 设置首次大小
		setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		// 初始化Maximo工具界面
		initializeMaximoToolUI();

		// 在独立线程中打开界面
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showMaximoToolUI();
			}
		});

		loadTopNavigation();
	}

	/**
	 * Load demos.
	 */
	void loadTopNavigation() {
		for (int i = 0; i < topNavigation.length; i++) {
			loadTopNavigation(topNavigation[i]);
		}
	}

	/**
	 * Loads a demo from a classname.
	 *
	 * @param classname
	 *            the classname
	 */
	void loadTopNavigation(final String navigationName) {
		SwingUtilities.invokeLater(new SwingSetRunnable(this, null) {
			public void run() {
				SwitchToDemoAction action = new SwitchToDemoAction(maximoToolUI,navigationName);
				JToggleButton tb = maximoToolUI.getToolBar().addToggleButton(action);
				// swingset.getToolBar().add(new
				// JSeparator(JSeparator.VERTICAL));
				maximoToolUI.getToolBarGroup().add(tb);
				if (maximoToolUI.getToolBarGroup().getSelection() == null) {
					tb.setSelected(true);
				}
				// tb.setText(null);
				tb.setIcon(null);
				tb.setToolTipText(navigationName);
			}
		});
	}

	/**
	 * 初始化Maximo工具界面
	 */
	public void initializeMaximoToolUI() {
		// 创建顶部菜单栏
		menuBar = createMenus();
		// 将菜单栏添加到窗体中
		frame.setJMenuBar(menuBar);

		// 顶部导航部分
		JPanel top = new JPanel();
		// 设置顶部导航布局
		top.setLayout(new BorderLayout());
		// 设置边框 参数 表示 上 右 下 左 外边距
		this.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
		// 将导航添加到界面顶端 (即 北 )
		add(top, BorderLayout.NORTH);

		ToolBarPanel toolbarPanel = new ToolBarPanel();
		toolbarPanel.setLayout(new BorderLayout());
		// 创建 按钮形 工具栏菜单
		toolbar = new ToggleButtonToolBar();
		// 将工具栏菜单添加到工具栏
		toolbarPanel.add(toolbar, BorderLayout.CENTER);
		// 添加到顶部导航栏
		top.add(toolbarPanel, BorderLayout.SOUTH);
		toolbarPanel.addContainerListener(toolbarPanel);

		// 主面板
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new DottedLinePanelBorder());
		add(mainPanel, BorderLayout.CENTER);

		// 底部 提醒 部分
		JPanel hinePanel = new JPanel(new BorderLayout());// add by jb2011
		JLabel hintLabel = N9ComponentFactory
				.createLabel_style1(systemEnvironmental.getResource2String("FriendlyTips"));
		hinePanel.add(hintLabel, BorderLayout.WEST);
		// 设置边距
		hinePanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 0, 4));
		add(hinePanel, BorderLayout.SOUTH);
	}

	/**
	 * 创建需要的顶部菜单栏及菜单
	 * 
	 * @return
	 */
	public JMenuBar createMenus() {
		// ***** 创建菜单栏 ****
		JMenuBar menuBar = new JMenuBar();
		menuBar.getAccessibleContext()
				.setAccessibleName(systemEnvironmental.getResource2String("MenuBar.accessible_description"));

		// ***** 创建文件菜单
		JMenu fileMenu = (JMenu) menuBar.add(new JMenu(systemEnvironmental.getResource2String("FileMenu.file_label")));

		// TODO 以下代码用来测试项级Menu上设置图标
		// fileMenu.setIcon(
		// org.jb2011.lnf.beautyeye.ch9_menu
		// .__IconFactory__.getInstance().getRadioButtonMenuItemCheckIcon());

		// 设置 快捷键的符号
		fileMenu.setMnemonic(systemEnvironmental.getResource2Mnemonic("FileMenu.file_mnemonic"));
		fileMenu.getAccessibleContext()
				.setAccessibleDescription(systemEnvironmental.getResource2String("FileMenu.accessible_description"));

		// 常见打开菜单
		createMenuItem(fileMenu, "FileMenu.open_label", "FileMenu.open_mnemonic",
				"FileMenu.open_accessible_description", null);

		// 创建保存菜单
		createMenuItem(fileMenu, "FileMenu.save_label", "FileMenu.save_mnemonic",
				"FileMenu.save_accessible_description", null);

		// 添加 分割线
		fileMenu.addSeparator();

		// 退出菜单
		createMenuItem(fileMenu, "FileMenu.exit_label", "FileMenu.exit_mnemonic",
				"FileMenu.exit_accessible_description", new ExitAction(this));

		// ***** 创建文件菜单
		JMenu helpMenu = (JMenu) menuBar.add(new JMenu(systemEnvironmental.getResource2String("HelpMenu.help_label")));
		// 设置 快捷键的符号
		helpMenu.setMnemonic(systemEnvironmental.getResource2Mnemonic("HelpMenu.help_mnemonic"));
		// 创建 顶级菜单 对应的子菜单 关于
		createMenuItem(helpMenu, "HelpMenu.about_label", "HelpMenu.about_mnemonic",
				"HelpMenu.about_accessible_description", new AboutAction(this));

		return menuBar;
	}

	/**
	 * 创建顶级菜单的子菜单
	 *
	 * @param menu
	 *            the menu
	 * @param label
	 *            the label
	 * @param mnemonic
	 *            the mnemonic
	 * @param accessibleDescription
	 *            the accessible description
	 * @param action
	 *            the action
	 * @return the j menu item
	 */
	public JMenuItem createMenuItem(JMenu menu, String label, String mnemonic, String accessibleDescription,
			Action action) {
		JMenuItem mi = (JMenuItem) menu.add(new JMenuItem(systemEnvironmental.getResource2String(label)));

		// mi.setBorder(BorderFactory.createEmptyBorder());
		mi.setMnemonic(systemEnvironmental.getResource2Mnemonic(mnemonic));
		mi.getAccessibleContext()
				.setAccessibleDescription(systemEnvironmental.getResource2String(accessibleDescription));
		// 设置点击事件
		mi.addActionListener(action);
		if (action == null) {
			mi.setEnabled(false);
		}
		return mi;
	}

	/**
	 * 创建主窗体
	 * 
	 * @param gc
	 * @return the
	 */
	public static JFrame createFrame(GraphicsConfiguration gc) {
		JFrame frame = new JFrame(gc);
		if (numSSs == 0) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			WindowListener l = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					numSSs--;
					swingSets.remove(this);
				}
			};
			frame.addWindowListener(l);
		}

		// 由Jack Jiang于2012-09-22加上，防止因JToolBar的大小而影响JFrame的prefereSize使得没法再缩小
		frame.setMinimumSize(new Dimension(100, 100));
		return frame;
	}


	// number of swingsets - for multiscreen
	// keep track of the number of SwingSets created - we only want to exit
	// the program when the last one has been closed.
	/** The num s ss. */
	private static int numSSs = 0;


	// Status Bar
	/** The status field. */
	private JLabel statusField = null;

	/** The swing sets. */
	private static Vector<MaximoToolUI> swingSets = new Vector<MaximoToolUI>();

	/** The toolbar group. */
	private ButtonGroup toolbarGroup = new ButtonGroup();

	/** The demos. */
	String[] demos = { "ButtonDemo", "ColorChooserDemo", "ComboBoxDemo", "FileChooserDemo", "HtmlDemo", "ListDemo",
			"OptionPaneDemo", "ProgressBarDemo", "ScrollPaneDemo", "SliderDemo", "SplitPaneDemo", "TabbedPaneDemo",
			"TableDemo", "ToolTipDemo", "TreeDemo" };

	/**
	 * Set the status.
	 *
	 * @param s
	 *            the new status
	 */
	public void setStatus(String s) {
		// do the following on the gui thread
		SwingUtilities.invokeLater(new SwingSetRunnable(this, s) {
			public void run() {
				maximoToolUI.statusField.setText((String) obj);
			}
		});
	}

	/**
	 * 显示Maximo迁移工具界面
	 */
	public void showMaximoToolUI() {
		if (getFrame() != null) {
			// put swingset in a frame and show it
			JFrame f = getFrame();
			f.setTitle(systemEnvironmental.getResource2String("Frame.title"));
			f.getContentPane().add(this, BorderLayout.CENTER);
			// f.pack();
			f.setSize(1024, 750);

			Rectangle screenRect = f.getGraphicsConfiguration().getBounds();
			Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(f.getGraphicsConfiguration());

			// Make sure we don't place the demo off the screen.
			int centerWidth = screenRect.width < f.getSize().width ? screenRect.x
					: screenRect.x + screenRect.width / 2 - f.getSize().width / 2;
			int centerHeight = screenRect.height < f.getSize().height ? screenRect.y
					: screenRect.y + screenRect.height / 2 - f.getSize().height / 2;

			centerHeight = centerHeight < screenInsets.top ? screenInsets.top : centerHeight;

			f.setLocation(centerWidth, centerHeight);
			f.setVisible(true);
			numSSs++;
			swingSets.add(this);
		}
	}

	public static void main(String[] args) throws Exception {

		BeautyEyeLNFHelper.debug = true;
		BeautyEyeLNFHelper.launchBeautyEyeLNF();
		// 构建Maximo工具界面
		MaximoToolUI maximotoolui = new MaximoToolUI(
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
	}

	/**
	 * Returns the toolbar button group.
	 *
	 * @return the tool bar group
	 */
	public ButtonGroup getToolBarGroup() {
		return toolbarGroup;
	}

	/**
	 * Returns the toolbar.
	 *
	 * @return the tool bar
	 */
	public ToggleButtonToolBar getToolBar() {
		return toolbar;
	}

	/**
	 * Returns the frame instance.
	 *
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Returns the menubar.
	 *
	 * @return the menu bar
	 */
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * Creates an icon from an image contained in the "images" directory.
	 *
	 * @param filename
	 *            the filename
	 * @param description
	 *            the description
	 * @return the image icon
	 */
	public ImageIcon createImageIcon(String filename, String description) {
		String path = "/resources/images/" + filename;
		return new ImageIcon(getClass().getResource(path));
	}

	// *******************************************************
	// ********* ToolBar Panel / Docking Listener ***********
	// *******************************************************
	/**
	 * The Class ToolBarPanel.
	 */
	class ToolBarPanel extends JPanel implements ContainerListener {

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

	// *******************************************************
	// ************** ToggleButtonToolbar *****************
	// *******************************************************
	/** The zero insets. */
	static Insets zeroInsets = new Insets(3, 2, 3, 2);

	/**
	 * The Class ToggleButtonToolBar.
	 */
	protected class ToggleButtonToolBar extends JToolBar {

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
		JToggleButton addToggleButton(Action a) {
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

	/**
	 * Generic SwingSet2 runnable. This is intended to run on the AWT gui event
	 * thread so as not to muck things up by doing gui work off the gui thread.
	 * Accepts a SwingSet2 and an Object as arguments, which gives subtypes of
	 * this class the two "must haves" needed in most runnables for this demo.
	 */
	class SwingSetRunnable implements Runnable {

		/** The swingset. */
		protected MaximoToolUI maximoToolUI;

		/** The obj. */
		protected Object obj;

		/**
		 * Instantiates a new swing set runnable.
		 *
		 * @param swingset
		 *            the swingset
		 * @param obj
		 *            the obj
		 */
		public SwingSetRunnable(MaximoToolUI maximoToolUI, Object obj) {
			this.maximoToolUI = maximoToolUI;
			this.obj = obj;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
		}
	}

	/**
	 * The Class SwitchToDemoAction.
	 */
	public class SwitchToDemoAction extends AbstractAction {

		/** The swingset. */
		MaximoToolUI maximoToolUI;

		/**
		 * Instantiates a new switch to demo action.
		 *
		 * @param swingset
		 *            the swingset
		 * @param demo
		 *            the demo
		 */
		public SwitchToDemoAction(MaximoToolUI maximoToolUI,String N) {
			super(N, null);
			this.maximoToolUI = maximoToolUI;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
		}
	}

	// -------------------------------------------------------------
	// 由jb2011于2012-06-20实现
	// 用于DemoPanel的边框实现，视觉目标是：简洁。
	// 原EtchedBorder边框太土，但是没边框将导致整体效果稍显单调，所以做此边框
	/**
	 * The Class DemoPanelBorder.
	 */
	private class DottedLinePanelBorder extends AbstractBorder {

		private static final long serialVersionUID = 1L;

		public DottedLinePanelBorder() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.border.AbstractBorder#paintBorder(java.awt.Component,
		 * java.awt.Graphics, int, int, int, int)
		 */
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			// g.drawLine(x,y, widthheight); // draw top
			// g.drawLine(x,y, x,height-1); // draw left
			// g.drawLine(width-1,y, width-1,height-1); // draw right

			// ** 绘制border的底线
			// 虚线样式
			Stroke oldStroke = ((Graphics2D) g).getStroke();
			Stroke sroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 1, 2 }, 0);// 实线，空白
			((Graphics2D) g).setStroke(sroke);
			// 底边上（浅灰色）
			g.setColor(new Color(200, 200, 200));
			g.drawLine(x, height - 2, width - 1, height - 2); // draw bottom1
			// 底边下（白色）：绘制一条白色虚线的目的是与上面的灰线产生较强对比度从而形成立体效果
			// ，本L&F实现中因与Panel的底色对比度不够强烈而立体感不明显（颜色越深的底色最终效果越明显）
			g.setColor(Color.white);
			g.drawLine(x, height - 1, width - 1, height - 1);// draw bottom2

			((Graphics2D) g).setStroke(oldStroke);
		}

		// border只有底边，且高度为2像素
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.border.AbstractBorder#getBorderInsets(java.awt.Component)
		 */
		public Insets getBorderInsets(Component c) {
			return new Insets(0, 0, 2, 0);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.border.AbstractBorder#getBorderInsets(java.awt.Component,
		 * java.awt.Insets)
		 */
		public Insets getBorderInsets(Component c, Insets insets) {
			// insets.top = insets.left = insets.bottom = insets.right = 1;
			return getBorderInsets(c);// insets;
		}
	}
}
