package tests;

import edu.sdsu.email.EmailContent;
import edu.sdsu.email.EmailSender;
import edu.sdsu.observer.MailObserver;
import edu.sdsu.observer.ObserverFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;


@RunWith(PowerMockRunner.class)
@PrepareForTest(MailObserver.class)
public class MailObserverTest {
    @Test
    public void testUpdate() throws Exception {
        EmailSender emailSender = mock(EmailSender.class);
        whenNew(EmailSender.class).withNoArguments().thenReturn(emailSender);

        EmailContent emailContent = mock(EmailContent.class);
        whenNew(EmailContent.class).withAnyArguments().thenReturn(emailContent);

        MailObserver observer = new MailObserver();
        observer.update(null, "Some message");

        Mockito.verify(emailSender).send(emailContent);
    }

    @Test
    public void testToString() {
        MailObserver mailObserver = new MailObserver();
        assertEquals(ObserverFactory.MAIL, mailObserver.toString());
    }
}