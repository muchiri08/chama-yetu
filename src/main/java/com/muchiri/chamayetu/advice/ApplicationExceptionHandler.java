package com.muchiri.chamayetu.advice;

import com.muchiri.chamayetu.exception.NoDataFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.muchiri.chamayetu.exception.PageNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private Map<String, String> errorMap = new HashMap<>();

    /**
     * Whenever we have a validation error, we are redirected to this method
     * which then gets the fields with errors and their error message and sends back
     *
     * @Param MethodArgumentNotValidException The exception that will be thrown during validation
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException argumentNotValidException) {
        BindingResult bindingResult = argumentNotValidException.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public Map<String, String> handleEmptyResultDataAccessException(EmptyResultDataAccessException emptyResultDataAccessException){
        errorMap.put("errorMessage", emptyResultDataAccessException.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PageNotFoundException.class)
    public Map<String, String> handlePageNotFoundException(PageNotFoundException pageNotFoundException){
        errorMap.put("errorMessage", pageNotFoundException.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoDataFoundException.class)
    public Map<String, String> handleNoDataFoundException(NoDataFoundException noDataFoundException){
        errorMap.put("errorMessage", noDataFoundException.getMessage());
        return errorMap;
    }
}
