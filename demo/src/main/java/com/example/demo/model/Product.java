package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "products")
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, length = 150)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "stock", nullable = false)
    private Integer stock;
    
    @Column(name = "sku", unique = true, length = 50)
    private String sku;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "is_active")
    private Boolean isActive = true; // Valor por defecto
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"products", "hibernateLazyInitializer", "handler"})
    private Category category;
    
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
