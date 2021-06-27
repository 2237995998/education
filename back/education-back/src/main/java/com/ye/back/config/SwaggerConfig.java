package com.ye.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置了swaggerDocket的bean实例
    @Bean
    public Docket docket(Environment environment){
//        //设置要显示的swagger环境
//        Profiles profiles = Profiles.of("dev", "test");
//        //通过environment.acceptsProfiles 判断是否处在自己设定环境中
//        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hjy")
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ye.back.controller"))
                //.paths()过滤什么路经
                .paths(PathSelectors.any())
                .build();
    }

    //配置swagger信息apiInfo
    private ApiInfo apiInfo(){

        //作者信息
        Contact DEFAULT_CONTACT = new Contact("何进业", "", "2237995998@qq.com");

        return new ApiInfo(
                "后端开发框架API文档",
                "学海无涯苦作舟",
                "1.0",
                "",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
