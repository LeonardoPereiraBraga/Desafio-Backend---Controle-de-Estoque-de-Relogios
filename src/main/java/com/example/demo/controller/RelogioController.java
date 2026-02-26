package com.example.demo.controller;

import com.example.demo.dto.RelogioDto;
import com.example.demo.dto.RelogioSearchParams;
import com.example.demo.entity.Relogio;
import com.example.demo.entity.enums.MaterialCaixa;
import com.example.demo.entity.enums.TipoMovimento;
import com.example.demo.entity.enums.TipoVidro;
import com.example.demo.mapper.RelogioMapper;
import com.example.demo.service.RelogioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/relogios")
@RequiredArgsConstructor
public class RelogioController {
    private final RelogioService service;
    private final RelogioMapper mapper;

    @GetMapping
    public ResponseEntity<Page<Relogio>> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                                 @RequestParam(defaultValue = "60") int pageSize,
                                                 @RequestParam(required = false) String busca,
                                                 @RequestParam(required = false) Integer resistenciaMin,
                                                 @RequestParam(required = false) Integer resistenciaMax,
                                                 @RequestParam(required = false) TipoMovimento tipoMovimento,
                                                 @RequestParam(required = false) String ordenar,
                                                 @RequestParam(required = false) MaterialCaixa materialCaixa,
                                                 @RequestParam(required = false) TipoVidro tipoVidro,
                                                 @RequestParam(required = false) Integer precoMin,
                                                 @RequestParam(required = false) Integer precoMax,
                                                 @RequestParam(required = false) Integer diametroMin,
                                                 @RequestParam(required = false) Integer diametroMax,
                                                 @RequestParam(required = false) String marca
    ){
        Page<Relogio> relogios = service.findAll(new RelogioSearchParams(pageNo, pageSize,busca,resistenciaMin,resistenciaMax,tipoMovimento,ordenar,materialCaixa,tipoVidro,precoMin,precoMax,diametroMin,diametroMax,marca));
        return ResponseEntity.ok(relogios);
    }
    @PostMapping
    public ResponseEntity<Relogio> save(@Valid @RequestBody RelogioDto relogioDto){
        Relogio relogio = mapper.toRelogio(relogioDto);
        Relogio relogioSalvo = service.save(relogio);
        return ResponseEntity.status(HttpStatus.CREATED).body(relogioSalvo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Relogio> findById(@PathVariable UUID id){
        Relogio relogioFound = service.findById(id);
        return ResponseEntity.ok(relogioFound);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Relogio> update(@Valid @RequestBody RelogioDto relogioDto,
                                          @PathVariable UUID id){
        Relogio relogio = mapper.toRelogio(relogioDto);
        relogio.setId(id);
        Relogio relogioSalvo = service.save(relogio);
        return ResponseEntity.status(HttpStatus.OK).body(relogioSalvo);
    }

}
