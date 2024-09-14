package com.iff.dev_web.Service;

import com.iff.dev_web.entities.Veiculo;
import com.iff.dev_web.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

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
}
