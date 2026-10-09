package com.devsuperior.dscommerce.controllers.handlers;

import com.devsuperior.dscommerce.dto.CustomError;
import com.devsuperior.dscommerce.dto.ValidationError;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // Indica que a classe é um manipulador global de exceções para controladores REST,
// permitindo capturar e tratar exceções lançadas durante o processamento das requisições.
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) // Anotação que indica que o metodo abaixo é um manipulador de exceção para a exceção ResourceNotFoundException.
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class) // Anotação que indica que o metodo abaixo é um manipulador de exceção para a exceção DatabaseException.
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // Anotação que indica que o metodo abaixo é um manipulador de exceção para a exceção MethodArgumentNotValidException.
    public ResponseEntity<CustomError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());

        // Itera sobre os erros de validação capturados na exceção MethodArgumentNotValidException
        // e adiciona cada erro à lista de erros do objeto ValidationError.
        // Adiciona cada erro de validação à lista de erros do objeto ValidationError.
        // O metodo addError é chamado para cada erro de campo, passando o nome do campo e a mensagem de erro correspondente.
        // Isso permite que o objeto ValidationError contenha informações detalhadas sobre os erros de validação ocorridos durante o processamento da requisição.
        //fieldError.getField() retorna o nome do campo que gerou o erro de validação.
        //fieldError.getDefaultMessage() retorna a mensagem de erro associada ao campo.
        //FieldError é uma classe do Spring que representa um erro de validação em um campo específico de um objeto.
        //FieldError é usado para capturar informações sobre erros de validação em campos individuais,
        // como o nome do campo e a mensagem de erro associada.
        // Ele capitura atraves da exceção MethodArgumentNotValidException, que é lançada quando
        // há erros de validação em argumentos de métodos anotados com @Valid.
        for (FieldError fieldError: e.getBindingResult().getFieldErrors()) {
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

}

