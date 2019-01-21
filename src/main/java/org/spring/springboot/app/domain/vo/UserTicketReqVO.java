package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: eric
 * Date: 2017-11-09
 * Time: 19:37
 */
@ApiModel(description = "参数")
@Data
public class UserTicketReqVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "换取token凭证", required = true)
    @NotEmpty(message = "凭证不能为空")
    @Length(min = 10, max = 100, message = "凭证长度不正确")
    private String ticket;

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }
}
