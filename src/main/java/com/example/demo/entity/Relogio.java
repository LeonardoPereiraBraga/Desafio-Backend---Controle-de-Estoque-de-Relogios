package com.example.demo.entity;

import com.example.demo.entity.enums.MaterialCaixa;
import com.example.demo.entity.enums.TipoMovimento;
import com.example.demo.entity.enums.TipoVidro;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relogio {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String marca;
    private String modelo;
    private String referencia;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;

    @Enumerated(EnumType.STRING)
    private MaterialCaixa materialCaixa;

    @Enumerated(EnumType.STRING)
    private TipoVidro tipoVidro;

    private Integer resistenciaAguaM;
    private Integer diametroMm;
    private Integer lugToLugMm;
    private Integer espessuraMm;
    private Integer larguraLugMm;
    private Integer precoEmCentavos;
    private String urlImagem;
    private String etiquetaResistenciaAgua;
    private Integer pontuacaoColecionador;
}
