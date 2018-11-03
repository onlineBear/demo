package org.anson.demo.web.controller.sysUser;

import org.anson.demo.javabean.biz.sys.sysUser.dto.AddDto;
import org.anson.demo.javabean.biz.sys.sysUser.dto.GetByNoDto;
import org.anson.demo.javabean.biz.sys.sysUser.dto.GetDto;
import org.anson.demo.javabean.biz.sys.sysUser.vo.SysUserVo;
import org.anson.demo.service.sys.SysUserService;
import org.anson.demo.web.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService service;

    @PostMapping("/getByNo")
    public Response<SysUserVo> getByNo(@RequestBody GetByNoDto dto){
        return Response.ok(service.getByNo(dto.getNo()));
    }

    @PostMapping("/get")
    public Response<List<SysUserVo>> get(@RequestBody GetDto dto){
        return Response.ok(service.get(dto));
    }

    @PostMapping("/add")
    public Response<SysUserVo> add(@RequestBody AddDto dto){
        return Response.ok(service.add(dto));
    }
}
