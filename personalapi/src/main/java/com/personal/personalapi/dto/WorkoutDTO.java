package com.personal.personalapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkoutDTO {
    @NotBlank(message = "O nome do treino é obrigatório")
    private String name;

    @NotBlank(message = "A descrição do treino é obrigatória")
    private String description;

    @NotNull(message = "O userId é obrigatório")
    private Long userId;
}
