package jiacai.mail.sniffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import jiacai.mail.util.ConfigureHelper;

public class LocalFileEmailSniffer implements EmailSniffer{
 
	private String file;
	
	public LocalFileEmailSniffer(String file) {
		this.file = file;
	}

	@Override
	public List<InternetAddress> getEmails() {
		List<InternetAddress> urls = new  ArrayList<InternetAddress>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(
				file))) {
			String line = null;
			
			Pattern p = Pattern.compile("\\w+@\\w+\\.\\w+");

			while ((line = br.readLine()) != null) {
				Matcher m = p.matcher(line);
				while (m.find()) {
					urls.add(new InternetAddress(m.group()));
				}
			}
		} catch (IOException | AddressException e) {
			e.printStackTrace();
		}
		return urls;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new LocalFileEmailSniffer(ConfigureHelper.getToFile()).getEmails());
	}
}
