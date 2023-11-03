package org.example;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("AppConfig.xml");

        Product beanWithProperties =(Product)  context.getBean("thanhlong1" , Product.class);

        Product beanWithConstructorArgs = (Product)  context.getBean("thanhlong2", Product.class);

        Product singletonBean = (Product) context.getBean("thanhlong3", Product.class);



        System.out.println("Bean with Properties: " + beanWithProperties.toString());
        System.out.println("Bean with Constructor Args: " + beanWithConstructorArgs.toString());
        System.out.println("Singleton Bean: " + singletonBean.toString());
        System.out.println("Are the first two beans prototypes? " + (beanWithProperties != beanWithConstructorArgs));

        context.close();

    }
}
