package edu.sdsu.observer;

import java.util.NoSuchElementException;
import java.util.Observer;


public class ObserverFactory {
    public static final String MAIL = "mail";
    public static final String TRANSCRIPT = "transcript";

    public Observer makeObserver(String id) {
        if (id.equals(MAIL)) {
            return new MailObserver();
        } else if (id.equals(TRANSCRIPT)) {
            return new TranscriptObserver();
        }

        throw new NoSuchElementException("Observer does not exist!");
    }
}