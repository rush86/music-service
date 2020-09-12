package com.skelton.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@EnableSwagger2
@Configuration
@Controller
@Profile("!test")
public class SwaggerConfig {

    @Value("${spring.application.name}")
    public String name;

    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }

    // Forces prod to have only GET buttons available for requests
    @Bean
    @Profile("prod")
    UiConfiguration uiConfig() {
        return new UiConfiguration(null, new String[]{"get"});
    }

    @Bean
    public Docket skeletonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("spring-boot-skeleton-project")
                .directModelSubstitute(LocalDate.class, String.class)
                .apiInfo(apiInfo())
                .select()
                .paths(path -> path != null
                        && (path.contains("/api")
                ))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-boot-skeleton-project")
                .description("Spring boot skeleton project")
                .build();
    }
}