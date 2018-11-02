package org.anson.demo.javabean.biz.sys.sysUser.dto;

import lombok.Data;
import org.anson.demo.javabean.biz.sys.sysUser.SysUserBo;
import org.anson.demo.javabean.biz.sys.sysUser.SysUser;
import org.anson.demo.javabean.biz.sys.sysUser.vo.SysUserVo;
import org.springframework.cglib.beans.BeanCopier;

@Data
public class AddDto {
    private String no;
    private String name;
    private String mobile;

    private static final BeanCopier dto2entityCopier = BeanCopier.create(AddDto.class, SysUser.class, false);
    private static final BeanCopier bo2voCopier = BeanCopier.create(SysUserBo.class, SysUserVo.class, false);

    public static SysUser vo2po(AddDto dto){
        if(dto == null){
            return null;
        }

        SysUser entity = new SysUser();
        dto2entityCopier.copy(dto, entity, null);
        return entity;
    }

    public static SysUserVo bo2vo(SysUserBo bo){
        if(bo == null){
            return null;
        }

        SysUserVo vo = new SysUserVo();
        bo2voCopier.copy(bo, vo, null);
        return vo;
    }
}
