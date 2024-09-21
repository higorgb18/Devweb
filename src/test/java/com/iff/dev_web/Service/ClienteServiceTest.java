package com.iff.dev_web.Service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.repository.ClienteRepository;
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

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarClientesPorScore() {
        Integer scoreMinimo = 800;
        List<Cliente> mockClientes = new ArrayList<>();
        mockClientes.add(new Cliente(1L, "Ana Julia", "juliaAna@gmail.com.br", "10987654321", "22994576329", "Lessence P",
                null, 850, new BigDecimal(500000), new BigDecimal(45000)));

        when(clienteRepository.buscarClientePorScore(scoreMinimo)).thenReturn(mockClientes);
        List<Cliente> result = clienteService.buscarClientesPorScore(scoreMinimo);

        assertEquals(1, result.size());
        assertEquals("Ana Julia", result.get(0).getUsuario());
    }
}