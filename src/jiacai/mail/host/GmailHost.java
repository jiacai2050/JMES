package jiacai.mail.host;

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
		this.setHostWithSSL(email);
		System.err.println("Gmail必须使用SSL验证连接,自动转到SSL");
	}

}
