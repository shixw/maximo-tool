package cn.shuto.maximo.tool.migration.app;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cn.shuto.maximo.tool.migration.app.bean.MaxApps;
import cn.shuto.maximo.tool.migration.app.bean.MaxMenu;
import cn.shuto.maximo.tool.migration.app.bean.MaxModules;
import cn.shuto.maximo.tool.migration.app.bean.MaxPresentation;
import cn.shuto.maximo.tool.migration.app.bean.Maxlabels;
import cn.shuto.maximo.tool.migration.app.bean.Sigoption;
import cn.shuto.maximo.tool.system.SystemEnvironmental;
import cn.shuto.maximo.tool.util.CommonUtil;
import cn.shuto.maximo.tool.util.DBUtil;
import cn.shuto.maximo.tool.util.SerializeUtil;

public class AppMigration {
	private static Logger _log = Logger.getLogger(AppMigration.class.getName());
	private String APPFILEPATH = "\\package\\app\\app.mtep";
	private String MODLESFILEPATH = "\\package\\app\\modules.mtep";

	private static final String SELECTMAXMODULES = "select MODULE, DESCRIPTION, MAXMODULESID from maxmodules where module = ?";
	private static final String SELECTMAXMODULESMENUS = "select MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID from maxmenu where menutype = 'MODULE' and  elementtype in ( 'HEADER','MODULE' ) and moduleapp = ?";
	private static final String SELECTMAXAPPS = "select APP, DESCRIPTION, APPTYPE, RESTRICTIONS, ORDERBY, ORIGINALAPP, CUSTAPPTYPE, MAINTBNAME, MAXAPPSID, ISMOBILE from maxapps where app = ?";
	private static final String SELECTMAXAPPSMENUS = "select MENUTYPE, MODULEAPP, POSITION, SUBPOSITION, ELEMENTTYPE, KEYVALUE, HEADERDESCRIPTION, URL, VISIBLE, IMAGE, ACCESSKEY, TABDISPLAY, MAXMENUID from maxmenu where  KEYVALUE=? or MODULEAPP=?";
	private static final String SELECTSIGOPTION = "select APP, OPTIONNAME, DESCRIPTION, ESIGENABLED, VISIBLE, ALSOGRANTS, ALSOREVOKES, PREREQUISITE, SIGOPTIONID, LANGCODE, HASLD from sigoption where app = ?";
	private static final String SELECTMAXLABELS = "select APP, ID, PROPERTY, VALUE, MAXLABELSID from maxlabels where app = ?";
	private static final String SELECTMAXPRESENTATION = "select app, maxpresentationid, presentation from maxpresentation where app = ?";

	private Connection conn = null;

	PreparedStatement maxmodulesST = null;
	PreparedStatement maxmodulesmenusST = null;
	PreparedStatement maxappsST = null;
	PreparedStatement maxappsmenusST = null;
	PreparedStatement sigoptionST = null;
	PreparedStatement maxlabelsST = null;
	PreparedStatement maxpresentationST = null;

	Statement importST = null;

