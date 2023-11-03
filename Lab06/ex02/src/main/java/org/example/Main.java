package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigure.class);
        Product product1 = (Product) context.getBean("productIphone");
        Product product2 = (Product) context.getBean("productSamsung");

        System.out.println(product1);
        System.out.println(product2);

    }



}
