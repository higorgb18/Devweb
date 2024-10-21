package com.iff.dev_web.controller.view;

import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.service.FuncionarioService;
import com.iff.dev_web.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Funcionario> funcionarios = funcionarioService.buscarTodosFuncionarios();
        List<Cliente> clientes = clienteService.buscarTodosClientes();

        List<Object> usuarios = new ArrayList<>();
        usuarios.addAll(funcionarios);
        usuarios.addAll(clientes);

        model.addAttribute("usuarios", usuarios);

        return "listarUsuarios";
    }
}
