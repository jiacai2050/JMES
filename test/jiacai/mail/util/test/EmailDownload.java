package jiacai.mail.util.test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import jiacai.mail.sniffer.URLEmailSniffer;

public class EmailDownload {

	public static void main(String[] args) throws Exception{
		
		FileOutputStream fos = new FileOutputStream("emails.txt", false);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		Set<InternetAddress> sets = new HashSet<>();
		for (int i = 12; i < 24; i++) {

			List<InternetAddress> tos = new URLEmailSniffer(
					"http://bbs.tianya.cn/post-english-220021-" + i + ".shtml")
					.getEmails();
			
			for (InternetAddress ia : tos) {
				sets.add(ia);
			}
			System.out.println(i);
		}
		
		for (InternetAddress ia : sets) {
			bw.write(ia.getAddress().toLowerCase());
			bw.newLine();
		}
		
		bw.close();
	}
}
