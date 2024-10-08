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

    public LojaFilial criarLoja(LojaFilial lojaFilial) {
        validarNome(lojaFilial.getNome());
        validarEndereco(lojaFilial.getEndereco());
        validarTelefone(lojaFilial.getNuTelefone());
        return lojaFilialRepository.save(lojaFilial);
    }

    public LojaFilial atualizarLoja(Long id, LojaFilial lojaFilialAtualizada) {
        LojaFilial lojaFilial = lojaFilialRepository.findById(id)
                .orElseThrow(() -> new DadosLojaFilialInvalidosException("Loja com ID " + id + " não encontrada."));

        validarNome(lojaFilialAtualizada.getNome());
        validarEndereco(lojaFilialAtualizada.getEndereco());
        validarTelefone(lojaFilialAtualizada.getNuTelefone());

        lojaFilial.setNome(lojaFilialAtualizada.getNome());
        lojaFilial.setEndereco(lojaFilialAtualizada.getEndereco());
        lojaFilial.setNuTelefone(lojaFilialAtualizada.getNuTelefone());

        return lojaFilialRepository.save(lojaFilial);
    }

    public void deletarLoja(Long id) {
        LojaFilial lojaFilial = lojaFilialRepository.findById(id)
                .orElseThrow(() -> new DadosLojaFilialInvalidosException("Loja com ID " + id + " não encontrada."));
        lojaFilialRepository.delete(lojaFilial);
    }

    public List<LojaFilial> buscarTodasLojas() {
        return lojaFilialRepository.findAll();
    }

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
