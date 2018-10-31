package org.anson.demo.service.sys;

import org.anson.demo.mapper.sys.SysUserMapper;
import org.anson.demo.po.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper mapper;

    public SysUser getById(Long id){
        return mapper.selectByPrimaryKey(id);
    }
}
