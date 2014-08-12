package jiacai.mail.domain;

import org.apache.commons.mail.HtmlEmail;

public class NetEase implements Domain{

	String STMP_NAME = "smtp.163.com";
	
	int PORT_SSL = 465;
	int PORT_NO_SSL = 25;
	
	@Override
	public void setHostWithSSL(HtmlEmail email) {
		email.setHostName(STMP_NAME);
		email.setSSLOnConnect(true);
		email.setSmtpPort(PORT_SSL);
	}
	@Override
	public void setHostWithNoSSL(HtmlEmail email) {
		email.setHostName(STMP_NAME);
		email.setSSLOnConnect(false);
		email.setSmtpPort(PORT_NO_SSL);
	}
	 
	
}
