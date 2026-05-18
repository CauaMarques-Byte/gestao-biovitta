package com.biovitta.gestaocnpj.domain.entity;

import com.biovitta.gestaocnpj.domain.entity.enums.StatusMensalidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "mensalidade")
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensalidade_seq_generator")
    @SequenceGenerator(
            name = "mensalidade_seq_generator",
            sequenceName = "seq_mensalidade_id",
            allocationSize = 1
    )
    @Column(name = "id_mensalidade")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "id_empresa",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_mensalidade_empresa")
    )
    private Empresa empresa;

    @NotNull
    @DecimalMin(value = "1.00", message = "Valor mínimo é R$ 1,00")
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @NotNull
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMensalidade status = StatusMensalidade.PENDENTE;

    public Mensalidade() {
    }

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = StatusMensalidade.PENDENTE;
        }
    }

    public void marcarComoPago() {
        if (this.status == StatusMensalidade.CANCELADO) {
            throw new RuntimeException("Mensalidade cancelada não pode ser marcada como paga");
        }

        if (this.status == StatusMensalidade.PAGO) {
            throw new RuntimeException("Mensalidade já está paga");
        }

        this.status = StatusMensalidade.PAGO;
        this.dataPagamento = LocalDate.now();
    }

    public void marcarComoAtrasada() {
        if (this.status == StatusMensalidade.PAGO) {
            throw new RuntimeException("Mensalidade paga não pode ser marcada como atrasada");
        }

        if (this.status == StatusMensalidade.CANCELADO) {
            throw new RuntimeException("Mensalidade cancelada não pode ser marcada como atrasada");
        }

        this.status = StatusMensalidade.ATRASADO;
    }

    public Long getId() {
        return id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public StatusMensalidade getStatus() {
        return status;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setStatus(StatusMensalidade status) {
        this.status = status;
    }
}