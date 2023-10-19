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

    @Cacheable(key = "#id", value = "Product")
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    public Product update(int id, Product request) throws Exception {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new Exception("Product not found");
        }
        return productRepository.save(request);
    }

    public String deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }
}
