package org.spring.springboot.util;

import org.spring.springboot.app.base.Constants;
import org.spring.springboot.app.base.ThreadLocalUtil;
import org.spring.springboot.app.domain.vo.UserTokenResVO;

import java.util.Date;


/**
 * 实体类相关工具类
 * 解决问题： 1、快速对实体的常驻字段
 * <p>
 * /**
 * Created by 大猫猫
 * Date: 20/7/20
 * Version 1.0.0
 */
public class EntityUtils {
    /**
     * 快速将bean的默认属性附上相关值
     */
    public static <T> void setCreatAndUpdatInfo(T entity) {
        setCreateInfo(entity);
        setUpdatedInfo(entity);
    }

    /**
     * 快速将bean的默认属性附上相关值
     *
     * @param entity 实体bean
     */
    public static <T> void setCreateInfo(T entity) {
        // 默认属性
        String[] fields = {"id", "createBy", "createDate"};
        // 默认值
        Object[] value = new Object[]{Uuid.getUUID(), getUser().getId(), new Date()};
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    public static UserTokenResVO getUser() {
        return ThreadLocalUtil.get(Constants.TOKEN_SESSION_NAME, UserTokenResVO.class);
    }

    /**
     * 快速将bean的updUser、updHost、updTime附上相关值
     *
     * @param entity 实体bean
     */
    public static <T> void setUpdatedInfo(T entity) {

        // 默认属性
        String[] fields = {"updateBy", "updateDate"};
        UserTokenResVO user = getUser();
        Object[] value = new Object[]{user.getId(), new Date()};;
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 依据对象的属性数组和值数组对对象的属性进行赋值
     *
     * @param entity 对象
     * @param fields 属性数组
     * @param value  值数组
     */
    private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if (ReflectionUtils.hasField(entity, field) && value != null && value[i] != null) {
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }

    /**
     * 根据主键属性，判断主键是否值为空
     *
     * @param entity
     * @param field
     * @return 主键为空，则返回false；主键有值，返回true
     */
    public static <T> boolean isPKNotNull(T entity, String field) {
        if (!ReflectionUtils.hasField(entity, field)) {
            return false;
        }
        Object value = ReflectionUtils.getFieldValue(entity, field);
        return value != null && !"".equals(value);
    }
}
