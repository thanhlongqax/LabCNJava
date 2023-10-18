package org.example.Repository;
import org.example.Models.*;
import java.util.List;

public interface ProductRepository {
    public boolean add(Product product);
    public boolean delete(Product product);
    public Product getById(Long id);
    public boolean update(Product product);
    public List<Product> getAllProduct();
}