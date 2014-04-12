package jiacai.mail;


import jiacai.mail.host.HostEntity;

import org.apache.commons.mail.HtmlEmail;

public class JMES {

	private HostEntity hostEntity;
	private JMEAuthentication auth;

	private boolean debug;

	public JMES(HostEntity hostEntity, JMEAuthentication auth, boolean debug) {
		super();
		this.hostEntity = hostEntity;
		this.auth = auth;
		this.debug = debug;
	}

	public void send(HtmlEmail email, JMEMessage JMEmsg) throws Exception {

		hostEntity.setHostWithNoSSL(email);
		email.setAuthenticator(auth);
		email.setCharset("UTF-8");
		email.setDebug(debug);
		email.setFrom(auth.getPwdAuth().getUserName()).setTo(
				JMEmsg.getToEmails());
		email.setHtmlMsg(JMEmsg.getMessage()).setSubject(JMEmsg.getSubject());
		email.setTextMsg("Your email client does not support HTML messages")
				.send();

		System.out.println("\nMail was sent successfully.");
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

	public void setHostEntity(HostEntity hostEntity) {
		this.hostEntity = hostEntity;
	}

	public HostEntity getHostEntity() {
		return hostEntity;
	}

}
