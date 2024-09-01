package com.iff.dev_web.repository;

import com.iff.dev_web.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
