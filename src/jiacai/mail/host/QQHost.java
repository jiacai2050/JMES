package jiacai.mail.host;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.mail.HtmlEmail;

public class QQHost implements HostEntity{

	//http://kf.qq.com/faq/120322fu63YV130422nqIrqu.html
	//qq企业http://service.exmail.qq.com/cgi-bin/help?id=28&no=1000585&subtype=1
	@Override
	public void setHostWithSSL(HtmlEmail email) {
		
		email.setSSLOnConnect(true);
		email.setHostName("smtp.mail.qq.com");
		email.setSmtpPort(465);
		
	}

	@Override
	public void setHostWithNoSSL(HtmlEmail email) {
		email.setSSLOnConnect(false);
		email.setHostName("smtp.qq.com");
		email.setSmtpPort(25);
		
	}
	
	
  
}
