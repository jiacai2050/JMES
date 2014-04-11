package jiacai.mail.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ConfigureHelper {

	private static Properties prop = new Properties();
	
	static String base = System.getProperty("user.dir") + File.separatorChar;
	
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
		return base + prop.getProperty("attachFolder");
	}
	public static String getToFile() {
		return prop.getProperty("tos");
	}
	public static String getHtmlPicFolder() {
		return base + prop.getProperty("htmlPicFolder");
	}
}
