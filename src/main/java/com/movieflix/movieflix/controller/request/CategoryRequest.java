package com.movieflix.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Requisição para cadastro de categoria")
public record CategoryRequest(
        @Schema(description = "Nome da categoria", example = "Ação", required = true)
        @NotBlank(message = "Nome da categoria é obrigatorio")
        String name) {
}
