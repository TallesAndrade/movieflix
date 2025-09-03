package com.movieflix.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de login contendo o token JWT gerado")
public record LoginResponse(
        @Schema(description = "Token JWT de autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6...")
        String token) {
}
