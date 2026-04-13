package com.personal.personalapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExerciseDTO {
    @NotBlank(message = "O nome do exercício é obrigatório")
    private String name;

    @NotBlank(message = "A descrição do exercício é obrigatória")
    private String description;

    @Min(value = 1, message = "O número de séries deve ser no mínimo 1")
    private int sets;

    @Min(value = 1, message = "O número de repetições deve ser no mínimo 1")
    private int reps;

    @NotNull(message = "O workoutId é obrigatório")
    private Long workoutId;
}

