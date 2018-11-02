[TOC]

# 导包

## mybatis

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>
```

## jdbc

```xml
<!-- mssql -->
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <scope>runtime</scope>
</dependency>
```

# 配置数据源

```yaml
spring:
  datasource:
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
    url: jdbc:sqlserver://127.0.0.1:1433;DatabaseName=demo
    username: sa
    password: 123456
```

# 配置xml路径(option)

> 当 xml 文件和 mapper 文件不在同一个包下, 需要配置 xml 路径

```yaml
mybatis:
  mapper-locations: classpath:mapper/**/*.xml   # 当 mapper 和 xml 不在同一个包下, 需要制定 xml的路径
```

# mapper 接口

## @Mapper

- 添加注解 @Mapper, 表明是一个 mapper 接口

## @Repository (option)

> 加这个注解的原因:
> 单纯的不想 idea 报错
> 报错描述: IntelliJ idea在Spring配置无错误时,装配对象出现 Could not autowire. no beans of "XXX" type found
> [参考](https://blog.csdn.net/u010334295/article/details/78076510)
