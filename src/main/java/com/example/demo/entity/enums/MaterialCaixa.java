package com.example.demo.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MaterialCaixa {
    STEEL("steel"),
    TITANIUM("titanium"),
    RESIN("resin"),
    BRONZE("bronze"),
    CERAMIC("ceramic");

    private final String value;

    MaterialCaixa(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MaterialCaixa from(String value) {

        for (MaterialCaixa material : values()) {
            if (material.value.equalsIgnoreCase(value)) {
                return material;
            }
        }

        throw new IllegalArgumentException("MaterialCaixa inválido: " + value);
    }
}
