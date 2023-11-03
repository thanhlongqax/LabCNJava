package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.w3c.dom.Text;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppicationConfigure.class);
        TextEditor textEditor = context.getBean(TextEditor.class);
        String content = "This is content";
        try {
            textEditor.input(content);
            textEditor.save("output.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
