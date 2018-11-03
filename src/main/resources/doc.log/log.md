[TOC]

# 日志要求

| 要求           |                             说明                             |                           详细说明                           |                           目的                            | 优先级 |
| -------------- | :----------------------------------------------------------: | :----------------------------------------------------------: | :-------------------------------------------------------: | :----: |
| 时效性         |         保存一定时间内的日志, 丢弃超过一定时间的日志         |                       保留60天内的日志                       |           时间太久的日志没有多大意义, 还占硬盘            |   高   |
| 限制总大小     |                       文件总大小限制,                        |                     总日志文件不超过20G                      |                防止占用太大服务器硬盘空间                 |   高   |
| 限制单文件大小 |                       单个文件大小限制                       |                    单个日志文件不超过20M                     |               单文件太大, 运维查看时非常慢                |        |
| 按级别分割     |          不同级别的日志分开输出, 保存到不同的文件中          | info 级别日志保存在 info 文件夹,error 级别日志保存在 error文件夹 |      一般来说, 运维比较关心error级别的日志, 方便查看      |   高   |
| 按时间分割     | 1天内每隔一段时间生成1个文件, 当这段时间生成的文件大小超过单文件大小限制, 进行分割 | 1小时生成1个日志文件(超过单文件大小, 生成多个文件, 文件名加_{1} _{2} 以区别) | 当1天的日志非常多时, 可按小时来生成文件, 方便运维查找定位 |   低   |
| 易配置         |                参数很容易配置, 无需重启即生效                |           配置保留多少天的日志, 总大小不超过多少等           |               方便配置, 也方便运维修改配置                |   高   |
| 易查看         |              控制台以不同颜色标识不同级别的日志              |                                                              |                 方面开发查看控制台的日志                  |   低   |
| 控制打印       |         error/warn打印全包, info/debug只打印项目的包         |                                                              |             减少日志量, 也方便开发及运维查看              |   低   |



# 日志框架选择

## logback

### 优点

- 功能较全, 效率高, 容易上手
- spring 使用的是 logback
- 日志要求 框架都实现

### 缺点

- 较长时间未更新

## log4j

## log2j

> 这里选用 logback

# logback 实现

## 坐标

springboot 已集成

## 日志配置文件路径

- 默认 spring-logback.xml / logback.xml, 官方推荐 spring-logback.xml

application.yml

```yml
logging:
	config: classpath:spring-logback.xml
```

