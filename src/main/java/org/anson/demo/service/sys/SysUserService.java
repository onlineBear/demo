package org.anson.demo.service.sys;

import org.anson.demo.domain.sys.SysUserDomain;
import org.anson.demo.pojo.po.sys.SysUserPo;
import org.anson.demo.web.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDomain domain;

    public Response getById(Long id){
        return Response.ok(domain.selById(id));
    }

    public Response add(SysUserPo user){

        domain.save(user);

        return Response.ok(user.getId());
    }
}
