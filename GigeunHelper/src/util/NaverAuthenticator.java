package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class NaverAuthenticator extends Authenticator{
	
	public PasswordAuthentication passwordAuthentication;
	
	public NaverAuthenticator() {
		
		try {
			String path =
					NaverAuthenticator
				.class
				.getResource("../prop/naver_mail.properties")
				.getPath();
			
			Properties prop = new Properties();
			prop.load(new FileReader(path));
			
			String auth  = prop.getProperty("auth");
			String pass  = prop.getProperty("pass");
			
			passwordAuthentication
				= new PasswordAuthentication(auth, pass);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return passwordAuthentication;
	}
	
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host","smtp.naver.com");
		p.put("mail.smtp.port",587);
		p.put("mail.smtp.auth","true");
		return p;
	}
	

}
