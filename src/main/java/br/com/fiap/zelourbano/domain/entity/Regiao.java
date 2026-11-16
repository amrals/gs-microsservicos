package br.com.fiap.zelourbano.domain.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regioes")
public class Regiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nomeBairro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 50)
    private String zonaUrbana;

    @Column(nullable = false)
    private Integer populacaoAtivaApp;

    @Column(length = 150)
    private String subprefeituraResponsavel;

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL)
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

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