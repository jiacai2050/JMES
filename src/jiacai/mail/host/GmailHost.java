package jiacai.mail.host;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.mail.HtmlEmail;

public class GmailHost implements HostEntity{

	@Override
	public void setHostWithSSL(HtmlEmail email) {
		email.setHostName("smtp.gmail.com");
		email.setSSLOnConnect(true);
		email.setSmtpPort(465);
	}

	@Override
	public void setHostWithNoSSL(HtmlEmail email) {
		try {
			throw new OperationNotSupportedException("gmail必须使用SSL验证连接");
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
