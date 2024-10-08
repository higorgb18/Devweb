package com.iff.dev_web.service;

import com.iff.dev_web.entities.CdStatusEnum;
import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Financiamento;
import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.entities.Veiculo;
import com.iff.dev_web.repository.FinanciamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FinanciamentoServiceTest {

    @InjectMocks
    private FinanciamentoService financiamentoService;

    @Mock
    private FinanciamentoRepository financiamentoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarFinanciamentoPorStatus() {
        CdStatusEnum status = CdStatusEnum.Vigente;
        List<Financiamento> mockFinanciamentos = new ArrayList<>();
        mockFinanciamentos.add(new Financiamento("F1234567890", new Veiculo(), new Cliente(), new Funcionario(), status,
                new BigDecimal(30000), new BigDecimal(2.5), 36, LocalDateTime.now(), LocalDateTime.now().plusYears(3), LocalDateTime.now()));

        when(financiamentoRepository.buscarFinanciamentoPorStatus(status)).thenReturn(mockFinanciamentos);

        List<Financiamento> result = financiamentoService.buscarFinanciamentoPorStatus(status);

        assertEquals(1, result.size());
        assertEquals(status, result.get(0).getCdStatus());
    }
}