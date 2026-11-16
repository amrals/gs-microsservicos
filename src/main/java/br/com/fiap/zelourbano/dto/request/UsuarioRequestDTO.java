package br.com.fiap.zelourbano.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioRequestDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "O perfil de mobilidade é obrigatório")
    private String perfilMobilidade;

    @NotNull(message = "O status ativo é obrigatório")
    private Boolean ativo;

    @NotNull(message = "O ID da região é obrigatório")
    private Long regiaoId;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPerfilMobilidade() { return perfilMobilidade; }
    public void setPerfilMobilidade(String perfilMobilidade) { this.perfilMobilidade = perfilMobilidade; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public Long getRegiaoId() { return regiaoId; }
    public void setRegiaoId(Long regiaoId) { this.regiaoId = regiaoId; }
}