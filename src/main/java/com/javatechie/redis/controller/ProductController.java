package com.javatechie.redis.controller;

import com.javatechie.redis.entity.Product;
import com.javatechie.redis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
