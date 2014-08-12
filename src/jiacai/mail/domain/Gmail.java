package jiacai.mail.domain;

import org.apache.commons.mail.HtmlEmail;

public class Gmail implements Domain{

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
