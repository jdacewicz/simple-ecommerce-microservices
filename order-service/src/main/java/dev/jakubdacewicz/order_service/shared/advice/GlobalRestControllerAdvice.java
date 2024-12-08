package dev.jakubdacewicz.order_service.shared.advice;

import dev.jakubdacewicz.order_service.shared.exception.CartDeletionFailureException;
import dev.jakubdacewicz.order_service.shared.exception.CartFetchFailureException;
import dev.jakubdacewicz.order_service.shared.exception.RecordNotFoundException;
import dev.jakubdacewicz.order_service.shared.model.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static dev.jakubdacewicz.order_service.shared.utils.ErrorMessageUtils.extractErrorMessages;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleRecordNotFoundException(RecordNotFoundException exception) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler({CartFetchFailureException.class, CartDeletionFailureException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleCartDeletionFailureException(CartDeletionFailureException exception) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = extractErrorMessages(ex);
        String errorMessage = String.join("; ", errors);

        return new ApiError(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolationExceptions(ConstraintViolationException ex) {
        List<String> errors = extractErrorMessages(ex);
        String errorMessage = String.join("; ", errors);

        return new ApiError(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(Exception exception) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
