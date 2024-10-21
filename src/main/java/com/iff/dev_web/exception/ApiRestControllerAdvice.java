package com.iff.dev_web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ApiRestControllerAdvice {

    @ExceptionHandler(DadosUsuarioInvalidosException.class)
    public ModelAndView handleDadosUsuarioInvalidosException(DadosUsuarioInvalidosException e) {
        return getErrorModelAndView(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosLojaFilialInvalidosException.class)
    public ModelAndView handleDadosLojaFilialInvalidosException(DadosLojaFilialInvalidosException e) {
        return getErrorModelAndView(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosVeiculoInvalidosException.class)
    public ModelAndView handleDadosVeiculoInvalidosException(DadosVeiculoInvalidosException e) {
        return getErrorModelAndView(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosFuncionarioInvalidosException.class)
    public ModelAndView handleDadosFuncionarioInvalidosException(DadosFuncionarioInvalidosException e) {
        return getErrorModelAndView(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosClienteInvalidosException.class)
    public ModelAndView handleDadosClienteInvalidosException(DadosClienteInvalidosException e) {
        return getErrorModelAndView(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DadosFinanciamentoInvalidosException.class)
    public ModelAndView handleDadosFinanciamentoInvalidosException(DadosFinanciamentoInvalidosException e) {
        return getErrorModelAndView(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e) {
        return getErrorModelAndView(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor");
    }

    private ModelAndView getErrorModelAndView(HttpStatus status, String errorMessage) {
        ModelAndView modelAndView = new ModelAndView("paginaErro");
        modelAndView.addObject("status", status.value());
        modelAndView.addObject("error", status.getReasonPhrase());
        modelAndView.addObject("message", errorMessage);
        return modelAndView;
    }
}
