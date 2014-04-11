package jiacai.mail.sniffer;

import java.util.List;

import javax.mail.internet.InternetAddress;

public interface EmailSniffer {

	 public List<InternetAddress> getEmails ();
}
