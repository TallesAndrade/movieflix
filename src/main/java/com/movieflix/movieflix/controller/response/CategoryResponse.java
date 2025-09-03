package com.movieflix.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Resposta com dados da categoria")
@Builder
public record CategoryResponse(
        @Schema(description = "ID da categoria", example = "1")
        Long id,
        @Schema(description = "Nome da categoria", example = "Ação")
        String name) {

}