package cn.shuto.maximo.tool.system;

public class SystemEnvironmental {
	
	private static class SingletonHolder {
		private static SystemEnvironmental instance = new SystemEnvironmental();
	}

	private SystemEnvironmental() {
	}

	public static SystemEnvironmental getInstance() {
		return SingletonHolder.instance;
	}
}
