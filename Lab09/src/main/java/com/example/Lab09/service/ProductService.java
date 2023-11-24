package com.example.Lab09.service;

import com.example.Lab09.model.Product;

public interface ProductService {
    Iterable<Product> getAllProducts();
    Product getProduct(long id) throws Exception;
    Product save(Product product);
    void removeById(Long id);
}
