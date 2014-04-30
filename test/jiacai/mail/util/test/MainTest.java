package jiacai.mail.util.test;

import java.util.List;

import javax.mail.internet.InternetAddress;

import jiacai.mail.JMEAuthentication;
import jiacai.mail.JMEMessage;
import jiacai.mail.JMES;
import jiacai.mail.host.GmailHost;
import jiacai.mail.sniffer.URLEmailSniffer;
import jiacai.mail.util.ConfigureHelper;
import junit.framework.Assert;

import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

public class MainTest {

	@Test
	public void testSend() {
		//Todo
		Assert.assertEquals(1, 1);
	}
	public static void main(String[] args) throws Exception {
		 
		JMES jms = new JMES(new GmailHost(), new JMEAuthentication(
				ConfigureHelper.getUserEmail(), ConfigureHelper.getUserPwd()),
				true);
		HtmlEmail email = new HtmlEmail();
		for (int i = 12; i < 24; i++) {
			
			List<InternetAddress> tos = new URLEmailSniffer(
					"http://bbs.tianya.cn/post-english-220021-"+i+".shtml").getEmails();
			
			jms.send(
					email,
					new JMEMessage(email, new InternetAddress(ConfigureHelper
							.getUserEmail()), tos, "资源分享", ConfigureHelper
							.getHtmlPicFolder(), ConfigureHelper
							.getAttachFolder()));
			System.out.println(i + "完成");
			Thread.sleep(1000 * 60);
		}
	}

}
