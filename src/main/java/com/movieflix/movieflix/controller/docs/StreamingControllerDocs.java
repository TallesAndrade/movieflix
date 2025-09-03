package com.movieflix.movieflix.controller.docs;

import com.movieflix.movieflix.controller.request.StreamingRequest;
import com.movieflix.movieflix.controller.response.StreamingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StreamingControllerDocs {

    @Operation(summary = "Listar todos os streamings", description = "Retorna uma lista de todos os streamings cadastrados.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de streamings retornada com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    ResponseEntity<List<StreamingResponse>> getAllStreamings();

    @Operation(summary = "Cadastrar novo streaming", description = "Cria um novo streaming.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Streaming criado com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponse.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    ResponseEntity<StreamingResponse> saveStreaming(@RequestBody(description = "Dados do streaming", required = true, content = @Content(schema = @Schema(implementation = StreamingRequest.class))) StreamingRequest streamingRequest);

    @Operation(summary = "Buscar streaming por ID", description = "Retorna um streaming pelo seu ID.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Streaming encontrado", content = @Content(schema = @Schema(implementation = StreamingResponse.class))),
        @ApiResponse(responseCode = "404", description = "Streaming não encontrado", content = @Content)
    })
    ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id);

    @Operation(summary = "Deletar streaming por ID", description = "Remove um streaming pelo seu ID.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Streaming removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Streaming não encontrado", content = @Content)
    })
    ResponseEntity<Void> deleteCategoryById(@PathVariable Long id);
}
