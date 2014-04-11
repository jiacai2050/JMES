package jiacai.mail;

import java.io.File;

import jiacai.mail.util.ConfigureHelper;
import jiacai.mail.util.EmailSniffer;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class ApacheMailTest {

	public static void main(String[] args) throws Exception {
		
		HtmlEmail email = new HtmlEmail();

		email.setHostName("smtp.163.com");
		email.setAuthentication(ConfigureHelper.getUserEmail(), ConfigureHelper.getUserPwd());
		email.setCharset("UTF-8");
		//email.setDebug(true);

		email.setFrom(ConfigureHelper.getUserEmail()).setSubject("编程资源");
		email.setTo(EmailSniffer.getEmails(ConfigureHelper.getToFile()));

		appendAttach(email, ConfigureHelper.getAttachFolder());
		
		File qrchart = new File(ConfigureHelper.getQRFile());

		StringBuffer sb = new StringBuffer();
		sb.append("<html><body>");
		sb.append("大家好:<br/>");
		sb.append("我现在需要做一份问卷调查，时间不长，希望大家能够抽出5分钟来给我填写一下。作为回报我会把我收集的编程资源分享给你。<br/>");
		sb.append("地址:<a href='http://www.sojump.com/jq/3068721.aspx'>http://www.sojump.com/jq/3068721.aspx</a><br/>");
		sb.append("附件中是我百度网盘的资源截图<br>");
		sb.append("<img src=cid:").append(email.embed(qrchart)).append("><br/>");
		sb.append("手机党可扫二维码进入问卷调查<br>");
		sb.append("<h3>Group-sending by <a href='https://github.com/jiacai2050/mass-email-sender'>mass-email-sender</a></h3>");
		sb.append("</body></html>");

		email.setHtmlMsg(sb.toString());
		email.setTextMsg("Your email client does not support HTML messages");
		email.send();
		System.out.println("ok");
	}

	public static void appendAttach(HtmlEmail email, String folder) {
		
		File[] files = new File(folder).listFiles();
		for(File f:files) {
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(f.getAbsolutePath());
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			
			try {
				email.attach(attachment);
			} catch (EmailException e) {
				e.printStackTrace();
			}	
		}
		
	}
}
