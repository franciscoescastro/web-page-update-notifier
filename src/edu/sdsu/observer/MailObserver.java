package edu.sdsu.observer;

import edu.sdsu.email.EmailContent;
import edu.sdsu.email.EmailSender;

import java.util.Observable;
import java.util.Observer;


public class MailObserver implements Observer {
    @Override
    public void update(Observable observable, Object arg) {
        EmailContent emailContent = new EmailContent("Subject: Web page modified!", arg.toString(), "mail@mail.com");
        EmailSender email = new EmailSender();
        email.send(emailContent);
    }

    @Override
    public String toString() {
        return ObserverFactory.MAIL;
    }
}