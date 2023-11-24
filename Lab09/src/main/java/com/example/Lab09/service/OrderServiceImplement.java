package com.example.Lab09.service;

import com.example.Lab09.model.Order;
import com.example.Lab09.model.OrderProduct;
import com.example.Lab09.model.Product;
import com.example.Lab09.repository.OrderProductRepository;
import com.example.Lab09.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplement implements OrderService{
    @Autowired
    OrderRepository repository;
    @Override
    public Iterable<Order> getAllOrders(){
        return repository.findAll();
    }
    @Override
    public Order create(Order order){
        return repository.save(order);
    }
    @Override
    public void update(Order order){
        order.setDateCreated(LocalDate.now());
        repository.save(order);
    }
    @Override
    public Order getOrder(Long id){
        return repository.findById(id).get();
    }
    @Override
    public void removeById(Long id){
        repository.deleteById(id);
    }
}
