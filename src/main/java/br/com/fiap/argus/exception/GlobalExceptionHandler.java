package br.com.fiap.argus.exception;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<Map<String, Object>>
    handleNotFound(

            ResourceNotFoundException ex

    ) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

    }

    @ExceptionHandler(
            BusinessException.class
    )
    public ResponseEntity<Map<String, Object>>
    handleBusiness(

            BusinessException ex

    ) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );

    }

    @ExceptionHandler(
            EntityNotFoundException.class
    )
    public ResponseEntity<Map<String, Object>>
    handleEntity(

            EntityNotFoundException ex

    ) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<Map<String, Object>>
    handleValidation(

            MethodArgumentNotValidException ex

    ) {

        Map<String, String> errors =
                new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(

                        error -> {

                            String field =
                                    (
                                            (FieldError) error
                                    ).getField();

                            String message =
                                    error.getDefaultMessage();

                            errors.put(
                                    field,
                                    message
                            );

                        }

                );

        Map<String, Object> body =
                new HashMap<>();

        body.put(
                "timestamp",
                LocalDateTime.now()
        );

        body.put(
                "status",
                HttpStatus.BAD_REQUEST.value()
        );

        body.put(
                "error",
                "Erro de validação"
        );

        body.put(
                "campos",
                errors
        );

        return ResponseEntity
                .badRequest()
                .body(
                        body
                );

    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<Map<String, Object>>
    handleGeneric(

            Exception ex

    ) {

        ex.printStackTrace();

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor. Tente novamente mais tarde."
        );

    }

    private ResponseEntity<Map<String, Object>>
    buildResponse(

            HttpStatus status,

            String message

    ) {

        Map<String, Object> body =
                new HashMap<>();

        body.put(
                "timestamp",
                LocalDateTime.now()
        );

        body.put(
                "status",
                status.value()
        );

        body.put(
                "error",
                status.getReasonPhrase()
        );

        body.put(
                "message",
                message
        );

        return ResponseEntity
                .status(
                        status
                )
                .body(
                        body
                );

    }

}