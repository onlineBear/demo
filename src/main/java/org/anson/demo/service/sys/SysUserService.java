package org.anson.demo.service.sys;

import org.anson.demo.domain.sys.SysUserDomain;
import org.anson.demo.pojo.bo.sys.SysUserBo;
import org.anson.demo.pojo.po.sys.SysUserPo;
import org.anson.demo.pojo.vo.sys.sysUser.param.AddVo;
import org.anson.demo.pojo.vo.sys.sysUser.SysUserVo;
import org.anson.demo.web.common.response.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDomain domain;

    public JsonResponse<SysUserVo> getById(Long id){
        SysUserBo bo = domain.selById(id);
        SysUserVo vo = SysUserVo.bo2vo(bo);
        return JsonResponse.ok(vo);
    }

    public JsonResponse<SysUserVo> add(AddVo userVo){
        SysUserPo userPo = AddVo.vo2po(userVo);

        // 参数检查
        return JsonResponse.ok(domain.save(userPo));
    }
}
