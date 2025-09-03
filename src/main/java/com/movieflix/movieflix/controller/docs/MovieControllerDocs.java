package com.movieflix.movieflix.controller.docs;

import com.movieflix.movieflix.controller.request.MovieRequest;
import com.movieflix.movieflix.controller.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieControllerDocs {

    @Operation(summary = "Cadastrar novo filme", description = "Cria um novo filme.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Filme criado com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    ResponseEntity<MovieResponse> saveMovie(@RequestBody(description = "Dados do filme", required = true, content = @Content(schema = @Schema(implementation = MovieRequest.class))) MovieRequest movieRequest);

    @Operation(summary = "Listar todos os filmes", description = "Retorna uma lista de todos os filmes cadastrados.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<List<MovieResponse>> getAllMovies();

    @Operation(summary = "Buscar filme por ID", description = "Retorna um filme pelo seu ID.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Filme encontrado", content = @Content(schema = @Schema(implementation = MovieResponse.class))),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    ResponseEntity<MovieResponse> getMovie(@PathVariable Long id);

    @Operation(summary = "Deletar filme por ID", description = "Remove um filme pelo seu ID.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Filme removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    ResponseEntity<Void> deleteMovie(@PathVariable Long id);

    @Operation(summary = "Atualizar filme por ID", description = "Atualiza os dados de um filme pelo seu ID.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class))),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content)
    })
    ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @RequestBody(description = "Dados do filme para atualização", required = true, content = @Content(schema = @Schema(implementation = MovieRequest.class))) MovieRequest movieRequest);

    @Operation(summary = "Buscar filmes por categoria", description = "Retorna uma lista de filmes filtrados por categoria.",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<List<MovieResponse>> getAllMoviesByCategory(@RequestParam Long category);
}
