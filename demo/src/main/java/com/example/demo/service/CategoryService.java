package com.example.demo.service;


import com.example.demo.model.Category;
import com.example.demo.respository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }


    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }


    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }


    @Transactional
    public Optional<Category> update(Long id, Category category) {
        return categoryRepository.findById(id)
            .map(existingCategory -> {
                existingCategory.setName(category.getName());
                existingCategory.setDescription(category.getDescription());
                existingCategory.setIsActive(category.getIsActive());
                return categoryRepository.save(existingCategory);
            });
    }
}
