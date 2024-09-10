package com.iff.dev_web.repository;

import com.iff.dev_web.entities.LojaFilial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LojaFilialRepository extends JpaRepository<LojaFilial, Long> {

    @Query("SELECT l FROM LojaFilial l WHERE l.nome = :nome")
    List<LojaFilial> buscarLojaPorNome(@Param("nome") String nome);

    @Query("SELECT l FROM LojaFilial l WHERE l.endereco = :endereco")
    List<LojaFilial> buscarLojaPorEndereco(@Param("endereco") String endereco);

    @Query("SELECT l FROM LojaFilial l WHERE l.nuTelefone = :telefone")
    List<LojaFilial> buscarLojaPorTelefone(@Param("telefone") String telefone);
}
