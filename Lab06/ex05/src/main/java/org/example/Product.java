package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@PropertySources(
        @PropertySource("classpath:application.properties")
)
@Component
public class Product {
    @Value("${vn.edu.tdtu.product.name}")
    private String name;
    @Value("#{new Double('${vn.edu.tdtu.product.price}')}")
    private double price;
    @Value("${vn.edu.tdtu.product.description}")
    private String description;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
