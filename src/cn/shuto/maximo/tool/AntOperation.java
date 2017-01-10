package cn.shuto.maximo.tool;

import java.io.File;
import java.security.Permission;
import java.security.Policy;
import java.security.ProtectionDomain;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Echo;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.taskdefs.Mkdir;
import org.apache.tools.ant.types.Environment.Variable;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

public class AntOperation {

	private Project project = new Project();
	private Path antRunLibPath = null;
	private String maximoPath ;

	public AntOperation(String maximoPath) {
		super();
		this.maximoPath = maximoPath;
		init();
	}

	private void init() {
		Policy.setPolicy(new AWSPolicy());
		project.setName("maximo-tool");
		// 配置日志输出
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		project.addBuildListener(consoleLogger);

		//构建Ant运行环境需要的Jar包
		antRunLibPath = new Path(project);
		Path businessobjectsPath = new Path(project, maximoPath+"\\businessobjects\\classes");
		Path maximouiwebPath = new Path(project, maximoPath+"\\maximouiweb\\webmodule\\WEB-INF\\classes");
		// TODO 需要在发布的时候修改路径
		Path p3 = new Path(project, "bin");
		antRunLibPath.add(businessobjectsPath);
		antRunLibPath.add(maximouiwebPath);
		antRunLibPath.add(p3);

		FileSet maximoLibFileSet = new FileSet();
		maximoLibFileSet.setDir(new File(maximoPath+"\\lib"));
		maximoLibFileSet.setIncludes("**/*.jar");

		antRunLibPath.addFileset(maximoLibFileSet);

	}

	public Project getProject() {
		return project;
	}
	/**
	 * 导出域配置
	 */
	public void exportdomaindm(String exportPath,String exportdomainids) {
		String name = "导出数据库配置";
		File exportDir = new File(exportPath + "/package/domainadm");
		if (!exportDir.exists() || exportDir.isDirectory()) {
			Target exportdbconfigTarget = new Target();
			exportdbconfigTarget.setProject(project);
			exportdbconfigTarget.setName(name);
			Echo echo = new Echo();
			echo.setProject(project);
			echo.addText("------------初始化域导出目录----------");
			echo.setTaskName(name);
			exportdbconfigTarget.addTask(echo);
			Delete delete = new Delete();
			delete.setProject(project);
			delete.setTaskName(name);
			delete.setDir(exportDir);
			exportdbconfigTarget.addTask(delete);
			Mkdir mkdir = new Mkdir();
			mkdir.setProject(project);
			mkdir.setTaskName(name);
			mkdir.setDir(exportDir);
			exportdbconfigTarget.addTask(mkdir);

			Java java = new Java();
			java.setTaskName(name);
			java.setProject(project);
			Variable variable = new Variable();
			variable.setKey("file.encoding");
			variable.setValue("UTF-8");
			java.addSysproperty(variable);
			java.setFork(false);
			java.setFailonerror(true);
			java.setClasspath(antRunLibPath);
			java.setClassname("cn.shuto.maximo.tool.App");
			java.createArg().setValue("-option=exportdomainadm");
			java.createArg().setValue("-maximopath="+maximoPath);
			java.createArg().setValue("-packagepath="+exportPath);
			java.createArg().setValue("-exportdomainids="+exportdomainids);
			
			exportdbconfigTarget.addTask(java);
			exportdbconfigTarget.execute();
		} else {
			throw new RuntimeException("-------------域导出路径错误!------------");
		}
	}
	/**
	 * 导出数据配置
	 */
	public void exportdbconfig(String exportPath,String exportobjects) {
		String name = "导出数据库配置";
		File exportDir = new File(exportPath + "/package/dbconfig");
		if (!exportDir.exists() || exportDir.isDirectory()) {
			Target exportdbconfigTarget = new Target();
			exportdbconfigTarget.setProject(project);
			exportdbconfigTarget.setName(name);
			Echo echo = new Echo();
			echo.setProject(project);
			echo.addText("------------初始化数据库配置导出目录----------");
			echo.setTaskName(name);
			exportdbconfigTarget.addTask(echo);
			Delete delete = new Delete();
			delete.setProject(project);
			delete.setTaskName(name);
			delete.setDir(exportDir);
			exportdbconfigTarget.addTask(delete);
			Mkdir mkdir = new Mkdir();
			mkdir.setProject(project);
			mkdir.setTaskName(name);
			mkdir.setDir(exportDir);
			exportdbconfigTarget.addTask(mkdir);

			Java java = new Java();
			java.setTaskName(name);
			java.setProject(project);
			Variable variable = new Variable();
			variable.setKey("file.encoding");
			variable.setValue("UTF-8");
			java.addSysproperty(variable);
			java.setFork(false);
			java.setFailonerror(true);
			java.setClasspath(antRunLibPath);
			java.setClassname("cn.shuto.maximo.tool.App");
			java.createArg().setValue("-option=exportdbconfig");
			java.createArg().setValue("-maximopath="+maximoPath);
			java.createArg().setValue("-packagepath="+exportPath);
			java.createArg().setValue("-exportobjects="+exportobjects);
			
			exportdbconfigTarget.addTask(java);
			exportdbconfigTarget.execute();
		} else {
			throw new RuntimeException("-------------数据库导出路径错误!------------");
		}
	}

	public static void main(String[] args) {
		AntOperation ao = new AntOperation("E:\\Projects\\maximo-xdj");
		ao.exportdbconfig("D://export//","ST_PI_AREA,ST_PI_AREA_ITEM");

	}

	private static class AWSPolicy extends Policy {
		private final Policy defaultPolicy;

		public AWSPolicy() {
			super();

			defaultPolicy = Policy.getPolicy();
		}

		@Override
		public boolean implies(ProtectionDomain domain, Permission permission) {
			if (permission instanceof javax.management.MBeanTrustPermission) {
				return true;
			} else {
				return defaultPolicy.implies(domain, permission);
			}
		}
	}
}
