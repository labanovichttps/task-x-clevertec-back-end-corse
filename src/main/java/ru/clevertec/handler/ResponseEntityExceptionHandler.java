package ru.clevertec.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.dto.ErrorDto;
import ru.clevertec.exception.EntityNotFoundException;

@RestControllerAdvice
public class ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerTagNotFoundException(EntityNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .message(exc.getMessage())
                        .errorCode(exc.getErrorCode())
                        .build());
    }

}
