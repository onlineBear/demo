package org.anson.demo.web.controller.sysUser;

import org.anson.demo.javabean.biz.sys.sysUser.dto.AddDto;
import org.anson.demo.javabean.biz.sys.sysUser.dto.GetByNoDto;
import org.anson.demo.javabean.biz.sys.sysUser.dto.GetDto;
import org.anson.demo.service.sys.SysUserService;
import org.anson.demo.web.common.response.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService service;

    @PostMapping("/getByNo")
    public JsonResponse getByNo(@RequestBody GetByNoDto dto){
        return JsonResponse.ok(service.getByNo(dto.getNo()));
    }

    @PostMapping("/get")
    public JsonResponse get(@RequestBody GetDto dto){
        return JsonResponse.ok(service.get(dto));
    }

    @PostMapping("/add")
    public JsonResponse add(@RequestBody AddDto dto){
        return JsonResponse.ok(service.add(dto));
    }
}
