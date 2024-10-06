package com.iff.dev_web.controller.apirest;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Buscar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
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
    @GetMapping("/clientes")
    public List<Cliente> buscarTodosClientes() {
        List<Cliente> clientes = usuarioService.buscarTodosClientes();

        return clientes.stream().map(cliente -> {
            cliente.add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioController.class)
                    .buscarTodosClientes()).withSelfRel());
            return cliente;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionários encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class)) }),
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
    @GetMapping("/funcionarios")
    public List<Funcionario> buscarTodosFuncionarios() {
        List<Funcionario> funcionarios = usuarioService.buscarTodosFuncionarios();

        return funcionarios.stream().map(funcionario -> {
            funcionario.add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioController.class)
                    .buscarTodosFuncionarios()).withSelfRel());
            return funcionario;
        }).collect(Collectors.toList());
    }
}
