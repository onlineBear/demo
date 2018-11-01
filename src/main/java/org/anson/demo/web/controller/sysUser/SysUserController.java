package org.anson.demo.web.controller.sysUser;

import org.anson.demo.pojo.vo.sys.sysUser.param.AddVo;
import org.anson.demo.pojo.vo.sys.sysUser.SysUserVo;
import org.anson.demo.service.sys.SysUserService;
import org.anson.demo.web.common.response.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService service;

    @GetMapping("/{id}")
    public JsonResponse<SysUserVo> get(@PathVariable("id")Long id){
        return service.getById(id);
    }

    @PostMapping("/add")
    public JsonResponse<SysUserVo> add(@RequestBody AddVo user){
        return service.add(user);
    }
}
