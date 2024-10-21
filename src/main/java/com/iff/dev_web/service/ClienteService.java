package com.iff.dev_web.service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.exception.DadosClienteInvalidosException;
import com.iff.dev_web.exception.DadosFuncionarioInvalidosException;
import com.iff.dev_web.repository.ClienteRepository;
import com.iff.dev_web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private static final String TELEFONE_REGEX = "^\\d{10,11}$";
    private static final String CPF_REGEX = "\\d{11}";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new DadosClienteInvalidosException("Cliente com ID " + id + " não encontrado."));
    }

    public Cliente salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new DadosClienteInvalidosException("Cliente com ID " + id + " não encontrado."));
        clienteRepository.delete(cliente);
    }

    public List<Cliente> buscarClientesPorScore(Integer score) {
        return clienteRepository.buscarClientePorScore(score);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new DadosClienteInvalidosException("Cliente com ID " + id + " não encontrado."));

        clienteExistente.setUsuario(clienteAtualizado.getUsuario());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setNuDocumento(clienteAtualizado.getNuDocumento());
        clienteExistente.setNuTelefone(clienteAtualizado.getNuTelefone());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
        clienteExistente.setScoreCredito(clienteAtualizado.getScoreCredito());
        clienteExistente.setLimiteCreditoFinanciamento(clienteAtualizado.getLimiteCreditoFinanciamento());
        clienteExistente.setRenda(clienteAtualizado.getRenda());

        return clienteRepository.save(clienteExistente);
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

    private void validarScoreCredito(Integer scoreCredito) {
        if (scoreCredito == null || scoreCredito < 0 || scoreCredito > 1000) {
            throw new DadosClienteInvalidosException("O score de crédito deve estar entre 0 e 1000.");
        }
    }

    private void validarLimiteCredito(BigDecimal limiteCreditoFinanciamento) {
        if (limiteCreditoFinanciamento == null || limiteCreditoFinanciamento.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DadosClienteInvalidosException("O limite de crédito deve ser um valor positivo.");
        }
    }

    private void validarRenda(BigDecimal renda) {
        if (renda == null || renda.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DadosClienteInvalidosException("A renda deve ser um valor positivo.");
        }
    }

    private void validarCliente(Cliente cliente) {
        usuarioService.validarDocumentoEEmailUnicos(cliente);
        usuarioService.validarDataNascimento(cliente.getDataNascimento());
        validarTelefone(cliente.getNuTelefone());
        validarCPF(cliente.getNuDocumento());
        validarScoreCredito(cliente.getScoreCredito());
        validarLimiteCredito(cliente.getLimiteCreditoFinanciamento());
        validarRenda(cliente.getRenda());
    }
}