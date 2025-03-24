package com.mlinyun.springboot3demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {

    private final JdbcTemplate jdbcTemplate;

    // 通过构造方法注入 JdbcTemplate
    @Autowired
    public JdbcController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 查询 tb_user 所有记录
    @GetMapping("/users/queryAll")
    public List<Map<String, Object>> queryAll() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tb_user");
        return list;
    }

    // 根据传入的参数向 tb_user 表中新增一条记录
    @GetMapping("/users/insert")
    public boolean insert(String name, String password) {
        if (!StringUtils.hasText(name) || !StringUtils.hasText(password)) {
            return false;
        }
        // 判断数据库中是否已经存在该用户名
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tb_user where name = ?", name);
        if (list.isEmpty()) {
            int res = jdbcTemplate.update("insert into tb_user(name,password) values(?,?)", name, password);
            return res > 0;
        }
        return false;
    }
}
