package com.lotte.danuri.messengeron.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.stereotype.Component;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
public class Workaround implements WebMvcOpenApiTransformationFilter {

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI openApi = context.getSpecification();
        Server local = new Server();
        local.setDescription("local");
        local.setUrl("http://localhost:8080");

        Server aws = new Server();
        aws.setDescription("aws");
        aws.setUrl("http://43.201.18.146/chat");
        openApi.setServers(Arrays.asList(local,aws));
        return openApi;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return delimiter.equals(DocumentationType.OAS_30);
    }
}