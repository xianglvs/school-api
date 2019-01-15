package org.spring.springboot.app.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserTokenResVO {

    @ApiModelProperty(value = "token", hidden = false)
    private String token;
    @ApiModelProperty(value = "过期时间", hidden = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date expireTime;
    @ApiModelProperty(value = "剩余有效毫秒", hidden = false)
    private Long effectiveMillisecond;



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Long getEffectiveMillisecond() {
        return effectiveMillisecond;
    }

    public void setEffectiveMillisecond(Long effectiveMillisecond) {
        this.effectiveMillisecond = effectiveMillisecond;
    }
}
