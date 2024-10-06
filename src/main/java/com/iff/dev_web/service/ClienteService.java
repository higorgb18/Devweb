package com.iff.dev_web.service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.exception.DadosClienteInvalidosException;
import com.iff.dev_web.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarClientesPorScore(Integer score) {
        validarScore(score);
        return clienteRepository.buscarClientePorScore(score);
    }

    private void validarScore(Integer score) {
        if (score == null || score < 0 || score > 1000) {
            throw new DadosClienteInvalidosException("Score de crédito inválido fornecido.");
        }
    }
}