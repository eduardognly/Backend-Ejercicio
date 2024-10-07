package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    @Transactional
    public Optional<Product> update(Long id, Product product) {
        return productRepository.findById(id)
            .map(existingProduct -> {
                existingProduct.setName(product.getName());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setStock(product.getStock());
                existingProduct.setIsActive(product.getIsActive());
                existingProduct.setCategory(product.getCategory());
                return productRepository.save(existingProduct);
            });
    }
}
