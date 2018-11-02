package org.anson.demo.javabean.biz.sys.sysUser.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetDto {
    private Date beginCreateTime;

    private Date endCreateTime;

    private Date lastUpdateTime;

    private String no;

    private String name;

    private String mobile;

    private String orderByClause;
}
