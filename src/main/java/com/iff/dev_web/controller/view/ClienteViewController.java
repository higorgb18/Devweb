package com.iff.dev_web.controller.view;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cadastrar")
    public String showCadastroForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastrarCliente";
    }

    @PostMapping()
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteService.salvarCliente(cliente);
        return "redirect:/usuarios";
    }
}
