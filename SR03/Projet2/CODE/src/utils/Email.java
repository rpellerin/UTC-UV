package utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	public enum Protocol {
	    SMTP,
	    SMTPS,
	    TLS
	}
	
	private int port = 465;
	private String host = "ssl0.ovh.net";
	private String from = "contact@romainpellerin.eu";
	private boolean auth = true;
	private String username = "contact@romainpellerin.eu";
	private String password = "";
	private Protocol protocol = Protocol.SMTPS;
	private boolean debug = true;
	
	private String to;
	private String subject;
	private String body;
	
	public Email(String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	public boolean send() {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		switch(protocol) {
		case SMTPS:
	        props.put("mail.smtp.ssl.enable", true);
	        break;
	    case TLS:
		default:
			props.put("mail.smtp.ssl.enable", true);
			break;
		}
		
		
		Authenticator authenticator = null;
		if (auth) {
		    props.put("mail.smtp.auth", true);
		    authenticator = new Authenticator() {
		        private PasswordAuthentication pa = new PasswordAuthentication(username, password);
		        @Override
		        public PasswordAuthentication getPasswordAuthentication() {
		            return pa;
		        }
		    };
		}
		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);
		
		MimeMessage message = new MimeMessage(session);
		try {
			System.out.println("Sending out email");
		    message.setFrom(new InternetAddress(from));
		    InternetAddress[] address = {new InternetAddress(to)};
		    message.setRecipients(Message.RecipientType.TO, address);
		    message.setSubject(subject);
		    message.setSentDate(new Date());
		    message.setText(body);
		    Transport.send(message);
		} catch (MessagingException ex) {
		    ex.printStackTrace();
		}
		
		return true;
	}
}
