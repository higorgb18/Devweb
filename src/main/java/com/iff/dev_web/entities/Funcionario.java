package com.iff.dev_web.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Funcionario extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;


    private BigDecimal salario;
    private String cargo;
    private Long cdLojaFilial;

    public Funcionario() {

    }

    public Funcionario(Long cdUsuario, String usuario, String email, String nuDocumento, String nuTelefone, String endereco, LocalDate dataNascimento, BigDecimal salario, String cargo, Long cdLojaFilial) {
        super(cdUsuario, usuario, email, nuDocumento, nuTelefone, endereco, dataNascimento);
        this.salario = salario;
        this.cargo = cargo;
        this.cdLojaFilial = cdLojaFilial;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Long getCdLojaFilial() {
        return cdLojaFilial;
    }

    public void setCdLojaFilial(Long cdLojaFilial) {
        this.cdLojaFilial = cdLojaFilial;
    }
}

