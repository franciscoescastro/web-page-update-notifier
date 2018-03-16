package edu.sdsu.observer;

import java.util.Observable;
import java.util.Observer;

public class TranscriptObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg.toString());
    }

    @Override
    public String toString() {
        return ObserverFactory.TRANSCRIPT;
    }
}