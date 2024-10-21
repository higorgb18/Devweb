package com.iff.dev_web.controller.view;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Financiamento;
import com.iff.dev_web.service.ClienteService;
import com.iff.dev_web.service.FinanciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/financiamentos")
public class FinanciamentoViewController {

    @Autowired
    private FinanciamentoService financiamentoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cadastrar")
    public String showCadastroForm(Model model) {
        model.addAttribute("financiamento", new Financiamento());
        return "cadastrarFinanciamento";
    }

    @PostMapping
    public String salvarFinanciamento(Financiamento financiamento) {
        financiamentoService.salvarFinanciamento(financiamento);
        return "redirect:/financiamentos";
    }

    @GetMapping
    public String listarFinanciamentos(Model model) {
        List<Financiamento> financiamentos = financiamentoService.buscarTodosFinanciamentos();
        model.addAttribute("financiamentos", financiamentos);
        return "listarFinanciamentos";
    }

    @GetMapping("/{id}")
    public String consultarFinanciamento(@PathVariable Long id, Model model) {
        Financiamento financiamento = financiamentoService.buscarFinanciamentoPorNuContrato(id);
        model.addAttribute("financiamento", financiamento);
        return "consultarFinanciamento";
    }

    @GetMapping("/editar/{id}")
    public String exibirEditarFinanciamento(@PathVariable Long id, Model model) {
        Financiamento financiamento = financiamentoService.buscarFinanciamentoPorNuContrato(id);
        model.addAttribute("financiamento", financiamento);
        return "editarFinanciamento";
    }

    @PostMapping("/editar")
    public String atualizarFinanciamento(Financiamento financiamento) {
        Cliente clienteExistente = clienteService.buscarClientePorId(financiamento.getCliente().getCdUsuario());
        financiamento.setCliente(clienteExistente);

        financiamentoService.salvarFinanciamento(financiamento);
        return "redirect:/financiamentos";
    }

    @PostMapping("/{id}/excluir")
    public String excluirFinanciamento(@PathVariable Long id) {
        financiamentoService.excluirFinanciamentoPorNuContrato(id);
        return "redirect:/financiamentos";
    }
}
