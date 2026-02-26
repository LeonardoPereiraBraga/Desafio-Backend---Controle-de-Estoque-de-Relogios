package com.example.demo.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoVidro {
    MINERAL("mineral"),
    SAPPHIRE("sapphire"),
    ACRYLIC("acrylic");

    private final String value;

    TipoVidro(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoVidro from(String value) {

        for (TipoVidro tipoVidro : values()) {
            if (tipoVidro.value.equalsIgnoreCase(value)) {
                return tipoVidro;
            }
        }

        throw new IllegalArgumentException("tipoMovimento inválido: " + value);
    }
}
