package jiacai.mail.host;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.mail.HtmlEmail;

public class SinaHost implements HostEntity{

	@Override
	public void setHostWithSSL(HtmlEmail email) {
		try {
			throw new OperationNotSupportedException("新浪暂不支持SSL验证连接");
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setHostWithNoSSL(HtmlEmail email) {
		email.setHostName("smtp.sina.com");
		email.setSSLOnConnect(false);
		email.setSmtpPort(25);
	}

}
