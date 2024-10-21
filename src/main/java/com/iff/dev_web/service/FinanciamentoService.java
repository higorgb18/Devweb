package com.iff.dev_web.service;

import com.iff.dev_web.entities.CdStatusEnum;
import com.iff.dev_web.entities.Financiamento;
import com.iff.dev_web.exception.DadosFinanciamentoInvalidosException;
import com.iff.dev_web.repository.FinanciamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FinanciamentoService {

    @Autowired
    private FinanciamentoRepository financiamentoRepository;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private ClienteService clienteService;

    public List<Financiamento> buscarFinanciamentoPorStatus(CdStatusEnum status) {
        validarStatus(status);
        return financiamentoRepository.buscarFinanciamentoPorStatus(status);
    }

    public Financiamento buscarFinanciamentoPorNuContrato(Long nuContrato) {
        return financiamentoRepository.findByNuContrato(nuContrato)
                .orElseThrow(() -> new DadosFinanciamentoInvalidosException("Financiamento não encontrado com o número de contrato: " + nuContrato));
    }

    public List<Financiamento> buscarTodosFinanciamentos() {
        return financiamentoRepository.findAll();
    }

    public Financiamento salvarFinanciamento(Financiamento financiamento) {
        BigDecimal valorFinanciamento = calcularValorFinanciamento(financiamento.getVeiculo().getValor(), financiamento.getTaxaJuros(), financiamento.getQtParcelas());
        financiamento.setValorFinanciamento(valorFinanciamento);

        clienteService.buscarClientePorId(financiamento.getCliente().getCdUsuario());
        validarFinanciamento(financiamento);
        calcularDatasFinanciamento(financiamento);
        veiculoService.validarVeiculo(financiamento.getVeiculo());

        return financiamentoRepository.save(financiamento);
    }

    public Financiamento atualizarFinanciamento(Long nuContrato, Financiamento financiamentoAtualizado) {
        Financiamento financiamentoExistente = financiamentoRepository.findById(nuContrato)
                .orElseThrow(() -> new DadosFinanciamentoInvalidosException("Financiamento não encontrado para o contrato: " + nuContrato));

        financiamentoExistente.setCdStatus(financiamentoAtualizado.getCdStatus());
        financiamentoExistente.setValorFinanciamento(financiamentoAtualizado.getValorFinanciamento());
        financiamentoExistente.setTaxaJuros(financiamentoAtualizado.getTaxaJuros());
        financiamentoExistente.setQtParcelas(financiamentoAtualizado.getQtParcelas());
        financiamentoExistente.setDtInicioFinanciamento(financiamentoAtualizado.getDtInicioFinanciamento());
        financiamentoExistente.setDtFimFinanciamento(financiamentoAtualizado.getDtFimFinanciamento());
        financiamentoExistente.setDtAtualizacao(financiamentoAtualizado.getDtAtualizacao());

        return financiamentoRepository.save(financiamentoExistente);
    }

    public void excluirFinanciamentoPorNuContrato(Long nuContrato) {
        Financiamento financiamento = buscarFinanciamentoPorNuContrato(nuContrato);
        financiamentoRepository.delete(financiamento);
    }

    public BigDecimal calcularValorFinanciamento(BigDecimal valorVeiculo, BigDecimal taxaJurosAnual, int quantidadeParcelas) {
        BigDecimal taxaJurosDecimal = taxaJurosAnual.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

        BigDecimal tempoAnos = BigDecimal.valueOf(quantidadeParcelas).divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        BigDecimal montante = valorVeiculo.multiply(
                BigDecimal.ONE.add(taxaJurosDecimal).pow(tempoAnos.intValue())
        );

        validarValorFinanciamento(montante);

        return montante.setScale(2, RoundingMode.HALF_UP);
    }

    private void calcularDatasFinanciamento(Financiamento financiamento) {
        financiamento.setDtInicioFinanciamento(LocalDateTime.now());

        LocalDateTime dataFim = financiamento.getDtInicioFinanciamento().plusMonths(financiamento.getQtParcelas());
        financiamento.setDtFimFinanciamento(dataFim);
    }

    public void validarFinanciamento(Financiamento financiamento) {
        validarTaxaJuros(financiamento.getTaxaJuros());
        validarQuantidadeParcelas(financiamento.getQtParcelas());
    }

    private void validarValorFinanciamento(BigDecimal valorFinanciamento) {
        if (valorFinanciamento == null || valorFinanciamento.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DadosFinanciamentoInvalidosException("Valor de financiamento inválido fornecido");
        }
    }

    private void validarTaxaJuros(BigDecimal taxaJuros) {
        if (taxaJuros == null || taxaJuros.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DadosFinanciamentoInvalidosException("Taxa de juros inválida fornecida");
        }
    }

    private void validarQuantidadeParcelas(Integer qtParcelas) {
        if (qtParcelas == null || qtParcelas <= 0) {
            throw new DadosFinanciamentoInvalidosException("Quantidade de parcelas inválida fornecida");
        }
    }

    private void validarStatus(CdStatusEnum status) {
        if (status == null) {
            throw new DadosFinanciamentoInvalidosException("Status de financiamento inválido fornecido");
        }
    }
}
