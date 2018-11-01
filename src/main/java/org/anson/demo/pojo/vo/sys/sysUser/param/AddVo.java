package org.anson.demo.pojo.vo.sys.sysUser.param;

import lombok.Data;
import org.anson.demo.pojo.po.sys.SysUserPo;
import org.springframework.cglib.beans.BeanCopier;

@Data
public class AddVo {
    private String no;
    private String name;
    private String mobile;

    private static final BeanCopier vo2poCopier = BeanCopier.create(AddVo.class, SysUserPo.class, false);

    public static SysUserPo vo2po(AddVo vo){
        if(vo == null){
            return null;
        }

        SysUserPo po = new SysUserPo();
        vo2poCopier.copy(vo, po, null);
        return po;
    }
}
