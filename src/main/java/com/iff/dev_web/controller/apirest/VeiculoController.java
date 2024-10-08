package com.iff.dev_web.controller.apirest;

import com.iff.dev_web.entities.Veiculo;
import com.iff.dev_web.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Operation(summary = "Criar novo veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo veiculoCriado = veiculoService.criarVeiculo(veiculo);
        veiculoCriado.add(WebMvcLinkBuilder.linkTo(methodOn(VeiculoController.class).buscarVeiculosPorId(veiculoCriado.getCdVeiculo())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoCriado);
    }

    @Operation(summary = "Atualizar um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class)) }),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
        veiculoAtualizado.add(WebMvcLinkBuilder.linkTo(methodOn(VeiculoController.class).buscarVeiculosPorId(veiculoAtualizado.getCdVeiculo())).withSelfRel());
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @Operation(summary = "Deletar um veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar todos os veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso não permitido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @GetMapping
    public List<Veiculo> buscarTodosVeiculos() {
        List<Veiculo> veiculos = veiculoService.buscarTodosVeiculos();

        return veiculos.stream().map(veiculo -> {
            veiculo.add(WebMvcLinkBuilder.linkTo(methodOn(VeiculoController.class)
                    .buscarTodosVeiculos()).withSelfRel());
            return veiculo;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar veículo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class)) }),
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
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarVeiculosPorId(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.buscarVeiculosPorId(id);
        veiculo.add(WebMvcLinkBuilder.linkTo(methodOn(VeiculoController.class).buscarVeiculosPorId(id)).withSelfRel());
        return ResponseEntity.ok(veiculo);
    }

    @Operation(summary = "Buscar veículos por marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class)) }),
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
    @GetMapping("/marca/{marca}")
    public List<Veiculo> buscarVeiculosPorMarca(
            @Parameter
            @PathVariable String marca) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorMarca(marca);

        return veiculos.stream().map(veiculo -> {
            veiculo.add(WebMvcLinkBuilder.linkTo(methodOn(VeiculoController.class)
                    .buscarVeiculosPorMarca(marca)).withSelfRel());
            return veiculo;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar veículos por modelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class)) }),
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
    @GetMapping("/modelo/{modelo}")
    public List<Veiculo> buscarVeiculosPorModelo(
            @Parameter
            @PathVariable String modelo) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorModelo(modelo);

        return veiculos.stream().map(veiculo -> {
            veiculo.add(WebMvcLinkBuilder.linkTo(methodOn(VeiculoController.class)
                    .buscarVeiculosPorModelo(modelo)).withSelfRel());
            return veiculo;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar veículos por ano de fabricação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class)) }),
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
    @GetMapping("/ano/{anoFabricao}")
    public List<Veiculo> buscarVeiculosPorAno(
            @Parameter
            @PathVariable Integer anoFabricao) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorAno(anoFabricao);

        return veiculos.stream().map(veiculo -> {
            veiculo.add(WebMvcLinkBuilder.linkTo(methodOn(VeiculoController.class)
                    .buscarVeiculosPorAno(anoFabricao)).withSelfRel());
            return veiculo;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar veículos por valor máximo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class)) }),
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
    @GetMapping("/valor/{valorMax}")
    public List<Veiculo> buscarVeiculosPorValor(
            @Parameter
            @PathVariable BigDecimal valorMax) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorValor(valorMax);

        return veiculos.stream().map(veiculo -> {
            veiculo.add(WebMvcLinkBuilder.linkTo(methodOn(VeiculoController.class)
                    .buscarVeiculosPorValor(valorMax)).withSelfRel());
            return veiculo;
        }).collect(Collectors.toList());
    }
}