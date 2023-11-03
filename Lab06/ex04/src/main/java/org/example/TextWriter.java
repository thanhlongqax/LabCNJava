package org.example;

import java.io.IOException;

public interface TextWriter {
    public void write(String content, String fileName) throws IOException;
}
