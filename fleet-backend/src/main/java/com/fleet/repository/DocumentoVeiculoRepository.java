package com.fleet.repository;

import com.fleet.domain.DocumentoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoVeiculoRepository extends JpaRepository<DocumentoVeiculo, Long> {
}

