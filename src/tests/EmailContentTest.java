package tests;

import edu.sdsu.email.EmailContent;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class EmailContentTest {
    private EmailContent emailContent;

    @Before
    public void setUp() {
        emailContent = new EmailContent();
    }

    @Test
    public void testSetSubject() {
        String value = null;
        try {
            emailContent.setSubject(value);
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }

        value = "";
        try {
            emailContent.setSubject(value);
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }
    }

    @Test
    public void testSetMessage() {
        String value = null;
        try {
            emailContent.setMessage(value);
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }

        value = "";
        try {
            emailContent.setMessage(value);
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }
    }

    @Test
    public void testSetRecipient() {
        String value = null;
        try {
            emailContent.setRecipient(value);
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }

        value = "";
        try {
            emailContent.setRecipient(value);
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }
    }
}