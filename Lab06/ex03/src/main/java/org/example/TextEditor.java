package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

public class TextEditor {

    private String text;
    @Autowired
    @Qualifier("PlainTextWriter")
    private TextWriter textWriter;
    public void save(String fileName) throws IOException {
        textWriter.write(text, fileName);
    }
    public void input(String content) {
        this.text = content;
    }
}
