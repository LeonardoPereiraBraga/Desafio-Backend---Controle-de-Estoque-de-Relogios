package com.example.demo.repository;

import com.example.demo.entity.Relogio;
import com.example.demo.entity.enums.MaterialCaixa;
import com.example.demo.entity.enums.TipoMovimento;
import com.example.demo.entity.enums.TipoVidro;
import org.springframework.data.jpa.domain.Specification;

public class RelogioSpecification {

    public static Specification<Relogio> marcaContem(String marca) {
        return (root, query, criteriaBuilder) -> {
            if (marca == null || marca.isEmpty()) {
                return criteriaBuilder.conjunction(); // WHERE 1=1 (sempre verdadeiro)
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("marca")),
                    "%" + marca.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Relogio> filtrarPorResistenciaMinima(Integer resistenciaMinima){
        return ((root, query, criteriaBuilder) -> {
            if (resistenciaMinima == null){
                return criteriaBuilder.conjunction();
            }
            System.out.println("ResistenciaMinima: " + resistenciaMinima);
            System.out.println("Resistencia pega do objeto" + root.get("resistenciaAguaM"));
            return criteriaBuilder.greaterThanOrEqualTo(root.get("resistenciaAguaM"),resistenciaMinima);
        });
    }
    public static Specification<Relogio> filtrarPorResistenciaMaxima(Integer resistenciaMaxima){
        return ((root, query, criteriaBuilder) -> {
            if (resistenciaMaxima == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("resistenciaAguaM"),resistenciaMaxima);
        });
    }
    public static Specification<Relogio> contemTipoMovimento(TipoMovimento tipoMovimento){
        return ((root, query, criteriaBuilder) -> {
            if (tipoMovimento == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("tipoMovimento"), tipoMovimento);
        });
    }
    public static Specification<Relogio> contemMaterialCaixa(MaterialCaixa materialCaixa){
        return ((root, query, criteriaBuilder) -> {
            if (materialCaixa == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("materialCaixa"), materialCaixa);
        });
    }
    public static Specification<Relogio> contemTipoVidro(TipoVidro tipoVidro){
        return ((root, query, criteriaBuilder) -> {
            if (tipoVidro == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("tipoVidro"), tipoVidro);
        });
    }
    public static Specification<Relogio> filtrarPrecoMaximo(Integer precoMax){
        return ((root, query, criteriaBuilder) -> {
            if (precoMax == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("precoEmCentavos"),precoMax);
        });
    }
    public static Specification<Relogio> filtrarPrecoMinimo(Integer precoMin){
        return ((root, query, criteriaBuilder) -> {
            if (precoMin == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("precoEmCentavos"),precoMin);
        });
    }
    public static Specification<Relogio> filtrarDiametroMaximo(Integer diametroMax){
        return ((root, query, criteriaBuilder) -> {
            if (diametroMax == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("diametroMm"),diametroMax);
        });
    }
    public static Specification<Relogio> filtrarDiametroMinimo(Integer diametroMin){
        return ((root, query, criteriaBuilder) -> {
            if (diametroMin == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("diametroMm"),diametroMin);
        });
    }
}
