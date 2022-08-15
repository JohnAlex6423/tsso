package com.olcow.tsso.controller;

import com.olcow.tsso.dto.ResultDTO;
import com.olcow.tsso.params.request.RegisterReq;
import com.olcow.tsso.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResultDTO register(@RequestBody RegisterReq req){
        return userService.register(req.getUsername(),req.getPassword(),req.getEmail());
    }
}
