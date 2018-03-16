package edu.sdsu.factory;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class URLMockFactory extends URLFactory {
    @Override
    public URL makeURL(String urlAddress) {
        URL url = mock(URL.class);
        HttpURLConnection httpURLConnection = mock(HttpURLConnection.class);

        try {
            when(url.getProtocol()).thenReturn("http://");
            when(url.getHost()).thenReturn("www.fakeadress.com");
            when(url.getPath()).thenReturn("/");
            when(url.openConnection()).thenReturn(httpURLConnection);
            long lastModified = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
            when(httpURLConnection.getLastModified()).thenReturn(lastModified);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }
}