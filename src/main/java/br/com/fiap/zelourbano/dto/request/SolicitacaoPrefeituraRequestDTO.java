package br.com.fiap.zelourbano.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SolicitacaoPrefeituraRequestDTO {
    @NotBlank(message = "O protocolo oficial é obrigatório")
    private String protocoloOficial;

    @NotBlank(message = "O órgão destinatário é obrigatório")
    private String orgaoDestinatario;

    @NotNull(message = "O percentual de apoio é obrigatório")
    private Double percentualApoioAtingido;

    @NotBlank(message = "O status de envio é obrigatório")
    private String statusEnvio;

    @NotBlank(message = "A mensagem formal é obrigatória")
    private String mensagemFormal;

    @NotNull(message = "O ID da ocorrência é obrigatório")
    private Long ocorrenciaId;

    // Getters e Setters
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
    public Long getOcorrenciaId() { return ocorrenciaId; }
    public void setOcorrenciaId(Long ocorrenciaId) { this.ocorrenciaId = ocorrenciaId; }
}