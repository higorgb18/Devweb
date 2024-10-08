package com.iff.dev_web.service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.exception.DadosUsuarioInvalidosException;
import com.iff.dev_web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

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

    public void validarEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new DadosUsuarioInvalidosException("Email inválido fornecido.");
        }
    }

    public void validarDocumento(String documento) {
        if (documento == null || documento.length() < 11 || documento.length() > 14) {
            throw new DadosUsuarioInvalidosException("Documento inválido fornecido.");
        }
    }
}
