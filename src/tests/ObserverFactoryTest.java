package tests;

import edu.sdsu.observer.MailObserver;
import edu.sdsu.observer.ObserverFactory;
import edu.sdsu.observer.TranscriptObserver;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Observer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ObserverFactoryTest {
    @Test
    public void testMakeObserver() {
        ObserverFactory observerFactory = new ObserverFactory();
        Observer observer = observerFactory.makeObserver(ObserverFactory.MAIL);
        assertTrue(observer instanceof MailObserver);

        observer = observerFactory.makeObserver(ObserverFactory.TRANSCRIPT);
        assertTrue(observer instanceof TranscriptObserver);

        String invalid = "invalidObserver";
        try {
            observerFactory.makeObserver(invalid);
        } catch (Exception e) {
            assertEquals(new NoSuchElementException("Observer does not exist!").getMessage(), e.getMessage());
        }
    }
}