package edu.sdsu.file;

import edu.sdsu.factory.URLFactory;
import edu.sdsu.observer.ObserverFactory;
import edu.sdsu.observer.WebObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Parser {
    private String text;
    private URLFactory urlConnectionFactory;
    private ObserverFactory observerFactory;

    public Parser(String text) {
        this.text = text;
        observerFactory = new ObserverFactory();
        urlConnectionFactory = new URLFactory();
    }

    public List<WebObservable> parse() {
        String[] splitText = text.split("\n");

        List<WebObservable> webObservableList = new ArrayList<WebObservable>();
        for (int i = 0; i < splitText.length; i++) {
            WebObservable webObservable = retrieveObservable(splitText[i]);
            webObservableList.add(webObservable);
        }

        return webObservableList;
    }

    private WebObservable retrieveObservable(String line) {
        String[] splitLine = line.split(" ");

        WebObservable webObservable = new WebObservable(urlConnectionFactory.makeURL(splitLine[0]));
        for (int i = 1; i < splitLine.length; i++) {
            Observer observer = observerFactory.makeObserver(splitLine[i]);
            webObservable.addObserver(observer);
        }

        return webObservable;
    }
}