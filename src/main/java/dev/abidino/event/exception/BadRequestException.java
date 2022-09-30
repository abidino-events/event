package dev.abidino.event.exception;

import org.springframework.http.HttpStatus;


public final class BadRequestException extends AbstractException {
    public BadRequestException(String message) {
        super(message);
    }

    @Override
    HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
