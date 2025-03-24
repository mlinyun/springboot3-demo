package com.mlinyun.springboot3demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringBootWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${file.upload.path:./src/main/resources/upload/}")
    private String fileUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加自定义的静态资源映射，使 upload 目录下的文件可以通过 /files/** 访问
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + fileUploadPath);
    }
}
