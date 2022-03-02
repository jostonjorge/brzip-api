package br.com.joston.brzip.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class})
    protected ResponseEntity<ApiError> handleBadRequest(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getMessage()));
    }
}
