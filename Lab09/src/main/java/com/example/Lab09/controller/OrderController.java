package com.example.Lab09.controller;

import com.example.Lab09.model.*;
import com.example.Lab09.service.OrderProductService;
import com.example.Lab09.service.OrderService;
import com.example.Lab09.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    ProductService productService;
    @GetMapping("/orders")
    public Iterable<Order> getAllOrder(){
        return orderService.getAllOrders();
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<List<Product>> getProductsInOrder(@PathVariable Long id) {
        List<Product> productsInOrder = orderProductService.getProductsByOrderId(id);

        if (!productsInOrder.isEmpty()) {
            return new ResponseEntity<>(productsInOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        // Xóa các mục đơn hàng trong OrderProduct
        orderProductService.deleteOrderProductsByOrderId(id);

        // Sau đó xóa đơn hàng chính
        orderService.removeById(id);

        return ResponseEntity.noContent().build();
    }
    @PostMapping("/orders")
    public ResponseEntity<Void> createOrder(@RequestBody OrderCreationRequest orderCreationRequest) {
        Order order = new Order();
        order.setDateCreated(LocalDate.now());
        order.setStatus(orderCreationRequest.getStatus());

        Order createdOrder = orderService.create(order);

        for (OrderItem orderItem : orderCreationRequest.getOrderItems()) {
            try{
                Product product = productService.getProduct(orderItem.getProductId());
                OrderProduct orderProduct = new OrderProduct(createdOrder, product, orderItem.getQuantity());
                orderProductService.create(orderProduct);
            }
            catch (Exception e){

            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) throws Exception {
        Order updateOrder = orderService.getOrder(id);
        if (order.getStatus() != null) updateOrder.setStatus(order.getStatus());
        orderService.update(updateOrder);
        return new ResponseEntity(updateOrder, HttpStatus.OK);
    }
}
