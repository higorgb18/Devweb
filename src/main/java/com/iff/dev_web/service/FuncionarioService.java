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

    private static final String TELEFONE_REGEX = "^\\d{10,11}$";
    private static final String CPF_REGEX = "\\d{11}";

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioService usuarioService;

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
            throw new DadosFuncionarioInvalidosException("Salário abaixo do permitido.");
        }
    }

    private void validarNomeFilial(String nomeFilial) {
        if (nomeFilial == null || nomeFilial.trim().isEmpty()) {
            throw new DadosFuncionarioInvalidosException("Nome da filial inválido fornecido.");
        }
    }

    public void validarTelefone(String telefone) {
        if (!telefone.matches(TELEFONE_REGEX)) {
            throw new DadosFuncionarioInvalidosException("Telefone inválido. O campo deve ter 10 ou 11 dígitos.");
        }
    }

    public void validarCPF(String cpf) {
        if (!cpf.matches(CPF_REGEX)) {
            throw new DadosFuncionarioInvalidosException("CPF inválido. O CPF deve conter 11 dígitos.");
        }
    }

    private void validarFuncionario(Funcionario funcionario) {
        usuarioService.validarDocumentoEEmailUnicos(funcionario);
        usuarioService.validarDataNascimento(funcionario.getDataNascimento());
        validarCargo(funcionario.getCargo());
        validarSalario(funcionario.getSalario());
        validarTelefone(funcionario.getNuTelefone());
        validarCPF(funcionario.getNuDocumento());
    }
}
