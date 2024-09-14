package com.iff.dev_web.Service;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.entities.Usuario;
import com.iff.dev_web.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodosClientes() {
        List<Cliente> mockClientes = new ArrayList<>();
        mockClientes.add(new Cliente(1L, "Ana Julia", "juliaAna@gmail.com", "123456789", "22991234567", "Endereco", null, 850, null, null));

        when(usuarioRepository.buscarTodosClientes()).thenReturn(mockClientes);

        List<Cliente> result = usuarioService.buscarTodosClientes();

        assertEquals(1, result.size());
        assertEquals("Ana Julia", result.get(0).getUsuario());
    }

    @Test
    public void testBuscarTodosFuncionarios() {
        List<Funcionario> mockFuncionarios = new ArrayList<>();
        mockFuncionarios.add(new Funcionario(1L, "Higor", "higor23@gmail.com", "123456789", "22991234567", "Endereco", null, null, "Atendente", null));
        mockFuncionarios.add(new Funcionario(2L, "Jean Jungkook", "voltaBts@gmail.com", "123456789", "22991234567", "Endereco", null, null, "Atendente", null));

        when(usuarioRepository.buscarTodosFuncionarios()).thenReturn(mockFuncionarios);

        List<Funcionario> result = usuarioService.buscarTodosFuncionarios();

        assertEquals(2, result.size());
        assertEquals("Higor", result.get(0).getUsuario());
        assertEquals("Jean Jungkook", result.get(1).getUsuario());
    }
}

