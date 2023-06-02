package com.pamela.sistemabanco.controllers.exceptions;

import com.pamela.sistemabanco.services.exceptions.account.AccountAlreadyExistsException;
import com.pamela.sistemabanco.services.exceptions.account.AccountNotFoundException;
import com.pamela.sistemabanco.services.exceptions.account.InsufficientFundsException;
import com.pamela.sistemabanco.services.exceptions.account.MaximumNumberOfAccountsCreatedException;
import com.pamela.sistemabanco.services.exceptions.client.ClientAlreadyExistsException;
import com.pamela.sistemabanco.services.exceptions.client.ClientNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,
                                                         HttpServletRequest request){

        ValidationError errors = new ValidationError((System.currentTimeMillis()), HttpStatus.BAD_REQUEST.value(),
                "Validation error","Erro na validação dos campos", request.getRequestURI());

        for (FieldError x : ex.getBindingResult().getFieldErrors()){
            errors.addErrors(x.getField(), "Erro de validação dos campos");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<StandardError> clientNotFoundException(ClientNotFoundException ex, HttpServletRequest request) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Client Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MaximumNumberOfAccountsCreatedException.class)
    public ResponseEntity<StandardError> maximumNumberOfAccountsCreatedException(MaximumNumberOfAccountsCreatedException ex, HttpServletRequest request) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Maximum Number Of Accounts Created", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<StandardError> clientAlreadyExistsException(ClientAlreadyExistsException ex, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Client already exists", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<StandardError> accountNotFoundException(AccountNotFoundException ex, HttpServletRequest request) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Account Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<StandardError> accountAlreadyExists(AccountAlreadyExistsException ex, HttpServletRequest request) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Account already exists", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<StandardError> insufficientFunds(InsufficientFundsException ex, HttpServletRequest request) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Insufficient Funds", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
