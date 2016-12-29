package cn.shuto.maximo.tool;

import java.sql.Connection;

import cn.shuto.maximo.tool.migration.dbconfig.DBConfigMigration;
import cn.shuto.maximo.tool.util.DBUtil;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	DBConfigMigration dbcm = new DBConfigMigration(args[0]);
    	dbcm.exportDBConfig(args[1]);
    }
}
