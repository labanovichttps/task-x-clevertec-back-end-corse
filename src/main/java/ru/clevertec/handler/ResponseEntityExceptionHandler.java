package ru.clevertec.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.clevertec.entity.exception.EntityNotFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class ResponseEntityExceptionHandler{

    private final String errorMessage = "String.format(Requested resource not found (id = %d)";

    @SneakyThrows
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerTagNotFoundException(EntityNotFoundException exc){
        ObjectMapper mapper = new ObjectMapper();
        String excMessage = String.format(errorMessage, exc.getMessage());
        return new ResponseEntity<>(mapper.writeValueAsString(excMessage), HttpStatus.NOT_FOUND);
    }

}
