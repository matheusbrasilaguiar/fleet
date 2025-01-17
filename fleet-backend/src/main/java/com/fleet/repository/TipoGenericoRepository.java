package com.fleet.repository;

import com.fleet.domain.TipoGenerico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoGenericoRepository extends JpaRepository<TipoGenerico, Long> {

    List<TipoGenerico> findByCategoria(String categoria);
}
