package br.com.qualidadedesoftware.configuration;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email extends Suporte {
	public void enviaEmail(String[] toAddress, String subject, String anexo) {
		if (anexo.equalsIgnoreCase("") || anexo.isEmpty()) {
			logger.error("Falta anexar o pdf");
		}
		try {
			for (int i = 0; i < toAddress.length; i++) {
				Properties props = new Properties();
				props.put("mail.smtp.auth", prop.getProperty("prop.email.smtp.auth"));
				props.put("mail.smtp.starttls.enable", prop.getProperty("prop.email.smtp.starttls.enable"));
				props.put("mail.smtp.host", prop.getProperty("prop.email.smtp.host"));
				props.put("mail.smtp.port", prop.getProperty("prop.email.smtp.port"));

				Session s = Session.getInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(prop.getProperty("prop.email.remetente"), prop.getProperty("prop.email.senha"));
							}
						});

				MimeMessage message = new MimeMessage(s);

				// Estipula quem esta enviando
				InternetAddress from = new InternetAddress(prop.getProperty("prop.email.remetente"));
				message.setFrom(from);

				// Estipula para quem será enviado
				InternetAddress to = new InternetAddress(toAddress[i]);
				message.addRecipient(Message.RecipientType.TO, to);
				message.setSubject(subject);

				// Texto que estará no corpo da mensagem
				message.setText(subject);

				// Anexa o pdf ao e-mail
				MimeBodyPart mbp2 = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(anexo);
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(fds.getName());
				Multipart mp = new MimeMultipart();
				mp.addBodyPart(mbp2);
				message.setContent(mp);

				// Envia o e-mail
				Transport.send(message);
			}
		} catch (Exception e) {
			logger.error(e.fillInStackTrace());
		}
		logger.info("E-mail enviado com sucesso!");
	}
}
