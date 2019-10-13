package com.olcow.tsso.dao;

import com.olcow.tsso.dto.UserDTO;
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

    /**
     * 通过id查询user信息
     * @param userId userId
     * @return user信息
     */
    @Select(" SELECT info.username,info.id userId,detail.nike_name nikeName,detail.avatar,role.role," +
            "       role.role_desc roleDesc,permission.permission,permission.permission_desc permissionDesc" +
            " FROM user_info info " +
            "    INNER JOIN user_detail detail ON info.id = #{userId} AND info.id = detail.user_id" +
            "    INNER JOIN user_role userRole ON userRole.user_id = info.id" +
            "    INNER JOIN role ON role.role = userRole.role" +
            "    INNER JOIN role_permission rolePermission ON userRole.role = rolePermission.role " +
            "    INNER JOIN permission ON rolePermission.permission = permission.permission")
    UserDTO selectUserDTOById(@Param("userId")Integer userId);
}