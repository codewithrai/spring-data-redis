package com.javatechie.redis.service;

import com.javatechie.redis.entity.Product;
import com.javatechie.redis.exception.ResourceNotFoundException;
import com.javatechie.redis.repository.ProductRedisRepository;
import com.javatechie.redis.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRedisRepository productRedisRepository;
    private final ProductRepository productRepository;
    private static final String PRODUCT_NOT_FOUND = "Product not found.";

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Cacheable(key = "#id", value = "Product")
    public Product findProductById(int id) throws Exception {
        log.info("DB call by findProductById()");
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        return optional.get();
    }

    @CachePut(key = "#id", value = "Product")
    public Product update(int id, Product request) throws Exception {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        request.setId(id);
        return productRepository.save(request);
    }

    @CacheEvict(key = "#id", value = "Product")
    public String deleteProduct(int id) throws ResourceNotFoundException {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
        return "Product deleted";
    }
}
