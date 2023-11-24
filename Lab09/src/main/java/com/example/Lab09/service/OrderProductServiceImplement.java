package com.example.Lab09.service;

import com.example.Lab09.model.Product;
import com.example.Lab09.repository.OrderProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Lab09.model.OrderProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProductServiceImplement implements OrderProductService{
    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductServiceImplement(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }
    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
    @Override
    public List<Product> getProductsByOrderId(Long orderId) {
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderId);
        return orderProducts.stream().map(OrderProduct::getProduct).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void deleteOrderProductsByOrderId(Long orderId) {
        orderProductRepository.deleteByOrderId(orderId);
    }
    @Transactional
    @Override
    public void deleteOrderProductsByProductId(Long productId) {
        orderProductRepository.deleteByProductId(productId);
    }
}
