package tests;

import edu.sdsu.CareTaker;
import edu.sdsu.factory.URLFactory;
import edu.sdsu.factory.URLMockFactory;
import edu.sdsu.file.Reader;
import edu.sdsu.observer.Memento;
import edu.sdsu.observer.TranscriptObserver;
import edu.sdsu.observer.WebObservable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
public class WebObservableTest {
    private WebObservable itCoupleObservable;

    @Before
    public void setUp() {
        URLFactory urlFactory = new URLFactory();
        URL itUrl = urlFactory.makeURL("http://www.itcoupleblog.com/");
        itCoupleObservable = new WebObservable(itUrl);
        itCoupleObservable.addObserver(new TranscriptObserver());
    }

    @Test
    public void testIsRunning() throws InterruptedException {
        assertFalse(itCoupleObservable.isRunning());
        Thread thread = new Thread(itCoupleObservable);
        thread.start();
        Thread.sleep(1000);
        assertTrue(itCoupleObservable.isRunning());
        itCoupleObservable.stop();
    }

    @Test
    public void testStop() throws InterruptedException {
        Thread thread = new Thread(itCoupleObservable);
        thread.start();
        Thread.sleep(1000);
        itCoupleObservable.stop();
        assertFalse(itCoupleObservable.isRunning());
    }

    @Test
    @PrepareForTest(URLMockFactory.class)
    public void testCreateMemento() throws InterruptedException {
        String urlAddress = "";
        URLFactory urlFactory = new URLMockFactory();
        URL url = urlFactory.makeURL(urlAddress);
        WebObservable observable = new WebObservable(url);
        observable.addObserver(new TranscriptObserver());
        Thread thread = new Thread(observable);
        thread.start();
        Thread.sleep(2000);
        CareTaker careTaker = new CareTaker();
        careTaker.add(observable.createMemento());
        thread.interrupt();
        Memento memento = careTaker.get(0);
        Reader reader = new Reader();
        reader.readFile(memento.getStatePath());
        assertEquals(observable.toString(), reader.getText());
    }

    @Test
    public void testRestoreState() throws Exception {
        Thread thread = new Thread(itCoupleObservable);
        thread.start();
        Thread.sleep(2000);
        CareTaker careTaker = new CareTaker();
        careTaker.add(itCoupleObservable.createMemento());
        /* Simulate crash by interrupting thread and
         * create a new WebObserver then compare the
         * new observer with the oldObserver
         * */
        thread.interrupt();
        URLMockFactory urlMockFactory = new URLMockFactory();
        WebObservable restoredObservable = new WebObservable(urlMockFactory.makeURL("mock"));
        Memento memento = careTaker.get(0);
        restoredObservable.restoreState(memento);
        deleteFile(memento.getStatePath());
        assertEquals(itCoupleObservable.toString(), restoredObservable.toString());
    }

    private void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }

    @Test
    public void testToString() {
        assertEquals(itCoupleObservable.toString(), "http://www.itcoupleblog.com/ transcript-0 false 3600");
    }
}