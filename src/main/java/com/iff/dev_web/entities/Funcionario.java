package com.iff.dev_web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Funcionario extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "cdLojaFilial")
    @JsonIgnore
    private LojaFilial lojaFilial;
    @OneToMany(mappedBy = "funcionario")
    @JsonIgnore
    private List<Financiamento> financiamentos;
    private BigDecimal salario;
    @NotBlank
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

