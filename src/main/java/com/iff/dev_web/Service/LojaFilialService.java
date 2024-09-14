package com.iff.dev_web.Service;

import com.iff.dev_web.entities.LojaFilial;
import com.iff.dev_web.repository.LojaFilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LojaFilialService {

    @Autowired
    private LojaFilialRepository lojaFilialRepository;

    public List<LojaFilial> buscarLojaPorNome(String nome) {
        return lojaFilialRepository.buscarLojaPorNome(nome);
    }

    public List<LojaFilial> buscarLojaPorEndereco(String endereco) {
        return lojaFilialRepository.buscarLojaPorEndereco(endereco);
    }

    public List<LojaFilial> buscarLojaPorTelefone(String telefone) {
        return lojaFilialRepository.buscarLojaPorTelefone(telefone);
    }
}

