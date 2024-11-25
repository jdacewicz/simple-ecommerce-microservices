package dev.jakubdacewicz.product_service.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryUpdateRequest(

        @NotBlank
        @Size(max = 64)
        String name,

        @NotNull
        String description) {
}
