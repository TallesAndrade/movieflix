package com.movieflix.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Resposta com dados do filme")
@Builder
public record MovieResponse(
        @Schema(description = "ID do filme", example = "1")
        Long id,
        @Schema(description = "Título do filme", example = "Matrix")
        String title,
        @Schema(description = "Descrição do filme", example = "Um hacker descobre a verdade sobre sua realidade.")
        String description,
        @Schema(description = "Data de lançamento do filme", example = "31/03/1999", type = "string", pattern = "dd/MM/yyyy")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        @Schema(description = "Nota do filme", example = "8.7")
        double rating,
        @Schema(description = "Categorias do filme")
        List<CategoryResponse> categories,
        @Schema(description = "Streamings do filme")
        List<StreamingResponse> streamings) {
}
