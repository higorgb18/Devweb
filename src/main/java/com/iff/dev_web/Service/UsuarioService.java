package com.iff.dev_web.Service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Funcionario;
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
}

