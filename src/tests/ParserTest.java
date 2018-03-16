package tests;

import edu.sdsu.factory.URLFactory;
import edu.sdsu.file.Parser;
import edu.sdsu.observer.TranscriptObserver;
import edu.sdsu.observer.WebObservable;
import org.junit.Test;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ParserTest {
    @Test
    public void testParse() {
        Parser parser = new Parser("http://www.eli.sdsu.edu/courses/spring15/cs635/notes/index.html transcript");
        List<WebObservable> webObservableList = parser.parse();
        assertEquals(1, webObservableList.size());

        URLFactory urlFactory = new URLFactory();
        URL eliUrl = urlFactory.makeURL("http://www.eli.sdsu.edu/courses/spring15/cs635/notes/index.html");
        WebObservable eliObservable = new WebObservable(eliUrl);
        eliObservable.addObserver(new TranscriptObserver());
        assertEquals(eliObservable.toString(), webObservableList.get(0).toString());
    }

    @Test
    public void testParseInvalidText() {
        StringBuilder textBuilder = new StringBuilder();
        Parser parser = new Parser(textBuilder.toString());
        try {
            parser.parse();
        } catch (Exception e) {
            assertEquals(new InvalidParameterException().getMessage(), e.getMessage());
        }
    }
}