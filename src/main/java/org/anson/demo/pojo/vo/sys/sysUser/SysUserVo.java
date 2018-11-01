package org.anson.demo.pojo.vo.sys.sysUser;

import lombok.Data;
import org.anson.demo.pojo.bo.sys.SysUserBo;
import org.anson.demo.pojo.po.sys.SysUserPo;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;

@Data
public class SysUserVo {
    private Long id;

    private Date lastUpdateTime;

    private String no;

    private String name;

    private String mobile;

    private static final BeanCopier bo2voCopier = BeanCopier.create(SysUserBo.class, SysUserVo.class, false);
    private static final BeanCopier vo2poCopier = BeanCopier.create(SysUserVo.class, SysUserPo.class, false);

    public static SysUserVo bo2vo(SysUserBo bo){
        if(bo == null){
            return null;
        }

        SysUserVo vo = new SysUserVo();
        bo2voCopier.copy(bo, vo, null);
        return vo;
    }

    public static SysUserPo vo2po(SysUserVo vo){
        if(vo == null){
            return null;
        }

        SysUserPo po = new SysUserPo();
        vo2poCopier.copy(vo, po, null);
        return po;
    }
}
