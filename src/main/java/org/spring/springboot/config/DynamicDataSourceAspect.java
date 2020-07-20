package org.spring.springboot.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义注解 + AOP的方式实现数据源动态切换。
 * Created by pure on 2018-05-06.
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    Log log = LogFactory.getLog(DynamicDataSource.class);

    @Before("@annotation(org.spring.springboot.config.DS)")
    public void beforeSwitchDS(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            log.error(this.getClass().getName(),e);
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("@annotation(org.spring.springboot.config.DS)")
    public void afterSwitchDS(JoinPoint point){
        DataSourceContextHolder.clearDB();
    }

    @Pointcut("execution(* org.spring.springboot.app.service..*.*(..)) || execution(* org.spring.springboot.app.service..*.*(..))")
    public void pointCutAt() {}

    @Before("pointCutAt()")
    public void beforeAction(JoinPoint point) throws  NoSuchMethodException {
        DS classDs = point.getTarget().getClass().getAnnotation(DS.class);
        if(classDs != null)DataSourceContextHolder.setDB(classDs.value());
        //拦截的方法名称
        String methodName = point.getSignature().getName();
        //拦截的放参数类型
        Class[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
        Method method = point.getSignature().getDeclaringType().getMethod(methodName,parameterTypes);
        DS methodDs = method.getAnnotation(DS.class);
        if(methodDs != null)DataSourceContextHolder.setDB(methodDs.value());
        else if(classDs == null) DataSourceContextHolder.setDB("db1");
    }
}