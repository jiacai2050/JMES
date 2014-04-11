package jiacai.mail;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailMessage {

	private String message;

	public EmailMessage(HtmlEmail email, String htmlPicFolder, String attachFolder) {

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

			message = sb.toString();
		}
		
		if (null != attachFolder) {
			appendAttach(email, attachFolder);
		}

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
