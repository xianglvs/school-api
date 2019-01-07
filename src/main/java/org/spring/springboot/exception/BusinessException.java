package org.spring.springboot.exception;

import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.Result;
import org.spring.springboot.app.base.Type;

import java.util.List;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    Result result = new Result();

    public BusinessException(Type type) {
        super(type.getMessage());
        result.setCode(type.getCode());
        result.setMessage(type.getMessage());
    }

    public BusinessException(Integer code, String message) {
        super(message);
        result.setCode(code);
        result.setMessage(message);
    }

    public BusinessException(Type type, List<Error> messages) {
        super(type.getMessage());
        result.setCode(type.getCode());
        result.setMessage(type.getMessage());
        result.setMessages(messages);
    }

    public BusinessException(Integer code, String message, List<Error> messages) {
        super(message);
        result.setCode(code);
        result.setMessage(message);
        result.setMessages(messages);
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
