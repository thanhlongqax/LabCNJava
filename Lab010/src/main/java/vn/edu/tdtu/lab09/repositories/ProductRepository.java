package vn.edu.tdtu.lab09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.lab09.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
