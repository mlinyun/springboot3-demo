package com.mlinyun.springboot3demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Springboot3DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        System.out.println("Spring Boot Test");
    }

    // 注入数据源对象
    @Autowired
    private DataSource dataSource;

    /**
     * 测试数据库连接
     * 尝试获取数据库连接对象，如果获取成功，则说明数据库连接正常
     */
    @Test
    public void testDatabaseConnection() throws SQLException {
        // 获取数据源类型
        System.out.println("默认数据源为：" + dataSource.getClass());
        // 获取数据库连接对象
        Connection connection = dataSource.getConnection();
        // 断言连接对象不为空
        assertNotNull(connection, "数据库连接对象为空");
        // 检查连接是否有效
        assertTrue(connection.isValid(1), "数据库连接无效");
        // 关闭连接
        connection.close();
    }

}
