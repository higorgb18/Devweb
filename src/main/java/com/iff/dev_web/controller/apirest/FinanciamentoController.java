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
}
