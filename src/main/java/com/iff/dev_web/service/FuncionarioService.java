package com.iff.dev_web.service;

import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.exception.DadosFuncionarioInvalidosException;
import com.iff.dev_web.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> buscarFuncionariosPorCargo(String cargo) {
        validarCargo(cargo);
        return funcionarioRepository.buscarFuncionarioPorCargo(cargo);
    }

    public List<Funcionario> buscarFuncionariosPorSalario(BigDecimal salario) {
        validarSalario(salario);
        return funcionarioRepository.buscarFuncionarioPorSalario(salario);
    }

    public List<Funcionario> buscarFuncionariosPorFilial(String nomeFilial) {
        validarNomeFilial(nomeFilial);
        return funcionarioRepository.buscarFuncionarioPorFilial(nomeFilial);
    }

    private void validarCargo(String cargo) {
        if (cargo == null || cargo.trim().isEmpty()) {
            throw new DadosFuncionarioInvalidosException("Cargo de funcionário inválido fornecido.");
        }
    }

    private void validarSalario(BigDecimal salario) {
        if (salario == null || salario.compareTo(new BigDecimal("1412.0")) < 0) {
            throw new DadosFuncionarioInvalidosException("Salário inválido fornecido.");
        }
    }

    private void validarNomeFilial(String nomeFilial) {
        if (nomeFilial == null || nomeFilial.trim().isEmpty()) {
            throw new DadosFuncionarioInvalidosException("Nome da filial inválido fornecido.");
        }
    }
}
