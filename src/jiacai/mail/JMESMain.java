package jiacai.mail;

import jiacai.mail.util.Configuration;

public class JMESMain {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration(System.getProperty("conf"));
		
		if (args.length < 1) {
			System.out.println("必须有一个邮件主题参数！");
			System.exit(0);
		}
		boolean debug = false;
		if (args.length == 2 && args[1].endsWith("d")) {
			debug = true;
		}
		JMES jmes = new JMES(conf,args[0],debug);
		jmes.send();
	}
}
