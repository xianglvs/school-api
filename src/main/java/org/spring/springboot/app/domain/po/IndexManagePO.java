package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "index_manage")
@Data
@EqualsAndHashCode(callSuper = false)
public class IndexManagePO{

    @Id
    private String id;
    /**
     * 标题
     */
    private String title;

    /**
     * 电话
     */
    private String mobile;

}