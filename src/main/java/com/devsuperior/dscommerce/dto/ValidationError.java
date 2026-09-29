package com.devsuperior.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

// Classe que representa um erro de validação em uma requisição HTTP.
// Ela contém informações sobre o momento em que o erro ocorreu,
// o status HTTP, a mensagem de erro, o caminho da requisição e
// uma lista de mensagens de erro associadas a campos específicos.
public class ValidationError extends CustomError {

    private List<FieldMessage> errors = new ArrayList<>(); // Lista de mensagens de erro associadas a campos específicos.

    // Metodo para adicionar uma mensagem de erro a lista de erros.
    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    // Metodo que retorna a lista de mensagens de erro associadas a campos específicos.
    public List<FieldMessage> getErrors() {
        return errors;
    }

    // Metodo que adiciona uma mensagem de erro a lista de erros, criando um novo objeto FieldMessage com o nome do campo e a mensagem de erro.
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
