package com.iff.dev_web.service;

import com.iff.dev_web.entities.CdStatusEnum;
import com.iff.dev_web.entities.Financiamento;
import com.iff.dev_web.exception.DadosFinanciamentoInvalidosException;
import com.iff.dev_web.repository.FinanciamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinanciamentoService {

    @Autowired
    private FinanciamentoRepository financiamentoRepository;

    public List<Financiamento> buscarFinanciamentoPorStatus(CdStatusEnum status) {
        validarStatus(status);
        return financiamentoRepository.buscarFinanciamentoPorStatus(status);
    }

    public Financiamento buscarFinanciamentoPorNuContrato(String nuContrato) {
        return financiamentoRepository.findByNuContrato(nuContrato)
                .orElseThrow(() -> new DadosFinanciamentoInvalidosException("Financiamento não encontrado com o número de contrato: " + nuContrato));
    }

    public List<Financiamento> buscarTodosFinanciamentos() {
        return financiamentoRepository.findAll();
    }

    public Financiamento salvarFinanciamento(Financiamento financiamento) {
        validarFinanciamento(financiamento);
        return financiamentoRepository.save(financiamento);
    }

    public Financiamento atualizarFinanciamento(String nuContrato, Financiamento financiamentoAtualizado) {
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

    public void excluirFinanciamentoPorNuContrato(String nuContrato) {
        Financiamento financiamento = buscarFinanciamentoPorNuContrato(nuContrato);
        financiamentoRepository.delete(financiamento);
    }

    public void validarFinanciamento(Financiamento financiamento) {
        validarValorFinanciamento(financiamento.getValorFinanciamento());
        validarTaxaJuros(financiamento.getTaxaJuros());
        validarQuantidadeParcelas(financiamento.getQtParcelas());
        validarDatas(financiamento.getDtInicioFinanciamento(), financiamento.getDtFimFinanciamento());
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

    private void validarDatas(LocalDateTime dtInicio, LocalDateTime dtFim) {
        if (dtInicio == null || dtFim == null || dtFim.isBefore(dtInicio)) {
            throw new DadosFinanciamentoInvalidosException("Datas de início e fim de financiamento inválidas");
        }
    }

    private void validarStatus(CdStatusEnum status) {
        if (status == null) {
            throw new DadosFinanciamentoInvalidosException("Status de financiamento inválido fornecido");
        }
    }
}
