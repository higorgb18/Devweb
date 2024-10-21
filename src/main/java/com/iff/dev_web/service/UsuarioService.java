package com.iff.dev_web.service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.entities.Usuario;
import com.iff.dev_web.exception.DadosClienteInvalidosException;
import com.iff.dev_web.exception.DadosUsuarioInvalidosException;
import com.iff.dev_web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private static final int IDADE_MINIMA = 18;
    private static final int IDADE_MAXIMA = 120;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Cliente> buscarTodosClientes() {
        return usuarioRepository.buscarTodosClientes();
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        return usuarioRepository.buscarTodosFuncionarios();
    }

    public void validarUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new DadosUsuarioInvalidosException("Nome de usuário inválido fornecido.");
        }
    }

    public void validarDocumentoEEmailUnicos(Usuario usuario) {
        Optional<Usuario> usuarioExistentePorDocumento = usuarioRepository.findByNuDocumento(usuario.getNuDocumento());
        if (usuarioExistentePorDocumento.isPresent() && !usuarioExistentePorDocumento.get().getCdUsuario().equals(usuario.getCdUsuario())) {
            throw new DadosClienteInvalidosException("Documento já existente na base de dados: " + usuario.getNuDocumento());
        }

        Optional<Usuario> usuarioExistentePorEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistentePorEmail.isPresent() && !usuarioExistentePorEmail.get().getCdUsuario().equals(usuario.getCdUsuario())) {
            throw new DadosClienteInvalidosException("E-mail já existente na base de dados: " + usuario.getEmail());
        }
    }

    public void validarDataNascimento(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();

        int idade = Period.between(dataNascimento, dataAtual).getYears();

        if (idade < IDADE_MINIMA) {
            throw new DadosUsuarioInvalidosException("Idade mínima permitida é " + IDADE_MINIMA + " anos.");
        }

        if (idade > IDADE_MAXIMA) {
            throw new DadosUsuarioInvalidosException("Idade máxima permitida é " + IDADE_MAXIMA + " anos.");
        }
    }
}
