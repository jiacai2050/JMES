package jiacai.mail.host;

import org.apache.commons.mail.HtmlEmail;

public interface HostEntity {
	void setHostWithSSL(HtmlEmail email);
	void setHostWithNoSSL(HtmlEmail email);
}
