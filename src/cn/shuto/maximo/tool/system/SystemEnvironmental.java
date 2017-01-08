package cn.shuto.maximo.tool.system;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SystemEnvironmental {

	private Map<String, Object> param = new HashMap<String, Object>();
	private ResourceBundle bundle;

	public ResourceBundle getResourceBundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle("resources.maximotool");
		}
		return bundle;
	}

	/**
	 * 从资源绑定中获取字符串
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	public String getResource2String(String key) {
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

	/**
	 * 从资源包返回一个助记符。通常用作菜单项中的键盘快捷键。
	 * 
	 * @param key
	 *            资源文件中的关键字
	 * @return
	 */
	public char getResource2Mnemonic(String key) {
		return (getResource2String(key)).charAt(0);
	}

	public void putParam(String name, Object value) {
		param.put(name, value);
	}

	public Object getParam(String name) {
		return param.get(name);
	}

	public String getStringParam(String name) {
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
