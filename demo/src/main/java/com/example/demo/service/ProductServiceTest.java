package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.respository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductServiceTest {


    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    private ProductService productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findAll() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);


        List<Product> result = productService.findAll();


        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }


    @Test
    void findById() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));


        Optional<Product> result = productService.findById(1L);


        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }


    @Test
    void save() {
        Product product = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(product);


        Product result = productService.save(product);


        assertNotNull(result);
        verify(productRepository).save(product);
    }
}
