package dev.jakubdacewicz.product_service.shared.advice;

import dev.jakubdacewicz.product_service.shared.exception.CategoryAssignedException;
import dev.jakubdacewicz.product_service.shared.exception.DocumentNotFoundException;
import dev.jakubdacewicz.product_service.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleDocumentNotFoundException(DocumentNotFoundException exception) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, CategoryAssignedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleHttpMessageNotReadableException(Exception exception) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String detailedMessage = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
                .collect(Collectors.joining("; "));
        return new ApiError(HttpStatus.BAD_REQUEST.value(), detailedMessage);
    }
}
