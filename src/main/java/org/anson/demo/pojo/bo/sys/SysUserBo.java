package org.anson.demo.pojo.bo.sys;

import lombok.Data;
import org.anson.demo.pojo.po.sys.SysUserPo;
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

    private Date lastUpdateTime;

    private String no;

    private String name;

    private String password;

    private String mobile;

    private static final BeanCopier boCopier = BeanCopier.create(SysUserPo.class, SysUserBo.class, false);
    //private static final BeanCopier poCopier = BeanCopier.create(SysUserBo.class, SysUserPo.class, false);

    public static SysUserBo po2bo(SysUserPo po) {
        if(po == null){
            return null;
        }

        SysUserBo bo = new SysUserBo();
        boCopier.copy(po, bo, null);

        return bo;
    }

    public static List<SysUserBo> po2bo(List<SysUserPo> poList) {
        if(poList == null || poList.size() <= 0){
            return null;
        }

        List<SysUserBo> boList = new ArrayList<>();
        for(SysUserPo po: poList){
            boList.add(SysUserBo.po2bo(po));
        }

        return boList;
    }
}
