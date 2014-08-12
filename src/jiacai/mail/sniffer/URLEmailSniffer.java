package jiacai.mail.sniffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class URLEmailSniffer implements EmailSniffer{

	private URL url;
	public URLEmailSniffer(String urlSeed) {
		try {
			int colon = urlSeed.indexOf(":");
			if (-1 == colon || urlSeed.startsWith("/")) {
				this.url = new File(urlSeed).toURI().toURL();
			} else {
				String protocol = urlSeed.substring(0, colon);
				if (protocol == null || protocol.contains(File.separatorChar+"")) {
					this.url = new File(urlSeed).toURI().toURL();
				} else {
					this.url = new URL(urlSeed);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
 	@Override
	public List<InternetAddress> getEmails() {
 		List<InternetAddress> urls = new  ArrayList<InternetAddress>();
		try (InputStream is = url.openStream()){
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String line = null;
			Pattern p = Pattern.compile("\\w+@\\w+\\.\\w+");

			while ((line = br.readLine()) != null) {
				Matcher m = p.matcher(line);
				while (m.find()) {
					urls.add(new InternetAddress(m.group()));
				}
			}
			return urls;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
