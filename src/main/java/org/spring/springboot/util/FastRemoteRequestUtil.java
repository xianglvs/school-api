package org.spring.springboot.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FastRemoteRequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(FastRemoteRequestUtil.class);
    private static OkHttpClient client;
    private CookieJar cookieJar;
    private int requestMode;

    //常用的传参方式
    public static final int PARAMETER = 1001;
    public static final int FROMDATA = 1002;
    public static final int APPLICATION_JSON = 1003;

    public static final int POST = 2001;
    public static final int GET = 2002;
    public static final int PUT = 2003;
    public static final int DELETE = 2004;

    public static OkHttpClient getClientInstace() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)//设置连接时间
                    .readTimeout(10, TimeUnit.SECONDS)//设置读取时间
                    .writeTimeout(10, TimeUnit.SECONDS)//设置写出时间
                    .build();
        }
        return client;
    }

    public Request.Builder excuteRequestFactory(Request.Builder builder, RequestBody requestBody){
        switch (this.requestMode){
            case FastRemoteRequestUtil.POST:
                builder = builder.post(requestBody);
                break;
            case FastRemoteRequestUtil.PUT:
                builder = builder.put(requestBody);
                break;
            case FastRemoteRequestUtil.DELETE:
                builder = builder.delete(requestBody);
                break;
            case FastRemoteRequestUtil.GET:
                builder = builder.get();
                break;
        }
        return builder;
    }

    public Request createRequest(String requestUrl, Map<String, Object> condition, int requestParamMode) {
        Request.Builder reqBuild = new Request.Builder();
        Request request = null;

        switch (requestParamMode) {
            case FastRemoteRequestUtil.PARAMETER:
                //只需要params传参的一定是get模式
                HttpUrl.Builder urlBuilder = HttpUrl.parse(requestUrl).newBuilder();
                reqBuild.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                if(condition != null)
                    condition.forEach((key, value) -> {
                        urlBuilder.addQueryParameter(key, value.toString());
                    });
                RequestBody paramsBody = RequestBody.create("",MediaType.get("application/x-www-form-urlencoded;charset=utf-8"));
                excuteRequestFactory(reqBuild,paramsBody).url(urlBuilder.build());
                request = reqBuild.build();
                break;
            case FastRemoteRequestUtil.FROMDATA:
                //只需要fromData传参的一定是post模式
                FormBody.Builder formBuilder = new FormBody.Builder();
                condition.forEach((key, value) -> {
                    formBuilder.add(key, value.toString());
                });
                RequestBody formBody = formBuilder.build();
                request = excuteRequestFactory(new Request.Builder()
                        .url(requestUrl),formBody)
                        .build();
                break;
            case FastRemoteRequestUtil.APPLICATION_JSON:
                // json模式两种情况都可能
                String conditionJson = JSONObject.toJSONString(condition);
                RequestBody requestBody = RequestBody.create(conditionJson,MediaType.get("application/json; charset=utf-8"));
                request = excuteRequestFactory(new Request.Builder()
                        .url(requestUrl),requestBody)
                        .build();
                break;
            default:
                logger.error("请先选择一个正确的请求方式!");
                break;
        }
        return request;
    }

    /**
     * 通过传入的cookie参数创建cookieJar对象
     * @param cookies
     */
    public void createCookieJar(Map<String, String> cookies){
        cookieJar = new CookieJar() {
            @Override
            public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                for(Cookie cookie:list){
                    logger.info("本次向" + httpUrl + ":保存的参数有 " +  cookie.name() + " =>" + cookie.value());
                }
            }

            @NotNull
            @Override
            public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                ArrayList<Cookie> requestCookies = new ArrayList<>();
                cookies.forEach((key, value)-> {
                    Cookie cookie = new Cookie.Builder().hostOnlyDomain(httpUrl.host()).name(key).value(value).build();
                    requestCookies.add(cookie);
                });
                return requestCookies;
            }
        };
    }

    /**
     * 代理判断是否设置cookie
     * @param builder
     * @return
     */
    public OkHttpClient.Builder setCookieJar(OkHttpClient.Builder builder){
        if(this.cookieJar != null)builder.cookieJar(this.cookieJar);
        return builder;
    }

    /**
     * 请求标准方法
     * @param requestUrl
     * @param condition
     * @param requestParamMode
     * @return
     */
    public JSONObject requestRestFul(String requestUrl, Map<String, Object> condition, int requestParamMode) {
        try {
            client = FastRemoteRequestUtil.getClientInstace();
            //装入可能需要的cookies信息
            OkHttpClient.Builder clientBuilder = setCookieJar(client.newBuilder());
            Request request = createRequest(requestUrl, condition, requestParamMode);
            Response response = clientBuilder.build().newCall(request).execute();
            String message = response.body().string();
            return JSONObject.parseObject(message);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查看是否在get模式下使用了不该使用的传参模式
     * @param requestParamMode
     * @return
     */
    public boolean isGetModeBelimit(int requestParamMode){
        switch (requestParamMode){
            case FastRemoteRequestUtil.FROMDATA:
                logger.error("get请求模式下不能使用fromData参数传递方式");
                return true;
            default:
                return false;
        }
    }

    /**
     * 不带指明参数请求类型的get请求,默认为paramter请求方式
     * @param requestUrl
     * @param condition
     * @return
     */
    public JSONObject requestGet(String requestUrl, Map<String, Object> condition) {
        return requestGet(requestUrl, condition, FastRemoteRequestUtil.PARAMETER);
    }

    /**
     * 带cookies传参的get请求方式
     *
     * @param requestUrl 请求地址
     * @param condition 传递的参数
     * @param cookies 传递的cookie参数
     * @param requestParamMode 请求参数模式
     * @return
     */
    public JSONObject requestGet(String requestUrl, Map<String, Object> condition, Map<String, String> cookies, int requestParamMode) {
        createCookieJar(cookies);
        return  requestGet(requestUrl, condition, requestParamMode);
    }

    /**
     * get请求方式,请求远程连接
     *
     * @param requestUrl       请求地址
     * @param condition        请求的参数
     * @param requestParamMode 请求传参方式目前有param、fromData、json三种方式
     * @return
     */
    public JSONObject requestGet(String requestUrl, Map<String, Object> condition, int requestParamMode) {
        if(isGetModeBelimit(requestParamMode))return null;
        requestMode = FastRemoteRequestUtil.GET;
        return requestRestFul(requestUrl,condition,requestParamMode);
    }

    public JSONObject requestPost(String requestUrl, Map<String, Object> condition) {
        return  requestPost(requestUrl, condition, FastRemoteRequestUtil.FROMDATA);
    }

    /**
     * 带cookie的post请求
     * @param requestUrl
     * @param condition
     * @param cookies
     * @param requestParamMode
     * @return
     */
    public JSONObject requestPost(String requestUrl, Map<String, Object> condition, Map<String, String> cookies, int requestParamMode) {
        createCookieJar(cookies);
        return  requestPost(requestUrl, condition, requestParamMode);
    }

    /**
     * post请求方式,请求远程连接
     * @param requestUrl
     * @param condition
     * @param requestParamMode
     * @return
     */
    public JSONObject requestPost(String requestUrl, Map<String, Object> condition, int requestParamMode) {
        requestMode = FastRemoteRequestUtil.POST;
        return requestRestFul(requestUrl,condition,requestParamMode);
    }

    public JSONObject requestPut(String requestUrl, Map<String, Object> condition) {
        return  requestPut(requestUrl, condition, FastRemoteRequestUtil.FROMDATA);
    }

    /**
     * 带cookie的put请求
     * @param requestUrl
     * @param condition
     * @param cookies
     * @param requestParamMode
     * @return
     */
    public JSONObject requestPut(String requestUrl, Map<String, Object> condition, Map<String, String> cookies, int requestParamMode) {
        createCookieJar(cookies);
        return  requestPut(requestUrl, condition, requestParamMode);
    }

    /**
     * put请求方式,请求远程连接
     * @param requestUrl
     * @param condition
     * @param requestParamMode
     * @return
     */
    public JSONObject requestPut(String requestUrl, Map<String, Object> condition, int requestParamMode) {
        requestMode = FastRemoteRequestUtil.PUT;
        return requestRestFul(requestUrl,condition,requestParamMode);
    }

    public JSONObject requestDelete(String requestUrl, Map<String, Object> condition) {
        return  requestPut(requestUrl, condition, FastRemoteRequestUtil.FROMDATA);
    }

    /**
     * 带cookie的delete请求
     * @param requestUrl
     * @param condition
     * @param cookies
     * @param requestParamMode
     * @return
     */
    public JSONObject requestDelete(String requestUrl, Map<String, Object> condition, Map<String, String> cookies, int requestParamMode) {
        createCookieJar(cookies);
        return  requestDelete(requestUrl, condition, requestParamMode);
    }

    /**
     * delete请求方式,请求远程连接
     * @param requestUrl
     * @param condition
     * @param requestParamMode
     * @return
     */
    public JSONObject requestDelete(String requestUrl, Map<String, Object> condition, int requestParamMode) {
        requestMode = FastRemoteRequestUtil.DELETE;
        return requestRestFul(requestUrl,condition,requestParamMode);
    }

    public static void main(String[] args){
        /**
         * fastRemoteRequestUtil调用方法说明
         * 1.实例化工具对象 FastRemoteRequestUtil fastRemoteRequestUtil = new FastRemoteRequestUtil();
         * 2.选择调用的方式 fastRemoteRequestUtil.requestXXX
         * 3.get常用调用方式 fastRemoteRequestUtil.requestGet(String requestUrl, Map<String, Object> condition,int requestParamMode)调用地址、请求参数、请求参数方式(可不填写模式为PARAMETER方式)
         *      带cookie方式 fastRemoteRequestUtil.requestGet(String requestUrl, Map<String, Object> condition, Map<String, String> cookies, int requestParamMode)三参传入传入的cookie参数
         * 4.post常用调用方式 fastRemoteRequestUtil.requestPost(String requestUrl, Map<String, Object> condition,int requestParamMode)调用地址、请求参数、请求参数方式(可不填写模式为FROMDATA方式)
         *      带cookie方式 fastRemoteRequestUtil.requestPost(String requestUrl, Map<String, Object> condition, Map<String, String> cookies, int requestParamMode)三参传入传入的cookie参数
         * 5.put常用调用方式 fastRemoteRequestUtil.requestPut(String requestUrl, Map<String, Object> condition,int requestParamMode)调用地址、请求参数、请求参数方式(可不填写模式为FROMDATA方式)
         *      带cookie方式 fastRemoteRequestUtil.requestPut(String requestUrl, Map<String, Object> condition, Map<String, String> cookies, int requestParamMode)三参传入传入的cookie参数
         * 6.delete常用调用方式 fastRemoteRequestUtil.requestDelete(String requestUrl, Map<String, Object> condition,int requestParamMode)调用地址、请求参数、请求参数方式(可不填写模式为FROMDATA方式)
         *      带cookie方式 fastRemoteRequestUtil.requestDelete(String requestUrl, Map<String, Object> condition, Map<String, String> cookies, int requestParamMode)三参传入传入的cookie参数
         */
       /*Map<String, String> params2 = new HashMap<>();
        params2.put("haishu-sc", "852FE60BB8F03562C1111C29C19DC53441DD3849F89C6EF0FF2E5278FDC360A23656062591C1E98CD4FB1B2C62786435A262E969895039AD3F65D77E00D429833D204007734800DF11A57DA6BE0CA5E9");
        Map<String, Object> params = new HashMap<>();
        params.put("clientId","5bdfe87b360000e61952a167");
        params.put("account","董恒测试");
        params.put("password","123456");
        //params.put("countPage",10);
        FastRemoteRequestUtil fastRemoteRequestUtil = new FastRemoteRequestUtil();
        JSONObject result = fastRemoteRequestUtil.requestPost("http://localhost:9888/api/login",params,FastRemoteRequestUtil.PARAMETER);
        logger.info(result.toString());*/

        /*Map<String, Object> params = new HashMap<>();
        params.put("uscode","91500000765916977A");
        params.put("pageNo","1");
        params.put("pageSize","10");
        params.put("order","desc");
        FastRemoteRequestUtil fastRemoteRequestUtil = new FastRemoteRequestUtil();
        JSONObject result = fastRemoteRequestUtil.requestPost("http://www.xycq.gov.cn/enterprise_api/credit/enterprise/searchEnterprisePage",params,FastRemoteRequestUtil.APPLICATION_JSON);
*/
        Map<String, Object> params = new HashMap<>();
        params.put("username","haishu");
        params.put("password","Hlw..2018");
        FastRemoteRequestUtil fastRemoteRequestUtil = new FastRemoteRequestUtil();
        JSONObject result = fastRemoteRequestUtil.requestPost("http://www.xycq.gov.cn/html/query/api_login.html",params,FastRemoteRequestUtil.FROMDATA);

        System.out.println(result);
    }
}
