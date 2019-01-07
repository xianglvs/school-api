package org.spring.springboot.app.base;

/**
 * 常用参数
 *
 * @author ry
 * @date 2018/9/25
 */
public class Constants {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    //逻辑删除标志,未删除
    public static final String DEL_FLAG_0 = "0";
    public static final String DEL_FLAG_1 = "1";

    //企业主体类型为政府
    public static final int TS_C500002_JBXX_ZTSB_FRXX_MAINPROPERTY_1 = 1;
    //企业主体类型为企业
    public static final int TS_C500002_JBXX_ZTSB_FRXX_MAINPROPERTY_2 = 2;
    //企业主体类型为个体户
    public static final int TS_C500002_JBXX_ZTSB_FRXX_MAINPROPERTY_3 = 3;
    //企业主体类型为政府为其它组织
    public static final int TS_C500002_JBXX_ZTSB_FRXX_MAINPROPERTY_4 = 4;

    //中小企业局拉取凭证信息表,未同步凭证状态
    public static final String ZXQYJ_CERTIFICATE_INFO_STATUS_0 = "0";
    //中小企业局拉取凭证信息表,同步凭证成功状态
    public static final String ZXQYJ_CERTIFICATE_INFO_STATUS_1 = "1";
    //中小企业局拉取凭证信息表,同步凭证失败状态
    public static final String ZXQYJ_CERTIFICATE_INFO_STATUS_2 = "2";

    /**
     * 接口返回的code
     */
    public static final String CODE = "code";

    /**
     * 接口返回的data
     */
    public static final String DATA = "data";
    /**
     * 国家接口返回的status
     */
    public static final String STATUS = "status";
    /**
     * 验证参数
     */
    public static final String BASEINFO = "baseinfo";
    /**
     * 时间戳,当前时间毫秒
     */
    public static final String REQTIME = "reqtime";
    /**
     * 用户身份标识
     */
    public static final String IDENTITY = "identity";
    /**
     * 调用凭据
     */
    public static final String SECURITY = "security";
    /**
     * 请求类型
     */
    public static final String TYPE = "type";
    /**
     * 子参数段
     */
    public static final String REQINFO = "reqinfo";
    /**
     * 参数段xdr参数
     */
    public static final String XDR = "xdr";
    /**
     * 参数段dm参数
     */
    public static final String DM = "dm";

    /**
     * 请求头长度
     */
    public static final Integer QQTCD = 500;

    /**
     * 参数长度
     */
    public static final Integer CSCD = 500;


}
