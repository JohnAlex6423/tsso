package com.olcow.tsso.controller;

import com.olcow.tsso.dto.KeyValueDTO;
import com.olcow.tsso.dto.ResultDTO;
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
    public ResultDTO register(@RequestParam("username")String username,
                              @RequestParam("password")String password,
                              @RequestParam("email")String email){
        KeyValueDTO<String,String> result = userService.register(username,password,email);
        return new ResultDTO(true,result.getKey(),result.getValue());
    }
}
