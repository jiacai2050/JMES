package jiacai.mail;

import java.util.List;

import javax.mail.internet.InternetAddress;

import jiacai.mail.domain.Domain;
import jiacai.mail.domain.factory.Factory;
import jiacai.mail.sniffer.URLEmailSniffer;
import jiacai.mail.util.Configuration;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class JMES {

	private Domain domain;
	private JMEAuthentication auth;
	private HtmlEmail email;
	private String subject;
	private JMEMessage msg;
	private List<InternetAddress> recipients;
	private boolean debug;

	public JMES(Configuration conf, String subject, boolean debug) {
		email = new HtmlEmail();
		this.subject = subject;
		this.auth = new JMEAuthentication(conf.getUserEmail(),
				conf.getUserPwd());
		this.debug = debug;
		domain = Factory.getDomain(conf.getDomainType());

		this.recipients = new URLEmailSniffer(conf.getRecipients()).getEmails();
		this.msg = new JMEMessage(email, conf.getMsgFile(), conf.getHtmlPicFolder(),
				conf.getAttachFolder());
	}

	public void send(){

		domain.setHostWithNoSSL(email);
		email.setAuthenticator(auth);
		email.setCharset("UTF-8");
		email.setDebug(debug);
		try {
			email.setFrom(auth.getPwdAuth().getUserName()).setTo(recipients);
			email.setHtmlMsg(msg.getMsg()).setSubject(subject);
			email.setTextMsg("Your email client does not support HTML messages")
					.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		System.out.println("成功向");
		for(InternetAddress rec: recipients) {
			System.out.println(rec.getAddress());
		}
		System.out.println("发送邮件Y(^_^)Y");
	}

	public JMEAuthentication getAuth() {
		return auth;
	}

	public void setAuth(JMEAuthentication auth) {
		this.auth = auth;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public HtmlEmail getEmail() {
		return email;
	}

	public void setEmail(HtmlEmail email) {
		this.email = email;
	}

}
