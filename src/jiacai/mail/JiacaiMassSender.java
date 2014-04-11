package jiacai.mail;

import java.util.Collection;

import javax.mail.internet.InternetAddress;

import jiacai.mail.host.$163;
import jiacai.mail.host.HostEntity;
import jiacai.mail.sniffer.LocalFileEmailSniffer;
import jiacai.mail.util.ConfigureHelper;

import org.apache.commons.mail.HtmlEmail;

public class JiacaiMassSender {

	private HostEntity hostEntity;
	private String userEmail;
	private String userPwd;
	private String subject;
	private Collection<InternetAddress> toEmails;

	private boolean debug;

	public JiacaiMassSender(HostEntity hostEntity, String userEmail, String userPwd,
			String subject, Collection<InternetAddress> toEmails, boolean debug) {
		super();
		this.hostEntity = hostEntity;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.subject = subject;
		this.toEmails = toEmails;
		this.debug = debug;
	}

	public void send(HtmlEmail email, EmailMessage message)
			throws Exception {

		hostEntity.setHostWithSSL(email); 
		email.setAuthentication(userEmail, userPwd);
		email.setCharset("UTF-8");
		email.setDebug(debug);
		email.setFrom(userEmail).setSubject(subject);
		email.setTo(toEmails);

		email.setHtmlMsg(message.getMessage());
		email.setTextMsg("Your email client does not support HTML messages");
		email.send();

		System.out.println("\nMail was sent successfully.");
	}

	public static void main(String[] args) throws Exception{
		
		JiacaiMassSender jms = new JiacaiMassSender(new $163(), ConfigureHelper.getUserEmail(), ConfigureHelper.getUserPwd(),
				"晚安", new LocalFileEmailSniffer(ConfigureHelper.getToFile()).getEmails(), true);
		HtmlEmail email = new HtmlEmail();
		jms.send(email, new EmailMessage(email, ConfigureHelper.getHtmlPicFolder(),ConfigureHelper.getAttachFolder()));
	}


}
