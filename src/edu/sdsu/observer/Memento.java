package edu.sdsu.observer;

import java.security.InvalidParameterException;

public class Memento {
    private String filePath;

    public Memento(String filePath) {
        setFilePath(filePath);
    }

    private void setFilePath(String filePath) {
        if (filePath == null || filePath.isEmpty() || filePath.length() < 5) {
            throw new InvalidParameterException();
        }

        this.filePath = filePath;
    }

    public String getStatePath() {
        return filePath;
    }
}