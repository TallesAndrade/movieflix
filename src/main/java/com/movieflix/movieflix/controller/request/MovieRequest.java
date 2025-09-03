package com.movieflix.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "Requisição para cadastro ou atualização de filme")
public record MovieRequest(
        @Schema(description = "Título do filme", example = "Matrix", required = true)
        @NotBlank(message = "Titulo do filme é obrigatorio")
        String title,
        @Schema(description = "Descrição do filme", example = "Um hacker descobre a verdade sobre sua realidade.")
        String description,
        @Schema(description = "Data de lançamento do filme", example = "31/03/1999", type = "string", pattern = "dd/MM/yyyy")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        @Schema(description = "Nota do filme", example = "8.7")
        double rating,
        @Schema(description = "IDs das categorias do filme", example = "[1,2,3]")
        List<Long> categories,
        @Schema(description = "IDs dos streamings do filme", example = "[1,2]")
        List<Long> streamings) {
}
