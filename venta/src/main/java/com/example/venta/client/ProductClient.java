package com.example.venta.client;



import com.example.venta.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;


@FeignClient(name = "demo-service", url = "${demo.service.url}")
public interface ProductClient {
    @GetMapping("/api/products")
    List<Product> getAllProducts();
}


