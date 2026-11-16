package br.com.fiap.zelourbano.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OcorrenciaRequestDTO {
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    @NotNull(message = "A quantidade de curtidas é obrigatória")
    @Min(value = 0, message = "As curtidas não podem ser negativas")
    private Integer quantidadeCurtidas;

    @NotNull(message = "O ID do usuário autor é obrigatório")
    private Long usuarioAutorId;

    @NotNull(message = "O ID da região é obrigatório")
    private Long regiaoId;

    // Getters e Setters
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
    public Long getUsuarioAutorId() { return usuarioAutorId; }
    public void setUsuarioAutorId(Long usuarioAutorId) { this.usuarioAutorId = usuarioAutorId; }
    public Long getRegiaoId() { return regiaoId; }
    public void setRegiaoId(Long regiaoId) { this.regiaoId = regiaoId; }
}