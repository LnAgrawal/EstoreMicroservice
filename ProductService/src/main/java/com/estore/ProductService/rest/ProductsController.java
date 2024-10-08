package com.estore.ProductService.rest;

import com.estore.ProductService.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {


    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductsController(Environment env, CommandGateway commandGateway){
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel){
        CreateProductCommand createProductCommand =CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString()).build();
        String returnValue = "";
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        }catch (Exception e) {
            System.out.println("Exception: "+ e);
        }
        return returnValue;
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
