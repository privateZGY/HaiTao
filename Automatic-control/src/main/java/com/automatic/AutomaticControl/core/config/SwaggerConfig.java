package com.automatic.AutomaticControl.core.config;

import com.automatic.AutomaticControl.core.statics.GlobalStaticVariable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: micro-questions-answers
 * @ClassName: SwaggerConfig
 * @Description: Swagger2配置类
 * @Author: zgy
 * @Date: 2021/11/10 13:07
 * swagger叫做接口文档，用swagger生成接口文档
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        // start 加上一个token
        RequestParameter requestParameter = new RequestParameterBuilder()
                // 不能叫Authorization
                .name(GlobalStaticVariable.HTTP_HEADER)
                .description(GlobalStaticVariable.HTTP_HEADER)
                .in(ParameterType.HEADER)
                .required(true)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build();
        List<RequestParameter> list = new ArrayList<>();
        list.add(requestParameter);
        // end 加上一个token
        return new Docket(DocumentationType.SWAGGER_2)
                // 关于文档的各种信息
                .apiInfo(apiInfo())
                // 使Swagger生效 项目上线的时候这个需要设置为false
                .enable(true)
                .groupName("zgy")
                .select()
                //选择扫描的接口  controller //指定扫描的接口
                .apis(RequestHandlerSelectors.any())
                .build().globalRequestParameters(list);
    }


    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("自动化管理平台")
                // 创建人     个人信息地址    邮箱
                .contact(new Contact("zgy", "", "1985701779@qq.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("自动化管理平台API接口")
                .build();
    }
}
