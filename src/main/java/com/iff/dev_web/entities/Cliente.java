package com.iff.dev_web.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Cliente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    private List<Financiamento> financiamentos;
    private Integer scoreCredito;
    private BigDecimal limiteCreditoFinanciamento;
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
