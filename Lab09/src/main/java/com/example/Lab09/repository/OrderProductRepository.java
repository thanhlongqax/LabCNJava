package com.example.Lab09.repository;

import com.example.Lab09.model.Order;
import com.example.Lab09.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findByOrderId(Long orderId);

    @Modifying
    @Query("DELETE FROM OrderProduct op WHERE op.order.id = :orderId")
    void deleteByOrderId(@Param("orderId") Long orderId);
    @Modifying
    @Query("DELETE FROM OrderProduct op WHERE op.product.id = :productId")
    void deleteByProductId(@Param("productId") Long productId);
}
