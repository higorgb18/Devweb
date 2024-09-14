package com.iff.dev_web.Service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarClientesPorScore(Integer score) {
        return clienteRepository.buscarClientePorScore(score);
    }
}

