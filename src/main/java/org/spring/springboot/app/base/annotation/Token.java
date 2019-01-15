package org.spring.springboot.app.base.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

    TokenType type() default TokenType.TOKEN;
    String[] RequiresPermissions() default {};
}
