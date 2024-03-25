package com.store.payment.controller;

import com.google.gson.Gson;
import com.store.payment.dto.errors.ErrorDetails;
import com.store.payment.dto.errors.PaymentException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "Ocorreu um erro interno. Detalhes: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> handleVideoException(Exception e) {
        Gson gson = new Gson();
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage());
        String jsonResponse = gson.toJson(errorDetails);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(jsonResponse);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> argumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        body.put("invalid_fields", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
