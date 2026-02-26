package com.example.demo.service.ordenacao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelogioOrdenador {
    private final List<RelogioOrdenar> strategies;

    public Sort resolver(String ordenacao){
        if (ordenacao == null){
            return Sort.unsorted();
        }
        return strategies.stream()
                .filter(s -> s.suporta(ordenacao))
                .findFirst()
                .orElseThrow()
                .ordenar();
    }
}
