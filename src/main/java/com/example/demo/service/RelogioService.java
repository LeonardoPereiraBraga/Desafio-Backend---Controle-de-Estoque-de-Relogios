package com.example.demo.service;

import com.example.demo.dto.RelogioSearchParams;
import com.example.demo.entity.Relogio;
import com.example.demo.entity.enums.MaterialCaixa;
import com.example.demo.entity.enums.TipoMovimento;
import com.example.demo.entity.enums.TipoVidro;
import com.example.demo.exception.RelogioNaoEncontrado;
import com.example.demo.repository.RelogioRepository;
import com.example.demo.repository.RelogioSpecification;
import com.example.demo.service.ordenacao.RelogioOrdenador;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelogioService {
    private final RelogioRepository repository;
    private final RelogioOrdenador ordenador;

    public Page<Relogio> findAll(RelogioSearchParams parametros){
        Specification<Relogio> specification = Specification.allOf(
                RelogioSpecification.contemTipoMovimento(parametros.tipoMovimento()),
                RelogioSpecification.contemMaterialCaixa(parametros.materialCaixa()),
                RelogioSpecification.contemTipoVidro(parametros.tipoVidro()),
                RelogioSpecification.filtrarPorResistenciaMinima(parametros.resistenciaMin()),
                RelogioSpecification.filtrarPorResistenciaMaxima(parametros.resistenciaMax()),
                RelogioSpecification.filtrarPrecoMinimo(parametros.precoMin()),
                RelogioSpecification.filtrarPrecoMaximo(parametros.precoMax()),
                RelogioSpecification.filtrarDiametroMinimo(parametros.diametroMin()),
                RelogioSpecification.filtrarDiametroMaximo(parametros.diametroMax()),
                RelogioSpecification.marcaContem(parametros.marca())
        );
        Sort ordenado = ordenador.resolver(parametros.ordenar());
        PageRequest pageable = PageRequest.of(parametros.pageNo(), parametros.pageSize(),ordenado);
        String busca = parametros.busca();
        if (busca != null){
            return repository.findByMarcaContainingIgnoreCaseOrModeloContainingIgnoreCaseOrReferenciaContainingIgnoreCase(busca,busca,busca,pageable);
        }
        return repository.findAll(specification,pageable);

    }
    public Relogio save(Relogio relogio){
        Relogio saved = repository.save(relogio);
        return saved;
    }
    public Relogio findById(UUID id){
        return repository.findById(id).orElseThrow(RelogioNaoEncontrado::new);
    }
    public void delete(UUID id){
        Relogio relogio = repository.findById(id).orElseThrow(RelogioNaoEncontrado::new);
        repository.delete(relogio);
    }
}
