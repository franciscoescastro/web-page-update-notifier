package edu.sdsu.factory;

import java.net.MalformedURLException;
import java.net.URL;

public class URLFactory {
    public URL makeURL(String urlAddress) {
        URL url = null;
        try {
            url = new URL(urlAddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}