package edu.sdsu.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private StringBuilder text;

    public void readFile(String path) {
        text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                text.append(currentLine.trim()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return text.toString().trim();
    }
}