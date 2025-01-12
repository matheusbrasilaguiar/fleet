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
@Table(name = "veiculo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false, length = 10)
    private String placa;

    @Column(nullable = false, length = 17)
    private String chassi;

    @Column(nullable = false)
    private int rodagemInicial;

    @Column(nullable = false)
    private int rodagemAtual;

    @ManyToOne
    @JoinColumn(name = "id_tipoVeiculo", nullable = false)
    private TipoGenerico tipoVeiculo;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private TipoGenerico categoria;

}
