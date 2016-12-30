package cn.shuto.maximo.tool.system;

import java.util.HashMap;
import java.util.Map;

public class SystemEnvironmental {
	
	private Map<String, Object> param = new HashMap<String, Object>();
	
	public void putParam(String name,Object value){
		param.put(name, value);
	}
	
	public Object getParam(String name){
		return param.get(name);
	}
	
	public String getStringParam(String name){
		return (String) param.get(name);
	}


	private static class SingletonHolder {
		private static SystemEnvironmental instance = new SystemEnvironmental();
	}

	private SystemEnvironmental() {
	}

	public static SystemEnvironmental getInstance() {
		return SingletonHolder.instance;
	}
}
