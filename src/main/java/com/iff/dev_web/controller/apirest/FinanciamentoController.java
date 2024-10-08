package com.iff.dev_web.controller.apirest;

import com.iff.dev_web.entities.CdStatusEnum;
import com.iff.dev_web.entities.Financiamento;
import com.iff.dev_web.service.FinanciamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/financiamentos")
public class FinanciamentoController {

    @Autowired
    private FinanciamentoService financiamentoService;

    @Operation(summary = "Buscar financiamentos por status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Financiamentos encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Financiamento.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso não permitido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping("/status/{status}")
    public List<Financiamento> buscarFinanciamentoPorStatus(
            @Parameter
            @PathVariable CdStatusEnum status) {
        List<Financiamento> financiamentos = financiamentoService.buscarFinanciamentoPorStatus(status);

        return financiamentos.stream().map(financiamento ->
                financiamento.add(
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FinanciamentoController.class)
                                .buscarFinanciamentoPorStatus(status)).withSelfRel())
        ).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar financiamento por número de contrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Financiamento encontrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Financiamento.class)) }),
            @ApiResponse(responseCode = "404", description = "Financiamento não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping("/{nuContrato}")
    public Financiamento buscarFinanciamentoPorNuContrato(@PathVariable String nuContrato) {
        return financiamentoService.buscarFinanciamentoPorNuContrato(nuContrato);
    }


    @Operation(summary = "Buscar todos os financiamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Financiamentos encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Financiamento.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping
    public List<Financiamento> buscarTodosFinanciamentos() {
        List<Financiamento> financiamentos = financiamentoService.buscarTodosFinanciamentos();

        return financiamentos.stream().map(financiamento ->
                financiamento.add(WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(FinanciamentoController.class)
                                        .buscarTodosFinanciamentos())
                        .withSelfRel())
        ).collect(Collectors.toList());
    }

    @Operation(summary = "Criar um novo financiamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Financiamento criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Financiamento.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PostMapping
    public Financiamento criarFinanciamento(@RequestBody Financiamento novoFinanciamento) {
        return financiamentoService.salvarFinanciamento(novoFinanciamento);
    }

    @Operation(summary = "Atualizar financiamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Financiamento atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Financiamento.class)) }),
            @ApiResponse(responseCode = "404", description = "Financiamento não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PutMapping("/{nuContrato}")
    public Financiamento atualizarFinanciamento(
            @PathVariable String nuContrato,
            @RequestBody Financiamento financiamentoAtualizado) {
        return financiamentoService.atualizarFinanciamento(nuContrato, financiamentoAtualizado);
    }

    @Operation(summary = "Deletar financiamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Financiamento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Financiamento não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @DeleteMapping("/{nuContrato}")
    public void excluirFinanciamentoPorNuContrato(@PathVariable String nuContrato) {
        financiamentoService.excluirFinanciamentoPorNuContrato(nuContrato);
    }
}
