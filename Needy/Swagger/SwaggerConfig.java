package com.dkv.victory.api.config.swagger;

import com.google.common.base.Predicates;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiModelReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    private static final String BASE = "/api/v1/";
    private static final String START = "(";
    private static final String END = ")";
    private static final String OR = "|";


    @Bean
    public Docket userManagementDocket() {
        String regex = BASE+ START
                +"oauth.*"+OR
                +"user.*"+OR
                +"client.*"+OR
                +"countr.*"+OR
                +"customer.*"+OR
                +"company.*"+OR
                +"mailed-token.*"+OR
                +"password.*"+OR
                +"signup.*"+OR
                +"card.*"+ END;
        return this.getDocket("UserManagement",regex);
    }

    @Bean
    public Docket obuDocket() {
        String regex = BASE+"obu.*";
        return this.getDocket("OBU",regex);
    }

    @Bean
    public Docket vehicleDocket() {
        String regex = BASE+"vehicle.*";
        return this.getDocket("Vehicle",regex);
    }

    /*@Bean
    public Docket authDocket() {
        String regex = "/oauth/token";
        return this.getDocket("Authentication",regex);
    }*/


    private Docket getDocket(String name, String regex){
        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.getApiInfo())
                .groupName(name)
                .pathMapping("/")
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .directModelSubstitute(OffsetDateTime.class, OffsetDateTime.class)
                .securityContexts(Lists.newArrayList(this.securityContext()))
                .securitySchemes(Lists.newArrayList(this.apiKeyScheme()))
                .useDefaultResponseMessages(false);

        docket = docket.select()
                .paths(PathSelectors.regex(regex))
//                .paths(Predicates.not(PathSelectors.regex("/oauth/token")))
                .build();
        return docket;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("DKV Victory API's")
                .description("DKV Victory Restful API Documentation")
                .version("v1")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Brain Station 51","https://brainstation-51.com/",""))
                .build();
    }

    private ApiKey apiKeyScheme() {
        return new ApiKey("Access Token", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    private AuthorizationScope[] authorizationScopes() {
        AuthorizationScope readScope = new AuthorizationScope("read", "Read access");
        AuthorizationScope writeScope = new AuthorizationScope("write", "Write access");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = readScope;
        authorizationScopes[1] = writeScope;

        return authorizationScopes;
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> list = new ArrayList<>();
        list.add(new SecurityReference("Access Token", authorizationScopes()));
        return list;
    }

    @Bean("swaggerUiMvcConfigBean")
    public WebMvcConfigurer configurerSwaggerUiMvcConfiguration() {
        return new WebMvcConfigurer() {
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // ------------- Swagger UI Resource registry Starts --------------
                registry.addResourceHandler("swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");

                // ------------- Swagger UI Resource registry Ends --------------
            }

            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
            }
        };
    }


    @Primary
    @Bean
    public ApiListingScanner addExtraOperations(
            ApiDescriptionReader apiDescriptionReader,
            ApiModelReader apiModelReader,
            DocumentationPluginsManager pluginsManager) {
        return new LoginOperationDocumentation(apiDescriptionReader, apiModelReader, pluginsManager);
    }
}

