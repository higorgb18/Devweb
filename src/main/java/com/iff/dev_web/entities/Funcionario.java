package com.iff.dev_web.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Funcionario extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "cdLojaFilial")
    private LojaFilial lojaFilial;
    @OneToMany(mappedBy = "funcionario")
    private List<Financiamento> financiamentos;
    private BigDecimal salario;
    private String cargo;


    public Funcionario() {

    }

    public Funcionario(Long cdUsuario, String usuario, String email, String nuDocumento, String nuTelefone, String endereco, LocalDate dataNascimento, BigDecimal salario, String cargo, LojaFilial lojaFilial) {
        super(cdUsuario, usuario, email, nuDocumento, nuTelefone, endereco, dataNascimento);
        this.salario = salario;
        this.cargo = cargo;
        this.lojaFilial = lojaFilial;
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

    public LojaFilial getLojaFilial() {
        return lojaFilial;
    }

    public void setLojaFilial(LojaFilial lojaFilial) {
        this.lojaFilial = lojaFilial;
    }
}

