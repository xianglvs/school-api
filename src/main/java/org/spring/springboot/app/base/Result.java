package org.spring.springboot.app.base;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel("结果")
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    private List<Error> messages;
    private Object data;

    public Result() {
        this(Type.SUCCESS);
    }

    public Result(Type type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }

    public Result(Type type, List<Error> messages) {
        this(type);
        this.messages = messages;
    }

    public Result(Type type, List<Error> messages, Object data) {
        this(type, messages);
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, List<Error> messages) {
        this.code = code;
        this.message = message;
        this.messages = messages;
    }

    public Result(Integer code, String message, List<Error> messages, Object data) {
        this.code = code;
        this.message = message;
        this.messages = messages;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Error> getMessages() {
        return messages;
    }

    public void setMessages(List<Error> messages) {
        this.messages = messages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
