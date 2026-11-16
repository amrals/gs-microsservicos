package br.com.fiap.zelourbano.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencias")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, length = 80)
    private String categoria; // ACESSIBILIDADE, ILUMINACAO, SEGURANCA, CALCADAS

    @Column(nullable = false, length = 250)
    private String endereco;

    @Column(nullable = false)
    private Integer quantidadeCurtidas = 0;

    @Column(nullable = false, length = 60)
    private String status = "EM_COLETA"; // EM_COLETA, SOLICITACAO_GERADA, RESOLVIDA

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioAutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regiao_id", nullable = false)
    private Regiao regiao;

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
    public Usuario getUsuarioAutor() { return usuarioAutor; }
    public void setUsuarioAutor(Usuario usuarioAutor) { this.usuarioAutor = usuarioAutor; }
    public Regiao getRegiao() { return regiao; }
    public void setRegiao(Regiao regiao) { this.regiao = regiao; }
}