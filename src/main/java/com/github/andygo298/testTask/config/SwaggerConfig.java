package com.github.andygo298.testTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket moviesAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.andygo298.testTask.controller"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Andrew Lozouski","https://www.linkedin.com/in/andrei-lozouski/","andygo298@gmail.com");
        StringVendorExtension listVendorExtension = new StringVendorExtension("Test task", "Candidate");
        return new ApiInfo("Town info RestFul Service API",
                "Town RestFul Service",
                "1.0",
                "",
                (Contact) contact,
                "Town - Source Code"
                ,"https://github.com/andygo298",
                Collections.singletonList(listVendorExtension));
    }
}
