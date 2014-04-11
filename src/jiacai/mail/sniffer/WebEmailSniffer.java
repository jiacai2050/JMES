package jiacai.mail.sniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class WebEmailSniffer implements EmailSniffer{

	private String urlStr;
	public WebEmailSniffer(String urlStr) {
		this.urlStr = urlStr;
	}
 	@Override
	public List<InternetAddress> getEmails() {
 		List<InternetAddress> urls = new  ArrayList<InternetAddress>();
		try (InputStream is = new URL(urlStr).openStream()){
			
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
 	public static void main(String[] args) {
		System.out.println(new WebEmailSniffer("http://bbs.tianya.cn/post-english-220021-1.shtml").getEmails());
	}
}
