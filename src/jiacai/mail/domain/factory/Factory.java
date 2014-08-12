package jiacai.mail.domain.factory;

import jiacai.mail.domain.Domain;
import jiacai.mail.domain.DomainType;
import jiacai.mail.domain.Gmail;
import jiacai.mail.domain.QQ;
import jiacai.mail.domain.Sina;
import jiacai.mail.domain.NetEase;

public class Factory {
	public static Domain getDomain(String s) {
		Domain domain = null;
		
		switch (DomainType.valueOf(s.toUpperCase())) {
		case GMAIL:
			domain = new Gmail();
			break;
		case NETEASE:
			domain = new NetEase();
			break;
		case SINA:
			domain = new Sina();
			break;
		case QQ:
			domain = new QQ();
			break;
			
		default:
			System.out.println("本系统目前不支持你的邮箱类型");
			System.exit(-1);
			break;
		}
		return domain;
	}
}
