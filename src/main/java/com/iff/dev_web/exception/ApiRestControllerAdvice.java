package com.iff.dev_web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DadosUsuarioInvalidosException.class)
    public ProblemDetail handleDadosUsuarioInvalidosException(DadosUsuarioInvalidosException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosLojaFilialInvalidosException.class)
    public ProblemDetail handleDadosLojaFilialInvalidosException(DadosLojaFilialInvalidosException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosVeiculoInvalidosException.class)
    public ProblemDetail handleDadosVeiculoInvalidosException(DadosVeiculoInvalidosException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosFuncionarioInvalidosException.class)
    public ProblemDetail handleDadosFuncionarioInvalidosException(DadosFuncionarioInvalidosException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosClienteInvalidosException.class)
    public ProblemDetail handleDadosClienteInvalidosException(DadosClienteInvalidosException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor");
    }
}
