package org.anson.demo.web.controller.sysUser;

import org.anson.demo.service.sys.SysUserService;
import org.anson.demo.web.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService service;

    @GetMapping("/{id}")
    public Response getUser(@PathVariable("id")Long id){
        return Response.ok(service.getById(id));
    }
}
