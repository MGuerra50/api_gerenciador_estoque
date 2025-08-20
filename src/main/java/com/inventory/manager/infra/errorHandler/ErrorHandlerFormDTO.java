package com.inventory.manager.infra.errorHandler;

import org.springframework.validation.FieldError;

public record ErrorHandlerFormDTO (String message, String field){
    public ErrorHandlerFormDTO(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
