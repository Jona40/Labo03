package org.example.productsbackend.common.mapper;

import lombok.Builder;
import lombok.Data;
import org.example.productsbackend.domain.dto.request.product.CreateProductRequest;
import org.example.productsbackend.domain.dto.response.ProductResponse;
import org.example.productsbackend.domain.entities.Product;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder

public class ProductMapper {
    public Product toEntityCreate(CreateProductRequest createProductRequest) {
        return Product.builder()
                .name(createProductRequest.getName())
                .category(createProductRequest.getCategory())
                .price(createProductRequest.getPrice())
                .available(createProductRequest.getAvailable())
                .build();
    }
}

public ProductResponse toDto(Product product) {
    return ProductResponse.builder()
            .name(product.getName())
            .price(product.getPrice())
            .available(product.getAvailable())
            .build();
}