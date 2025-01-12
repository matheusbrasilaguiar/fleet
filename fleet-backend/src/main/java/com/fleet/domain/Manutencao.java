package com.fleet.domain;

import java.time.LocalDate;

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
@Table(name = "manutencao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manutencao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_manutencao", nullable = false)
    private TipoGenerico tipoManutencao;

    @ManyToOne
    @JoinColumn(name = "id_oficina", nullable = false)
    private Oficina oficina;

    @Column(name = "data_manutencao", nullable = false)
    private LocalDate dataManutencao;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;
}
