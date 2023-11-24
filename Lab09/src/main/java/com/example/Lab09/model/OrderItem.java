package com.example.Lab09.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OrderItem {
    private Long productId;
    private int quantity;

}