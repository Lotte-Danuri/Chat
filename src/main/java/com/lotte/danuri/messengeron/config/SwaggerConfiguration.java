package com.lotte.danuri.messengeron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api(){
        Server local = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(), Collections.emptyList());
        Server aws = new Server("aws", "http://http://43.201.18.146/chat", "for aws", Collections.emptyList(), Collections.emptyList());
        Server dns = new Server("dns", "http://http://sbbro.xyz/chat", "for dns", Collections.emptyList(), Collections.emptyList());

        return new Docket(DocumentationType.OAS_30)
                .servers(local,aws,dns)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lotte.danuri.messengeron.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Chat API")
                .description("Chat 관련 API")
                .version("1.0")
                .build();
    }
}