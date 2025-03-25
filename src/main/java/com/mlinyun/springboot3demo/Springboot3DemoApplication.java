package com.mlinyun.springboot3demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mlinyun.springboot3demo.dao")
@SpringBootApplication
public class Springboot3DemoApplication {

    public static void main(String[] args) {
        // 运行 run() 方法，项目就可以正常启动了
        SpringApplication.run(Springboot3DemoApplication.class, args);
    }

}
