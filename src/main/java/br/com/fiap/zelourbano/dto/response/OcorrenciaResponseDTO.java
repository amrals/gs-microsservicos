package br.com.fiap.zelourbano.dto.response;

import java.time.LocalDateTime;

public class OcorrenciaResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String categoria;
    private String endereco;
    private Integer quantidadeCurtidas;
    private String status;
    private LocalDateTime dataCriacao;
    private Long usuarioAutorId;
    private Long regiaoId;
    private Integer metaLikesParaDisparo80Porcento;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public Integer getQuantidadeCurtidas() { return quantidadeCurtidas; }
    public void setQuantidadeCurtidas(Integer quantidadeCurtidas) { this.quantidadeCurtidas = quantidadeCurtidas; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public Long getUsuarioAutorId() { return usuarioAutorId; }
    public void setUsuarioAutorId(Long usuarioAutorId) { this.usuarioAutorId = usuarioAutorId; }
    public Long getRegiaoId() { return regiaoId; }
    public void setRegiaoId(Long regiaoId) { this.regiaoId = regiaoId; }
    public Integer getMetaLikesParaDisparo80Porcento() { return metaLikesParaDisparo80Porcento; }
    public void setMetaLikesParaDisparo80Porcento(Integer metaLikesParaDisparo80Porcento) { this.metaLikesParaDisparo80Porcento = metaLikesParaDisparo80Porcento; }
}