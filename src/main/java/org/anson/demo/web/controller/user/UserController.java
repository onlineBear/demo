package org.anson.demo.web.controller.user;

import org.anson.demo.web.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public Response getUser(@PathVariable("id")String id){
        return Response.ok(id);
    }
}
