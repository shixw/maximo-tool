package cn.shuto.maximo.tool.UI.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import cn.shuto.maximo.tool.UI.MaximoToolUI;
import cn.shuto.maximo.tool.UI.action.ExportActionListener;

public class SystemConfig extends MaximoToolModule implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SystemConfig demo = new SystemConfig(null);
		demo.mainImpl();
	}

	public SystemConfig(MaximoToolUI maximoToolUI) {
		super(maximoToolUI, "SystemConfig", "toolbar/JTable.gif");

		// 纵向排列的面板
		JPanel vrticalPanel = super.createVerticalPanel(false);

		// 第一个面板 导出配置
		JPanel exportPanel = new JPanel();
		exportPanel.setBorder(new TitledBorder(getString("SystemConfig.export.config")));
		// exportPanel.setLayout(new BoxLayout(exportPanel, BoxLayout.Y_AXIS));
		exportPanel.setLayout(new BorderLayout());
		// ---------------------------------------------------------------------------------------------------------------------
		JPanel exportCenterPanel = new JPanel();
		exportCenterPanel.setLayout(new BoxLayout(exportCenterPanel, BoxLayout.Y_AXIS));
		// ---------------------------------------------------------------------------------------------------------------------
		// 项目发布路径
		exportCenterPanel.add(createChooseFilePanel("-maximopath", 1, "SystemConfig.export.project.releasepath"));
		// ---------------------------------------------------------------------------------------------------------------------
		// 信息导出路径
		exportCenterPanel.add(createChooseFilePanel("-maximosourcepath", 1, "SystemConfig.export.source.path"));
		// 信息导出路径
		exportCenterPanel.add(createChooseFilePanel("-packagepath", 1, "SystemConfig.export.path"));
		// ---------------------------------------------------------------------------------------------------------------------
		// 需要导出的对象
		exportCenterPanel.add(createJTextFieldPanel("-exportobjects", "SystemConfig.export.exportobjects"));
		// ---------------------------------------------------------------------------------------------------------------------
		// 需要导出的域的信息
		exportCenterPanel.add(createJTextFieldPanel("-exportdomainids", "SystemConfig.export.exportdomainids"));
		// ---------------------------------------------------------------------------------------------------------------------
		// 需要导出的系统模块的信息
		exportCenterPanel.add(createJTextFieldPanel("-exportmodules", "SystemConfig.export.exportmodules"));
		// ---------------------------------------------------------------------------------------------------------------------
		// 需要导出的应用程序的信息
		exportCenterPanel.add(createJTextFieldPanel("-exportapps", "SystemConfig.export.exportapps"));
		// ---------------------------------------------------------------------------------------------------------------------
		// 需要导出的消息的信息 使用字段 msgid 用 , 隔开
		exportCenterPanel.add(createJTextFieldPanel("-exportmaxmessages", "SystemConfig.export.exportmaxmessages"));
		// ---------------------------------------------------------------------------------------------------------------------
		// 需要导出的系统XML中的lookups.xml的ID，用 , 隔开
		exportCenterPanel.add(createJTextFieldPanel("-exportlookupsids", "SystemConfig.export.exportlookupsids"));
		// ---------------------------------------------------------------------------------------------------------------------

		// ---------------------------------------------------------------------------------------------------------------------
		exportPanel.add(exportCenterPanel, BorderLayout.CENTER);
		// ---------------------------------------------------------------------------------------------------------------------
		// 底部按钮
		exportPanel.add(createSouthBtnPanel("SystemConfig.export.button", new ExportActionListener(maximoToolUI)),
				BorderLayout.SOUTH);
		// ---------------------------------------------------------------------------------------------------------------------
		vrticalPanel.add(exportPanel);
		// ---------------------------------------------------------------------------------------------------------------------
		// 第二个面板 导入配置
		JPanel importPanel = new JPanel();
		importPanel.setBorder(new TitledBorder(getString("SystemConfig.import.config")));
		importPanel.setLayout(new BorderLayout());
		// ---------------------------------------------------------------------------------------------------------------------
		JPanel importCenterPanel = new JPanel();
		importCenterPanel.setLayout(new BoxLayout(importCenterPanel, BoxLayout.Y_AXIS));
		// ---------------------------------------------------------------------------------------------------------------------
		// 需导入的项目发布路径
		importCenterPanel.add(createChooseFilePanel("-maximopath", 1, "SystemConfig.import.project.releasepath"));
		// ---------------------------------------------------------------------------------------------------------------------
		// 信息导出路径
		importCenterPanel.add(createChooseFilePanel("-importpath", 1, "SystemConfig.import.path"));
		// ---------------------------------------------------------------------------------------------------------------------
		importPanel.add(importCenterPanel, BorderLayout.CENTER);
		// ---------------------------------------------------------------------------------------------------------------------
		// 底部按钮
		importPanel.add(createSouthBtnPanel("SystemConfig.import.button", new ExportActionListener(maximoToolUI)),
				BorderLayout.SOUTH);
		// ---------------------------------------------------------------------------------------------------------------------
		vrticalPanel.add(importPanel);
		// ---------------------------------------------------------------------------------------------------------------------
		// 添加到主面板
		getMaximoToolModulePanel().add(vrticalPanel);

	}

	public JPanel createJTextAreaPanel(String labelKey) {
		JPanel jTextAreaPanel = new JPanel();
		jTextAreaPanel.setLayout(new BoxLayout(jTextAreaPanel, BoxLayout.Y_AXIS));
		JTextArea jTextArea = new JTextArea(2, 50);
		JLabel jlLabel = new JLabel(getString(labelKey));
		jlLabel.setLabelFor(jTextArea);
		jTextAreaPanel.add(jlLabel);
		jTextAreaPanel.add(jTextArea);
		return jTextAreaPanel;
	}

	public JPanel createJTextFieldPanel(final String environmentalKey, String labelKey) {
		JPanel jTextFieldPanel = new JPanel();
		jTextFieldPanel.setLayout(new BoxLayout(jTextFieldPanel, BoxLayout.LINE_AXIS));
		final JTextField jTextField = new JTextField(50);
		jTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				systemEnvironmental.putParam(environmentalKey, jTextField.getText());
			}
		});
		JLabel jlLabel = new JLabel(getString(labelKey));
		jlLabel.setLabelFor(jTextField);
		jTextFieldPanel.add(jlLabel);
		jTextFieldPanel.add(jTextField);
		return jTextFieldPanel;
	}

	public JPanel createSouthBtnPanel(String btnTextKey, ActionListener listener) {
		JPanel southButtonPanel = new JPanel();
		southButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton btn = new JButton(getString(btnTextKey));
		btn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		btn.setForeground(Color.white);

		btn.addActionListener(listener);
		southButtonPanel.add(btn);

		return southButtonPanel;
	}

	public JPanel createChooseFilePanel(final String environmentalKey, int selectionMode, String labelNameKey) {
		JPanel chooseFilePanel = new JPanel();
		chooseFilePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		final JTextField chooseFilePathTextField = new JTextField(50);
		chooseFilePathTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				systemEnvironmental.putParam(environmentalKey, chooseFilePathTextField.getText());
			}
		});
		JLabel chooseFilePathLabel = new JLabel(getString(labelNameKey));
		chooseFilePathLabel.setLabelFor(chooseFilePathTextField);
		chooseFilePanel.add(chooseFilePathLabel);
		chooseFilePanel.add(chooseFilePathTextField);
		chooseFilePanel.add(createPlainFileChooserButton(selectionMode, chooseFilePathTextField));

		return chooseFilePanel;
	}

	public JButton createPlainFileChooserButton(final int selectionMode, final JTextField textField) {
		Action a = new AbstractAction("...") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = createFileChooser();

				fc.setFileSelectionMode(selectionMode);
				// 显示文件选择窗口
				int result = fc.showOpenDialog(getMaximoToolModulePanel());

				if (result == JFileChooser.APPROVE_OPTION) {
					textField.setText(fc.getSelectedFile().getPath());
				}
			}
		};
		return createButton(a);
	}

	public JFileChooser createFileChooser() {
		// create a filechooser
		JFileChooser fc = new JFileChooser();
		fc.setDragEnabled(true);
		return fc;
	}

	public JButton createButton(Action a) {
		JButton b = new JButton(a) {
			private static final long serialVersionUID = 1L;

			public Dimension getMaximumSize() {
				int width = Short.MAX_VALUE;
				int height = super.getMaximumSize().height;
				return new Dimension(width, height);
			}
		};
		return b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
