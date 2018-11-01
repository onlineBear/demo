package org.anson.demo.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * po 通用属性
 */
@Data
public class BasePo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 主键列 - id
     */
    public static final String ID = "id";

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    /**
     * 最后更新时间列 - lastUpdateTime
     */
    public static final String LASTUPDATETIME = "lastUpdateTime";
}
