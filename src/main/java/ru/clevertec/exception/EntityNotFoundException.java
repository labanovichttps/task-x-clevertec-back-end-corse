package ru.clevertec.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{

    private String entityName;
    private String fieldName;
    private Object fieldValue;
    private HttpStatus httpStatus;
    private Integer errorCode;

    public EntityNotFoundException(String entityName, String fieldName, Object fieldValue, HttpStatus httpStatus) {
        super(String.format("%s is not found with %s = %s", entityName, fieldName, fieldValue));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.httpStatus = httpStatus;
        this.errorCode = httpStatus.value();
    }
}
