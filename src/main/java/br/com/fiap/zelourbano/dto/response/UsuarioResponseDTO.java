package br.com.fiap.zelourbano.dto.response;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String perfilMobilidade;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
    private Long regiaoId;
    private String nomeBairroRegiao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPerfilMobilidade() { return perfilMobilidade; }
    public void setPerfilMobilidade(String perfilMobilidade) { this.perfilMobilidade = perfilMobilidade; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public Long getRegiaoId() { return regiaoId; }
    public void setRegiaoId(Long regiaoId) { this.regiaoId = regiaoId; }
    public String getNomeBairroRegiao() { return nomeBairroRegiao; }
    public void setNomeBairroRegiao(String nomeBairroRegiao) { this.nomeBairroRegiao = nomeBairroRegiao; }
}