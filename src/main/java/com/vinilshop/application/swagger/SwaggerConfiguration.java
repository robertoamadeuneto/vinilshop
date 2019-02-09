package com.vinilshop.application.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class responsible for Swagger2 configurations.
 *
 * @author Roberto Amadeu Neto
 * @since 05/02/2018
 * @version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Creates a {@link Docket} for the API.
     *
     * @return a {@link Docket}.
     */
    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_12)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vinilshop.application.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    /**
     * Creates a {@link ApiInfo} with the API's meta data to be shown on
     * swagger.
     *
     * @return a {@link ApiInfo}.
     */
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Burgerly by Roberto Amadeu Neto")
                .description("The best music application.")
                .version("1.0")
                .contact(new Contact("Roberto Amadeu Neto", "http://www.robertoaneto.com.br", "robertoaneto@hotmail.com.br"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/license/LICENSE-2.0")
                .build();
    }
}
