package ru.clevertec.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.clevertec.dto.ErrorDto;
import ru.clevertec.exception.EntityNotFoundException;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorDto handleEntityNotFoundException(EntityNotFoundException exc) {
        return ErrorDto.builder()
                .message(exc.getMessage())
                .errorCode(exc.getErrorCode())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorDto handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .errorCode(1337)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .errorCode(69)
                .build();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorDto handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .errorCode(420)
                .build();
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpClientErrorException.UnsupportedMediaType.class)
    public ErrorDto handleUnsupportedMediaType(HttpClientErrorException.UnsupportedMediaType e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .errorCode(22833)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorDto handleBindException(BindException e) {
        return ErrorDto.builder()
                .message(e.getAllErrors().toString())
                .errorCode(40404)
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDto handleConstraintViolationException(ConstraintViolationException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .errorCode(42233)
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorDto handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .errorCode(900999)
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.class)
    public ErrorDto handleHttpClientErrorException(HttpClientErrorException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .errorCode(3333)
                .build();
    }

}
