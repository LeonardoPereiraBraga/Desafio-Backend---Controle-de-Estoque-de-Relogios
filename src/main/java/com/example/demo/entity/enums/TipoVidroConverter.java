package com.example.demo.entity.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//Componente que faz o @ReqParams ficar case insensitive
// Para transformacao de JsonBody funciona colocar as regras no Enum
//Mas para ReqParam precisa criar um Converter so para isso
@Component
public class TipoVidroConverter implements Converter<String, TipoVidro> {

    @Override
    public TipoVidro convert(String source) {
        if (source == null || source.isBlank())
            return null;

        return TipoVidro.from(source); // reutiliza seu @JsonCreator
    }
}