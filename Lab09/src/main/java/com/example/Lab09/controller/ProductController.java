package com.example.Lab09.controller;
import com.example.Lab09.model.Order;
import com.example.Lab09.model.Product;
import com.example.Lab09.service.OrderProductService;
import com.example.Lab09.service.OrderService;
import com.example.Lab09.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderProductService orderProductService;
    @GetMapping("/products")
    public Iterable<Product> getAll(){
        return productService.getAllProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) throws Exception{
        return productService.getProduct(id);
    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws Exception {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @PutMapping(value = {"/products/{id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws Exception {
        Product updateProduct = productService.getProduct(id);
        if (product.getName() != null) updateProduct.setName(product.getName());
        if (product.getPrice() != 0.0)  updateProduct.setPrice(product.getPrice());
        if (product.getBrand() != null) updateProduct.setBrand(product.getBrand());
        if (product.getColor() != null) updateProduct.setColor(product.getColor());
        productService.save(updateProduct);
        return new ResponseEntity(updateProduct, HttpStatus.OK);
    }
    @PatchMapping(value = {"/products/{id}"})
    public ResponseEntity<Product> modifyProduct(@PathVariable Long id, @RequestBody Product product) throws Exception {
        Product updateProduct = productService.getProduct(id);
        if (product.getName() != null) updateProduct.setName(product.getName());
        if (product.getPrice() != 0.0) updateProduct.setPrice(product.getPrice());
        if (product.getBrand() != null) updateProduct.setBrand(product.getBrand());
        if (product.getColor() != null) updateProduct.setColor(product.getColor());
        productService.save(updateProduct);
        return new ResponseEntity(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Xóa các mục OrderProduct liên quan đến Product có ID tương ứng
        orderProductService.deleteOrderProductsByProductId(id);

        // Sau đó xóa sản phẩm
        productService.removeById(id);

        return ResponseEntity.noContent().build();
    }
}
