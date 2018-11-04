[TOC]

# logback

## 日志级别

- error > warn > info > debug

> 还有些不常用的级别, 就不提了

## 输出

- 当配置为输出 error 时, 输出级别大于等于 error 级别的日志(即 error 级别)
- 当配置为输出 warn 时, 输出级别大于等于 warn 级别的日志(即 error 和 warn 级别)
- 当配置为输出 info 时, 输出级别大于等于 info 级别的日志(即 error warn 和 info 级别)
- etc...

## 使用

- 不使用字符串拼接, 使用占位符

> eg: 不应这样写 log.info("a = " + a);
> 应该这样写 log.info("a = {}", a);

- 打印堆栈信息 error(String, Throwable)

> log.error(e.toString(), e);

## 根节点

- 根节点 configuration

```xml
<configuration scan="true" scanPeriod="60 secends" debug="false">
    <property name="log.level" value="debug" />
    
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d -2 %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="com.dudu.controller">
        <appender-ref ref="console"/>
    </logger>
    
    <root level="debug">
        <appender-ref ref="console" />
    </root>
    
</configuration>
```
### 属性

#### 属性 scan

- 当此属性设置为true时, 配置文件如果发生改变, 将会被重新加载, 默认值为true

#### 属性 scanPeriod

- 设置监测配置文件是否有修改的时间间隔, 如果没有给出时间单位, 默认单位是毫秒. 当scan为true时, 此属性生效. 默认的时间间隔为1分钟.

#### 属性 debug

- 当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。

### 子节点 property

- 设置变量
- 用来定义变量值的标签， 有两个属性，name和value；其中name的值是变量的名称，value的值时变量定义的值。通过定义的值会被插入到logger上下文中。定义变量后，可以使“${}”来使用变量。

```xml
<property name="log.level" value="debug" />
```

### 子节点 appender

- appender用来格式化日志输出节点，有俩个属性name和class，class用来指定哪种输出策略，常用就是控制台输出策略和文件输出策略

```xml
<appender name="console" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <pattern>%d -2 %msg%n</pattern>
    </encoder>
</appender>
```

#### 属性 name

- appender 名称

#### 属性 class

- 输出策略
- ch.qos.logback.core.ConsoleAppender 控制台输出策略 / ch.qos.logback.core.rolling.RollingFileAppender 文件输出策略

### 子节点 logger

- 用来设置某一个包或者具体的某一个类的日志打印级别、以及指定 <appender>
-  属性 addtivity : 是否向上级loger传递打印信息。默认是true。 

```xml
<logger name="com.dudu.controller">
    <appender-ref ref="console"/>
</logger>
```

### 子节点 root

- root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性
- 可以包含零个或多个元素，标识这个appender将会添加到这个loger。

```xml
<root level="debug">
  <appender-ref ref="console" />
  <appender-ref ref="file" />
</root>
```

## 常见问题

### root 与 logger

- root 为必选节点, 用来指定最基础的日志输出级别, 无需指定包名(类名)

> 由于不需指定包(类)名, 可输出项目内外的日志, 而 logger 需要知道包(类)名才能输出, 如果想输出项目代码外(srping/mybatis/...)的报错信息而不想细到包(类)名, 可使用root 输出

- logger 为可选节点, 用来设置某个包(类)的输出, 输出以后可向上级(root)传递输出信息, 是否传递取决于 addtivity 属性

#### 例子

```xml
	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">       
            <fileNamePattern>/logs/logs.%d{yyyy-MM-dd}.log</fileNamePattern>            
        </rollingPolicy>        
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <logger name="com.demo.logger" level="debug" additivity="true">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />        
    </logger>

    <root level="debug">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />   
    </root>
```
> 此时运行  com.demo.logger#logger.info("test1"); com.demo.a#logger.info(test2");

##### additivity="true"

- 结果: 输出两次 test1 , 一次 test2
- 运行 com.demo.logger#logger.info("test1"); 时, 找到了 一个logger与其匹配, 控制台输出 test1, 并将输出向上级传递(传递给root), root 又在控制台输出一次 test1
- 运行 com.demo.a#logger.info("test2"); 时, 没有找到了 一个logger与其匹配, root 接收到, root 在控制台输出一次 test2

##### additivity="false"

- 将 additivity="true" 改成 additivity="false"
- 结果: 输出一次 test1 , 一次 test2
- 运行 com.demo.logger#logger.info("test1"); 时, 找到了 一个logger与其匹配, 控制台输出 test1, 并不将输出向上级传递(root), 只输出一次
- 运行 com.demo.a#logger.info("test2"); 时, 没有找到了 一个logger与其匹配, root 接收到, root 在控制台输出一次 test2

### 输出格式化

> %-12(%d{yyyy-MM-dd HH:mm:ss.SSS}) %-5level [%t] %c : %msg%n
> [参考](https://logback.qos.ch/manual/layouts.html)

#### 日期

- %d / %date 

#### 日志级别

- %level / %le / %p

#### 线程 

- %t / %thread

#### 类名

- %c / %logger / %lo

#### 日志内容

- %m / %msg / %message

#### 换行

- %n

#### 对齐 / 宽度等

|     格式      | 是否向左对齐 | 最小宽度 | 最大宽度 | comment |
| :-----------: | :----------: | :------: | :------: | :-----: |
|   %20logger   |    false     |    20    |   none   |         |
|  %-20logger   |     true     |    20    |   none   |         |
|  %.30logger   |      NA      |   none   |    30    |         |
| %20.30logger  |    false     |    20    |    30    |         |
| %-20.30logger |     true     |    20    |    30    |         |
|  %.-30logger  |      NA      |   none   |    30    |         |

#### 控制台文字颜色

##### 常规颜色

- %black / %red / %green / %yellow / %blue / %magenta / %white / %gray / etc...

#### 自动颜色

- %highlight 
- highlight 会给 error 级别的以深红色, wan 红色, info 蓝色, 其他级别默认的颜色

```txt
The %highlight conversion specifier prints its sub-pattern in bold-red for events of level ERROR, in red for WARN, in BLUE for INFO, and in the default color for other levels.
```

> Grouping by parentheses as explained above allows coloring of sub-patterns. As of version 1.0.5, PatternLayout recognizes "%black", "%red", "%green","%yellow","%blue", "%magenta","%cyan", "%white", "%gray", "%boldRed","%boldGreen", "%boldYellow", "%boldBlue", "%boldMagenta""%boldCyan", "%boldWhite" and "%highlight" as conversion words. These conversion words are intended to contain a sub-pattern. Any sub-pattern enclosed by a coloring word will be output in the specified color.
> Below is a configuration file illustrating coloring. Note the %cyan conversion specifier enclosing "%logger{15}". This will output the logger name abbreviated to 15 characters in cyan. The %highlight conversion specifier prints its sub-pattern in bold-red for events of level ERROR, in red for WARN, in BLUE for INFO, and in the default color for other levels.

## 参考

- [https://logback.qos.ch](https://logback.qos.ch/manual/layouts.html)
- [Spring Boot 日志配置(超详细)](https://blog.csdn.net/inke88/article/details/75007649)
- [SpringBoot从入门到进阶——学会Logback日志的配置和搭建 - 比脚更长的路](http://www.yilan.io/article/5bdac48a49205a2c2748e8ce)