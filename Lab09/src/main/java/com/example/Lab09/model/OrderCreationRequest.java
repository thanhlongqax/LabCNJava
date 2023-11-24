package com.example.Lab09.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Setter
@Getter
public class OrderCreationRequest {
    private String status;
    private List<OrderItem> orderItems;
}
