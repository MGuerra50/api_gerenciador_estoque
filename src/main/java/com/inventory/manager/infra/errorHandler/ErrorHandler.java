package com.inventory.manager.infra.errorHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> notFound (){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorHandlerFormDTO>> notValid (MethodArgumentNotValidException ex){
        var error = ex.getFieldErrors();
        return ResponseEntity.unprocessableEntity().body(
                error.stream().map(ErrorHandlerFormDTO::new).collect(Collectors.toList())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorHandlerDTO> illegalArgument (IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(new ErrorHandlerDTO(ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorHandlerDTO> accessDenied (AccessDeniedException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ErrorHandlerDTO("Acesso negado. \nVocê não tem permissão para executar essa operação!")
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorHandlerDTO> notSupported (HttpRequestMethodNotSupportedException ex){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                new ErrorHandlerDTO("Método HTTP não suportado. Métodos suportados: " + ex.getSupportedHttpMethods())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorHandlerDTO> exception (Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorHandlerDTO("Erro interno inesperado no sistema!")
        );
    }
}
