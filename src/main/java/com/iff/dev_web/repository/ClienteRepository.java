package com.iff.dev_web.repository;

import com.iff.dev_web.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.scoreCredito >= :score")
    List<Cliente> buscarClientePorScore(@Param("score") Integer score);

    Optional<Cliente> findByNuDocumento(String nuDocumento);

    Optional<Cliente> findByEmail(String email);
}
