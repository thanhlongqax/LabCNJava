package org.example;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Configurable
public class AppicationConfigure {

    @Bean(name = "PlainTextWriter")
    @Scope("singleton")
    public PlainTextWriter plainTextWriter(){
        return new PlainTextWriter();
    }

    @Bean(name = "PdfTextWriter")
    @Scope("singleton")
    public PdfTextWriter pdfTextWriter(){
        return new PdfTextWriter();
    }

    @Bean(name = "TextEditor")
    @Scope("singleton")
    public TextEditor textEditor(){
        return new TextEditor();
    }
}
