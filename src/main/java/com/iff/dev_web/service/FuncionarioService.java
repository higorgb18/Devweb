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

    public Funcionario criarFuncionario(Funcionario funcionario) {
        validarFuncionario(funcionario);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario funcionario) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new DadosFuncionarioInvalidosException("Funcionário não encontrado com o ID: " + id));
        atualizarDadosFuncionario(funcionarioExistente, funcionario);
        return funcionarioRepository.save(funcionarioExistente);
    }

    public void excluirFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new DadosFuncionarioInvalidosException("Funcionário não encontrado com o ID: " + id));
        funcionarioRepository.delete(funcionario);
    }

    private void atualizarDadosFuncionario(Funcionario funcionarioExistente, Funcionario novoFuncionario) {
        funcionarioExistente.setUsuario(novoFuncionario.getUsuario());
        funcionarioExistente.setEmail(novoFuncionario.getEmail());
        funcionarioExistente.setNuDocumento(novoFuncionario.getNuDocumento());
        funcionarioExistente.setNuTelefone(novoFuncionario.getNuTelefone());
        funcionarioExistente.setEndereco(novoFuncionario.getEndereco());
        funcionarioExistente.setDataNascimento(novoFuncionario.getDataNascimento());
        funcionarioExistente.setSalario(novoFuncionario.getSalario());
        funcionarioExistente.setCargo(novoFuncionario.getCargo());
        funcionarioExistente.setLojaFilial(novoFuncionario.getLojaFilial());
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

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

    private void validarFuncionario(Funcionario funcionario) {
        validarCargo(funcionario.getCargo());
        validarSalario(funcionario.getSalario());
    }
}
