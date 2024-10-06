package com.iff.dev_web.service;

import com.iff.dev_web.entities.Veiculo;
import com.iff.dev_web.exception.DadosVeiculoInvalidosException;
import com.iff.dev_web.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    private static final String PLACA_REGEX = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}";
    private static final String CHASSI_REGEX = "^[A-Za-z0-9]{3,3}[A-Za-z0-9]{6,6}[A-Za-z0-9]{2,2}[A-Za-z0-9]{6,6}$";

    public List<Veiculo> buscarVeiculosPorMarca(String marca) {
        return veiculoRepository.buscarVeiculosPorMarca(marca);
    }

    public List<Veiculo> buscarVeiculosPorModelo(String modelo) {
        return veiculoRepository.buscarVeiculosPorModelo(modelo);
    }

    public List<Veiculo> buscarVeiculosPorAno(Integer anoFabricao) {
        return veiculoRepository.buscarVeiculosPorAno(anoFabricao);
    }

    public List<Veiculo> buscarVeiculosPorValor(BigDecimal valorMax) {
        return veiculoRepository.buscarVeiculosPorValor(valorMax);
    }

    private void validarPlaca(String placa) {
        if (placa == null || !Pattern.matches(PLACA_REGEX, placa)) {
            throw new DadosVeiculoInvalidosException("A placa deve estar no padrão ABC1234 ou Mercosul");
        }
    }

    private void validarChassi(String chassi) {
        if (chassi == null || !Pattern.matches(CHASSI_REGEX, chassi)) {
            throw new DadosVeiculoInvalidosException("Chassi inválido");
        }
    }
}
