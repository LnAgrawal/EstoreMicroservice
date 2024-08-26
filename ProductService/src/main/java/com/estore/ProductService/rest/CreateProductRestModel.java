package com.estore.ProductService.rest;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @name LN Agrawal
 */
@Data
public class CreateProductRestModel {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
