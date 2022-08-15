package com.olcow.tsso.controller;

import com.olcow.tsso.dto.ResultDTO;
import com.olcow.tsso.params.request.LoginReq;
import com.olcow.tsso.params.request.TestTokenReq;
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
     * @param req 用户名密码
     * @return token
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultDTO Login(@RequestBody LoginReq req) {
        return loginService.login(req.getUsername(),req.getPassword());
    }

    /**
     * 验证token 并查询保存用户
     * @param req token
     * @return 验证结果 查询到的用户
     */
    @RequestMapping(value = "testToken",method = RequestMethod.POST)
    public ResultDTO testToken(@RequestBody TestTokenReq req){
        return loginService.testToken(req.getToken());
    }
}