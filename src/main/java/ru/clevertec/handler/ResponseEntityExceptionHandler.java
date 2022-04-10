package ru.clevertec.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.clevertec.entity.exception.TagNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequiredArgsConstructor
public class ResponseEntityExceptionHandler{

    private final String errorMessage = "";

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<?> handlerTagNotFoundException(HttpServletRequest request, TagNotFoundException exc){
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
