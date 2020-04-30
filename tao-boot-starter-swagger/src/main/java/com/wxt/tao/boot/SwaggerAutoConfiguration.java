
package com.wxt.tao.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration(proxyBeanMethods = false)
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperty.class)
@ConditionalOnClass(Docket.class)
@ConditionalOnProperty(prefix = "tao.boot.swagger", value = "enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SwaggerAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public Docket docket(SwaggerProperty swaggerProperty) {
        checkValid(swaggerProperty);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo(swaggerProperty))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperty.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    private void checkValid(SwaggerProperty swaggerProperty) {
        if (swaggerProperty.getBasePackage() == null) {
            Class<?> mainApplicationClass = deduceMainApplicationClass();
            if (mainApplicationClass != null) {
                swaggerProperty.setBasePackage(mainApplicationClass.getPackage().getName());
            }
        }
        if (swaggerProperty.getVersion() == null) {
            swaggerProperty.setVersion("1.0.0");
        }
    }

    private Class<?> deduceMainApplicationClass() {
        try {
            StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if ("main".equals(stackTraceElement.getMethodName())) {
                    return Class.forName(stackTraceElement.getClassName());
                }
            }
        } catch (ClassNotFoundException ex) {
            // Swallow and continue
        }
        return null;
    }

    private ApiInfo apiInfo(SwaggerProperty swaggerProperty) {
        return new ApiInfoBuilder()
                .title(swaggerProperty.getTitle())
                .description(swaggerProperty.getDescription())
                .termsOfServiceUrl(swaggerProperty.getTermsOfServiceUrl())
                .contact(new Contact(swaggerProperty.getContactName(), swaggerProperty.getContactUrl(), swaggerProperty.getContactEmail()))
                .license(swaggerProperty.getLicense())
                .licenseUrl(swaggerProperty.getLicenseUrl())
                .version(swaggerProperty.getVersion())
                .build();
    }

}
