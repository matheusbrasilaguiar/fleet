package com.fleet.repository;

import com.fleet.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    Veiculo findByPlaca(String placa);
    
    boolean existsByPlaca(String placa);
}