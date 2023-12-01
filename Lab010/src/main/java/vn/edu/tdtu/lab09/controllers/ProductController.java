package vn.edu.tdtu.lab09.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.lab09.dto.ResDTO;
import vn.edu.tdtu.lab09.models.Product;
import vn.edu.tdtu.lab09.services.ProductService;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(
                new ResDTO(
                        "Products fetched successfully",
                        true,
                        productService.getAllProducts()
                )
        );
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(
                new ResDTO(
                        "Product added successfully",
                        true,
                        productService.addProduct(product).getId()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id){
        return ResponseEntity.ok(
                new ResDTO(
                        "Product fetched successfully",
                        true,
                        productService.getProductById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putProduct(@PathVariable long id, @RequestBody Product product){
        product.setId(id);
        productService.putProduct(product);
        return ResponseEntity.ok(
                new ResDTO(
                        "Put product success",
                        true,
                        product
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProduct(@PathVariable long id, @RequestBody Product product){
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.ok(
                new ResDTO(
                        "Update product success",
                        true,
                        product
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(
                new ResDTO(
                        "Product deleted successfully",
                        true,
                        null
                )
        );
    }
}
