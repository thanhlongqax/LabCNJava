package org.example;


import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Component("PdfTextWriter")
public class PdfTextWriter {
    public void write(String content, String fileName) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        printWriter.println("Printed as pdf form:");
        printWriter.println(content);
        printWriter.close();
    }
}
