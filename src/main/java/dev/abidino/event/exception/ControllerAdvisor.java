package dev.abidino.event.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String errorMessage = ex.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));

        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorMessage, new Date());
        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBadRequestException(
            BadRequestException ex,
            WebRequest request) {

        ErrorDto dto = new ErrorDto(ex.getHttpStatus().value(), ex.getHttpStatus().name(), ex.getMessage(), new Date());
        return handleExceptionInternal(ex, dto, new HttpHeaders(), ex.getHttpStatus(), request);
    }
}

record ErrorDto(int resultCode, String result, String errorMessage, Date time) {
}