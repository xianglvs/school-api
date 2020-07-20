package org.spring.springboot.config;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.exception.BusinessException;
import org.spring.springboot.util.ClientUtil;
import org.spring.springboot.util.EntityUtils;
import org.spring.springboot.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 大猫猫
 * Date: 20/7/20
 * Version 1.0.0
 */
public abstract class BaseBiz<M extends Mapper<T>, T> {
    @Autowired
    protected M mapper;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }


    public List<T> selectListAll() {
        return mapper.selectAll();
    }


    public Long selectCount(T entity) {
        return new Long(mapper.selectCount(entity));
    }


    public void insert(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insert(entity);
    }


    public void insertSelective(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insertSelective(entity);
    }

    public void insertSelectiveNoAutoSet(T entity) {
        mapper.insertSelective(entity);
    }


    public void delete(T entity) {
        mapper.delete(entity);
    }


    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }


    public void updateById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKey(entity);
    }


    public void updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    //@Cacheable(value = "t", key= "#query")
    public R<PageInfo<T>> selectByQuery(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz, false);
        setSelectProperties(example, query);
        if (query != null && query.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andCondition("1=1");
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                paramAddWhereAndOrder(entry.getKey(), entry.getValue().toString(), example, criteria, clazz);
            }
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit(), false);
        List<T> list = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return new R<PageInfo<T>>().setData(pageInfo);
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        if (RequestContextHolder.getRequestAttributes() != null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        if (request == null) {
            throw new BusinessException(Type.EXCEPTION.getCode(), "HttpServletRequest is null");
        }
        return request;
    }

    public String getIp() {
        return ClientUtil.getClientIp(this.getRequest());
    }

    //@Cacheable(value = "t", key= "#query")
    public R<Integer> selectCountByQuery(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz, false);
        setSelectProperties(example, query);
        if (query != null && query.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andCondition("1=1");
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                paramAddWhereAndOrder(entry.getKey(), entry.getValue().toString(), example, criteria, clazz);
            }
        }
        int size = mapper.selectCountByExample(example);
        return new R<Integer>(size);
    }


    //@Cacheable(value = "t", key= "#query")
    public R<PageInfo<T>> selectByQueryPage(Query query) {
        return this.selectByQueryPage(query, true);
    }

    //@Cacheable(value = "t", key= "#query")
    public R<PageInfo<T>> selectByQueryPage(Query query, boolean count) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz, false);
        //如果有指定查询出来的字段就进行字段指定
        setSelectProperties(example, query);
        if (query != null && query.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andCondition("1=1");
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                paramAddWhereAndOrder(entry.getKey(), entry.getValue().toString(), example, criteria, clazz);
                //criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit(), count);
        List<T> list = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return new R<PageInfo<T>>().setData(pageInfo);
    }

    public void setSelectProperties(Example example, Query query) {
        if (query.get("select_col") != null) {
            String[] cols = query.get("select_col").toString().split(",");
            example.selectProperties(cols);
            query.remove("select_col");
        }
    }

    public void paramAddWhereAndOrder(String key, String value, Example example, Example.Criteria criteria, Class clazz) {
        if (key.startsWith("$")) {
            this.paramAddOrder(key, value, example, clazz);
            return;
        }
        this.paramAddWhere(key, value, criteria, clazz);
    }

    public void paramAddOrder(String key, String value, Example example, Class clazz) {
        try {
            clazz.getMethod("get" + firstUpperCase(key.substring(1)), null);
        } catch (NoSuchMethodException e) {
            return;
        }
        Example.OrderBy orderBy = example.orderBy(key.substring(1));
        if (value.equalsIgnoreCase("asc")) {
            orderBy.asc();
        } else if (value.equalsIgnoreCase("desc")) {
            orderBy.desc();
        }
    }

    public void paramAddWhere(String key, String value, Example.Criteria criteria, Class clazz) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        Integer _index = value.indexOf("_");
        String _value = value.substring(_index + 1);
        String op = (_index != -1 ? value.substring(0, _index).toLowerCase() : "");
        Class paramType = String.class;
        try {
            paramType = clazz.getMethod("get" + firstUpperCase(key), null).getReturnType();
        } catch (NoSuchMethodException e) {
            return;
        }
        final Class type = paramType;
        switch (op) {
            case "null":
                criteria.andIsNull(key);
                break;
            case "noll":
                criteria.andIsNotNull(key);
                break;
            case "eq":
                criteria.andEqualTo(key, stringToObject(_value, paramType));
                break;
            case "ne":
                criteria.andNotEqualTo(key, stringToObject(_value, paramType));
                break;
            case "in":
                criteria.andIn(key,
                        Stream.of(_value.split(","))
                                .map(row -> stringToObject(row, type))
                                .collect(Collectors.toList()));
                break;
            case "ni":
                criteria.andNotIn(key,
                        Stream.of(_value.split(","))
                                .map(row -> stringToObject(row, type))
                                .collect(Collectors.toList()));
                break;
            case "lt":
                criteria.andLessThan(key, stringToObject(_value, paramType));
                break;
            case "lte":
                criteria.andLessThanOrEqualTo(key, stringToObject(_value, paramType));
                break;
            case "gt":
                criteria.andGreaterThan(key, stringToObject(_value, paramType));
                break;
            case "gte":
                criteria.andGreaterThanOrEqualTo(key, stringToObject(_value, paramType));
                break;
            case "like":
                //替换所有的连续空白为%
                _value = likeQueryFilter(_value);
                criteria.andLike(key, "%" + stringToObject(_value, paramType) + "%");
                break;
            case "between":
                String[] bts = _value.split(",");
                criteria.andBetween(key, stringToObject(bts[0], paramType), stringToObject(bts[1], paramType));
                break;
            default:
                if (StringUtils.isNotEmpty(value))
                    criteria.andEqualTo(key, stringToObject(value, paramType));
                break;
        }
    }

    /**
     * 将一个字符串去掉左右空白，并将中间连续空白字符用%代替
     *
     * @param str
     * @return
     */
    public String likeQueryFilter(String str) {
        str = str.trim();
        StringTokenizer pas = new StringTokenizer(str, " ");
        str = ""; //这里清空了str，但StringTokenizer对象中已经保留了原来字符串的内容。
        while (pas.hasMoreTokens()) {
            str = str + pas.nextToken();
            if (pas.hasMoreTokens()) {
                str = str + "%";
            }
        }
        return str;
    }

    public String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private Timestamp StringToTimestamp(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new Timestamp(StringToDate(value).getTime());
    }

    private java.sql.Date StringToSqlDate(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new java.sql.Date(StringToDate(value).getTime());
    }

    private Date StringToDate(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        String[] formatDate = {
                "yyyy/MM/dd",
                "yyyy-MM-dd",
                "yyyy年MM月dd日",
                "yyyyMMdd"
        };
        String[] formatDateTime = {
                "yyyy/MM/dd HH",
                "yyyy-MM-dd HH",
                "yyyy年MM月dd日 HH时",
                "yyyyMMdd HH",
                "yyyy/MM/dd HH:mm",
                "yyyy-MM-dd HH:mm",
                "yyyy年MM月dd日 HH时mm分",
                "yyyyMMdd HH:mm",
                "yyyy/MM/dd HH:mm:ss",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy年MM月dd日 HH时mm分ss秒",
                "yyyyMMdd HH:mm:ss"
        };
        Date dateValue = null;
        for (String formatValue : formatDate) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatValue);
                LocalDate localDate = LocalDate.parse(value, formatter);
                dateValue = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } catch (Exception e) {
            } finally {
                if (dateValue != null) {
                    break;
                }
            }
        }

        if (dateValue == null) {
            for (String formatValue : formatDateTime) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatValue);
                    LocalDateTime localDateTime = LocalDateTime.parse(value, formatter);
                    dateValue = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                } catch (Exception e) {
                } finally {
                    if (dateValue != null) {
                        break;
                    }
                }
            }
        }
        if (dateValue == null) {
            throw new BusinessException(Type.EXCEPTION.getCode(), "不支持的时间格式");
        }
        return dateValue;
    }

    /**
     * 字符串转各种类型
     */
    public Object stringToObject(String value, Class paramType) {
        switch (paramType.getTypeName()) {
            case "java.math.BigDecimal":
                return new BigDecimal(value);
            case "java.lang.String":
                return value;
            case "java.util.Date":
                return StringToDate(value);
            case "java.sql.Date":
                return StringToSqlDate(value);
            case "java.lang.Integer":
                return Integer.valueOf(value);
            case "java.lang.Boolean":
                return Boolean.valueOf(value);
            case "java.lang.Long":
                return Long.valueOf(value);
            case "java.lang.Float":
                return Float.valueOf(value);
            case "java.lang.Double":
                return Double.valueOf(value);
            case "java.sql.Timestamp":
                return StringToTimestamp(value);
            default:
                return value;
        }
    }
}
