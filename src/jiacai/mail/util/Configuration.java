package jiacai.mail.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configuration {

	private Properties conf = new Properties();
	
	public Configuration(String confPath) {
		try {
			conf.load(new FileInputStream(confPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUserEmail() {
		return conf.getProperty("email");
	}
	public String getUserPwd() {
		return conf.getProperty("pwd");
	}
	public String getAttachFolder() {
		return conf.getProperty("attachFolder");
	}
	public String getRecipients() {
		return conf.getProperty("recipients");
	}
	public String getMsgFile() {
		return conf.getProperty("msgFile");
	}
	public String getHtmlPicFolder() {
		return conf.getProperty("picFolder");
	}
	public String getDomainType() {
		Pattern pattern = Pattern.compile("\\w+[\\w|\\.]*@(\\w+)\\.");
		Matcher matcher = pattern.matcher(this.getUserEmail());
		if (matcher.find()) {
			String domain = matcher.group(1); 
			if (domain.matches("(163|126)")) {
				domain = "netease";
			}
			return domain;	
		} else {
			try {
				throw new Exception("邮箱地址不正确！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
