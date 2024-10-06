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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

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
}
