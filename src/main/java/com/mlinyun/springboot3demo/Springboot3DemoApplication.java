package com.mlinyun.springboot3demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 使用 @EnableScheduling 注解，开启定时任务功能
@EnableScheduling
// 使用 @MapperScan 注解，扫描 com.mlinyun.springboot3demo.dao 包下的所有 Mapper 接口
@MapperScan("com.mlinyun.springboot3demo.dao")
// 使用 @SpringBootApplication 注解，标注这是一个 Spring Boot 应用
@SpringBootApplication
public class Springboot3DemoApplication {

    public static void main(String[] args) {
        // 运行 run() 方法，项目就可以正常启动了
        SpringApplication.run(Springboot3DemoApplication.class, args);
    }

}
