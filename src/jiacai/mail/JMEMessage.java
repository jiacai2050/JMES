package jiacai.mail;

import java.io.File;
import java.util.Collection;

import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class JMEMessage {

	private String message;
	private String subject;
	private Collection<InternetAddress> toEmails;
	private InternetAddress from;
	private String htmlPicFolder;
	private String attachFolder;

	public JMEMessage(HtmlEmail email, InternetAddress from,
			Collection<InternetAddress> toEmails, String subject,
			String htmlPicFolder, String attachFolder) {
		this.from = from;
		this.toEmails = toEmails;
		this.subject = subject;
		this.htmlPicFolder = htmlPicFolder;
		this.attachFolder = attachFolder;

		StringBuffer sb = new StringBuffer();
		sb.append("<html><body>");
		sb.append("大家好:<br/>");
		sb.append("我现在需要做一份问卷调查，时间不长，希望大家能够抽出5分钟来给我填写一下。作为回报我会把我收集的资源分享给你。<br/>");
		sb.append("地址:<a href='http://www.sojump.com/jq/3068721.aspx'>http://www.sojump.com/jq/3068721.aspx</a><br/>");
		sb.append("我这个人没别的爱好，就喜欢收藏各种学习好资源，独乐乐不如众乐乐，现在分享给大家了～<br>");
		sb.append("附件是我百度网盘的资源截图，肯定有你想要的～O(∩_∩)O~<br>");
		if (null != htmlPicFolder) {
			File[] files = new File(htmlPicFolder).listFiles();
			for (File f : files) {
				try {
					sb.append("<img src=cid:").append(email.embed(f))
							.append("><br/>");
				} catch (EmailException e) {
					e.printStackTrace();
				}
			}
			sb.append("手机党可扫二维码进入问卷调查<br>");
			sb.append("<h3>Group-sending by <a href='https://github.com/jiacai2050/mass-email-sender'>mass-email-sender</a></h3>");
			sb.append("</body></html>");

			this.message = sb.toString();
		}

		if (null != attachFolder) {
			appendAttach(email, attachFolder);
		}

	}

	public InternetAddress getFrom() {
		return from;
	}

	public void setFrom(InternetAddress from) {
		this.from = from;
	}

	public String getHtmlPicFolder() {
		return htmlPicFolder;
	}

	public void setHtmlPicFolder(String htmlPicFolder) {
		this.htmlPicFolder = htmlPicFolder;
	}

	public String getAttachFolder() {
		return attachFolder;
	}

	public void setAttachFolder(String attachFolder) {
		this.attachFolder = attachFolder;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Collection<InternetAddress> getToEmails() {
		return toEmails;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setToEmails(Collection<InternetAddress> toEmails) {
		this.toEmails = toEmails;
	}

	public String getMessage() {
		return message;
	}

	private static void appendAttach(HtmlEmail email, String folder) {

		File[] files = new File(folder).listFiles();
		for (File f : files) {
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
