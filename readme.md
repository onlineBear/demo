[TOC]

# javabean

> [参考](https://blog.csdn.net/zjrbiancheng/article/details/6253232)

## entity

- 实体对象, 和表对应

## bo

- bussiness object
- 商业对象, 和业务对应

## dto

- data transfer object
- 数据传输对象

## vo

- view object
- 视图对象, 和视图对应

> entity / bo / dto / vo 很容易混淆

> eg: 拿订单举个例子

> po: 订单Entity Order

```java
public class Order{
    private Long id;      // 订单id
    private Long userId;    // 下单用户id
    // ...
}

public class User{
    private Long id;    
    private String name;    
    // ...
}

public class OrderDtl{
    private Long orderId;       // 订单id          
    private Long goodsId;       // 商品id
    private Double orderQty;     // 订单数量
    private Double orderPrice;     // 订货价格
    private Double acptQty;      // 收货数量
    private Double acptPrice;   // 收货价格
    // ...
}
```

> bo: 订单bo OrderBo

```java
public class OrderBo{
    private Long id;
    private UserBo user;
    private List<OrderDtlBo> orderDtlList; 
    // ...
}

public class UserBo{
    private Long id;
    private String name;
    // ...
}

public class OrderDtlBo{
    private Long goodsId;
    private Double orderQty;     // 订单数量
    private Double orderPrice;     // 订货价格
    private Double acptQty;      // 收货数量
    private Double acptPrice;   // 收货价格
    // ...
}
```

> controller 层接收 接口的参数时, 参数一般和 bo 也会有差异, 比如这里提交订货单, 并不需要 bo 里面的收货相关的属性, 故需要另外一个对象来对应

```java
public class OrderDto{
    private Long userId;
    private List<OrderDto> orderDtlList; 
    // ...
}

public class OrderDto{
    private Long goodsId;
    private Double orderQty;     // 订单数量
    private Double orderPrice;     // 订货价格
}
```

> controller 层返回数据时, 和 bo 也是会有差异

```java
public class OrderVo{
    private Long id;    
    private List<OrderDtlVo> orderDtlList; 
    // ...
}

public class OrderDtlVo{
    private Long goodsId;
    private Double orderQty;     // 订单数量
    private Double orderPrice;     // 订货价格
    private Double acptQty;      // 收货数量
    private Double acptPrice;   // 收货价格
    // ...
}
```

# 分层

分 controller / service / domain / dao 层

## controller

- 只做转发请求, 返回数据
- 接收接口的 dto, 向 service 传递 dto, 接收 service 返回的vo 直接返回给接口

## service

- 做业务处理, 通过 组装 domain 层的方法来完成业务
- service 不直接操作数据库, 通过 domain 来操作数据库
- 接收来自 controller 的 dto, 返回 controller 需要的 vo
- 需要将 vo 转换成 bo, bo 转换成 vo

> service 不调用 dao 层 的方法

## domain

- 领域层, 直接操作数据库, 为封装的业务操作, 供 service 调用
- 接收来自 service 的 bo, 返回 service 需要的 bo
- 需要将 bo 转换成 entity, vo 转换成 bo

## dao

- 即 mapper
- 数据库操作: 单表的数据库操作(单表 CRUD) / 多表查询(主要是报表)

> controller 层只做请求转发及返回数据, 所有业务有关的检查/操作等一律放入 service 层来做

> domain 做领域操作(即专业化的操作), 核心的流程都在这里 

> service 做业务操作时, 不关系业务细节(比如新增订单, 需要操作哪些表, 不需要关心, domain 方法已封装好), 只通过组装 domain 的领域方法来完成

> 做复杂报表时, 不需按照 dto -> bo -> entity -> bo 的流程来, 直接在 domain 层 返回 vo

## 分层图

![分层图](http://owz8v0b5i.bkt.clouddn.com/%E6%97%A0%E6%A0%87%E9%A2%98.png)
