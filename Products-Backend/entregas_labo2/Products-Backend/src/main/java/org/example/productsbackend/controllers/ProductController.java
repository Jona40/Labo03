package org.example.productsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.productsbackend.domain.dto.request.product.CreateProductRequest;
import org.example.productsbackend.domain.entities.Product;
import org.example.productsbackend.services.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid CreateProductRequest product) {
        productService.createProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable UUID id,
            @RequestBody Product product
    ) {
        productService.updateProduct(id, product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.deleteProduct(id));
    }
}
