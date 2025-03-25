package com.mlinyun.springboot3demo.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataSourceController {

    private final DataSource dataSource;

    @Autowired
    public DataSourceController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 查询数据源信息
    @GetMapping("/datasource")
    public Map<String, Object> getDataSource() throws SQLException {
        Map<String, Object> result = new HashMap<>();
        result.put("数据源类名", dataSource.getClass() + "");
        // 获取数据库连接对象
        Connection connection = dataSource.getConnection();
        // 判断连接对象是否为空
        result.put("能否正确获得连接", connection != null);
        if (connection != null) {
            connection.close();
        }
        return result;
    }

    @GetMapping("/datasource2")
    public Map<String, Object> datasource2() throws SQLException {
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        Map<String, Object> result = new HashMap<>();
        result.put("数据源类名", druidDataSource.getClass() + "");
        // 获取数据库连接对象
        Connection connection = druidDataSource.getConnection();
        // 判断连接对象是否为空
        result.put("能否正确获得连接", connection != null);
        result.put("initialSize 值为", druidDataSource.getInitialSize());
        result.put("maxActive 值为", druidDataSource.getMaxActive());
        result.put("minIdle 值为", druidDataSource.getMinIdle());
        result.put("validationQuery 值为", druidDataSource.getValidationQuery());
        result.put("maxWait 值为", druidDataSource.getMaxWait());
        if (connection != null) {
            connection.close();
        }
        return result;
    }

    @GetMapping("/druid/stat")
    public List<Map<String, Object>> druidStat() throws SQLException {
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

}
