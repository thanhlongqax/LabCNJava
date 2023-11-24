package com.example.Lab09.service;

import com.example.Lab09.model.Product;
import com.example.Lab09.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplement implements ProductService{
    @Autowired
    ProductRepository repository;
    @Override
    public Iterable<Product> getAllProducts(){
        return repository.findAll();
    }
    @Override
    public Product getProduct(long id) throws Exception{
        return repository.findById(id).get();
    }
    @Override
    public Product save(Product product){
        return repository.save(product);
    }
    @Override
    public void removeById(Long id){
        repository.deleteById(id);
    }
}
