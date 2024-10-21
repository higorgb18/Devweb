package com.iff.dev_web.controller.view;

import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioViewController {

    @Autowired
    private FuncionarioService funcionarioService;


    @GetMapping("/cadastrar")
    public String showCadastroForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "cadastrarFuncionario";
    }

    @PostMapping()
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario) {
        funcionarioService.criarFuncionario(funcionario);
        return "redirect:/usuarios";
    }
}
