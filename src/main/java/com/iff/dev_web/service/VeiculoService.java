package com.iff.dev_web.service;

import com.iff.dev_web.entities.Veiculo;
import com.iff.dev_web.exception.DadosVeiculoInvalidosException;
import com.iff.dev_web.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    private static final String PLACA_REGEX = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}";
    private static final String CHASSI_REGEX = "^[A-Za-z0-9]{3,3}[A-Za-z0-9]{6,6}[A-Za-z0-9]{2,2}[A-Za-z0-9]{6,6}$";

    public Veiculo criarVeiculo(Veiculo veiculo) {
        validarVeiculo(veiculo);
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente.isEmpty()) {
            throw new DadosVeiculoInvalidosException("Veículo não encontrado.");
        }

        validarVeiculo(veiculo);
        veiculo.setCdVeiculo(id);
        return veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new DadosVeiculoInvalidosException("Veículo não encontrado.");
        }
        veiculoRepository.deleteById(id);
    }

    public List<Veiculo> buscarTodosVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarVeiculosPorId(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() -> new DadosVeiculoInvalidosException("Veículo não encontrado."));
    }

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

    public void validarVeiculo(Veiculo veiculo) {
        if (!veiculo.getPlaca().matches(PLACA_REGEX)) {
            throw new DadosVeiculoInvalidosException("A placa deve estar no padrão ABC1234 ou Mercosul.");
        }
        if (!veiculo.getChassi().matches(CHASSI_REGEX)) {
            throw new DadosVeiculoInvalidosException("Chassi inválido. Deve possuir 17 caracteres com apenas letras e números");
        }

        validarPlacaEChassiUnicos(veiculo);
        validarAnoVeiculo(veiculo.getAnoModelo());
    }

    private void validarPlacaEChassiUnicos(Veiculo veiculo) {
        Optional<Veiculo> veiculoExistentePorPlaca = veiculoRepository.findByPlaca(veiculo.getPlaca());
        Optional<Veiculo> veiculoExistentePorChassi = veiculoRepository.findByChassi(veiculo.getChassi());

        if (veiculoExistentePorPlaca.isPresent() && !veiculoExistentePorPlaca.get().getCdVeiculo().equals(veiculo.getCdVeiculo())) {
            throw new DadosVeiculoInvalidosException("Placa já existente na base de dados: " + veiculo.getPlaca());
        }

        if (veiculoExistentePorChassi.isPresent() && !veiculoExistentePorChassi.get().getCdVeiculo().equals(veiculo.getCdVeiculo())) {
            throw new DadosVeiculoInvalidosException("Chassi já existente na base de dados: " + veiculo.getChassi());
        }
    }


    private void validarAnoVeiculo(Integer anoModelo) {
        int anoAtual = java.time.Year.now().getValue();
        if (anoModelo < 2012 || anoModelo > anoAtual) {
            throw new DadosVeiculoInvalidosException("O ano do veículo deve estar entre 2012 e " + anoAtual);
        }
    }
}
