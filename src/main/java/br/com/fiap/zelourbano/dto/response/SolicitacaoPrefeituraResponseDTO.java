package br.com.fiap.zelourbano.dto.response;

import java.time.LocalDateTime;

public class SolicitacaoPrefeituraResponseDTO {
    private Long id;
    private String protocoloOficial;
    private String orgaoDestinatario;
    private Double percentualApoioAtingido;
    private String statusEnvio;
    private String mensagemFormal;
    private LocalDateTime dataEnvio;
    private Long ocorrenciaId;

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
    public Long getOcorrenciaId() { return ocorrenciaId; }
    public void setOcorrenciaId(Long ocorrenciaId) { this.ocorrenciaId = ocorrenciaId; }
}