package com.dkv.victory.api.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import java.util.*;

/**
 * @author SbrTa
 * @since 1/22/2020  8:43 PM
 */

class LoginOperationDocumentation extends ApiListingScanner {
    @Autowired
    private TypeResolver typeResolver;

    public LoginOperationDocumentation(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager) {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context) {
        final Multimap<String, ApiListing> def = super.scan(context);
        final List<ApiDescription> apis = new LinkedList<>();
        final List<Operation> operations = new ArrayList<>();

        Set<String> tagName = new HashSet<>();
        tagName.add("Authentication");
        operations.add(new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .uniqueId("login")
                .parameters(Arrays.asList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .description("Authorization header")
                                .parameterType("header")
                                .defaultValue("Basic ZGt2LWFuZ3VsYXItY2xpZW50OnNlY3JldA==")
                                .type(typeResolver.resolve(String.class))
                                .modelRef(new ModelRef("string"))
                                .build(),
                        new ParameterBuilder()
                                .name("username")
                                .description("Email")
                                .parameterType("formData")
                                .type(typeResolver.resolve(String.class))
                                .modelRef(new ModelRef("string"))
                                .build(),
                        new ParameterBuilder()
                                .name("password")
                                .description("Password")
                                .parameterType("formData")
                                .type(typeResolver.resolve(String.class))
                                .modelRef(new ModelRef("string"))
                                .build(),
                        new ParameterBuilder()
                                .name("grant_type")
                                .description("Grant Type")
                                .parameterType("formData")
                                .defaultValue("password")
                                .type(typeResolver.resolve(String.class))
                                .modelRef(new ModelRef("string"))
                                .build()
                ))
                .summary("Log in")
                .notes("Input grand_type=password")
                .responseMessages(responseMessages())
                .responseModel(new ModelRef(("UserToken")))
                .tags(tagName)
                .build());
        apis.add(new ApiDescription("authentication","/oauth/token", "Authentication documentation", operations, false));

        Tag tag = new Tag("Authentication","Authentication");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        def.put("authentication", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
                .apis(apis)
                .description("Custom authentication")
                .tags(tags)
                .build());

        return def;
    }

    private Set<ResponseMessage> responseMessages() {
        return Set.of(new ResponseMessageBuilder()
                        .code(200)
                        .message("Login successful")
                        .build(),
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad credentials / Unsupported grant type")
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("User not found")
                        .build()
        );
    }
}
