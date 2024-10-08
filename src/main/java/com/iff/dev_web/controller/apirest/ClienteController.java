package com.iff.dev_web.controller.apirest;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.service.ClienteService;
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
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Salvar um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Documento ou e-mail já existente",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PostMapping
    public Cliente salvarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.salvarCliente(cliente);
        novoCliente.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
                .buscarTodosClientes()).withSelfRel());
        return novoCliente;
    }

    @Operation(summary = "Buscar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping
    public List<Cliente> buscarTodosClientes() {
        List<Cliente> clientes = clienteService.buscarTodosClientes();
        return clientes.stream().map(cliente -> {
            cliente.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
                    .buscarTodosClientes()).withSelfRel());
            return cliente;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@Parameter(description = "ID do cliente") @PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        cliente.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
                .buscarClientePorId(id)).withSelfRel());
        return cliente;
    }

    @Operation(summary = "Buscar clientes com score maior ou igual ao fornecido")
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
    @GetMapping("/score/{score}")
    public List<Cliente> buscarClientesPorScore(
            @Parameter
            @PathVariable Integer score) {
        return clienteService.buscarClientesPorScore(score);
    }

    @Operation(summary = "Deletar um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }

    @Operation(summary = "Atualizar dados de um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Documento ou e-mail já existente",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        clienteAtualizado.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
                .buscarTodosClientes()).withSelfRel());
        return clienteAtualizado;
    }
}
