package com.mlinyun.springboot3demo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    // 扫描的包路径
    private static final String basePackage = "com.mlinyun.springboot3demo.controller";

    /**
     * 创建 GroupedOpenApi 对象，用于分组 API
     *
     * @return GroupedOpenApi 对象
     */
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Springboot3demo")
                .packagesToScan(basePackage)
                .build();
    }

    /**
     * 创建该 API 的基本信息（这些基本信息会展现在文档页面中）
     *
     * @return Info 对象
     */
    private Info apiInfo() {
        Contact contact = new Contact();
        contact.setEmail("lingyun2311@gmail.com");
        contact.setName("mlinyun");
        contact.setUrl("https://github.com/mlinyun");
        return new Info()
                .title("Spring Boot 3 Demo API 文档")
                .description("Spring Boot 3 Demo API 文档")
                .version("v1.0.0")
                .contact(contact)
                .license(new License().name("Apache 2.0").url("https://github.com/mlinyun"));
    }

    /**
     * 创建 ExternalDocumentation 对象，用于指定项目的外部文档
     *
     * @return ExternalDocumentation 对象
     */
    private ExternalDocumentation externalDocumentation() {
        return new ExternalDocumentation()
                .description("项目 GitHub 地址")
                .url("https://github.com/mlinyun/springboot3-demo");
    }

    /**
     * 创建 OpenAPI 对象，用于配置整个 API 文档
     *
     * @return OpenAPI 对象
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .externalDocs(externalDocumentation());
    }

}
