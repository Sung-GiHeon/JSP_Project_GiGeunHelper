package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthenticator extends Authenticator{
	
	PasswordAuthentication passAuth;
	
	public GoogleAuthenticator() {
		
		try {
			String path =
				GoogleAuthenticator
				.class
				.getResource("../prop/google_mail.properties")
				.getPath();
			
			Properties prop = new Properties();
			prop.load(new FileReader(path));
			
			String auth  = prop.getProperty("auth");
			String pass  = prop.getProperty("pass");
			
			passAuth = new PasswordAuthentication(auth, pass);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
	
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.host","smtp.gmail.com");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.port","587");
		return p;
	}
	
	

}
