package com.example.demo.exception;

public class RelogioNaoEncontrado extends RuntimeException {
    public RelogioNaoEncontrado() {
        super("Relógio Nao Encontrado");
    }
    public RelogioNaoEncontrado(String message) {
        super(message);
    }
}
