package org.anson.demo.javabean.biz.sys.sysUser.vo;

import lombok.Data;
import org.anson.demo.javabean.biz.sys.sysUser.SysUserBo;
import org.anson.demo.javabean.biz.sys.sysUser.SysUser;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SysUserVo {
    private Long id;

    private Date createTime;

    private Date lastUpdateTime;

    private String no;

    private String name;

    private String mobile;

    private static final BeanCopier bo2voCopier = BeanCopier.create(SysUserBo.class, SysUserVo.class, false);

    public static SysUserVo bo2vo(SysUserBo bo){
        if(bo == null){
            return null;
        }

        SysUserVo vo = new SysUserVo();
        bo2voCopier.copy(bo, vo, null);
        return vo;
    }

    public static List<SysUserVo> bo2vo(List<SysUserBo> boList){
        if(boList == null){
            return null;
        }

        List<SysUserVo> voList = new ArrayList<SysUserVo>();

        for(SysUserBo bo : boList){
            voList.add(bo2vo(bo));
        }
        return voList;
    }
}
