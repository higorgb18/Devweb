package com.iff.dev_web;

import com.iff.dev_web.entities.Cliente;
import com.iff.dev_web.entities.Funcionario;
import com.iff.dev_web.entities.LojaFilial;
import com.iff.dev_web.entities.Usuario;
import com.iff.dev_web.repository.LojaFilialRepository;
import com.iff.dev_web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    LojaFilialRepository lojaFilialRepository;

    @Override
    public void run(String... args) throws Exception {

        LojaFilial lojaFilial1 = new LojaFilial(1L, "Loja Central", "Rua Principal, 123", "22991234567");
        lojaFilialRepository.save(lojaFilial1);

        Funcionario funcionario1 = new Funcionario(2L,
                "Higor",
                "higor23@gmail.com.br",
                "12345678910",
                "22991472589",
                "New Brasília",
                LocalDate.parse("2000-10-26"),
                new BigDecimal(3500),
                "Atendente",
                lojaFilial1);
        usuarioRepository.save(funcionario1);

        Cliente cliente1 = new Cliente(1L,
                "Ana Julia",
                "juliaAna@gmail.com.br",
                "10987654321",
                "22994576329",
                "Lessence P",
                LocalDate.parse("2001-07-04"),
                999,
                new BigDecimal(500000),
                new BigDecimal(45000));
        usuarioRepository.save(cliente1);


        System.out.println("ID: " + funcionario1.getCdUsuario() +
                " | Usuário: " + funcionario1.getUsuario() +
                " | E-mail: " + funcionario1.getEmail() +
                " | Documento: " + funcionario1.getNuDocumento() +
                " | Telefone: " + funcionario1.getNuTelefone() +
                " | Endereço: " + funcionario1.getEndereco() +
                " | Data de nascimento: " + funcionario1.getDataNascimento() +
                " | Salário: " + funcionario1.getSalario() +
                " | Cargo: " + funcionario1.getCargo() +
                " | Loja filial: " + funcionario1.getLojaFilial().getNome());

        System.out.println("ID: " + cliente1.getCdUsuario() +
                " | Usuário: " + cliente1.getUsuario() +
                " | E-mail: " + cliente1.getEmail() +
                " | Documento: " + cliente1.getNuDocumento() +
                " | Telefone: " + cliente1.getNuTelefone() +
                " | Endereço: " + cliente1.getEndereco() +
                " | Data de nascimento: " + cliente1.getDataNascimento() +
                " | Score Serasa: " + cliente1.getScoreCredito() +
                " | Limite de crédito: " + cliente1.getLimiteCreditoFinanciamento() +
                " | Renda: " + cliente1.getRenda());
    }
}
