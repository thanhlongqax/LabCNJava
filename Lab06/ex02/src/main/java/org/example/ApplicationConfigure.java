package org.example;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Configurable
public class ApplicationConfigure {

    @Bean(name =  "productIphone")
    @Scope("prototype")
    public Product product1(){
        return new Product("1","Thanhlong" ,1300, "This is the latest phone");
    }


    @Bean(name = "productSamsung")
    @Scope("singleton")
    public Product product2(){
        return new Product("1","Thanhlong" ,1300, "This is the latest phone");
    }
}
