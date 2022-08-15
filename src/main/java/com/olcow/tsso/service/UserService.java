package com.olcow.tsso.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.olcow.tsso.dto.ResultDTO;
import com.olcow.tsso.mapper.UserDetailMapper;
import com.olcow.tsso.mapper.UserInfoMapper;
import com.olcow.tsso.mapper.UserRoleMapper;
import com.olcow.tsso.entity.UserDetail;
import com.olcow.tsso.entity.UserInfo;
import com.olcow.tsso.entity.UserRole;
import com.olcow.tsso.utils.EncryptUtils;
import com.olcow.tsso.utils.RegularUtils;
import com.olcow.tsso.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

import static com.olcow.tsso.constant.Constant.ENCRYPT_SHA256;
import static com.olcow.tsso.constant.Constant.USER_ROLE_NORMAL;
import static com.olcow.tsso.constant.ReturnCode.*;
import static java.lang.System.currentTimeMillis;

@Service
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private EncryptUtils encryptUntil;

    @Resource
    private RegularUtils regularUntil;

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
    public ResultDTO register(String userName, String password, String email){
        if (!regularUntil.usernameTest(userName)){
            return new ResultDTO(CODE01001,null);
        }
        if(!regularUntil.passwordTest(password)){
            return new ResultDTO(CODE01002,null);
        }
        if(StringUtils.isBlank(publicKey)){
            return new ResultDTO(CODE99999,null);
        }
        try {
            Integer usernameIsExistUserId = userInfoMapper.selectUserIdByUsername(userName);
            if (usernameIsExistUserId != null){
                return new ResultDTO(CODE01004,null);
            }
            Integer emailIsExistUserId = userInfoMapper.selectUserIdByEmail(email);
            if (emailIsExistUserId != null){
                return new ResultDTO(CODE01005,null);
            }
            String salt = String.valueOf(currentTimeMillis());
            String result = encryptUntil.Encryption(ENCRYPT_SHA256,password+salt);
            if (!result.equals("error")){
                LocalDate date = LocalDate.now();
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
                return new ResultDTO(SUCCESS,token);
            }else {
                return new ResultDTO(CODE01003,null);
            }
        } catch (Exception e){
            return new ResultDTO(FAIL,null);
        }
    }
}