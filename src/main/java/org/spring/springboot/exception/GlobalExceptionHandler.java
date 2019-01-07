package org.spring.springboot.exception;


import org.spring.springboot.app.base.Result;
import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.Type;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;


/**
 * 全局错误拦截器
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 引入Exception
     */

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public Result jsonErrorHandler(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return new Result(Type.FORMAT_EXCEPTION);
    }


    @ExceptionHandler(value = ResourceAccessException.class)
    @ResponseBody
    public Result resourceAccessException(ResourceAccessException ex) {
        return new Result(Type.TIMEOUT);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Result httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return new Result(Type.Content_TYPE_ERROR);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public Result constraintViolationExceptionHandler(
            ConstraintViolationException e) {
        Set<? extends ConstraintViolation> constraintViolations = e.getConstraintViolations();
        List<Error> errors = new ArrayList<>();
        for (ConstraintViolation v : constraintViolations) {
            Error error = new Error(v.getPropertyPath().toString(), v.getMessage());
            errors.add(error);
        }
        Result r = new Result(Type.PARAM_VALIDATE_FAIL, errors);
        return r;
        //return ResponseEntity.badRequest().body(e.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).findFirst().orElse(e.getMessage()));
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result bindExceptionHandler(BindException ex) {
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Error error = new Error(fieldError.getField(), fieldError.getDefaultMessage());
            errors.add(error);
        }
        Result r = new Result(Type.PARAM_VALIDATE_FAIL, errors);
        return r;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Error error = new Error(fieldError.getField(), fieldError.getDefaultMessage());
            errors.add(error);
        }
        Result r = new Result(Type.PARAM_VALIDATE_FAIL, errors);
        return r;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result bindExceptionHandler(BusinessException ex) {
        return ex.getResult();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result bindExceptionHandler(Exception ex) {
        Result r = new Result(Type.EXCEPTION);
        ex.printStackTrace();
        return r;
    }


}

