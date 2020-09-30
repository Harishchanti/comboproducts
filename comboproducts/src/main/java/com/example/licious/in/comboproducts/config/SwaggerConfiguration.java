package com.example.licious.in.comboproducts.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger", name = "enabled", matchIfMissing = true)
public class SwaggerConfiguration {

    @Autowired
    Environment environment;

    Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);


    @Bean
    public Docket productApi() {

        logger.info("base package : {}   , Base URL : {}", environment.getProperty("swagger.basepackage", "com.example.licious.in"), environment.getProperty("swagger.baseurl", "/.*"));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(environment.getProperty("swagger.basepackage", "com.example.licious.in")))
                .paths(regex(environment.getProperty("swagger.baseurl", "/.*")))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                environment.getProperty("swagger.servicename", ""),
                environment.getProperty("swagger.description", ""),
                environment.getProperty("swagger.version", "0.1"),
                "", ApiInfo.DEFAULT_CONTACT,
                "",
                "", Collections.singleton(new VendorExtension() {
            @Override
            public String getName() {
                return "";
            }

            @Override
            public Object getValue() {
                return "";
            }
        }));
        return apiInfo;
    }


}
