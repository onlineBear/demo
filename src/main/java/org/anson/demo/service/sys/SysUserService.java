package org.anson.demo.service.sys;

import org.anson.demo.domain.sys.SysUserDomain;
import org.anson.demo.pojo.po.sys.SysUser;
import org.anson.demo.web.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
    @Autowired
    private SysUserDomain domain;

    public Response getById(Long id){
        return Response.ok(domain.selectByPrimaryKey(id));
    }

    public Response add(SysUser user){

        domain.insert(user);

        return Response.ok(user.getId());
    }
}
