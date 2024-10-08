package com.iff.dev_web.service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.exception.DadosClienteInvalidosException;
import com.iff.dev_web.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new DadosClienteInvalidosException("Cliente com ID " + id + " não encontrado."));
    }

    public Cliente salvarCliente(Cliente cliente) {
        validarDocumentoEEmailUnicos(cliente);
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

    private void validarDocumentoEEmailUnicos(Cliente cliente) {
        Optional<Cliente> clienteExistentePorDocumento = clienteRepository.findByNuDocumento(cliente.getNuDocumento());
        Optional<Cliente> clienteExistentePorEmail = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteExistentePorDocumento.isPresent()) {
            throw new DadosClienteInvalidosException("Documento já existente na base de dados: " + cliente.getNuDocumento());
        }

        if (clienteExistentePorEmail.isPresent()) {
            throw new DadosClienteInvalidosException("E-mail já existente na base de dados: " + cliente.getEmail());
        }
    }
}