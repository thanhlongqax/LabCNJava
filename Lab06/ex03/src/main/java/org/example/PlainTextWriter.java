package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PlainTextWriter implements TextWriter{
    public void write(String content, String fileName) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        printWriter.println(content);
        printWriter.close();
    }
}
