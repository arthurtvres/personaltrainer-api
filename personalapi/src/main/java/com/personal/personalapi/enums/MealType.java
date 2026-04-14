package com.personal.personalapi.enums;

public enum MealType {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack");

    private final String description;

    MealType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static MealType fromString(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value
                .trim()
                .toUpperCase()
                .replace(" ", "_");
        for (MealType mealType : values()) {
            if (mealType.name().equals(normalized)) {
                return mealType;
            }
        }
        throw new IllegalArgumentException("Tipo de refeicao invalido: " + value);
    }
}
