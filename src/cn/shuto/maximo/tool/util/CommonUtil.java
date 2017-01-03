package cn.shuto.maximo.tool.util;

public class CommonUtil {
	/**
	 * 以 “,” 为分隔符构建需要导出的对象数组
	 * 
	 * @param exportObjects
	 * @return
	 */
	public static String[] buildExportObjects(String exportParames) {
		return exportParames!=null?exportParames.split(","):null;
	}

	/**
	 * 判断字段是否为空 如果空 返回 "" 不能返回 "null"
	 * 
	 * @param v
	 * @return
	 */
	public static String NULLTOEMPTY(String v) {
		return v == null ? "" : v;
	}
}
