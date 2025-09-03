package com.movieflix.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para cadastro de usuário")
public record UserRequest(
        @Schema(description = "Nome do usuário", example = "João Silva", required = true)
        String name,
        @Schema(description = "E-mail do usuário", example = "joao@email.com", required = true)
        String email,
        @Schema(description = "Senha do usuário", example = "123456", required = true)
        String password) {
}
