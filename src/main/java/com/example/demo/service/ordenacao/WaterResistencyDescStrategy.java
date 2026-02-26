package com.example.demo.service.ordenacao;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class WaterResistencyDescStrategy implements RelogioOrdenar{
    @Override
    public Sort ordenar() {
        return Sort.by(Sort.Direction.DESC,"resistenciaAguaM");
    }

    @Override
    public boolean suporta(String ordenacao) {
        return ordenacao.equalsIgnoreCase("wr_desc");
    }
}
