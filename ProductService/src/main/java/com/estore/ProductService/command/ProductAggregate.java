package com.estore.ProductService.command;

import com.estore.ProductService.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate(){

    }

    /**
     * Command handler to validate the product command and dispatch the product event
     * @param createProductCommand
     */
    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){
        //validate create product command

        if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Price cannot be less or equal than zero");
        }
        if(createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()){
            throw new IllegalArgumentException("Title cannot be empty");
        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);
    }

    /**
     * Used to update the Aggregate state
     * @param productCreatedEvent
     */
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.productId = productCreatedEvent.getProductId();
        this.price = productCreatedEvent.getPrice();
        this.title = productCreatedEvent.getTitle();
        this.quantity = productCreatedEvent.getQuantity();
    }
}
