package com.iff.dev_web.service;

import com.iff.dev_web.entities.LojaFilial;
import com.iff.dev_web.exception.DadosLojaFilialInvalidosException;
import com.iff.dev_web.repository.LojaFilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaFilialService {

    @Autowired
    private LojaFilialRepository lojaFilialRepository;

    public List<LojaFilial> buscarLojaPorNome(String nome) {
        validarNome(nome);
        return lojaFilialRepository.buscarLojaPorNome(nome);
    }

    public List<LojaFilial> buscarLojaPorEndereco(String endereco) {
        validarEndereco(endereco);
        return lojaFilialRepository.buscarLojaPorEndereco(endereco);
    }

    public List<LojaFilial> buscarLojaPorTelefone(String telefone) {
        validarTelefone(telefone);
        return lojaFilialRepository.buscarLojaPorTelefone(telefone);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosLojaFilialInvalidosException("Nome de loja inválido fornecido.");
        }
    }

    private void validarEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new DadosLojaFilialInvalidosException("Endereço inválido fornecido.");
        }
    }

    private void validarTelefone(String telefone) {
        if (telefone == null || !telefone.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            throw new DadosLojaFilialInvalidosException("Telefone inválido fornecido.");
        }
    }
}
