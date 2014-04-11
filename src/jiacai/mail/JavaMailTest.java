package jiacai.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import jiacai.mail.util.ConfigureHelper;
import jiacai.mail.util.EmailSniffer;

public class JavaMailTest {

	@SuppressWarnings("unused")
	public static void main(String[] argv) throws Exception {

		String subject = "编程资源";
		String from = ConfigureHelper.getUserEmail();
		String pwd = ConfigureHelper.getUserPwd();
		String qrchart = ConfigureHelper.getQRFile();
		String tosfile = ConfigureHelper.getToFile();
		String folder = ConfigureHelper.getAttachFolder();

		Properties props = System.getProperties();

		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props,
				new MyAuthenticator(from, pwd));
		session.setDebug(true);

		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(from));
		msg.setSubject(subject);
		
		List<InternetAddress> tosList = EmailSniffer.getEmails(tosfile);
		InternetAddress[] tosArray = new InternetAddress[tosList.size()];
		tosList.toArray(tosArray);
		msg.setRecipients(RecipientType.TO, tosArray);
		

		MimeMultipart htmlPart = new MimeMultipart("mixed");

		if (folder != null) {
			MimeBodyPart attachPart = new MimeBodyPart();
			MimeMultipart filesPart = new MimeMultipart();

			getAttachment(folder, filesPart);
			attachPart.setContent(filesPart);
			htmlPart.addBodyPart(attachPart);
		}

		MimeBodyPart contentPart = new MimeBodyPart();

		// 构造正文
		MimeMultipart bodyPart = new MimeMultipart("related");

		contentPart.setContent(bodyPart);
		// 文字部分
		MimeBodyPart wordPart = new MimeBodyPart();
		wordPart.setDisposition(javax.mail.Part.INLINE);
		wordPart.setContent(collect(), "text/html;charset=utf-8");
		// 图片部分
		MimeBodyPart picPart = new MimeBodyPart();
		picPart.setDataHandler(new DataHandler(new FileDataSource(qrchart)));
		picPart.setFileName("QRcode.png");
		picPart.setContentID("QRcode");

		bodyPart.addBodyPart(wordPart);
		bodyPart.addBodyPart(picPart);
		htmlPart.addBodyPart(contentPart);

		msg.setContent(htmlPart);
		msg.saveChanges();

		Transport.send(msg);

		System.out.println("\nMail was sent successfully.");

	}

	private static InternetAddress[] getTosAddress(String tos) {
		try {
			List<InternetAddress> list = new ArrayList<InternetAddress>();
			String[] to = tos.split(",");
			for (int i = 0; i < to.length; i++) {
				list.add(new InternetAddress(to[i]));
			}
			InternetAddress[] dest = (InternetAddress[]) list
					.toArray(new InternetAddress[list.size()]);

			return dest;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void getAttachment(String folder, MimeMultipart mp) {
		try {
			File[] files = new File(folder).listFiles();
			for (File f : files) {
				MimeBodyPart filePart = new MimeBodyPart();
				filePart.attachFile(f);
				mp.addBodyPart(filePart);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private static String collect() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>");
		sb.append("大家好:<br/>");
		sb.append("我现在需要做一份问卷调查，时间不长，希望大家能够抽出5分钟来给我填写一下。作为回报我会把我收集的编程资源分享给你。<br/>");
		sb.append("地址:<a href='http://www.sojump.com/jq/3068721.aspx'>http://www.sojump.com/jq/3068721.aspx</a><br/>");
		sb.append("附件中是我百度网盘的资源截图<br>");
		sb.append("<img src=\"cid:QRcode\"></img><br>");
		sb.append("Powered by JavaMail");
		sb.append("</body></html>");
		return sb.toString();
	}
}

class MyAuthenticator extends javax.mail.Authenticator {
	private String strUser;
	private String strPwd;

	public MyAuthenticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}
