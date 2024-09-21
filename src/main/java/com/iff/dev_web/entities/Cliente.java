package com.iff.dev_web.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@DiscriminatorValue("1")
public class Cliente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    private List<Financiamento> financiamentos;
    @Min(value = 0, message = "O score de crédito deve ser igual ou maior que 0")
    @Max(value = 1000, message = "O score de crédito deve ser menor ou igual a 1000")
    private Integer scoreCredito;
    @DecimalMin(value = "0.0", message = "O limite de crédito deve ser maior que zero")
    private BigDecimal limiteCreditoFinanciamento;
    @DecimalMin(value = "0.0", message = "A renda deve ser maior que zero")
    private BigDecimal renda;

    public Cliente() {}

    public Cliente(Long cdUsuario, String usuario, String email, String nuDocumento, String nuTelefone, String endereco, LocalDate dataNascimento, Integer scoreCredito, BigDecimal limiteCreditoFinanciamento, BigDecimal renda) {
        super(cdUsuario, usuario, email, nuDocumento, nuTelefone, endereco, dataNascimento);
        this.scoreCredito = scoreCredito;
        this.limiteCreditoFinanciamento = limiteCreditoFinanciamento;
        this.renda = renda;
    }

    public Integer getScoreCredito() {
        return scoreCredito;
    }

    public void setScoreCredito(Integer scoreCredito) {
        this.scoreCredito = scoreCredito;
    }

    public BigDecimal getLimiteCreditoFinanciamento() {
        return limiteCreditoFinanciamento;
    }

    public void setLimiteCreditoFinanciamento(BigDecimal limiteCreditoFinanciamento) {
        this.limiteCreditoFinanciamento = limiteCreditoFinanciamento;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }
}
