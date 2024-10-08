package com.iff.dev_web.service;

import com.iff.dev_web.entities.LojaFilial;
import com.iff.dev_web.repository.LojaFilialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LojaFilialServiceTest {

    @InjectMocks
    private LojaFilialService lojaFilialService;

    @Mock
    private LojaFilialRepository lojaFilialRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarLojaPorNome() {
        String nomeLoja = "Loja Central";
        List<LojaFilial> mockLojas = new ArrayList<>();
        mockLojas.add(new LojaFilial(1L, nomeLoja, "Rua Principal", "123456789"));

        when(lojaFilialRepository.buscarLojaPorNome(nomeLoja)).thenReturn(mockLojas);

        List<LojaFilial> result = lojaFilialService.buscarLojaPorNome(nomeLoja);

        assertEquals(1, result.size());
        assertEquals("Loja Central", result.get(0).getNome());
    }
}

