package com.example.demo.entity.enums;

import com.example.demo.exception.TipoMovimentoInvalido;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoMovimento {
    QUARTZ("quartz"),
    AUTOMATIC("automatic"),
    MANUAL("manual");

    private final String value;

    TipoMovimento(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoMovimento from(String value) {

        for (TipoMovimento tipoMovimento : values()) {
            if (tipoMovimento.value.equalsIgnoreCase(value)) {
                return tipoMovimento;
            }
        }

        throw new TipoMovimentoInvalido(value);
    }
}
