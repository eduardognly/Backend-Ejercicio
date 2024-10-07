package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "categories")
@Data
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "is_active")
    private Boolean isActive = true; // Valor por defecto
    
    @JsonIgnoreProperties({"category", "hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
