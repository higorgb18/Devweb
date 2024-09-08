package com.iff.dev_web;

import com.iff.dev_web.entities.*;
import com.iff.dev_web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    LojaFilialRepository lojaFilialRepository;
    @Autowired
    VeiculoRepository veiculoRepository;
    @Autowired
    FinanciamentoRepository financiamentoRepository;

    @Override
    public void run(String... args) throws Exception {

        Integer scoreMinimo = 800;
        String cargoFuncionario = "Atendente";
        String nomeLoja = "Loja Central";
        String modeloVeiculo = "Civic";

        LojaFilial lojaFilial1 = new LojaFilial(
                null,
                "Loja Central",
                "Rua Principal, 123",
                "22991234567");
        lojaFilialRepository.save(lojaFilial1);

        Funcionario funcionario1 = new Funcionario(
                null,
                "Higor",
                "higor23@gmail.com.br",
                "12345678910",
                "22991472589",
                "New Brasília",
                LocalDate.parse("2000-10-26"),
                new BigDecimal(3500),
                "Atendente",
                lojaFilial1);
        usuarioRepository.save(funcionario1);

        Cliente cliente1 = new Cliente(
                null,
                "Ana Julia",
                "juliaAna@gmail.com.br",
                "10987654321",
                "22994576329",
                "Lessence P",
                LocalDate.parse("2001-07-04"),
                999,
                new BigDecimal(500000),
                new BigDecimal(45000));
        usuarioRepository.save(cliente1);

        Veiculo veiculo1 = new Veiculo(
                null,
                1,
                "ABC-1234",
                "1HGCM82633A123456",
                new BigDecimal(30000),
                "Honda",
                "Civic",
                2020,
                "Gasolina",
                1023L,
                50000);
        veiculoRepository.save(veiculo1);

        Financiamento financiamento1 = new Financiamento(
                "F1234567890",
                veiculo1,
                cliente1,
                funcionario1,
                CdStatusEnum.Vigente,
                new BigDecimal(30000),
                new BigDecimal(2.5),
                36,
                LocalDateTime.now(),
                LocalDateTime.now().plusYears(3),
                null);
        financiamentoRepository.save(financiamento1);

        List<Cliente> clientesScoreAlto = clienteRepository.buscarClientePorScore(scoreMinimo);
        List<Funcionario> funcionariosEncontrados = funcionarioRepository.buscarFuncionarioPorCargo(cargoFuncionario);
        List<LojaFilial> lojasEncontradas = lojaFilialRepository.buscarLojaPorNome(nomeLoja);
        List<Veiculo> veiculosEncontrados = veiculoRepository.buscarVeiculosPorModelo(modeloVeiculo);
        List<Financiamento> financiamentosEmAnalise = financiamentoRepository.buscarFinanciamentoPorStatus(CdStatusEnum.Analise);

        System.out.println("ID: " + funcionario1.getCdUsuario() +
                " | Usuário: " + funcionario1.getUsuario() +
                " | E-mail: " + funcionario1.getEmail() +
                " | Documento: " + funcionario1.getNuDocumento() +
                " | Telefone: " + funcionario1.getNuTelefone() +
                " | Endereço: " + funcionario1.getEndereco() +
                " | Data de nascimento: " + funcionario1.getDataNascimento() +
                " | Salário: " + funcionario1.getSalario() +
                " | Cargo: " + funcionario1.getCargo() +
                " | Loja filial: " + funcionario1.getLojaFilial().getNome());

        System.out.println("ID: " + cliente1.getCdUsuario() +
                " | Usuário: " + cliente1.getUsuario() +
                " | E-mail: " + cliente1.getEmail() +
                " | Documento: " + cliente1.getNuDocumento() +
                " | Telefone: " + cliente1.getNuTelefone() +
                " | Endereço: " + cliente1.getEndereco() +
                " | Data de nascimento: " + cliente1.getDataNascimento() +
                " | Score Serasa: " + cliente1.getScoreCredito() +
                " | Limite de crédito: " + cliente1.getLimiteCreditoFinanciamento() +
                " | Renda: " + cliente1.getRenda());

        System.out.println("\nNúmero de contrato: " + financiamento1.getNuContrato() +
                " | Veículo: " + financiamento1.getVeiculo().getModelo() +
                " | Cliente: " + financiamento1.getCliente().getUsuario() +
                " | Funcionário responsável: " + financiamento1.getFuncionario().getUsuario() +
                " | Valor: " + financiamento1.getValorFinanciamento() +
                " | Status: " + financiamento1.getCdStatus());

        System.out.println("\nClientes com score maior ou igual a " + scoreMinimo + ":");
        for (Cliente cliente : clientesScoreAlto) {
            System.out.println("ID: " + cliente.getCdUsuario() +
                    " | Nome: " + cliente.getUsuario() +
                    " | Score de Crédito: " + cliente.getScoreCredito());
        }

        System.out.println("\nFuncionarios atendentes: ");
        for (Funcionario funcionario : funcionariosEncontrados) {
            System.out.println("ID: " + funcionario.getCdUsuario() +
                    " | Nome: " + funcionario.getUsuario() +
                    " | Salário: " + funcionario.getSalario());
        }

        System.out.println("\nLojas encontradas com o nome '" + nomeLoja + "':");
        for (LojaFilial loja : lojasEncontradas) {
            System.out.println("ID: " + loja.getCdLojaFilial() +
                    " | Nome: " + loja.getNome() +
                    " | Endereço: " + loja.getEndereco() +
                    " | Telefone: " + loja.getNuTelefone());
        }

        System.out.println("\nVeículos encontrados com o modelo '" + modeloVeiculo + "':");
        for (Veiculo veiculo : veiculosEncontrados) {
            System.out.println("ID: " + veiculo.getCdVeiculo() +
                    " | Modelo: " + veiculo.getModelo() +
                    " | Marca: " + veiculo.getMarca() +
                    " | Placa: " + veiculo.getPlaca() +
                    " | Valor: " + veiculo.getValor() +
                    " | Ano: " + veiculo.getAnoModelo() +
                    " | Tipo de combustível: " + veiculo.getTipoCombustivel());
        }

        System.out.println("\nFinanciamentos com status em análise: ");
        for (Financiamento financiamento : financiamentosEmAnalise) {
            System.out.println("\nNúmero de contrato: " + financiamento.getNuContrato() +
                    " | Valor: " + financiamento.getValorFinanciamento() +
                    " | Cliente: " + financiamento.getCliente().getUsuario() +
                    " | Funcionário responsável: " + financiamento.getFuncionario().getUsuario() +
                    " | Veículo: " + financiamento.getVeiculo().getModelo() +
                    " | Status: " + financiamento.getCdStatus());
        }
    }
}
