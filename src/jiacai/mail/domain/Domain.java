package jiacai.mail.domain;

import org.apache.commons.mail.HtmlEmail;

public interface Domain {
	void setHostWithSSL(HtmlEmail email);
	void setHostWithNoSSL(HtmlEmail email);
}
