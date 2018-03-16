package edu.sdsu.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private String path;
    private String text;

    public Writer(String path, String text) {
        this.path = path;
        this.text = text;
    }

    public void writeFile() {
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(text.trim());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}