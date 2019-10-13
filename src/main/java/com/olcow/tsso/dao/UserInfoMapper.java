package com.olcow.tsso.dao;

import com.olcow.tsso.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserInfoMapper extends Mapper<UserInfo> {

    /**
     * 通过用户名查询该账号的id
     * @param username username
     * @return 账号Id
     */
    @Select("SELECT id FROM user_info WHERE username = #{username}")
    Integer selectUserIdByUsername(@Param("username") String username);

    /**
     * 通过用户名查询该账号的id和密码
     * @param username username
     * @return 账号Id
     */
    @Select("SELECT id,password,salt FROM user_info WHERE username = #{username}")
    UserInfo selectUserIdAndPasswordByUsername(@Param("username") String username);

    /**
     * 通过邮箱查询该账号的id
     * @param email email
     * @return 账号Id
     */
    @Select("SELECT id FROM user_info WHERE email = #{email}")
    Integer selectUserIdByEmail(@Param("email")String email);

    /**
     * 通过邮箱查询该账号的id和密码
     * @param email email
     * @return 账号Id
     */
    @Select("SELECT id,password,salt FROM user_info WHERE email = #{email}")
    UserInfo selectUserIdAndPasswordByEmail(@Param("email")String email);
}