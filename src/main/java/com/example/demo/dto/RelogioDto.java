package com.example.demo.dto;

import com.example.demo.entity.enums.MaterialCaixa;
import com.example.demo.entity.enums.TipoMovimento;
import com.example.demo.entity.enums.TipoVidro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Data
@NoArgsConstructor
public class RelogioDto {

    @NotBlank(message = "Marca obrigatório")
    private String marca;
    @NotBlank(message = "Modelo obrigatório")
    private String modelo;
    @NotBlank(message = "Referencia obrigatório")
    private String referencia;

    @NotNull(message = "TipoMovimento obrigatório")
    private TipoMovimento tipoMovimento;

    @NotNull(message = "MaterialCaixa obrigatório")
    private MaterialCaixa materialCaixa;

    @NotNull(message = "TipoVidro obrigatório")
    private TipoVidro tipoVidro;

    @NotNull(message = "ResistenciaAgua obrigatório")
    private Integer resistenciaAguaM;

    @NotNull(message = "Diametro obrigatório")
    private Integer diametroMm;

    @NotNull(message = "Lug obrigatório")
    private Integer lugToLugMm;

    @NotNull(message = "Espessura obrigatório")
    private Integer espessuraMm;

    @NotNull(message = "Largura obrigatório")
    private Integer larguraLugMm;

    @NotNull(message = "Preco obrigatório")
    private Integer precoEmCentavos;

    @NotBlank(message = "UrlImagem obrigatório")
    private String urlImagem;
}
