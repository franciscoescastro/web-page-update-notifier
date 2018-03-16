package edu.sdsu.observer;

import edu.sdsu.file.Parser;
import edu.sdsu.file.Reader;
import edu.sdsu.file.Writer;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidParameterException;
import java.util.*;

public class WebObservable extends Observable implements Runnable {
    private URL url;
    private long currentDateTime;
    private boolean runningState;
    private List<String> observerList;
    private int periodInSeconds;

    public WebObservable(URL url) throws InvalidParameterException {
        this(url, 3600);
    }

    public WebObservable(URL url, int periodInSeconds) {
        setURL(url);
        this.currentDateTime = 0;
        this.runningState = false;
        this.observerList = new ArrayList<String>();
        this.periodInSeconds = periodInSeconds;
    }

    private void setURL(URL url) {
        if (url == null) {
            throw new InvalidParameterException();
        }

        this.url = url;
    }

    @Override
    public synchronized void addObserver(Observer observer) {
        super.addObserver(observer);
        observerList.add(observer.toString());
    }

    public boolean isRunning() {
        return runningState;
    }

    public void stop() {
        runningState = false;
    }

    private void updateCurrentDateTime() {
        long lastModified = getPageLastModified();
        if (lastModified > 0) {
            currentDateTime = lastModified;
        }
    }

    private long getPageLastModified() {
        try {
            URLConnection urlConnection = url.openConnection();

            return urlConnection.getLastModified();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private boolean hasDateTimeChanged(long previousDateTime) {
        return previousDateTime < currentDateTime;
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        runningState = true;
        createMemento();

        while (runningState) {
            long previousDateTime = currentDateTime;
            updateCurrentDateTime();
            if (hasDateTimeChanged(previousDateTime)) {
                createMemento();
                setChanged();
                notifyObservers(getHeaderMessage());
                clearChanged();
            }

            sleep(periodInSeconds);
        }
    }

    private String getHeaderMessage() {
        StringBuilder message = new StringBuilder();
        message.append(url.getHost()).append(url.getPath());
        message.append("\nLast modified: " + new Date(currentDateTime));
        message.append("\n");

        return message.toString();
    }

    public Memento createMemento() {
        String filePath = super.toString() + "Memento.txt";
        Memento memento = new Memento(filePath);
        Writer writer = new Writer(memento.getStatePath(), this.toString());
        writer.writeFile();

        return memento;
    }

    public void restoreState(Memento memento) {
        String[] stateSplit = getMementoInformation(memento);
        setFields(stateSplit);
        setObservers();
    }

    private String[] getMementoInformation(Memento memento) {
        Reader reader = new Reader();
        reader.readFile(memento.getStatePath());
        String[] stateSplit = reader.getText().split("-");

        return stateSplit;
    }

    private void setFields(String[] stateSplit) {
        Parser parser = new Parser(stateSplit[0]);
        WebObservable webObservable = parser.parse().get(0);
        this.url = webObservable.url;
        this.observerList = webObservable.observerList;
        this.deleteObservers();

        String[] split = stateSplit[1].split(" ");
        this.currentDateTime = Long.parseLong(split[0]);
        this.runningState = Boolean.parseBoolean(split[1]);
        this.periodInSeconds = Integer.parseInt(split[2]);
    }

    private void setObservers() {
        ObserverFactory observerFactory = new ObserverFactory();
        for (String observerName : observerList) {
            super.addObserver(observerFactory.makeObserver(observerName));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(url.getProtocol()).append("://");
        builder.append(url.getHost());
        builder.append(url.getPath()).append(" ");

        int i;
        for (i = 0; i < observerList.size() - 1; i++) {
            builder.append(observerList.get(i)).append(" ");
        }

        builder.append(observerList.get(i));
        builder.append("-");
        builder.append(currentDateTime).append(" ").append(runningState);
        builder.append(" ").append(periodInSeconds);

        return builder.toString();
    }
}