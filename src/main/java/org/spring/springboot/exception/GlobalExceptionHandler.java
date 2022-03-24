package org.spring.springboot.exception;


import org.spring.springboot.app.base.R;
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
    public R jsonErrorHandler(HttpMessageNotReadableException e) {
        return new R(Type.FORMAT_EXCEPTION);
    }


    @ExceptionHandler(value = ResourceAccessException.class)
    @ResponseBody
    public R resourceAccessException(ResourceAccessException ex) {
        return new R(Type.TIMEOUT);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public R httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return new R(Type.CONTENT_TYPE_ERROR);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public R constraintViolationExceptionHandler(
            ConstraintViolationException e) {
        Set<? extends ConstraintViolation> constraintViolations = e.getConstraintViolations();
        List<Error> errors = new ArrayList<>();
        for (ConstraintViolation v : constraintViolations) {
            Error error = new Error(v.getPropertyPath().toString(), v.getMessage());
            errors.add(error);
        }
        R r = new R(Type.PARAM_VALIDATE_FAIL, errors);
        return r;
        //return ResponseEntity.badRequest().body(e.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).findFirst().orElse(e.getMessage()));
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public R bindExceptionHandler(BindException ex) {
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Error error = new Error(fieldError.getField(), fieldError.getDefaultMessage());
            errors.add(error);
        }
        R r = new R(Type.PARAM_VALIDATE_FAIL, errors);
        return r;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public R methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Error error = new Error(fieldError.getField(), fieldError.getDefaultMessage());
            errors.add(error);
        }
        R r = new R(Type.PARAM_VALIDATE_FAIL, errors);
        return r;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public R bindExceptionHandler(BusinessException ex) {
        return ex.getResult();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R bindExceptionHandler(Exception ex) {
        ex.printStackTrace();
        R r = new R(Type.EXCEPTION);
        return r;
    }


}

