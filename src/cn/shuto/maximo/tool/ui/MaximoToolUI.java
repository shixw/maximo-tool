package cn.shuto.maximo.tool.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class MaximoToolUI extends JPanel {
	private static final long serialVersionUID = 1L;
	// Resource bundle for internationalized and accessible text
	/** The bundle. */
	private ResourceBundle bundle = null;
	// The preferred size of the demo
	/** The Constant PREFERRED_WIDTH. */
	private static final int PREFERRED_WIDTH = 720;

	/** The Constant PREFERRED_HEIGHT. */
	private static final int PREFERRED_HEIGHT = 640;
	// Used only if swingset is an application
	/** The frame. */
	private JFrame frame = null;
	// number of swingsets - for multiscreen
	// keep track of the number of SwingSets created - we only want to exit
	// the program when the last one has been closed.
	/** The num s ss. */
	private static int numSSs = 0;
	/** The swing sets. */
	private static Vector<MaximoToolUI> swingSets = new Vector<MaximoToolUI>();

	public MaximoToolUI(GraphicsConfiguration gc) {
		frame = createFrame(gc);
		// set the layout
		setLayout(new BorderLayout());

		// set the preferred size of the demo
		setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showMaximoToolUI();
			}
		});

	}

	public void showMaximoToolUI() {
		if (getFrame() != null) {
			// put swingset in a frame and show it
			JFrame f = getFrame();
			f.setTitle(getString("Frame.title"));
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

	/**
	 * This method returns a string from the demo's resource bundle.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	public String getString(String key) {
		String value = null;
		try {
			value = getResourceBundle().getString(key);
		} catch (MissingResourceException e) {
			System.out.println("java.util.MissingResourceException: Couldn't find value for: " + key);
		}
		if (value == null) {
			value = "Could not find resource: " + key + "  ";
		}
		return value;
	}

	public ResourceBundle getResourceBundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle("resources.maximotool");
		}
		return bundle;
	}

	/**
	 * Create a frame for SwingSet2 to reside in if brought up as an
	 * application.
	 *
	 * @param gc
	 *            the gc
	 * @return the j frame
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

	public static void main(String[] args) throws Exception {

		BeautyEyeLNFHelper.debug = true;
		BeautyEyeLNFHelper.launchBeautyEyeLNF();

		MaximoToolUI maximotoolui = new MaximoToolUI(
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
	}

	/**
	 * Returns the frame instance.
	 *
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}
}
