package com.example.demo.dto;

import com.example.demo.entity.enums.MaterialCaixa;
import com.example.demo.entity.enums.TipoMovimento;
import com.example.demo.entity.enums.TipoVidro;
import org.springframework.web.bind.annotation.RequestParam;

public record RelogioSearchParams(
        int pageNo,
        int pageSize,
        String busca,
        Integer resistenciaMin,
        Integer resistenciaMax,
        TipoMovimento tipoMovimento,
        String ordenar,
        MaterialCaixa materialCaixa,
        TipoVidro tipoVidro,
        Integer precoMin,
        Integer precoMax,
        Integer diametroMin,
        Integer diametroMax,
        String marca
        ) {
}
