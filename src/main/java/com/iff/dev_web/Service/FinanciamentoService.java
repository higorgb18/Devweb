package com.iff.dev_web.Service;

import com.iff.dev_web.entities.CdStatusEnum;
import com.iff.dev_web.entities.Financiamento;
import com.iff.dev_web.repository.FinanciamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanciamentoService {

    @Autowired
    private FinanciamentoRepository financiamentoRepository;

    public List<Financiamento> buscarFinanciamentoPorStatus(CdStatusEnum status) {
        return financiamentoRepository.buscarFinanciamentoPorStatus(status);
    }
}
