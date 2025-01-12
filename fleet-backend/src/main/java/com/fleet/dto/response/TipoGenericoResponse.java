package com.fleet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipoGenericoResponse {
    
    private Long id;
    private String nome;
    private String descricao;
    private String categoria;

}
