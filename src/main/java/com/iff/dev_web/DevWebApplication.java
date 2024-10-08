package com.iff.dev_web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "API de Gestão de Financiamentos da Financar",
				version = "1.0.0",
				description = "A API permite ao usuário o gerenciamento de financiamentos de veículos, incluindo operações CRUD para clientes, funcionários e veículos"
		)
)
@SpringBootApplication
public class DevWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevWebApplication.class, args);
	}

}
