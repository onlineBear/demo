package org.anson.demo.web.controller.sysUser;

import org.anson.demo.pojo.po.sys.SysUser;
import org.anson.demo.service.sys.SysUserService;
import org.anson.demo.web.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService service;

    @GetMapping("/{id}")
    public Response get(@PathVariable("id")Long id){
        return service.getById(id);
    }

    @PostMapping("/add")
    public Response add(SysUser user){
        return service.add(user);
    }
}
