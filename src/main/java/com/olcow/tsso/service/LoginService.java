package com.olcow.tsso.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.olcow.tsso.dao.UserInfoMapper;
import com.olcow.tsso.dto.KeyValueDTO;
import com.olcow.tsso.model.UserInfo;
import com.olcow.tsso.until.EncryptUntil;
import com.olcow.tsso.until.RegularUntil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.olcow.tsso.Constant.ENCRYPT_SHA256;

@Service
public class LoginService {

    @Resource
    private RegularUntil regularUntil;

    @Resource
    private EncryptUntil encryptUntil;

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 登录
     * @param username 用户名或邮箱
     * @param password 密码
     * @return token
     */
    public KeyValueDTO<String,String> login(String username, String password){
        UserInfo user;
        if (regularUntil.emailTest(username)){
            user = userInfoMapper.selectUserIdAndPasswordByEmail(username);
        } else {
            user = userInfoMapper.selectUserIdAndPasswordByUsername(username);
        }
        if (user == null) {
            return new KeyValueDTO<>("02001",null);
        }
        String encryptPassword = encryptUntil.Encryption(ENCRYPT_SHA256,password+user.getSalt());
        if (encryptPassword.equals("error")){
            return new KeyValueDTO<>("01003",null);
        }
        if (!user.getPassword().equals(encryptPassword)){
            return new KeyValueDTO<>("02001",null);
        }
        String token = JWT.create()
                .withClaim("userId",username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 2592000000L))
                .sign(Algorithm.HMAC256(password));
        return new KeyValueDTO<>("00001",token);
    }
}
