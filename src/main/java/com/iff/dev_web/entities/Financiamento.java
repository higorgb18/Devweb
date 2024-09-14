package com.iff.dev_web.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "TbFinanciamento")
public class Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private String nuContrato;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cdVeiculo", referencedColumnName = "cdVeiculo")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "cdCliente", referencedColumnName = "cdUsuario")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cdFuncionario", referencedColumnName = "cdUsuario")
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private CdStatusEnum cdStatus;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", message = "O valor do financiamento deve ser maior que 0")
    private BigDecimal valorFinanciamento;
    @Column(nullable = false)
    @DecimalMin(value = "0.0", message = "A taxa de juros deve ser maior que 0")
    private BigDecimal taxaJuros;
    @Min(value = 0, message = "A quantidade de parcelas deve ser maior que 0")
    @Column(nullable = false)
    private Integer qtParcelas;
    @Column(nullable = false)
    private LocalDateTime dtInicioFinanciamento;
    @Future
    @Column(nullable = false)
    private LocalDateTime dtFimFinanciamento;
    @PastOrPresent
    private LocalDateTime dtAtualizacao;


    public Financiamento() {
    }

    public Financiamento(String nuContrato, Veiculo veiculo, Cliente cliente, Funcionario funcionario, CdStatusEnum cdStatus, BigDecimal valorFinanciamento, BigDecimal taxaJuros, Integer qtParcelas, LocalDateTime dtInicioFinanciamento, LocalDateTime dtFimFinanciamento, LocalDateTime dtAtualizacao) {
        this.nuContrato = nuContrato;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.cdStatus = cdStatus;
        this.valorFinanciamento = valorFinanciamento;
        this.taxaJuros = taxaJuros;
        this.qtParcelas = qtParcelas;
        this.dtInicioFinanciamento = dtInicioFinanciamento;
        this.dtFimFinanciamento = dtFimFinanciamento;
        this.dtAtualizacao = dtAtualizacao;
    }

    public String getNuContrato() {
        return nuContrato;
    }

    public void setNuContrato(String nuContrato) {
        this.nuContrato = nuContrato;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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

    public LocalDateTime getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(LocalDateTime dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }
}

