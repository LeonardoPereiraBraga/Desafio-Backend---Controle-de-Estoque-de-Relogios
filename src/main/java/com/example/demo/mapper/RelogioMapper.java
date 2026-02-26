package com.example.demo.mapper;

import com.example.demo.dto.RelogioDto;
import com.example.demo.entity.Relogio;
import com.example.demo.entity.enums.MaterialCaixa;
import com.example.demo.entity.enums.TipoMovimento;
import com.example.demo.entity.enums.TipoVidro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelogioMapper {
    public Relogio toRelogio(RelogioDto relogioDto){
        String etiquetaResistencia = calcularEtiquetaResistenciaAgua(relogioDto.getResistenciaAguaM());
        int pontuacaoColecionador = calcularPontuacaoColecionador(relogioDto);
        Relogio relogio = Relogio.builder()
                .marca(relogioDto.getMarca())
                .modelo(relogioDto.getModelo())
                .referencia(relogioDto.getReferencia())
                .tipoMovimento(relogioDto.getTipoMovimento())
                .materialCaixa(relogioDto.getMaterialCaixa())
                .tipoVidro(relogioDto.getTipoVidro())
                .resistenciaAguaM(relogioDto.getResistenciaAguaM())
                .diametroMm(relogioDto.getDiametroMm())
                .lugToLugMm(relogioDto.getLugToLugMm())
                .espessuraMm(relogioDto.getEspessuraMm())
                .larguraLugMm(relogioDto.getLarguraLugMm())
                .precoEmCentavos(relogioDto.getPrecoEmCentavos())
                .urlImagem(relogioDto.getUrlImagem())
                .etiquetaResistenciaAgua(etiquetaResistencia)
                .pontuacaoColecionador(pontuacaoColecionador)
                .build();
        return relogio;

    }
    public List<Relogio> toRelogioList(List<RelogioDto> relogioDtos){
        List<Relogio> relogioList = relogioDtos.stream().map(relogioDto -> toRelogio(relogioDto)).toList();
        return relogioList;
    }

    public String calcularEtiquetaResistenciaAgua(Integer resistenciaAgua){
        if (resistenciaAgua < 50){
            return "respingos";
        }
        if(resistenciaAgua >= 50 && resistenciaAgua  <= 99){
            return "uso_diario";
        }
        if(resistenciaAgua >= 100 && resistenciaAgua <= 199){
            return "natacao";
        }
        if(resistenciaAgua >= 200){
            return "mergulho";
        }
        return null;
    }

    public int calcularPontuacaoColecionador(RelogioDto relogioDto){
        int pontuacaoColecionador = 0;
        if(relogioDto.getTipoVidro() == TipoVidro.SAPPHIRE){
            pontuacaoColecionador+=25;
        }
        if (relogioDto.getResistenciaAguaM() >= 100){
            pontuacaoColecionador+=15;
        }
        if (relogioDto.getResistenciaAguaM() >= 200){
            pontuacaoColecionador+=10;
        }
        if(relogioDto.getTipoMovimento() == TipoMovimento.AUTOMATIC){
            pontuacaoColecionador+=20;
        }
        if(relogioDto.getMaterialCaixa() == MaterialCaixa.STEEL){
            pontuacaoColecionador+=10;
        }
        if(relogioDto.getMaterialCaixa() == MaterialCaixa.TITANIUM){
            pontuacaoColecionador+=12;
        }
        if(relogioDto.getDiametroMm() >= 38 && relogioDto.getDiametroMm() <= 42){
            pontuacaoColecionador+=8;
        }
        return pontuacaoColecionador;
    }
}
