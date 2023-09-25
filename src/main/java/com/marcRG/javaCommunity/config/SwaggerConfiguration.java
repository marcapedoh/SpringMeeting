package com.marcRG.javaCommunity.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.marcRG.javaCommunity.constants.Utils.APP_ROOT;

public class SwaggerConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("Task Management API document")
                                .title("Management of Task and planification API app")
                                .build()
                )
                .groupName("REST API V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.marcRG.javaCommunity"))
                .paths(PathSelectors.ant(APP_ROOT+"/**"))
                .build();
    }
}
