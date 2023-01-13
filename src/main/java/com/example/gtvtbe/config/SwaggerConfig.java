package com.example.gtvtbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.*;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Configuration
@EnableSwagger2
public class SwaggerConfig{
    private final Set<String> consumerAndProduces = new HashSet<>(List.of("application/json"));
    @Bean
    public Docket api() throws IOException {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.gtvtbe.endpoint"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .consumes(consumerAndProduces)
                .produces(consumerAndProduces)
                .useDefaultResponseMessages(false)
                .enable(true);
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }
    private ApiInfo apiInfo() throws IOException {
        String file = System.getProperty("user.dir").concat("/build/tmp/jar/MANIFEST.MF");
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        Manifest manifest = new Manifest(in);

        Attributes attributes = manifest.getMainAttributes();
        return new ApiInfo(
                attributes.getValue("Implementation-Title"),
                attributes.getValue("Implementation-Description"),
                attributes.getValue("Implementation-Version"),
                attributes.getValue("Specification-Term-Service-Url"),
                new Contact(
                        attributes.getValue("Contact-Name"),
                        attributes.getValue("Contact-Url"),
                        attributes.getValue("Contact-Email")
                ),
                attributes.getValue("Specification-License"),
                attributes.getValue("Specification-License-Url"),
                vendorExtensions()
        );
    }

    private List<VendorExtension> vendorExtensions() {
        List<VendorExtension> list = List.of();
        return list;
    }

}
