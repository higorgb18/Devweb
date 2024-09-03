package com.iff.dev_web.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "TbVeiculo")
public class Veiculo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdVeiculo;

    @Column(nullable = false)
    private Integer cdTipoVeiculo;

    @Column(nullable = false)
    private String placa;

    @Column(nullable = false)
    private String chassi;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer anoModelo;

    @Column(nullable = false)
    private String tipoCombustivel;

    @Column(nullable = false)
    private Long cdTabelaFipe;

    @Column(nullable = false)
    private Integer quilometragem;

    public Veiculo() {

    }

    public Veiculo(Long cdVeiculo, Integer cdTipoVeiculo, String placa, String chassi, BigDecimal valor, String marca, String modelo, Integer anoModelo, String tipoCombustivel, Long cdTabelaFipe, Integer quilometragem) {
        this.cdVeiculo = cdVeiculo;
        this.cdTipoVeiculo = cdTipoVeiculo;
        this.placa = placa;
        this.chassi = chassi;
        this.valor = valor;
        this.marca = marca;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.tipoCombustivel = tipoCombustivel;
        this.cdTabelaFipe = cdTabelaFipe;
        this.quilometragem = quilometragem;
    }
}
