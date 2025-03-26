package com.mlinyun.springboot3demo.service;

import com.mlinyun.springboot3demo.entity.TbUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    @DisplayName("测试添加用户")
    @Transactional
    void testAddUser() {

        // 1. 创建用户对象
        TbUser tbUser = new TbUser();
        tbUser.setUsername("LingYun");
        tbUser.setPassword("123456");

        // 2. 添加用户
        boolean saveResult = tbUserService.save(tbUser);

        // 3. 断言添加结果
        assertTrue(saveResult, "添加用户失败");

        // 4. 验证用户 ID 是否正常生成
        assertNotNull(tbUser.getId(), "用户 ID 为空");

        // 5. 从数据库中查询用户信息
        TbUser savedUser = tbUserService.getById(tbUser.getId());
        assertNotNull(savedUser, "查询的用户不存在");
        assertEquals("LingYun", savedUser.getUsername(), "用户名不匹配");
        assertEquals("123456", savedUser.getPassword(), "密码不匹配");
        assertEquals(0, savedUser.getIsDelete(), "删除标记不正确");
    }

}