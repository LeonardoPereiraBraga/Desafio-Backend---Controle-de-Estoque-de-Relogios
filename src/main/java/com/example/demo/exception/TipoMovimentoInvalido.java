package com.example.demo.exception;

public class TipoMovimentoInvalido extends RuntimeException {
    public TipoMovimentoInvalido() {
        super("TipoMovimento Invalido");
    }
    public TipoMovimentoInvalido(String message) {
        super(message);
    }
}
