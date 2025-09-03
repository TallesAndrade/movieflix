package com.movieflix.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição de login do usuário")
public record LoginRequest(
        @Schema(description = "E-mail do usuário", example = "usuario@email.com", required = true)
        String email,
        @Schema(description = "Senha do usuário", example = "123456", required = true)
        String password) {
}
