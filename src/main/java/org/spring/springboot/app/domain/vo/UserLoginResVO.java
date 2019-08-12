package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "返回参数")
public class UserLoginResVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "获取token的凭证")
    private String ticket;

    @ApiModelProperty(value = "ticket过期时间,单位毫秒")
    private Long expire;

    @ApiModelProperty(value = "过期时间,字符格式")
    private Date expireTime;

    @ApiModelProperty(value = "剩余有效毫秒")
    private Long effectiveMillisecond;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
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
