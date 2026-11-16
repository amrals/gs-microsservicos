package br.com.fiap.zelourbano.dto.response;

public class RegiaoResponseDTO {
    private Long id;
    private String nomeBairro;
    private String cidade;
    private String zonaUrbana;
    private Integer populacaoAtivaApp;
    private String subprefeituraResponsavel;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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