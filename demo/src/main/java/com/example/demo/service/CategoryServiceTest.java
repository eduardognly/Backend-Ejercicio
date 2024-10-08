package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.respository.CategoryRepository;
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


class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;


    @InjectMocks
    private CategoryService categoryService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findAll() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);


        List<Category> result = categoryService.findAll();


        assertEquals(2, result.size());
        verify(categoryRepository).findAll();
    }


    @Test
    void findById() {
        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));


        Optional<Category> result = categoryService.findById(1L);


        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }


    @Test
    void save() {
        Category category = new Category();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);


        Category result = categoryService.save(category);


        assertNotNull(result);
        verify(categoryRepository).save(category);
    }
}


