package ru.clevertec.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.clevertec.dto.ErrorDto;
import ru.clevertec.exception.EntityNotFoundException;

@ControllerAdvice
public class ResponseEntityExceptionHandler{

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handlerTagNotFoundException(EntityNotFoundException exc){
        return ResponseEntity.status(exc.getHttpStatus())
                .body(ErrorDto.builder()
                        .message(exc.getMessage())
                        .errorCode(exc.getErrorCode())
                        .build());
    }

}
