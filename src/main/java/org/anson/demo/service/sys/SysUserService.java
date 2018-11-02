package org.anson.demo.service.sys;

import org.anson.demo.domain.sys.SysUserDomain;
import org.anson.demo.javabean.biz.sys.sysUser.SysUser;
import org.anson.demo.javabean.biz.sys.sysUser.SysUserBo;
import org.anson.demo.javabean.biz.sys.sysUser.dto.AddDto;
import org.anson.demo.javabean.biz.sys.sysUser.dto.GetDto;
import org.anson.demo.javabean.biz.sys.sysUser.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户service
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDomain domain;

    public SysUserVo getByNo(String no){
        SysUserBo bo = domain.selByNo(no);
        SysUserVo vo = SysUserVo.bo2vo(bo);
        return vo;
    }

    public List<SysUserVo> get(GetDto dto){
        List<SysUserBo> userList = domain.sel(dto);
        return SysUserVo.bo2vo(userList);
    }

    public SysUserVo add(AddDto userVo){
        // 参数检查

        SysUser userPo = AddDto.vo2po(userVo);
        Long id = domain.save(userPo);

        SysUserBo bo = domain.selById(id);
        SysUserVo vo = SysUserVo.bo2vo(bo);

        return vo;
    }
}
