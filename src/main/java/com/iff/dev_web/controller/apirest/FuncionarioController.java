package com.iff.dev_web.controller.apirest;

import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(summary = "Criar um novo funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PostMapping
    public Funcionario criarFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.criarFuncionario(funcionario);
    }

    @Operation(summary = "Atualizar um funcionário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Funcionario atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return funcionarioService.atualizarFuncionario(id, funcionario);
    }

    @Operation(summary = "Excluir um funcionário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public void excluirFuncionario(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
    }

    @Operation(summary = "Buscar todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionários encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping
    public List<Funcionario> buscarTodosFuncionarios() {
        return funcionarioService.buscarTodosFuncionarios();
    }

    @Operation(summary = "Buscar funcionários por cargo")
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
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping("/cargo/{cargo}")
    public List<Funcionario> buscarFuncionariosPorCargo(
            @Parameter
            @PathVariable String cargo) {
        return funcionarioService.buscarFuncionariosPorCargo(cargo);
    }

    @Operation(summary = "Buscar funcionários por salário")
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
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping("/salario/{salario}")
    public List<Funcionario> buscarFuncionariosPorSalario(
            @Parameter
            @PathVariable BigDecimal salario) {
        return funcionarioService.buscarFuncionariosPorSalario(salario);
    }

    @Operation(summary = "Buscar funcionários por nome da filial")
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
    @GetMapping("/filial/{nomeFilial}")
    public List<Funcionario> buscarFuncionariosPorFilial(
            @Parameter
            @PathVariable String nomeFilial) {
        return funcionarioService.buscarFuncionariosPorFilial(nomeFilial);
    }
}
