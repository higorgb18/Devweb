package com.iff.dev_web.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "TbFinanciamento")
public class Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String nuContrato;

    @Column(nullable = false)
    private Long cdCliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CdStatusEnum cdStatus;

    @Column(nullable = false)
    private BigDecimal valorFinanciamento;

    @Column(nullable = false)
    private BigDecimal taxaJuros;

    @Column(nullable = false)
    private Integer qtParcelas;

    @Column(nullable = false)
    private LocalDateTime dtInicioFinanciamento;

    @Column(nullable = false)
    private LocalDateTime dtFimFinanciamento;

    @Column(nullable = false)
    private Long cdVeiculo;

    @Column(nullable = false)
    private LocalDateTime dtAtualizacao;

    @Column(nullable = false)
    private Long cdFuncionario;

    public Financiamento() {
    }

    public Financiamento(String nuContrato, Long cdCliente, CdStatusEnum cdStatus, BigDecimal valorFinanciamento, BigDecimal taxaJuros, Integer qtParcelas, LocalDateTime dtInicioFinanciamento, LocalDateTime dtFimFinanciamento, Long cdVeiculo, LocalDateTime dtAtualizacao, Long cdFuncionario) {
        this.nuContrato = nuContrato;
        this.cdCliente = cdCliente;
        this.cdStatus = cdStatus;
        this.valorFinanciamento = valorFinanciamento;
        this.taxaJuros = taxaJuros;
        this.qtParcelas = qtParcelas;
        this.dtInicioFinanciamento = dtInicioFinanciamento;
        this.dtFimFinanciamento = dtFimFinanciamento;
        this.cdVeiculo = cdVeiculo;
        this.dtAtualizacao = dtAtualizacao;
        this.cdFuncionario = cdFuncionario;
    }

    public String getNuContrato() {
        return nuContrato;
    }

    public void setNuContrato(String nuContrato) {
        this.nuContrato = nuContrato;
    }

    public Long getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Long cdCliente) {
        this.cdCliente = cdCliente;
    }

    public CdStatusEnum getCdStatus() {
        return cdStatus;
    }

    public void setCdStatus(CdStatusEnum cdStatus) {
        this.cdStatus = cdStatus;
    }

    public BigDecimal getValorFinanciamento() {
        return valorFinanciamento;
    }

    public void setValorFinanciamento(BigDecimal valorFinanciamento) {
        this.valorFinanciamento = valorFinanciamento;
    }

    public BigDecimal getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Integer getQtParcelas() {
        return qtParcelas;
    }

    public void setQtParcelas(Integer qtParcelas) {
        this.qtParcelas = qtParcelas;
    }

    public LocalDateTime getDtInicioFinanciamento() {
        return dtInicioFinanciamento;
    }

    public void setDtInicioFinanciamento(LocalDateTime dtInicioFinanciamento) {
        this.dtInicioFinanciamento = dtInicioFinanciamento;
    }

    public LocalDateTime getDtFimFinanciamento() {
        return dtFimFinanciamento;
    }

    public void setDtFimFinanciamento(LocalDateTime dtFimFinanciamento) {
        this.dtFimFinanciamento = dtFimFinanciamento;
    }

    public Long getCdVeiculo() {
        return cdVeiculo;
    }

    public void setCdVeiculo(Long cdVeiculo) {
        this.cdVeiculo = cdVeiculo;
    }

    public LocalDateTime getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(LocalDateTime dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Long getCdFuncionario() {
        return cdFuncionario;
    }

    public void setCdFuncionario(Long cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }
}

