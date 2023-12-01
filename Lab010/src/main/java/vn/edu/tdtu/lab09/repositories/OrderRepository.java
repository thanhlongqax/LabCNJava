package vn.edu.tdtu.lab09.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.lab09.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
