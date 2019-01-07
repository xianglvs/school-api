package org.spring.springboot.app.base;

/**
 * 信息类
 * 
 * @author ry
 * @date 2018/9/25
 */

public enum Type {
    SUCCESS(200000, "成功"),

    PARAM_VALIDATE_FAIL(400001, "参数验证失败"), 
    EXIST_ERROR(400002, "信息已存在"),
    POWER_VALIDATE_FAIL(400003, "登录已失效,请重新登录"), 
    DATA_DEL_ERROR(400004, "信息已删除"), 
    NOT_FOUND_ERROR(400005, "信息未找到"),
    PARAM_LENGTH_ERROR(400006, "参数太长"),
    Content_TYPE_ERROR(400007, "header信息中的Content-Type不正确,POST请求只支持Content-Type为application/json的请求,编码格式utf-8"),

    PERMISSIONS_VALIDATE_FAIL(401000, "没有权限访问此接口"), 
    ACCESS_TOKEN_VERFIY_FAIL(401005, "安全签名验证失败"),
    ACCESS_IP_VERFIY_FAIL(401006, "访问IP没有权限访问此接口"),

    PAGE_NOT_FOUND_ERROR(404000, "页面未找到"), 
    API_NOT_FOUND_ERROR(404001, "接口未找到"),

    TIMEOUT(408001, "请求超时"),

    EXCEPTION(500000, "系统错误,请联系管理员"), 
    FORMAT_EXCEPTION(500002, "参数格式不正确");

    private Integer code;
    private String message;

    Type(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
