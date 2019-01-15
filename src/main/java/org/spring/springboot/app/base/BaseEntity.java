package org.spring.springboot.app.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.spring.springboot.util.Uuid;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: eric
 * Date: 2017-11-13
 * Time: 16:23
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id", hidden = true)
    private String id;
    @ApiModelProperty(value = "创建人", hidden = true)
    @Column(name = "create_by")
    private String createBy;
    @ApiModelProperty(value = "创建时间", hidden = true)
    @Column(name = "create_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @ApiModelProperty(value = "更新者", hidden = true)
    @Column(name = "update_by")
    private String updateBy;
    @ApiModelProperty(value = "更新时间", hidden = true)
    @Column(name = "update_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    @ApiModelProperty(value = "备注信息", hidden = true)
    private String remarks;
    @ApiModelProperty(value = "禁用状态", hidden = true)
    @Column(name = "disable_flag")
    private Boolean disableFlag;
    @ApiModelProperty(value = "删除状态", hidden = true)
    @Column(name = "del_flag")
    private Boolean delFlag;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private User user;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean isDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Boolean disableFlag) {
        this.disableFlag = disableFlag;
    }

    public Boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BaseEntity() {
        super();
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        setId(Uuid.getUUID());
        User user = this.getUser();
        if (user != null) {
            this.updateBy = user.getId();
            this.createBy = user.getId();
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
        this.delFlag = Boolean.FALSE;
    }



    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate() {
        User user = this.getUser();
        if (user != null) {
            this.updateBy = user.getId();
        }
        this.updateDate = new Date();
    }


    /**
     * 删除之前执行方法，需要手动调用
     */
    public void preDelete() {
        User user = this.getUser();
        if (user != null) {
            this.updateBy = user.getId();
        }
        this.updateDate = new Date();
        this.delFlag = Boolean.TRUE;
    }

    public User getUser() {
        this.user = ThreadLocalUtil.get(Constants.TOKEN_SESSION_NAME, User.class);
        return user;
    }




}
