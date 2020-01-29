package com.dkv.victory.api.config.swagger;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.bean.validators.plugins.Validators;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;
import static springfox.bean.validators.plugins.Validators.annotationFromField;

@Component
@Order(Validators.BEAN_VALIDATOR_PLUGIN_ORDER)
public class SwaggerValidationAnnotationPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public boolean supports(DocumentationType delimiter) {
        // we simply support all documentationTypes!
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        extractAnnotationForNotBlank(context);
        extractAnnotationForNotEmpty(context);
        extractAnnotationForNotNull(context);
        extractAnnotationForPattern(context);
    }

    @VisibleForTesting
    void extractAnnotationForNotBlank(ModelPropertyContext context) {
        var notBlank = annotationFromBean(context, NotBlank.class).or(annotationFromField(context, NotBlank.class));
        if (notBlank.isPresent()) {
            context.getBuilder().required(true).allowEmptyValue(false).description(notBlank.get().message());
        }
    }

    @VisibleForTesting
    void extractAnnotationForNotEmpty(ModelPropertyContext context) {
        var notEmpty = annotationFromBean(context, NotEmpty.class).or(annotationFromField(context, NotEmpty.class));
        if (notEmpty.isPresent()) {
            context.getBuilder().required(true).allowEmptyValue(false).description(notEmpty.get().message());
        }
    }

    @VisibleForTesting
    void extractAnnotationForNotNull(ModelPropertyContext context) {
        var notNull = annotationFromBean(context, NotNull.class).or(annotationFromField(context, NotNull.class));
        if (notNull.isPresent()) {
            context.getBuilder().required(true).allowEmptyValue(false).description(notNull.get().message());
        }
    }

    @VisibleForTesting
    void extractAnnotationForPattern(ModelPropertyContext context) {
        var pattern = annotationFromBean(context, Pattern.class).or(annotationFromField(context, Pattern.class));
        if (pattern.isPresent()) {
            context.getBuilder().description(pattern.get().message());
        }
    }
}
