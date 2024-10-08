package com.iff.dev_web.service;

import com.iff.dev_web.entities.CdTipoCombustivelEnum;
import com.iff.dev_web.entities.Veiculo;
import com.iff.dev_web.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarVeiculosPorModelo() {
        String modelo = "Civic";
        List<Veiculo> mockVeiculos = new ArrayList<>();
        mockVeiculos.add(new Veiculo(1L, 1, "ABC-1234", "1HGCM82633A123456", new BigDecimal(30000), "Honda", modelo, 2020, CdTipoCombustivelEnum.Gasolina, 1023L, 50000));

        when(veiculoRepository.buscarVeiculosPorModelo(modelo)).thenReturn(mockVeiculos);
        List<Veiculo> result = veiculoService.buscarVeiculosPorModelo(modelo);

        assertEquals(1, result.size());
        assertEquals("Civic", result.get(0).getModelo());
    }

    @Test
    public void testBuscarVeiculosPorValor() {
        BigDecimal valorMax = new BigDecimal(30000);
        List<Veiculo> mockVeiculos = new ArrayList<>();
        mockVeiculos.add(new Veiculo(1L, 1, "ABC-1234", "1HGCM82633A123456", valorMax, "Honda", "Civic", 2020, CdTipoCombustivelEnum.Gasolina, 1023L, 50000));

        when(veiculoRepository.buscarVeiculosPorValor(valorMax)).thenReturn(mockVeiculos);
        List<Veiculo> result = veiculoService.buscarVeiculosPorValor(valorMax);

        assertEquals(1, result.size());
        assertEquals(valorMax, result.get(0).getValor());
    }
}

