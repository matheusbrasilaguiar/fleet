package com.fleet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servico_manutencao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoManutencao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_manutencao", nullable = false)
    private Manutencao manutencao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_servico", nullable = false)
    private TipoGenerico tipoServico;

    @Column(name = "valor_servico", nullable = false)
    private Double valorServico;

    @Column(length = 255)
    private String observacoes;
    
}
