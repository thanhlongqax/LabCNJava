package org.example;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hello world!
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
   //id, name, price and description.
    private String id;
    private  String name;
    private  double price;
    private  String description;
}
