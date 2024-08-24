package com.estore.ProductService.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private Environment env;

    @PostMapping
    public String createProduct(){
        return "HTTP Post handled";
    }

    @GetMapping
    public String getProduct(){
        return "HTTP get handled, Port no.: "+ env.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduct(){
        return "HTTp Put handled";
    }

    @DeleteMapping
    public  String deleteProduct(){
        return "HTTP Delete handled";
    }

}
