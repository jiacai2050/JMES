package jiacai.mail.util;

import java.io.IOException;
import java.util.Properties;

public class ConfigureHelper {

	private static Properties prop = new Properties();
	
	static {
		try {
			prop.load(ConfigureHelper.class.getClassLoader()
					.getResourceAsStream("conf.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getUserEmail() {
		return prop.getProperty("email");
	}
	public static String getUserPwd() {
		return prop.getProperty("pwd");
	}
	public static String getAttachFolder() {
		return prop.getProperty("attach");
	}
	public static String getToFile() {
		return prop.getProperty("tos");
	}
	public static String getQRFile() {
		return prop.getProperty("qrchart");
	}
}
