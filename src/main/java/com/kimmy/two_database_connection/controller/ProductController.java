package com.kimmy.two_database_connection.controller;

import com.kimmy.two_database_connection.model.product.Product;
import com.kimmy.two_database_connection.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public Mono<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/products/{id}")
    public Mono<Product> findUser(@PathVariable int id) {
        return productService.findProduct(id);
    }
}
