package com.mlinyun.springboot3demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ThymeleafController {

    @GetMapping("/thymeleaf")
    public String hello(HttpServletRequest request, @RequestParam(value = "description", required = false, defaultValue = "Spring Boot Thymeleaf") String description) {
        request.setAttribute("description", description);
        return "thymeleaf";
    }

    @GetMapping("/attributes")
    public String attributes(ModelMap map) {
        // 更改 h1 内容
        map.put("title", "Thymeleaf 标签演示");
        // 更改 id、name、value
        map.put("th_id", "thymeleaf-input");
        map.put("th_name", "thymeleaf-input");
        map.put("th_value", "13");
        // 更改 class、href
        map.put("th_class", "thymeleaf-class");
        map.put("th_href", "http://13blog.site");
        return "attributes";
    }

    @GetMapping("/simple")
    public String simple(ModelMap map) {
        map.put("thymeleafText", "lanqiao");
        map.put("number1", 2025);
        map.put("number2", 3);
        return "simple";
    }

    @GetMapping("/complex")
    public String complex(ModelMap map) {
        map.put("title", "Thymeleaf 语法测试");
        map.put("testString", "玩转 Spring Boot");
        map.put("bool", true);
        map.put("testArray", new Integer[]{2023, 2024, 2025, 2026});
        map.put("testList", Arrays.asList("Spring", "Spring Boot", "Thymeleaf", "MyBatis", "Java"));
        Map testMap = new HashMap();
        testMap.put("platform", "lanqiao");
        testMap.put("title", "玩转 Spring Boot 3");
        testMap.put("author", "十三");
        map.put("testMap", testMap);
        map.put("testDate", new Date());
        return "complex";
    }

}
