package com.movieflix.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Schema(description = "Requisição para cadastro de streaming")
@Builder
public record StreamingRequest(
        @Schema(description = "Nome do serviço de streaming", example = "Netflix", required = true)
        @NotBlank(message = "Nome do streaming é obrigatorio")
        String name) {
}
