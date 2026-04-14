package com.personal.personalapi.enums;

public enum DietGoal {

    WEIGHT_LOSS("Weight loss"),
    MUSCLE_GAIN("Muscle gain"),
    WEIGHT_MAINTENANCE("Weight maintenance"),
    HEALTH_IMPROVEMENT("Health improvement"),
    ENERGY_BOOST("Energy boost");

    private final String description;

    DietGoal(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DietGoal fromString(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value
                .trim()
                .toUpperCase()
                .replace(" ", "_");
        for (DietGoal goal : values()) {
            if (goal.name().equals(normalized)) {
                return goal;
            }
        }
        throw new IllegalArgumentException("Objetivo de dieta invalido: " + value);
    }
}
