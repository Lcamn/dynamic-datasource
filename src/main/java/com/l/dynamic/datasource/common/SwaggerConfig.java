package com.l.dynamic.datasource.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("适用于前后端分离统一的接口文档")
                .version("1.0")
                .build();
    }
    // @Bean
    // public Docket docket() {
    //     return new Docket(DocumentationType.OAS_30).apiInfo(
    //             new ApiInfoBuilder()
    //                     .contact(new Contact("Kern", "", "825***@qq.com"))
    //                     .title("Swagger2测试项目")
    //                     .build()
    //     );
    // }

}
