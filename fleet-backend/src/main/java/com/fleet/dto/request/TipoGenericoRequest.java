package com.fleet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipoGenericoRequest {

    private String nome;
    private String descricao;
    private String categoria;

}
