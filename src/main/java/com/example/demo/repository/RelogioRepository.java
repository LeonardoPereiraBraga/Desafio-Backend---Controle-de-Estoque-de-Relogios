package com.example.demo.repository;

import com.example.demo.entity.Relogio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RelogioRepository extends JpaRepository<Relogio, UUID>, JpaSpecificationExecutor<Relogio> {
    Page<Relogio> findByMarcaContainingIgnoreCaseOrModeloContainingIgnoreCaseOrReferenciaContainingIgnoreCase(
            String marca,
            String modelo,
            String referencia,
            Pageable pageable
    );
    boolean existsBy();

}
