package org.spring.springboot.app.base;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("结果")
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "逻辑代码")
    private Integer code;
    @ApiModelProperty(value = "消息提示")
    private String message;
    @ApiModelProperty(value = "服务器时间")
    private Long currentTimeMillis = System.currentTimeMillis();
    @ApiModelProperty(value = "错误提示列表")
    private List<Error> messages;
    @ApiModelProperty(value = "数据")
    private T data;

    public R() {
        this(Type.SUCCESS);
    }

    public R(Type type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }

    public R(T data) {
        this(Type.SUCCESS);
        this.setData(data);
    }

    public R(Type type, List<Error> messages) {
        this(type);
        this.messages = messages;
    }

    public R(Type type, List<Error> messages, T data) {
        this(type, messages);
        this.data = data;
    }

    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(Integer code, String message, List<Error> messages) {
        this.code = code;
        this.message = message;
        this.messages = messages;
    }

    public R(Integer code, String message, List<Error> messages, T data) {
        this.code = code;
        this.message = message;
        this.messages = messages;
        this.data = data;
    }

    public R set(Type type) {
        this.code = type.getCode();
        this.message = type.getMessage();
        return this;
    }

    public R set(Type type, List<Error> messages) {
        this.set(type);
        this.setMessages(messages);
        return this;
    }

    public R set(Type type, T data) {
        this.set(type);
        this.setData(data);
        return this;
    }

    public R set(Type type, T data, List<Error> messages) {
        this.set(type);
        this.setData(data);
        this.setMessages(messages);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public R setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public R setMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Error> getMessages() {
        return messages;
    }

    public R setMessages(List<Error> messages) {
        this.messages = messages;
        return this;
    }

    public T getData() {
        return data;
    }

    public R setData(T data) {
        this.data = data;
        return this;
    }

    public Long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public R setCurrentTimeMillis(Long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
        return this;
    }
}
