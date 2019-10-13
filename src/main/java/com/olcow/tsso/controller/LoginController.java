package com.olcow.tsso.controller;

import com.auth0.jwt.JWT;
import com.olcow.tsso.dto.KeyValueDTO;
import com.olcow.tsso.dto.ResultDTO;
import com.olcow.tsso.dto.UserDTO;
import com.olcow.tsso.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultDTO Login(@RequestParam("username")String username,
                           @RequestParam("password")String password){
        KeyValueDTO<String,String> result = loginService.login(username,password);
        return new ResultDTO(true,result.getKey(),result.getValue());
    }

    /**
     * 验证token 并查询保存用户
     * @param token token
     * @return 验证结果 查询到的用户
     */
    @RequestMapping(value = "testToken",method = RequestMethod.POST)
    public ResultDTO testToken(@RequestParam("token")String token){
        KeyValueDTO<String, UserDTO> result = loginService.testToken(token);
        return new ResultDTO(true,result.getKey(),result.getValue());
    }
}