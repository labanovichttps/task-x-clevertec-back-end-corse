package ru.clevertec.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException{

    private String entityName;
    private String fieldName;
    private Object fieldValue;
    private final Integer errorCode = 40442069;

    public EntityNotFoundException(String entityName, String fieldName, Object fieldValue) {
        super(String.format("%s is not found with %s = %s", entityName, fieldName, fieldValue));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
