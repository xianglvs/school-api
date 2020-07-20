package org.spring.springboot.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置
 *
 * @author ry
 * @date 2018/9/25
 */

//@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.spring.springboot.app"))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目接口文档")
                .description(
                        "<div>&nbsp;</div>" +
                        "<div>&nbsp;</div>" +
                        "<div>重要说明</div>" +
                        "<div>&nbsp;</div>" +
                        "<div>&nbsp;</div>" +
                        "<div>&nbsp;</div>" +
                        "<div>签名token获取：&nbsp;&nbsp;以用户账号密码作为参数请求\"用户登录\"接口,拿到用户信息和ticket，</div>" +
                        "<div>&nbsp;</div>" +
                        "<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                         "然后用ticket作为参数调用\"创建token\"接口来取到token,token有效期1个小时，</div>" +
                        "<div>&nbsp;</div>" +
                        "<div>&nbsp;</div>" +
                        "token读取规则：&nbsp;&nbsp;所有需要token的接口,系统读取token流程为：如果请求url中有token参数，" +
                        "<div>&nbsp;</div>" +
                        "<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "则用url参数中的token,如果url中没有取到token,则在cookie中读取token参数。</div>" +
                        "<div>&nbsp;</div>" +
                        "<div>&nbsp;</div>" +
                        "<div>接口返回说明：&nbsp;&nbsp;&nbsp;&nbsp;在返回数据中,为空或者为NULL的字段将不会出现在返回列表中。</div>" +
                        "<div>&nbsp;</div>"
                )
                .termsOfServiceUrl("www.baidu.com")
                .version("1.0")
                .build();
    }

}