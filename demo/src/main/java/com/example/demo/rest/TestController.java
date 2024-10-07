package com.example.demo.rest;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


    @RestController
    @RequestMapping("/api")
    public class TestController {


        @GetMapping("/test")
        public Map<String, String> test() {
            Map<String, String> response = new HashMap<>();
            response.put("status", "OK");
            response.put("message", "La aplicación está funcionando correctamente");
            return response;
        }
    }
