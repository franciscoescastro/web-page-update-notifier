package edu.sdsu.email;

import java.security.InvalidParameterException;

public class EmailContent {
    private String subject;
    private String message;
    private String recipient;

    public EmailContent() {
    }

    public EmailContent(String subject, String message, String recipient) {
        setSubject(subject);
        setMessage(message);
        setRecipient(recipient);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (isNullOrEmpty(subject)) {
            throw new InvalidParameterException();
        }

        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (isNullOrEmpty(subject)) {
            throw new InvalidParameterException();
        }

        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        if (isNullOrEmpty(subject)) {
            throw new InvalidParameterException();
        }

        this.recipient = recipient;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}