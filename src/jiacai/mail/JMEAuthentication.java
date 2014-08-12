package jiacai.mail;

import javax.mail.PasswordAuthentication;

import org.apache.commons.mail.DefaultAuthenticator;

public class JMEAuthentication extends DefaultAuthenticator{

	
	public JMEAuthentication(String userName, String password) {
		super(userName, password);
	}
	public PasswordAuthentication getPwdAuth () {
		return getPasswordAuthentication();
	}

}
