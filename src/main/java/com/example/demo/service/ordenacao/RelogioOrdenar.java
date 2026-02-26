package com.example.demo.service.ordenacao;

import org.springframework.data.domain.Sort;

public interface RelogioOrdenar {
    Sort ordenar();
    boolean suporta(String ordenacao);
}
