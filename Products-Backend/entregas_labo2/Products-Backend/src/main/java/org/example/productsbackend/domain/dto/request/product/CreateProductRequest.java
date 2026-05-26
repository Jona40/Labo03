package org.example.productsbackend.domain.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.productsbackend.common.Category;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateProductRequest {
    @NotBlank(message = "Product name cannot be empty.")
    private String name;
    private Category category;

    @NotNull(message = "Price cannot be empty.")
    @Positive(message = "Price cannot be negative.")
    private Double price;

    @NotNull(message = "It must be specified whether the product is available.")
    private Boolean available;
}
