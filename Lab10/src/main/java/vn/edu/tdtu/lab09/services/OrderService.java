package vn.edu.tdtu.lab09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.lab09.models.Order;
import vn.edu.tdtu.lab09.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public List<Order> getAllOrders(){
        return repository.findAll();
    }

    public Order addOrder(Order order){
        return repository.save(order);
    }

    public Order getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public void updateOrder(Order order){
        repository.findById(order.getOrderNumber()).ifPresent(ord -> {
            ord.setTotal(order.getTotal());
            ord.setProducts(order.getProducts());

            repository.save(ord);
        });
    }

    public void deleteOrder(long id){
        repository.findById(id).ifPresent(repository::delete);
    }
}
