package com.olcow.tsso.controller;

import com.auth0.jwt.JWT;
import com.olcow.tsso.dto.KeyValueDTO;
import com.olcow.tsso.dto.ResultDTO;
import com.olcow.tsso.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ResultDTO Login(@RequestParam("username")String username,
                           @RequestParam("password")String password){
        KeyValueDTO<String,String> result = loginService.login(username,password);
        return new ResultDTO(true,result.getKey(),result.getValue());
    }

    @RequestMapping(value = "getToken",method = RequestMethod.GET)
    public String getToken(@RequestParam("token")String token){
        return JWT.decode(token).getClaim("username").asString();
    }
}