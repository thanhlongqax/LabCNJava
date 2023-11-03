package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

/**
 * Hello world!
 *
 */
@ComponentScan
public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        TextEditor textEditor = context.getBean(TextEditor.class);
        String content = "Spring is coming!";
        try {
            textEditor.input(content);
            textEditor.save("output.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
