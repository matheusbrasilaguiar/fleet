package com.fleet.repository;

import com.fleet.domain.TipoGenerico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoGenericoRepository extends JpaRepository<TipoGenerico, Long> {
}
