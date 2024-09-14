package com.iff.dev_web.Service;

import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.repository.FuncionarioRepository;
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

public class FuncionarioServiceTest {
    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarFuncionariosPorCargo() {
        String cargo = "Atendente";
        List<Funcionario> mockFuncionarios = new ArrayList<>();
        mockFuncionarios.add(new Funcionario(1L, "Higor", "higor23@gmail.com", "123456789", "123456789",
                "Endereco", null, new BigDecimal(3500), cargo, null));

        when(funcionarioRepository.buscarFuncionarioPorCargo(cargo)).thenReturn(mockFuncionarios);
        List<Funcionario> result = funcionarioService.buscarFuncionariosPorCargo(cargo);

        assertEquals(1, result.size());
        assertEquals("Higor", result.get(0).getUsuario());
    }
}
