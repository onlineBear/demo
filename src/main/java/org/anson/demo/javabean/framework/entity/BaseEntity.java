package org.anson.demo.javabean.framework.entity;

import lombok.Data;

import java.util.Date;

/**
 * entity 通用属性
 */
@Data
public class BaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 主键列 - id
     */
    public static final String ID = "id";

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间列 - createTime
     */
    public static final String CREATETIME = "createTime";

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    /**
     * 最后更新时间列 - lastUpdateTime
     */
    public static final String LASTUPDATETIME = "lastUpdateTime";
}
