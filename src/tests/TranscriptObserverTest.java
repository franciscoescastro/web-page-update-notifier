package tests;

import edu.sdsu.observer.ObserverFactory;
import edu.sdsu.observer.TranscriptObserver;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

public class TranscriptObserverTest {
    @Test
    public void testUpdate() throws Exception {
        PrintStream backup = System.out;
        PrintStream printStreamMock = mock(PrintStream.class);

        System.setOut(printStreamMock);
        TranscriptObserver transcriptObserver = mock(TranscriptObserver.class);
        whenNew(TranscriptObserver.class).withNoArguments().thenReturn(transcriptObserver);
        TranscriptObserver observer = new TranscriptObserver();
        observer.update(null, "Some message");

        Mockito.verify(printStreamMock).println("Some message");
        System.setOut(backup);
    }

    @Test
    public void testToString() {
        TranscriptObserver transcriptObserver = new TranscriptObserver();
        assertEquals(ObserverFactory.TRANSCRIPT, transcriptObserver.toString());
    }
}