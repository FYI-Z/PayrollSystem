package com.tomorrow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //swagger的Docket
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo());
    }

    //配置swagger信息
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact =  new Contact("liang" + "", "", "1257322785@qq.com");
        return new ApiInfo(
                "miku的swaggerApi文档",
                "miku一生一世",
                "1.0",
                "",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
