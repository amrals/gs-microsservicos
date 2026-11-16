package br.com.fiap.zelourbano.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegiaoRequestDTO {
    @NotBlank(message = "O nome do bairro é obrigatório")
    private String nomeBairro;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "A zona urbana é obrigatória")
    private String zonaUrbana;

    @NotNull(message = "A população ativa é obrigatória")
    @Min(value = 1, message = "A população ativa deve ser no mínimo 1")
    private Integer populacaoAtivaApp;

    private String subprefeituraResponsavel;

    // Getters e Setters
    public String getNomeBairro() { return nomeBairro; }
    public void setNomeBairro(String nomeBairro) { this.nomeBairro = nomeBairro; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getZonaUrbana() { return zonaUrbana; }
    public void setZonaUrbana(String zonaUrbana) { this.zonaUrbana = zonaUrbana; }
    public Integer getPopulacaoAtivaApp() { return populacaoAtivaApp; }
    public void setPopulacaoAtivaApp(Integer populacaoAtivaApp) { this.populacaoAtivaApp = populacaoAtivaApp; }
    public String getSubprefeituraResponsavel() { return subprefeituraResponsavel; }
    public void setSubprefeituraResponsavel(String subprefeituraResponsavel) { this.subprefeituraResponsavel = subprefeituraResponsavel; }
}