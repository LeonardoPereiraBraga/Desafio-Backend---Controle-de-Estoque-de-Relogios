package com.example.demo.service.ordenacao;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PriceAscStrategy implements RelogioOrdenar{


    @Override
    public Sort ordenar() {
        return Sort.by(Sort.Direction.ASC,"precoEmCentavos");
    }

    @Override
    public boolean suporta(String ordenacao) {
        return ordenacao.equalsIgnoreCase("price_asc");
    }
}
