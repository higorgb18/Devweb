package com.iff.dev_web.repository;

import com.iff.dev_web.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("SELECT f FROM Funcionario f WHERE f.cargo = :cargo")
    List<Funcionario> buscarFuncionarioPorCargo(@Param("cargo") String cargo);

    @Query("SELECT f FROM Funcionario f WHERE f.salario > :salario")
    List<Funcionario> buscarFuncionarioPorSalario(@Param("salario") BigDecimal salario);

    @Query("SELECT f FROM Funcionario f WHERE f.lojaFilial.nome = :nomeFilial")
    List<Funcionario> buscarFuncionarioPorFilial(@Param("nomeFilial") String nomeFilial);
}
