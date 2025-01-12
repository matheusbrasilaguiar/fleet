package com.fleet.repository;

import com.fleet.domain.ImpostoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpostoVeiculoRepository extends JpaRepository<ImpostoVeiculo, Long> {
}
