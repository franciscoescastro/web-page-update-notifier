package edu.sdsu.email;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private Session session;
    private Properties properties;
    private String email;
    private String password;

    public EmailSender() {
        this("<PUT A VALID EMAIL HERE>", "<PUT A VALID PASSWORD>");
    }

    public EmailSender(String email, String password) {
        setDefaultUser(email, password);
        setProperties();
        setSession();
    }

    private void setDefaultUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private void setProperties() {
        properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
    }

    private void setSession() {
        session = Session.getDefaultInstance(properties, null);
    }

    public boolean send(EmailContent emailContent) {
        try {
            Message message = createMessage(emailContent);
            sendMessage(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Message createMessage(EmailContent emailContent) throws MessagingException, AddressException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailContent.getRecipient()));
        message.setSubject(emailContent.getSubject());
        message.setText(emailContent.getMessage());
        return message;
    }

    private void sendMessage(Message message) throws NoSuchProviderException, MessagingException {
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", email, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}