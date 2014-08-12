package jiacai.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class JMEMessage {

	private String msg;

	public JMEMessage(HtmlEmail email, String msgFile, String htmlPicFolder,
			String attachFolder) {
		StringBuffer buffer = parseMsg(msgFile);
		
		if (null != htmlPicFolder && !htmlPicFolder.trim().equals("")) {
			File[] files = new File(htmlPicFolder).listFiles();
			for (File f : files) {
				try {
					buffer.append("<img src=cid:").append(email.embed(f))
							.append("><br/>");
				} catch (EmailException e) {
					e.printStackTrace();
				}
			}

		}
		buffer.append("<br><br>群发Power BY <a herf='https://github.com/jiacai2050/JMES'>JMES</a>");
		buffer.append("<br>作者个人博客<a herf='http://liujiacai.net'>http://liujiacai.net</a>");
		this.msg = buffer.toString();
		if (null != attachFolder && !attachFolder.trim().equals("")) {
			appendAttach(email, attachFolder);
		}
	}

	private StringBuffer parseMsg(String msgFile){
	
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(msgFile)));
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
