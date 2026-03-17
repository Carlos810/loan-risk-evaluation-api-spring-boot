package com.prueba.tecnica.mvp.handler.Error;

import com.prueba.tecnica.mvp.model.ResponseApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*import org.springframework.dao.DataIntegrityViolationException;*/

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getRootCause(Throwable ex) {
        Throwable cause = ex;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause.getMessage();
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseApiError> handleDatabaseError(
            DataIntegrityViolationException ex,
            HttpServletRequest request){

        ResponseApiError error = new ResponseApiError(
                HttpStatus.BAD_REQUEST.value(),
                request.getMethod(),
                request.getRequestURI(),
                "Database constraint error",
                ex.getMostSpecificCause().getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApiError> handleGenericError(
            Exception ex,
            HttpServletRequest request){

        ResponseApiError error = new ResponseApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getMethod(),
                request.getRequestURI(),
                ex.getMessage(),
                ex.getCause() != null ? ex.getCause().getMessage() : "N/A",
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseApiError> handleValidationError(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ResponseApiError error = new ResponseApiError(
                HttpStatus.BAD_REQUEST.value(),
                request.getMethod(),
                request.getRequestURI(),
                message,
                "Validation failed",
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseApiError> handleEnumError(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ResponseApiError error = new ResponseApiError(
                HttpStatus.BAD_REQUEST.value(),
                request.getMethod(),
                request.getRequestURI(),
                "Producto financiero capturado no existe, favor de ingresar uno válido",
                getRootCause(ex),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(error);
    }

}
