package com.olcow.tsso.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.olcow.tsso.dao.UserDetailMapper;
import com.olcow.tsso.dao.UserInfoMapper;
import com.olcow.tsso.dao.UserRoleMapper;
import com.olcow.tsso.dto.KeyValueDTO;
import com.olcow.tsso.model.UserDetail;
import com.olcow.tsso.model.UserInfo;
import com.olcow.tsso.model.UserRole;
import com.olcow.tsso.until.EncryptUntil;
import com.olcow.tsso.until.RegularUntil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.olcow.tsso.Constant.ENCRYPT_SHA256;
import static com.olcow.tsso.Constant.USER_ROLE_NORMAL;
import static java.lang.System.currentTimeMillis;

@Service
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private EncryptUntil encryptUntil;

    @Resource
    private RegularUntil regularUntil;

    @Resource
    private UserDetailMapper userDetailMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Value("${PublicKey}")
    private String publicKey;

    /**
     * 注册
     * @param userName 用户名
     * @param password 密码
     * @param email 邮箱
     * @return 注册结果
     */
    public KeyValueDTO<String,String> register(String userName, String password, String email){
        if (!regularUntil.usernameTest(userName)){
            return new KeyValueDTO<>("01001",null);
        }
        if(!regularUntil.passwordTest(password)){
            return new KeyValueDTO<>("01002",null);
        }
        try {
            Integer usernameIsExistUserId = userInfoMapper.selectUserIdByUsername(userName);
            if (usernameIsExistUserId != null){
                return new KeyValueDTO<>("01004",null);
            }
            Integer emailIsExistUserId = userInfoMapper.selectUserIdByEmail(email);
            if (emailIsExistUserId != null){
                return new KeyValueDTO<>("01005",null);
            }
            String salt = String.valueOf(currentTimeMillis());
            String result = encryptUntil.Encryption(ENCRYPT_SHA256,password+salt);
            if (!result.equals("error")){
                Date date = new Date();
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(userName);
                userInfo.setSalt(salt);
                userInfo.setPassword(result);
                userInfo.setCreateDate(date);
                userInfo.setEmail(email);
                userInfoMapper.insert(userInfo);
                UserDetail userDetail = new UserDetail();
                userDetail.setAvatar("");
                userDetail.setNikeName("用户"+userInfo.getId());
                userDetail.setUserId(userInfo.getId());
                userDetailMapper.insert(userDetail);
                UserRole userRole = new UserRole();
                userRole.setRole(USER_ROLE_NORMAL);
                userRole.setUserId(userInfo.getId());
                userRole.setCreateDate(date);
                userRoleMapper.insert(userRole);
                String token = JWT.create()
                        .withClaim("userId",userInfo.getId())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 2592000000L))
                        .sign(Algorithm.HMAC256(publicKey));
                return new KeyValueDTO<>("00001",token);
            }else {
                return new KeyValueDTO<>("01003",null);
            }
        } catch (Exception e){
            return new KeyValueDTO<>("00002",null);
        }
    }
}