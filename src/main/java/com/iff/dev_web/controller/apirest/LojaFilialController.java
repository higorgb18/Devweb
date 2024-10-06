package com.iff.dev_web.controller.apirest;

import com.iff.dev_web.entities.LojaFilial;
import com.iff.dev_web.service.LojaFilialService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/lojas")
public class LojaFilialController {

    @Autowired
    private LojaFilialService lojaFilialService;

    @Operation(summary = "Buscar loja por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loja encontrada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LojaFilial.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso não permitido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public List<LojaFilial> buscarLojaPorNome(
            @Parameter @PathVariable String nome) {
        List<LojaFilial> lojas = lojaFilialService.buscarLojaPorNome(nome);

        return lojas.stream().map(loja -> {
            loja.add(WebMvcLinkBuilder.linkTo(methodOn(LojaFilialController.class)
                    .buscarLojaPorNome(nome)).withSelfRel());
            return loja;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar loja por endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loja encontrada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LojaFilial.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso não permitido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping("/endereco/{endereco}")
    public List<LojaFilial> buscarLojaPorEndereco(
            @Parameter @PathVariable String endereco) {
        List<LojaFilial> lojas = lojaFilialService.buscarLojaPorEndereco(endereco);

        return lojas.stream().map(loja -> {
            loja.add(WebMvcLinkBuilder.linkTo(methodOn(LojaFilialController.class)
                    .buscarLojaPorEndereco(endereco)).withSelfRel());
            return loja;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar loja por telefone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loja encontrada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LojaFilial.class)) }),
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
    @GetMapping("/telefone/{telefone}")
    public List<LojaFilial> buscarLojaPorTelefone(
            @Parameter @PathVariable String telefone) {
        List<LojaFilial> lojas = lojaFilialService.buscarLojaPorTelefone(telefone);

        return lojas.stream().map(loja -> {
            loja.add(WebMvcLinkBuilder.linkTo(methodOn(LojaFilialController.class)
                    .buscarLojaPorTelefone(telefone)).withSelfRel());
            return loja;
        }).collect(Collectors.toList());
    }
}
