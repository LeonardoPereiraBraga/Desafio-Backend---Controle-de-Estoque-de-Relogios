package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestError {
    private String timestamp;
    private Integer status;
    private String erro;
    private String mensagem;
    private String caminho;
    private List<ErroCampo> errosDeCampo;
}
