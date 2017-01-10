package cn.shuto.maximo.tool;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Echo;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.taskdefs.Mkdir;
import org.apache.tools.ant.types.LogLevel;

public class Test {
	public static void main(String[] args) {
		File f = new File("F:\\Workspace\\Eclipse\\workspace-maximo\\maximo-tool\\build.xml");
		Project p = new Project();

		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(LogLevel.INFO.getLevel());
		
		p.addBuildListener(consoleLogger);
		
		Echo echo = new Echo();
		echo.setProject(p);
		echo.addText("hello");
		Java j = new Java(echo);
		j.setClassname("cn.shuto.maximo.tool.App");
		
		Target target = new Target();
		target.setProject(p);
		target.setName("asdsa");
		target.addTask(j);
		target.addTask(echo);
		
	
		
		
		target.execute();
		

	}
}
