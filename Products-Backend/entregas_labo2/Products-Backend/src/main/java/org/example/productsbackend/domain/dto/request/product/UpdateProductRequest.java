package org.example.productsbackend.domain.dto.request.product;

import org.example.productsbackend.common.Category;

public class UpdateProductRequest {
    private String name;
    private Category category;
    private Double price;
    private Boolean avaible;
}
