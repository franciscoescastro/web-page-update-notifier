package tests;

import edu.sdsu.email.EmailContent;
import edu.sdsu.email.EmailSender;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class EmailSenderTest {
    @Test
    public void testSend() {
        EmailSender email = new EmailSender();
        EmailContent emailContent = new EmailContent("Subject", "Message", "mail@mail.com");
        assertTrue(email.send(emailContent));

        emailContent.setRecipient("");
        assertFalse(email.send(emailContent));
    }
}