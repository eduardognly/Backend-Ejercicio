package com.example.venta.controller;




import com.example.venta.client.ProductClient;
import com.example.venta.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/api/venta")
public class VentaController {


    @Autowired
    private ProductClient productClient;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productClient.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
