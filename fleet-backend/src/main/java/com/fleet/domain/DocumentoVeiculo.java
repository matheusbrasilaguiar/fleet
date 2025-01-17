package com.fleet.domain;

import java.time.LocalDate;
import com.fleet.domain.enums.StatusDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "documento_veiculo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoVeiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoGenerico tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 50)
    private String numeroDocumento;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "orgao_emissor", length = 50)
    private String orgaoEmissor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusDocumento status;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "anexo_documento", length = 255)
    private String anexoDocumento;
}
