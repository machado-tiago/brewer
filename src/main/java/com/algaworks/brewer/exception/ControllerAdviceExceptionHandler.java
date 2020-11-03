package com.algaworks.brewer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler{
    
    @ExceptionHandler(NovoEstiloJaCadastradoException.class)
    public ResponseEntity<String> handleNovoEstiloJaCadastradoException(NovoEstiloJaCadastradoException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
