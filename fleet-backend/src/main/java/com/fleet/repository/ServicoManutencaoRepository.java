package com.fleet.repository;

import com.fleet.domain.ServicoManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoManutencaoRepository extends JpaRepository<ServicoManutencao, Long> {
}
