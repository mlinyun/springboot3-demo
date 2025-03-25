package com.mlinyun.springboot3demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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

    @Autowired
    private DruidDataSource druidDataSource;

    @Test
    public void testDruidConfigurations() {
        // 检查数据源是否为 DruidDataSource 类型
        assertEquals(DruidDataSource.class, druidDataSource.getClass().getSuperclass(), "数据源类型不为 DruidDataSource");
        // 检查 Druid 数据源配置是否生效
        assertEquals(10, druidDataSource.getInitialSize(), "initialSize 值不为 10");
        assertEquals(5, druidDataSource.getMinIdle(), "minIdle 值不为 5");
        assertEquals(30, druidDataSource.getMaxActive(), "maxActive 值不为 50");
        assertEquals(60000, druidDataSource.getMaxWait(), "maxWait 值不为 60000");
        assertEquals(60000, druidDataSource.getTimeBetweenEvictionRunsMillis(), "timeBetweenEvictionRunsMillis 值不为 60000");
        assertEquals(300000, druidDataSource.getMinEvictableIdleTimeMillis(), "minEvictableIdleTimeMillis 值不为 300000");
        assertEquals("SELECT 1", druidDataSource.getValidationQuery(), "validationQuery 值不为 SELECT 1");
        assertTrue(druidDataSource.isTestWhileIdle(), "testWhileIdle 值不为 true");
        assertFalse(druidDataSource.isTestOnBorrow(), "testOnBorrow 值不为 false");
        assertFalse(druidDataSource.isTestOnReturn(), "testOnReturn 值不为 false");
        assertFalse(druidDataSource.isPoolPreparedStatements(), "poolPreparedStatements 值不为 false");
        assertEquals(-1, druidDataSource.getMaxPoolPreparedStatementPerConnectionSize(), "maxPoolPreparedStatementPerConnectionSize 值不为 -1");
        assertTrue(druidDataSource.isUseGlobalDataSourceStat(), "useGlobalDataSourceStat 值不为 true");
    }

}
