package com.movieflix.movieflix.controller.docs;

import com.movieflix.movieflix.controller.request.LoginRequest;
import com.movieflix.movieflix.controller.request.UserRequest;
import com.movieflix.movieflix.controller.response.LoginResponse;
import com.movieflix.movieflix.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface AuthControllerDocs {

    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário na plataforma.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    ResponseEntity<UserResponse> registerUser(@RequestBody(description = "Dados do usuário para cadastro", required = true, content = @Content(schema = @Schema(implementation = UserRequest.class))) UserRequest userRequest);

    @Operation(summary = "Login do usuário", description = "Autentica o usuário e retorna um token JWT.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
        @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos", content = @Content)
    })
    ResponseEntity<LoginResponse> login(@RequestBody(description = "Credenciais de login", required = true, content = @Content(schema = @Schema(implementation = LoginRequest.class))) LoginRequest loginRequest);
}

