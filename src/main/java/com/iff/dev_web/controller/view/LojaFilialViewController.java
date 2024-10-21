package com.iff.dev_web.controller.view;

import com.iff.dev_web.entities.LojaFilial;
import com.iff.dev_web.repository.LojaFilialRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lojas")
public class LojaFilialViewController {

    @Autowired
    private LojaFilialRepository lojaFilialRepository;

    @GetMapping
    public String listarLojas(Model model) {
        model.addAttribute("lojas", lojaFilialRepository.findAll());
        return "lojas";
    }

    @GetMapping("/new")
    public String novaLojaForm(Model model) {
        model.addAttribute("loja", new LojaFilial());
        return "new-loja";
    }

    @PostMapping
    public String salvarLoja(@Valid @ModelAttribute("loja") LojaFilial lojaFilial, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loja", lojaFilial);
            return "new-loja";
        }
        lojaFilialRepository.save(lojaFilial);
        return "redirect:/lojas";
    }

    @GetMapping("/edit/{id}")
    public String editarLojaForm(@PathVariable Long id, Model model) {
        LojaFilial loja = lojaFilialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loja inválida: " + id));
        model.addAttribute("loja", loja);
        return "edit-loja";
    }

    @PostMapping("/update/{id}")
    public String atualizarLoja(@PathVariable Long id, @ModelAttribute LojaFilial lojaFilial) {
        LojaFilial lojaExistente = lojaFilialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loja inválida: " + id));
        lojaExistente.setNome(lojaFilial.getNome());
        lojaExistente.setEndereco(lojaFilial.getEndereco());
        lojaExistente.setNuTelefone(lojaFilial.getNuTelefone());
        lojaFilialRepository.save(lojaExistente);
        return "redirect:/lojas";
    }

    @GetMapping("/delete/{id}")
    public String deletarLoja(@PathVariable Long id) {
        lojaFilialRepository.deleteById(id);
        return "redirect:/lojas";
    }
}
