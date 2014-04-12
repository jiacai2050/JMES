package jiacai.mail.util.test;

import java.util.List;

import javax.mail.internet.InternetAddress;

import jiacai.mail.JMEAuthentication;
import jiacai.mail.JMEMessage;
import jiacai.mail.JMES;
import jiacai.mail.host.$163Host;
import jiacai.mail.sniffer.URLEmailSniffer;
import jiacai.mail.util.ConfigureHelper;

import org.apache.commons.mail.HtmlEmail;

public class MainTest {

	public static void main(String[] args) throws Exception {
		 
		JMES jms = new JMES(new $163Host(), new JMEAuthentication(
				ConfigureHelper.getUserEmail(), ConfigureHelper.getUserPwd()),
				true);
		HtmlEmail email = new HtmlEmail();
		//for (int i = 12; i < 24; i++) {

			List<InternetAddress> tos = new URLEmailSniffer(
					ConfigureHelper.getTos()).getEmails();
			jms.send(
					email,
					new JMEMessage(email, new InternetAddress(ConfigureHelper
							.getUserEmail()), tos, "资源分享", ConfigureHelper
							.getHtmlPicFolder(), ConfigureHelper
							.getAttachFolder()));

			System.out.println(1 + "完成");
		//}
	}

}
