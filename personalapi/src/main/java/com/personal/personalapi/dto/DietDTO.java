package com.personal.personalapi.dto;

import com.personal.personalapi.enums.DietGoal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DietDTO {
    @NotBlank(message = "O nome da dieta é obrigatório")
    private String name;

    @NotBlank(message = "A descrição da dieta é obrigatória")
    private String description;

    @NotNull(message = "O objetivo da dieta é obrigatório")
    private DietGoal goal;

    @DecimalMin(value = "1.0", message = "As calorias diárias devem ser maiores que 0")
    private double dailyCalories;

    @DecimalMin(value = "0.0", inclusive = true, message = "A proteína não pode ser negativa")
    private double proteinGrams;

    @DecimalMin(value = "0.0", inclusive = true, message = "Os carboidratos não podem ser negativos")
    private double carbGrams;

    @DecimalMin(value = "0.0", inclusive = true, message = "As gorduras não podem ser negativas")
    private double fatGrams;

    @NotNull(message = "O userId é obrigatório")
    private Long userId;

}

