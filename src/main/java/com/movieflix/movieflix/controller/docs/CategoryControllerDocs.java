package com.movieflix.movieflix.controller.docs;

import com.movieflix.movieflix.controller.request.CategoryRequest;
import com.movieflix.movieflix.controller.response.CategoryResponse;
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

public interface CategoryControllerDocs {

    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista de todas as categorias cadastradas.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso", content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    ResponseEntity<List<CategoryResponse>> getAllCategories();

    @Operation(summary = "Cadastrar nova categoria", description = "Cria uma nova categoria.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso", content = @Content(schema = @Schema(implementation = CategoryResponse.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    ResponseEntity<CategoryResponse> saveCategory(@RequestBody(description = "Dados da categoria", required = true, content = @Content(schema = @Schema(implementation = CategoryRequest.class))) CategoryRequest category);

    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria pelo seu ID.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada", content = @Content(schema = @Schema(implementation = CategoryResponse.class))),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id);

    @Operation(summary = "Deletar categoria por ID", description = "Remove uma categoria pelo seu ID.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
    })
    ResponseEntity<Void> deleteCategoryById(@PathVariable Long id);
}
