package com.example.Lab09.service;

import com.example.Lab09.model.OrderProduct;
import com.example.Lab09.model.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrderProductService {

    OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);
    List<Product> getProductsByOrderId(Long orderId);
    void deleteOrderProductsByOrderId(Long orderId);
    void deleteOrderProductsByProductId(Long orderId);
}
