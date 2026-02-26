package com.example.demo.config;

import com.example.demo.entity.Relogio;
import com.example.demo.entity.enums.MaterialCaixa;
import com.example.demo.entity.enums.TipoMovimento;
import com.example.demo.entity.enums.TipoVidro;
import com.example.demo.repository.RelogioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class Seed implements CommandLineRunner {
    private final RelogioRepository relogioRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runtime");
        boolean exists = relogioRepository.existsBy();
        if (!exists){
            Relogio r1 = new Relogio(
                    null,
                    "Casio",
                    "Duro MDV-106",
                    "MDV106-1A",
                    TipoMovimento.QUARTZ,
                    MaterialCaixa.STEEL,
                    TipoVidro.MINERAL,
                    200,
                    44,
                    48,
                    12,
                    22,
                    42000,
                    "https://example.com/casio-duro.jpg",
                    "Diver 200m",
                    70
            );
            Relogio r2 = new Relogio(
                    null,
                    "Seiko",
                    "Presage Cocktail Time",
                    "SRPB43J1",
                    TipoMovimento.AUTOMATIC,
                    MaterialCaixa.STEEL,
                    TipoVidro.MINERAL,
                    50,
                    40,
                    47,
                    11,
                    20,
                    290000,
                    "https://example.com/seiko-presage.jpg",
                    "50m (resistente a respingos)",
                    85
            );
            Relogio r3 = new Relogio(
                    null,
                    "Tissot",
                    "Le Locle Powermatic 80",
                    "T0064071103300",
                    TipoMovimento.AUTOMATIC,
                    MaterialCaixa.STEEL,
                    TipoVidro.SAPPHIRE,
                    30,
                    39,
                    46,
                    9,
                    19,
                    450000,
                    "https://example.com/tissot-lelocle.jpg",
                    "30m (uso cotidiano)",
                    90
            );
            relogioRepository.save(r1);
            relogioRepository.save(r2);
            relogioRepository.save(r3);

        }
    }
}