## spring-logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<configuration debug="false" scan="true" scanPeriod="60 seconds">

    <!-- 日志级别 -->
    <property name="log.level" value="debug" />
    <!-- 日志文件存储位置 -->
    <property name="file.filePath" value="./logs/demo" />
    <!-- 日志文件保留天数 -->
    <property name="file.maxHistory" value="60" />
    <!-- 日志文件总大小 -->
    <property name="file.totalSizeCap" value="20GB" />
    <!-- 单个日志文件大小 -->
    <property name="file.maxFileSize" value="10MB" />
    <!-- 文件日志输出格式 -->
    <property name="log.pattern" value="%-12(%d{yyyy-MM-dd HH:mm:ss.SSS}) %-5level [%t] %c : %msg%n" />
    <!-- 控制台日志输出格式 -->
    <property name="console.pattern" value="%-12(%d{yyyy-MM-dd HH:mm:ss.SSS}) %highlight(%-5level) [%t] %cyan(%c) : %highlight(%msg) %n" />
    <!-- 项目根目录 -->
    <property name="project.root" value="org.anson.demo" />

    <!-- DEBUG控制台日志格式 -->
    <appender name="DEBUG_CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--配置日志的级别过滤器，只保留DEBUG Level的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>DEBUG</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern>${console.pattern}</pattern>
        </encoder>
    </appender>

    <!-- INFO控制台日志格式 -->
    <appender name="INFO_CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--配置日志的级别过滤器，只保留INFO Level的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern>${console.pattern}</pattern>
        </encoder>
    </appender>

    <!-- WARN控制台日志格式 -->
    <appender name="WARN_CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--配置日志的级别过滤器，只保留WARN Level的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>WARN</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern>${console.pattern}</pattern>
        </encoder>
    </appender>

    <!-- ERROR控制台日志格式 -->
    <appender name="ERROR_CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <!--配置日志的级别过滤器，只保留ERROR Level的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern>${console.pattern}</pattern>
        </encoder>
    </appender>

    <!--只输出DEBUG Level到日志文件的appender-->
    <appender name="DEBUG_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--日志文件输出的文件名,%i用来标记分割日志的序号 -->
            <fileNamePattern>${file.filePath}/debug/debug.%d{yyyy-MM-dd_HH}.%i.log</fileNamePattern>
            <!-- 单个日志文件大小 -->
            <maxFileSize>${file.maxFileSize}</maxFileSize>
            <!-- 保存 x 天的日志文件 -->
            <maxHistory>${file.maxHistory}</maxHistory>
            <!-- 日志文件总大小 -->
            <totalSizeCap>${file.totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <!--配置日志的级别过滤器，只保留DEBUG Level的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>DEBUG</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${log.pattern}</pattern>
        </encoder>
    </appender>

    <!--只输出INFO Level到日志文件的appender-->
    <appender name="INFO_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--日志文件输出的文件名,%i用来标记分割日志的序号 -->
            <fileNamePattern>${file.filePath}/info/info.%d{yyyy-MM-dd_HH}.%i.log</fileNamePattern>
            <!-- 单个日志文件大小 -->
            <maxFileSize>${file.maxFileSize}</maxFileSize>
            <!-- 保存 x 天的日志文件 -->
            <maxHistory>${file.maxHistory}</maxHistory>
            <!-- 日志文件总大小 -->
            <totalSizeCap>${file.totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <!--配置日志的级别过滤器，只保留INFO Level的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${log.pattern}</pattern>
        </encoder>
    </appender>

    <!--只输出WARN Level到日志文件的appender-->
    <appender name="WARN_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--日志文件输出的文件名,%i用来标记分割日志的序号 -->
            <fileNamePattern>${file.filePath}/warn/warn.%d{yyyy-MM-dd_HH}.%i.log</fileNamePattern>
            <!-- 单个日志文件大小 -->
            <maxFileSize>${file.maxFileSize}</maxFileSize>
            <!-- 保存 x 天的日志文件 -->
            <maxHistory>${file.maxHistory}</maxHistory>
            <!-- 日志文件总大小 -->
            <totalSizeCap>${file.totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <!--配置日志的级别过滤器，只保留WARN Level的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>WARN</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${log.pattern}</pattern>
        </encoder>
    </appender>

    <!--只输出ERROR Level到日志文件的appender-->
    <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--日志文件输出的文件名,%i用来标记分割日志的序号 -->
            <fileNamePattern>${file.filePath}/error/error.%d{yyyy-MM-dd_HH}.%i.log</fileNamePattern>
            <!-- 单个日志文件大小 -->
            <maxFileSize>${file.maxFileSize}</maxFileSize>
            <!-- 保存 x 天的日志文件 -->
            <maxHistory>${file.maxHistory}</maxHistory>
            <!-- 日志文件总大小 -->
            <totalSizeCap>${file.totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <!--配置日志的级别过滤器，只保留ERROR Level的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${log.pattern}</pattern>
        </encoder>
    </appender>

    <!-- debug/info 只输出项目的包, 不输出项目以外的; 输出以后, 日志不再向上传递(additivity="false") -->
    <logger name="${project.root}" level="${log.level}" additivity="false">
        <appender-ref ref="DEBUG_FILE" />
        <appender-ref ref="INFO_FILE" />
        <!-- 生产环境请注释掉 DEBUG_CONSOLE 和 INFO_CONSOLE, 生产环境不需要输出到控制台 -->
        <appender-ref ref="DEBUG_CONSOLE" />
        <appender-ref ref="INFO_CONSOLE" />
    </logger>

    <!-- warn/error 输出所有的包 -->
    <root level="${log.level}">
        <appender-ref ref="WARN_FILE" />
        <appender-ref ref="ERROR_FILE" />
        <!-- 生产环境请注释掉 DEBUG_CONSOLE 和 INFO_CONSOLE, 生产环境不需要输出到控制台 -->
        <appender-ref ref="WARN_CONSOLE" />
        <appender-ref ref="ERROR_CONSOLE" />
    </root>

</configuration>
```

# logback 知识

## root 节点

## appender 节点

- 格式化日志输出

## logger

## [格式](https://logback.qos.ch/manual/layouts.html)