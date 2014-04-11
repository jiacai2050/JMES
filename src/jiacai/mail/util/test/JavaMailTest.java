package jiacai.mail.util.test;


import java.io.File;
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

import jiacai.mail.sniffer.LocalFileEmailSniffer;
import jiacai.mail.util.ConfigureHelper;

@Deprecated
public class JavaMailTest {

//	public static void main(String[] args) throws Exception {
//		send(EmailConent.PROGRAM, ConfigureHelper.getQRFile(), ConfigureHelper.getAttachFolder());
//	}
	@SuppressWarnings("unused")
	public static void send(String contentWord,String contentPicFolder,String attachFolder) throws Exception {

		String subject = "编程资源";
		String from = ConfigureHelper.getUserEmail();
		String pwd = ConfigureHelper.getUserPwd();
		String tosfile = ConfigureHelper.getToFile();

		Properties props = System.getProperties();

		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props,
				new MyAuthenticator(from, pwd));
		session.setDebug(true);

		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(from));
		msg.setSubject(subject);
		
		List<InternetAddress> tosList = new LocalFileEmailSniffer(tosfile).getEmails();
		InternetAddress[] tosArray = new InternetAddress[tosList.size()];
		tosList.toArray(tosArray);
		msg.setRecipients(RecipientType.TO, tosArray);
		

		MimeMultipart htmlPart = new MimeMultipart("mixed");

		if (attachFolder != null) {
			MimeBodyPart attachPart = new MimeBodyPart();
			MimeMultipart filesPart = new MimeMultipart();

			File[] files = new File(attachFolder).listFiles();
			for (File f : files) {
				MimeBodyPart filePart = new MimeBodyPart();
				filePart.attachFile(f);
				htmlPart.addBodyPart(filePart);
			}
			
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
		wordPart.setContent(contentWord, "text/html;charset=utf-8");
		bodyPart.addBodyPart(wordPart);
		
		if (null != contentPicFolder) {
			File[] files = new File(contentPicFolder).listFiles();
			for (File f : files) {
				MimeBodyPart picPart = new MimeBodyPart();
				
				picPart.setDataHandler(new DataHandler(new FileDataSource(f)));
				picPart.setFileName(f.getName());
				picPart.setContentID(f.getName());
				bodyPart.addBodyPart(picPart);
			}
		}

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