	public AppMigration() {
		conn = DBUtil.getInstance()
				.getMaximoConnection(SystemEnvironmental.getInstance().getStringParam("-maximopath"));
		if (conn != null) {
			try {
				maxmodulesST = conn.prepareStatement(SELECTMAXMODULES);
				maxmodulesmenusST = conn.prepareStatement(SELECTMAXMODULESMENUS);
				maxappsST = conn.prepareStatement(SELECTMAXAPPS);
				maxappsmenusST = conn.prepareStatement(SELECTMAXAPPSMENUS);
				sigoptionST = conn.prepareStatement(SELECTSIGOPTION);
				maxlabelsST = conn.prepareStatement(SELECTMAXLABELS);
				maxpresentationST = conn.prepareStatement(SELECTMAXPRESENTATION);

				importST = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导入App信息
	 */
	public void importApp() {
		try {
			_log.info("-----导入模块信息----------");
			importModules();

			_log.info("-----导入应用程序信息----------");

			importApps();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	/**
	 * 导入应用程序
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 */
	private void importApps() throws SQLException, IOException {
		List<MaxApps> applist = SerializeUtil.readObjectForList(
				new File(SystemEnvironmental.getInstance().getStringParam("-importpath") + APPFILEPATH));
		if (applist != null && applist.size() > 0) {
			for (MaxApps app : applist) {
				clearMaxApps(app);
				_log.info("-----导入应用:----------" + app.getDescription());
				_log.info("-----导入MaxApps表-----------");
				importST.addBatch(app.toInsertSql());
				_log.info("-----导入MaxPresentation表-----------");
				importST.addBatch(app.getMaxPresentation().toInsertSql());
				_log.info("-----导入maxlabels表-----------");
				List<Maxlabels> maxlabels = app.getMaxlabels();
				if (maxlabels != null && maxlabels.size() > 0) {
					for (Maxlabels maxlabel : maxlabels) {
						importST.addBatch(maxlabel.toInsertSql());
					}
				}
				_log.info("-----导入Maxmenu表-----------");
				List<MaxMenu> maxMenus = app.getMaxMenus();
				if (maxMenus != null && maxMenus.size() > 0) {
					for (MaxMenu maxMenu : maxMenus) {
						importST.addBatch(maxMenu.toInsertSql());
					}
				}

				_log.info("-----导入Sigoption表-----------");
				List<Sigoption> Sigoptions = app.getSigoptions();
				if (Sigoptions != null && Sigoptions.size() > 0) {
					for (Sigoption sigoption : Sigoptions) {
						importST.addBatch(sigoption.toInsertSql());
					}
				}
				// 执行 提交
				importST.executeBatch();
				conn.commit();
				//导入 XML
				insertXMLToMaxPresentation(app);
			}
			
			
		}
	}

	/**
	 * 导入 应用程序 XML
	 * @param app
	 * @throws SQLException
	 * @throws IOException
	 */
	private void insertXMLToMaxPresentation(MaxApps app) throws SQLException, IOException {
		_log.info("--------------将应用程序-"+app.getDescription()+"----对应的XML导入到MaxPresentation表中------------------");
		// 写入操作
		String updatePresentationSql = "SELECT presentation from maxpresentation where app = ? for update";
		PreparedStatement pstmt = conn.prepareStatement(updatePresentationSql);
		pstmt.setString(1, app.getApp());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			// 造型为oracle.sql.CLOB
			Clob clob = rs.getClob(1);
			Writer writer = clob.setCharacterStream(1);
			writer.write(app.getMaxPresentation().getPresentation());
			writer.close();
		}
		rs.close();
		pstmt.close();

	}

	private static final String DELETEMAXAPPS = "delete maxapps where app = '%s'";
	private static final String DELETEMAXPRESENTATION = "delete from maxpresentation where APP= '%s'";
	private static final String DELETEMAXLABELS = "delete from maxlabels where app='%s'";
	private static final String DELETEMAXMENU = "delete from Maxmenu where KEYVALUE='%s' or MODULEAPP='%s'";
	private static final String DELETESIGOPTION = "delete from Sigoption where APP='%s'";

	/**
	 * 导入前首先清理 应用 对应的数据
	 */
	private void clearMaxApps(MaxApps app) {
		_log.info("-----清理应用程序数据:----------" + app.getDescription());
		try {
			importST.addBatch(String.format(DELETEMAXAPPS, app.getApp()));
			importST.addBatch(String.format(DELETEMAXPRESENTATION, app.getApp()));
			importST.addBatch(String.format(DELETEMAXLABELS, app.getApp()));
			importST.addBatch(String.format(DELETEMAXMENU, app.getApp(), app.getApp()));
			importST.addBatch(String.format(DELETESIGOPTION, app.getApp()));

			importST.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		_log.info("-----清理模块数据结束:----------" + app.getDescription());
	}

	/**
	 * 导入模块
	 * 
	 * @throws SQLException
	 */
	private void importModules() throws SQLException {
		List<MaxModules> modulelist = SerializeUtil.readObjectForList(
				new File(SystemEnvironmental.getInstance().getStringParam("-importpath") + MODLESFILEPATH));
		if (modulelist != null && modulelist.size() > 0) {
			for (MaxModules module : modulelist) {
				// 清理模块数据
				clearMaxModules(module);
				_log.info("-----导入模块:----------" + module.getDescription());
				importST.addBatch(module.toInsertSql());
				_log.info("-----导入模块对应的菜单:----------");
				List<MaxMenu> maxMenus = module.getMaxMenus();
				if (maxMenus != null && maxMenus.size() > 0) {
					for (MaxMenu maxMenu : maxMenus) {
						importST.addBatch(maxMenu.toInsertSql());
					}
				}
			}
			// 执行 提交
			importST.executeBatch();
			conn.commit();
		}
	}

	private static final String DELETEMAXMODULES = "delete maxmodules where module = '%s'";
	private static final String DELETEMAXMODULESMENUS = "delete maxmenu where menutype = 'MODULE' and  elementtype in ( 'HEADER','MODULE' ) and moduleapp = '%s'";

	/**
	 * 导入前首先清理 模块 对应的数据
	 */
	private void clearMaxModules(MaxModules module) {
		_log.info("-----清理模块数据:----------" + module.getDescription());
		try {
			importST.addBatch(String.format(DELETEMAXMODULES, module.getModule()));
			importST.addBatch(String.format(DELETEMAXMODULESMENUS, module.getModule()));

			importST.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		_log.info("-----清理模块数据结束:----------" + module.getDescription());
	}

	/**
	 * 导出APP 包括 导出 模块 及模块对应的二级菜单、应用及应用对应的二级菜单
	 * 
	 * @param exportObjects
	 */
	public void exportApp(String modules, String apps) {
		// 判断模块参数是否为空，不为空 导出模块
		try {
			if (modules != null && !"".equals(modules)) {
				_log.info("----------导出模块:" + modules);
				// 需要导出的对象数组
				List<MaxModules> moduleList = exportModules(CommonUtil.buildExportObjects(modules));
				SerializeUtil.writeObject(moduleList,
						new File(SystemEnvironmental.getInstance().getStringParam("-packagepath") + MODLESFILEPATH));
			}
			// 导出 应用程序
			if (apps != null && !"".equals(apps)) {
				_log.info("----------导出应用程序:" + apps);
				List<MaxApps> appList = exportApps(CommonUtil.buildExportObjects(apps));
				SerializeUtil.writeObject(appList,
						new File(SystemEnvironmental.getInstance().getStringParam("-packagepath") + APPFILEPATH));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}

	/**
	 * 导出应用程序
	 * 
	 * @param apps
	 * @return
	 * @throws SQLException
	 */
	private List<MaxApps> exportApps(String[] apps) throws SQLException {
		List<MaxApps> list = new ArrayList<MaxApps>();
		for (String app : apps) {
			_log.info("---------导出应用程序--：" + app);
			maxappsST.setString(1, app);
			ResultSet rs = maxappsST.executeQuery();
			if (rs.next()) {
				MaxApps maxApp = new MaxApps(CommonUtil.NULLTOEMPTY(rs.getString(1)),
						CommonUtil.NULLTOEMPTY(rs.getString(2)), CommonUtil.NULLTOEMPTY(rs.getString(3)),
						CommonUtil.NULLTOEMPTY(rs.getString(4)), CommonUtil.NULLTOEMPTY(rs.getString(5)),
						CommonUtil.NULLTOEMPTY(rs.getString(6)), CommonUtil.NULLTOEMPTY(rs.getString(7)),
						CommonUtil.NULLTOEMPTY(rs.getString(8)), CommonUtil.NULLTOEMPTY(rs.getString(10)));
				_log.info("-----导出应用程序--" + app + "--对应的菜单---------");
				maxApp.setMaxMenus(exportAppMenus(app));
				_log.info("-----导出应用程序--" + app + "--对应的授权信息---------");
				maxApp.setSigoptions(exportAppSigoptions(app));
				_log.info("-----导出应用程序--" + app + "--对应的MaxLabels的信息---------");
				maxApp.setMaxlabels(exportAppMaxLabels(app));
				_log.info("-----导出应用程序--" + app + "--对应的maxpresentation的信息---------");
				maxApp.setMaxPresentation(exportAppMaxPresentation(app));

				list.add(maxApp);
			}
			rs.close();
		}
		return list;
	}

	/**
	 * 导出 MaxPresentation
	 * 
	 * @param app
	 * @return
	 * @throws SQLException
	 */
	private MaxPresentation exportAppMaxPresentation(String app) throws SQLException {
		maxpresentationST.setString(1, app);
		ResultSet rs = maxpresentationST.executeQuery();
		if (rs.next()) {
			return new MaxPresentation(CommonUtil.NULLTOEMPTY(rs.getString(1)),
					CommonUtil.NULLTOEMPTY(rs.getString(3)));
		}
		rs.close();
		return null;
	}

	/**
	 * 导出 Maxlabels 表信息
	 * 
	 * @param app
	 * @return
	 * @throws SQLException
	 */
	private List<Maxlabels> exportAppMaxLabels(String app) throws SQLException {
		List<Maxlabels> list = new ArrayList<Maxlabels>();
		maxlabelsST.setString(1, app);
		ResultSet rs = maxlabelsST.executeQuery();
		while (rs.next()) {
			list.add(new Maxlabels(CommonUtil.NULLTOEMPTY(rs.getString(1)), CommonUtil.NULLTOEMPTY(rs.getString(2)),
					CommonUtil.NULLTOEMPTY(rs.getString(3)), CommonUtil.NULLTOEMPTY(rs.getString(4))));
		}
		rs.close();
		return list;
	}

	/**
	 * 导出授权表信息
	 * 
	 * @param app
	 * @return
	 * @throws SQLException
	 */
	private List<Sigoption> exportAppSigoptions(String app) throws SQLException {
		List<Sigoption> list = new ArrayList<Sigoption>();
		sigoptionST.setString(1, app);
		ResultSet rs = sigoptionST.executeQuery();
		while (rs.next()) {
			list.add(new Sigoption(CommonUtil.NULLTOEMPTY(rs.getString(1)), CommonUtil.NULLTOEMPTY(rs.getString(2)),
					CommonUtil.NULLTOEMPTY(rs.getString(3)), rs.getInt(4), rs.getInt(5),
					CommonUtil.NULLTOEMPTY(rs.getString(6)), CommonUtil.NULLTOEMPTY(rs.getString(7)),
					CommonUtil.NULLTOEMPTY(rs.getString(8)), CommonUtil.NULLTOEMPTY(rs.getString(10)), rs.getInt(11)));
		}
		rs.close();
		return list;
	}

	/**
	 * 导出应用程序对应的菜单
	 * 
	 * @param app
	 * @return
	 * @throws SQLException
	 */
	private List<MaxMenu> exportAppMenus(String app) throws SQLException {
		List<MaxMenu> list = new ArrayList<MaxMenu>();
		maxappsmenusST.setString(1, app);
		maxappsmenusST.setString(2, app);
		ResultSet rs = maxappsmenusST.executeQuery();
		while (rs.next()) {
			list.add(new MaxMenu(CommonUtil.NULLTOEMPTY(rs.getString(1)), CommonUtil.NULLTOEMPTY(rs.getString(2)),
					rs.getInt(3), rs.getInt(4), CommonUtil.NULLTOEMPTY(rs.getString(5)),
					CommonUtil.NULLTOEMPTY(rs.getString(6)), CommonUtil.NULLTOEMPTY(rs.getString(7)),
					CommonUtil.NULLTOEMPTY(rs.getString(8)), rs.getInt(9), CommonUtil.NULLTOEMPTY(rs.getString(10)),
					CommonUtil.NULLTOEMPTY(rs.getString(11)), CommonUtil.NULLTOEMPTY(rs.getString(12))));
		}
		rs.close();
		return list;

	}

	/**
	 * 导出模块
	 * 
	 * @param modules
	 * @return
	 * @throws SQLException
	 */
	private List<MaxModules> exportModules(String[] modules) throws SQLException {
		List<MaxModules> list = new ArrayList<MaxModules>();
		for (String module : modules) {
			_log.info("--导出模块：" + module);
			maxmodulesST.setString(1, module);
			ResultSet rs = maxmodulesST.executeQuery();
			if (rs.next()) {
				MaxModules maxModules = new MaxModules(CommonUtil.NULLTOEMPTY(rs.getString(1)),
						CommonUtil.NULLTOEMPTY(rs.getString(2)));
				_log.info("--导出模块对应的二级菜单-------------");
				maxModules.setMaxMenus(exportModuleMenus(module));
				// 添加到集合
				list.add(maxModules);
			}
			rs.close();
		}

		return list;
	}

	/**
	 * 导出模块对应的二级菜单
	 * 
	 * @param module
	 * @return
	 * @throws SQLException
	 */
	private List<MaxMenu> exportModuleMenus(String module) throws SQLException {
		List<MaxMenu> list = new ArrayList<MaxMenu>();
		maxmodulesmenusST.setString(1, module);
		ResultSet rs = maxmodulesmenusST.executeQuery();
		while (rs.next()) {
			list.add(new MaxMenu(CommonUtil.NULLTOEMPTY(rs.getString(1)), CommonUtil.NULLTOEMPTY(rs.getString(2)),
					rs.getInt(3), rs.getInt(4), CommonUtil.NULLTOEMPTY(rs.getString(5)),
					CommonUtil.NULLTOEMPTY(rs.getString(6)), CommonUtil.NULLTOEMPTY(rs.getString(7)),
					CommonUtil.NULLTOEMPTY(rs.getString(8)), rs.getInt(9), CommonUtil.NULLTOEMPTY(rs.getString(10)),
					CommonUtil.NULLTOEMPTY(rs.getString(11)), CommonUtil.NULLTOEMPTY(rs.getString(12))));
		}
		rs.close();
		return list;
	}

	/**
	 * 关闭打开的资源
	 */
	private void closeResource() {
		try {
			if (maxmodulesST != null)
				maxmodulesST.close();
			if (maxmodulesmenusST != null)
				maxmodulesmenusST.close();
			if (maxappsST != null)
				maxappsST.close();
			if (maxappsmenusST != null)
				maxappsmenusST.close();
			if (sigoptionST != null)
				sigoptionST.close();
			if (maxlabelsST != null)
				maxlabelsST.close();
			if (maxpresentationST != null)
				maxpresentationST.close();

			if (importST != null)
				importST.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
