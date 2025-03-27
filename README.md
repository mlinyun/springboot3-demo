# Spring Boot 3 学习项目

## 项目简介

这是一个基于 Spring Boot 3 的学习示例项目，集成了多种常用技术和框架，旨在展示 Spring Boot 3 在实际开发中的应用。本项目可作为学习参考或项目脚手架使用。

## 技术栈

- **核心框架**：Spring Boot 3.x
- **数据库连接池**：Druid
- **ORM 框架**：MyBatis、MyBatis-Plus
- **缓存**：Redis
- **API 文档**：Swagger 3.0 (SpringDoc)、Knife4j
- **其他**：Java 17+、Maven

## 项目特性

- 集成 Druid 连接池，提供完善的数据库连接管理
- 整合 MyBatis 与 MyBatis-Plus，简化数据库操作
- 实现 Redis 缓存支持，提高系统性能
- 集成 Swagger 3.0 与 Knife4j，方便接口文档管理和测试
- 提供逻辑删除功能，数据安全有保障

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 5.7+
- Redis 6.0+

### 本地开发

1. 克隆项目到本地

```bash
git clone https://github.com/mlinyun/springboot3-demo.git
```

2. 配置数据库
    - 创建数据库
    - 修改 `application.properties` 中的数据库连接信息

3. 配置 Redis
    - 确保 Redis 服务器正常运行
    - 根据需要修改 `application.properties` 中的 Redis 连接信息

4. 启动应用

```bash
mvn spring-boot:run
```

5. 访问
    - 应用：http://localhost:8080
    - Swagger 文档：http://localhost:8080/swagger-ui.html
    - Knife4j 文档：http://localhost:8080/doc.html
    - Druid 监控页面：http://localhost:8080/druid (用户名：admin，密码：123456)

## 项目配置

### 数据库配置

项目使用 Druid 作为数据源，提供了完善的连接池配置，包括初始连接数、最大连接数、连接检测等功能。

### MyBatis / MyBatis-Plus 配置

- 配置了 XML 文件路径与实体类包路径
- 启用了 MyBatis-Plus 的逻辑删除功能
- 关闭了驼峰命名规则映射

### Redis 配置

项目集成了 Redis，支持缓存功能，配置了连接池参数和超时时间。

### 接口文档配置

- 集成 Swagger 3.0，提供标准的 RESTful API 文档
- 整合 Knife4j 增强 API 文档体验，提供接口调试功能
- 配置了文档访问保护功能，保障接口文档安全

## 目录结构

```
src/main/
├── java/com/mlinyun/springboot3demo/
│   ├── config/         # 配置类
│   ├── controller/     # 控制器
│   ├── dao/            # 数据访问层
│   ├── entity/         # 实体类
│   ├── service/        # 服务层
│   │   └── impl/       # 服务实现
│   └── util/           # 工具类
└── resources/
    ├── mapper/         # MyBatis XML 映射文件
    ├── static/         # 静态资源
    ├── templates/      # 模板文件
    └── application.properties  # 应用配置文件
```

## 开发者

- [mlinyun](https://github.com/mlinyun)

## 许可证

[MIT License](LICENSE)