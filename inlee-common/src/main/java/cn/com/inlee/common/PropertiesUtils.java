package cn.com.inlee.common;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件
 * <p>
 * Date: 2014-7-16
 * </p>
 * 
 * @company 英黎科技 @author rono
 */
public final class PropertiesUtils {

	public PropertiesUtils() {
		createPropertiesUtil("config.properties");
	}

	private static PropertiesUtils propertiesUtil = null;
	private static Properties properties = new Properties();

	public static synchronized void createPropertiesUtil(String filename) {
	 
		try {
			System.out.println(PropertiesUtils.class.getResourceAsStream("/"
					+ filename));
			properties.load(PropertiesUtils.class.getResourceAsStream("/"
					+ filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getString(String key, String defaultValue) {

		if (propertiesUtil == null)
			propertiesUtil = new PropertiesUtils();

		return properties.getProperty(key, defaultValue);
	}

	public static String getString(String key) {
		if (propertiesUtil == null)
			propertiesUtil = new PropertiesUtils();

		return properties.getProperty(key);
	}

	public static Integer getInteger(String key, Integer defaultValue) {
		if (propertiesUtil == null)
			propertiesUtil = new PropertiesUtils();

		if (!properties.containsKey(key))
			return defaultValue;
		return Integer.parseInt(properties.getProperty(key).trim());
	}

	public static Boolean getBoolean(String key, Boolean defaultValue) {

		if (propertiesUtil == null)
			propertiesUtil = new PropertiesUtils();

		if (!properties.containsKey(key))
			return defaultValue;
		return "true".equals(properties.getProperty(key).trim()) ? true : false;
	}
}
