package com.fleet.service.interfaces;

import java.util.List;
import com.fleet.dto.request.TipoGenericoRequest;
import com.fleet.dto.response.TipoGenericoResponse;

public interface TipoGenericoServiceInterface {
    
    List<TipoGenericoResponse> getAll();

    TipoGenericoResponse getById(Long id);

    List<TipoGenericoResponse> listByCategory(String categoria);

    TipoGenericoResponse save(TipoGenericoRequest tipoGenericoRequest);

    void delete(Long id);
}
