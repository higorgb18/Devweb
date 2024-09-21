package com.iff.dev_web.Service;
import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> buscarFuncionariosPorCargo(String cargo) {
        return funcionarioRepository.buscarFuncionarioPorCargo(cargo);
    }

    public List<Funcionario> buscarFuncionariosPorSalario(BigDecimal salario) {
        return funcionarioRepository.buscarFuncionarioPorSalario(salario);
    }

    public List<Funcionario> buscarFuncionariosPorFilial(String nomeFilial) {
        return funcionarioRepository.buscarFuncionarioPorFilial(nomeFilial);
    }
}


