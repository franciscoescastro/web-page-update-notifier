package tests;

import edu.sdsu.factory.URLFactory;
import edu.sdsu.factory.URLMockFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(URLMockFactory.class)
public class URLFactoryTest {
    @Test
    public void testMakeURL() throws IOException {
        String urlAddress = "http://localhost/whatever.html";
        URLFactory urlFactory = new URLFactory();
        URL url = urlFactory.makeURL(urlAddress);
        assertNotNull(url);
    }

    @Test
    public void testMakeURLMock() throws IOException {
        String urlAddress = "";
        URLFactory urlFactory = new URLMockFactory();
        URL url = urlFactory.makeURL(urlAddress);
        assertNotNull(url);

        URLConnection urlConnection = url.openConnection();
        long expectedDateTime = System.currentTimeMillis();
        assertTrue(expectedDateTime < urlConnection.getLastModified());
    }
}