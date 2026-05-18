package com.biovitta.gestaocnpj.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "empresa",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_empresa_cnpj", columnNames = "cnpj"),
                @UniqueConstraint(name = "uk_empresa_email", columnNames = "email")
        }
)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_seq_generator")
    @SequenceGenerator(
            name = "empresa_seq_generator",
            sequenceName = "seq_empresa_id",
            allocationSize = 1
    )
    @Column(name = "id_empresa")
    private Long id;

    @NotBlank
    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @NotBlank
    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @NotBlank
    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    public Empresa() {
    }

    public Empresa(String razaoSocial, String cnpj) {
        if (razaoSocial == null || razaoSocial.isBlank()) {
            throw new IllegalArgumentException("Razão social inválida, não pode ser nula e nem vazia");
        }

        if (cnpj == null || cnpj.isBlank()) {
            throw new IllegalArgumentException("CNPJ inválido, não pode ser nulo e nem vazio");
        }

        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.ativo = true;
    }

    public boolean isAtivo() {
        return Boolean.TRUE.equals(this.ativo);
    }

    public Long getId() {
        return id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}