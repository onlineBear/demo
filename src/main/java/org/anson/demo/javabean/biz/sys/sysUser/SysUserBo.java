package org.anson.demo.javabean.biz.sys.sysUser;

import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户 bo
 */
@Data
public class SysUserBo {
    private Long id;

    private Date createTime;

    private Date lastUpdateTime;

    private String no;

    private String name;

    private String password;

    private String mobile;

    private static final BeanCopier boCopier = BeanCopier.create(SysUser.class, SysUserBo.class, false);
    //private static final BeanCopier poCopier = BeanCopier.create(SysUserBo.class, SysUser.class, false);

    public static SysUserBo entity2bo(SysUser po) {
        if(po == null){
            return null;
        }

        SysUserBo bo = new SysUserBo();
        boCopier.copy(po, bo, null);

        return bo;
    }

    public static List<SysUserBo> entity2bo(List<SysUser> poList) {
        if(poList == null || poList.size() <= 0){
            return null;
        }

        List<SysUserBo> boList = new ArrayList<>();
        for(SysUser po: poList){
            boList.add(SysUserBo.entity2bo(po));
        }

        return boList;
    }
}
