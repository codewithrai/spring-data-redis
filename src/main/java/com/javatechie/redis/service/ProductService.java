package com.javatechie.redis.service;

import com.javatechie.redis.entity.Product;
import com.javatechie.redis.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Cacheable(key = "#id", value = "Product", unless = "#result.price > 100000")
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    public String deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }
}
