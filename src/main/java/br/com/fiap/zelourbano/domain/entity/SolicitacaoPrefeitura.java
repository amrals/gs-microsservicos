package br.com.fiap.zelourbano.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacoes_prefeitura")
public class SolicitacaoPrefeitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    private String protocoloOficial;

    @Column(nullable = false, length = 150)
    private String orgaoDestinatario;

    @Column(nullable = false)
    private Double percentualApoioAtingido;

    @Column(nullable = false, length = 60)
    private String statusEnvio; // ENVIADA_AUTOMATICAMENTE, EM_ANALISE, ATENDIDA

    @Column(nullable = false, length = 600)
    private String mensagemFormal;

    @Column(nullable = false)
    private LocalDateTime dataEnvio = LocalDateTime.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ocorrencia_id", nullable = false, unique = true)
    private Ocorrencia ocorrencia;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProtocoloOficial() { return protocoloOficial; }
    public void setProtocoloOficial(String protocoloOficial) { this.protocoloOficial = protocoloOficial; }
    public String getOrgaoDestinatario() { return orgaoDestinatario; }
    public void setOrgaoDestinatario(String orgaoDestinatario) { this.orgaoDestinatario = orgaoDestinatario; }
    public Double getPercentualApoioAtingido() { return percentualApoioAtingido; }
    public void setPercentualApoioAtingido(Double percentualApoioAtingido) { this.percentualApoioAtingido = percentualApoioAtingido; }
    public String getStatusEnvio() { return statusEnvio; }
    public void setStatusEnvio(String statusEnvio) { this.statusEnvio = statusEnvio; }
    public String getMensagemFormal() { return mensagemFormal; }
    public void setMensagemFormal(String mensagemFormal) { this.mensagemFormal = mensagemFormal; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }
    public Ocorrencia getOcorrencia() { return ocorrencia; }
    public void setOcorrencia(Ocorrencia ocorrencia) { this.ocorrencia = ocorrencia; }
}