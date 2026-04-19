package com.crm.leads.Exception;

import com.crm.leads.DTO.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LeadsException.class)
    public ResponseEntity<Object> handleLeadsException(LeadsException ex) {
        HttpStatusCode statusCode = ex.getStatusCode();
        return ResponseEntity.status(statusCode)
                .body(new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage()));
    }

}
