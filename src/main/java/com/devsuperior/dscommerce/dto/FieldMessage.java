package com.devsuperior.dscommerce.dto;
// Classe que representa uma mensagem de erro associada a um campo específico em uma requisição HTTP.
public class FieldMessage {

    private String fieldName; // Nome do campo que gerou a mensagem de erro.
    private String message; // Mensagem de erro associada ao campo.

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
