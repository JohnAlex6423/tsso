package com.olcow.tsso.service;

import com.olcow.tsso.dao.UserDetailMapper;
import com.olcow.tsso.dao.UserInfoMapper;
import com.olcow.tsso.dao.UserRoleMapper;
import com.olcow.tsso.model.UserDetail;
import com.olcow.tsso.model.UserInfo;
import com.olcow.tsso.model.UserRole;
import com.olcow.tsso.until.EncryptUntil;
import com.olcow.tsso.until.RegularUntil;
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

    /**
     * 注册
     * @param userName 用户名
     * @param password 密码
     * @param email 邮箱
     * @return 注册结果
     */
    public String register(String userName,String password,String email){
        if (!regularUntil.usernameTest(userName)){
            return "01001";
        }
        if(!regularUntil.passwordTest(password)){
            return "01002";
        }
        try {
            Integer usernameIsExistUserId = userInfoMapper.selectUserIdByUsername(userName);
            if (usernameIsExistUserId != null){
                return "01004";
            }
            Integer emailIsExistUserId = userInfoMapper.selectUserIdByEmail(email);
            if (emailIsExistUserId != null){
                return "01005";
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

                return "00001";
            }else {
                return "01003";
            }
        } catch (Exception e){
            return "00002";
        }
    }
}