package com.olcow.tsso.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.olcow.tsso.dto.ResultDTO;
import com.olcow.tsso.mapper.UserInfoMapper;
import com.olcow.tsso.dto.KeyValueDTO;
import com.olcow.tsso.dto.UserDTO;
import com.olcow.tsso.entity.UserInfo;
import com.olcow.tsso.utils.EncryptUtils;
import com.olcow.tsso.utils.JWTUtils;
import com.olcow.tsso.utils.RegularUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.olcow.tsso.constant.Constant.*;
import static com.olcow.tsso.constant.ReturnCode.*;

@Service
public class LoginService {

    @Resource
    private RegularUtils regularUntil;

    @Resource
    private EncryptUtils encryptUntil;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private JWTUtils jwtUntil;

    @Value("${PublicKey}")
    private String publicKey;

    /**
     * 登录
     * @param username 用户名或邮箱
     * @param password 密码
     * @return token
     */
    public ResultDTO login(String username, String password){
        UserInfo user;
        if (regularUntil.emailTest(username)){
            user = userInfoMapper.selectUserIdAndPasswordByEmail(username);
        } else {
            user = userInfoMapper.selectUserIdAndPasswordByUsername(username);
        }
        if (user == null) {
            return new ResultDTO(CODE02001,null);
        }
        String encryptPassword = encryptUntil.Encryption(ENCRYPT_SHA256,password+user.getSalt());
        if (encryptPassword.equals("error")){
            return new ResultDTO(CODE01003,null);
        }
        if (!user.getPassword().equals(encryptPassword)){
            return new ResultDTO(CODE02001,null);
        }
        String token = JWT.create()
                .withClaim("userId",user.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 2592000000L))
                .sign(Algorithm.HMAC256(publicKey));
        return new ResultDTO(SUCCESS,token);
    }

    /**
     * 验证token 并查询保存用户
     * @param token token
     * @return 验证结果 查询到的用户
     */
    public ResultDTO testToken(String token){
        KeyValueDTO<Integer,Integer> verResult = jwtUntil.verifier(token);
        if (verResult.getKey().equals(VERIFIER_ERROR)) {
            return new ResultDTO(CODE02002,null);
        } else if (verResult.getKey().equals(VERIFIER_EXPIRED)){
            return new ResultDTO(CODE02003,null);
        } else if (verResult.getKey().equals(VERIFIER_SUCCESS)){
            if (verResult.getValue() != null && !verResult.getValue().equals(0)){
                UserDTO user = userInfoMapper.selectUserDTOById(verResult.getValue());
                return new ResultDTO(SUCCESS,user);
            } else {
                return new ResultDTO(FAIL,null);
            }
        } else {
            return new ResultDTO(FAIL,null);
        }
    }
}
