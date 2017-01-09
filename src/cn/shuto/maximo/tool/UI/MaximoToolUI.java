package cn.shuto.maximo.tool.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.lang.reflect.Constructor;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.widget.N9ComponentFactory;

import cn.shuto.maximo.tool.UI.action.AboutAction;
import cn.shuto.maximo.tool.UI.action.ExitAction;
import cn.shuto.maximo.tool.UI.action.NavigationAction;
import cn.shuto.maximo.tool.UI.border.DottedLinePanelBorder;
import cn.shuto.maximo.tool.UI.panel.MaximoToolModule;
import cn.shuto.maximo.tool.UI.panel.SystemConfig;
import cn.shuto.maximo.tool.UI.panel.ToolBarPanel;
import cn.shuto.maximo.tool.UI.toolbar.ToggleButtonToolBar;
import cn.shuto.maximo.tool.system.SystemEnvironmental;

/**
 * Maximo迁移工具界面主入口
 * @author shixw
 *
 */
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

	/** 状态栏. */
	private JLabel statusField = null;

	private ButtonGroup toolbarGroup = new ButtonGroup();

	private static final String panelClassPackage = "cn.shuto.maximo.tool.UI.panel";
	/** The demos. */
	String[] topNavigation = { "DBConfig","Application" };

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
		//设置窗体左上角图标
		frame.setIconImage(createImageIcon(systemEnvironmental.getResource2String("Application.icon"), "").getImage());
		// 初始化Maximo工具界面
		initializeMaximoToolUI();

		preloadFirstMaximoToolPanel();

		// 在独立线程中打开界面
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showMaximoToolUI();
			}
		});
		// 加载导航栏菜单
		TopNavigationLoadThread topNavigationLoad = new TopNavigationLoadThread(this);
		topNavigationLoad.start();

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void loadTopNavigation(String navigationClassName) {
		setStatus(systemEnvironmental.getResource2String("Status.loading")
				+ systemEnvironmental.getResource2String(navigationClassName + ".name"));
		MaximoToolModule module = null;
		try {
			Class mtmClass = Class.forName(panelClassPackage+"."+navigationClassName);
			Constructor mtmConstructor = mtmClass.getConstructor(new Class[] { MaximoToolUI.class });

			module = (MaximoToolModule) mtmConstructor.newInstance(new Object[] { this });
			addMaximoToolModule(module);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred loading panel: " + navigationClassName);
		}
	}

	public void preloadFirstMaximoToolPanel() {
		MaximoToolModule module = addMaximoToolModule(new SystemConfig(this));
		setMaximoToolModule(module);
	}

	/**
	 * Sets the current demo.
	 *
	 * @param demo
	 *            the new demo
	 */
	public void setMaximoToolModule(MaximoToolModule module) {
		// currentDemo = demo;

		// Ensure panel's UI is current before making visible
		JComponent currentMTMPanel = module.getMaximoToolModulePanel();
		// ** 以下代码被注释掉了，它将会导致针对某一组件在运行时设置的UI将在下
		// ** 次重新loadDemo时被置为当前正在使用的L&F的默认组件UI:比如，原本在程序初始化时把一按钮设置成绿色的自定义
		// ** UI，但它所有在Demo被利于新load时因调用此方法而使该按钮重新恢复到当前L&F的默认UI（再也不是绿色了
		// ** ，当然该段代码不能算作bug，只是SwingSet2为了确保演示L&F时能即时刷新UI，但恰好跟jb2011需要的目标不同而已）！
		// SwingUtilities.updateComponentTreeUI(currentDemoPanel);
		mainPanel.removeAll();
		mainPanel.add(currentMTMPanel, BorderLayout.CENTER);
		//更新页面，否则无法显示
		mainPanel.updateUI();
		
		
	}

	public MaximoToolModule addMaximoToolModule(MaximoToolModule module) {
		// demosList.add(demo);
		// do the following on the gui thread
		SwingUtilities.invokeLater(new MaximoToolUIRunnable(this, module) {
			public void run() {
				NavigationAction action = new NavigationAction(maximoToolUI, (MaximoToolModule) obj);
				JToggleButton tb = maximoToolUI.getToolBar().addToggleButton(action);
				// swingset.getToolBar().add(new
				// JSeparator(JSeparator.VERTICAL));
				maximoToolUI.getToolBarGroup().add(tb);
				if (maximoToolUI.getToolBarGroup().getSelection() == null) {
					tb.setSelected(true);
				}
				// tb.setText(null);
				tb.setIcon(null);
				tb.setToolTipText(((MaximoToolModule) obj).getToolTip());

				if ((panelClassPackage+"."+topNavigation[topNavigation.length - 1]).equals(obj.getClass().getName())) {
					setStatus(systemEnvironmental.getResource2String("Status.popupMenuAccessible"));
				}
			}
		});
		return module;
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
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new DottedLinePanelBorder());
		add(mainPanel, BorderLayout.CENTER);

		// 底部 提醒 部分
		JPanel hinePanel = new JPanel(new BorderLayout());// add by jb2011
		JLabel hintLabel = N9ComponentFactory
				.createLabel_style1(systemEnvironmental.getResource2String("FriendlyTips"));
		hinePanel.add(hintLabel, BorderLayout.WEST);

		// 状态栏
		statusField = new JLabel("");
		statusField.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
		hinePanel.add(statusField, BorderLayout.CENTER);

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 由Jack Jiang于2012-09-22加上，防止因JToolBar的大小而影响JFrame的prefereSize使得没法再缩小
		frame.setMinimumSize(new Dimension(100, 100));
		return frame;
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
		}
	}

	public static void main(String[] args) throws Exception {

		BeautyEyeLNFHelper.debug = true;
		BeautyEyeLNFHelper.launchBeautyEyeLNF();
		// 构建Maximo工具界面
		// MaximoToolUI maximotoolui =
		new MaximoToolUI(
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
	}

	/**
	 * 设置状态栏状态
	 *
	 */
	public void setStatus(String s) {
		// do the following on the gui thread
		SwingUtilities.invokeLater(new MaximoToolUIRunnable(this, s) {
			public void run() {
				maximoToolUI.statusField.setText((String) obj);
			}
		});
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

	/**
	 * Generic SwingSet2 runnable. This is intended to run on the AWT gui event
	 * thread so as not to muck things up by doing gui work off the gui thread.
	 * Accepts a SwingSet2 and an Object as arguments, which gives subtypes of
	 * this class the two "must haves" needed in most runnables for this demo.
	 */
	class MaximoToolUIRunnable implements Runnable {

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
		public MaximoToolUIRunnable(MaximoToolUI maximoToolUI, Object obj) {
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
	 * 在单独线程中加载导航栏
	 */
	class TopNavigationLoadThread extends Thread {

		MaximoToolUI maximoToolUI;

		/**
		 * Instantiates a new demo load thread.
		 *
		 * @param swingset
		 *            the swingset
		 */
		public TopNavigationLoadThread(MaximoToolUI maximoToolUI) {
			this.maximoToolUI = maximoToolUI;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			maximoToolUI.loadTopNavigation();
		}
	}

}
