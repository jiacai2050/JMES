package jiacai.mail.host;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.mail.HtmlEmail;

public class SinaHost implements HostEntity{

	@Override
	public void setHostWithSSL(HtmlEmail email) {
		System.err.println("新浪暂不支持SSL验证连接,自动转动非SSL连接");
		this.setHostWithNoSSL(email);
	}

	@Override
	public void setHostWithNoSSL(HtmlEmail email) {
		email.setHostName("smtp.sina.com");
		email.setSSLOnConnect(false);
		email.setSmtpPort(25);
	}

}
