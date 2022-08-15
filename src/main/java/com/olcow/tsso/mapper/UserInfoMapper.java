package com.olcow.tsso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olcow.tsso.dto.UserDTO;
import com.olcow.tsso.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zfb
 * @since 2022-08-16
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

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
            "       role.role_desc roleDesc,userPermission.permission,userPermission.permission_desc permissionDesc" +
            " FROM user_info info " +
            "    INNER JOIN user_detail detail ON info.id = #{userId} AND info.id = detail.user_id" +
            "    INNER JOIN user_role userRole ON userRole.user_id = info.id" +
            "    INNER JOIN role ON role.role = userRole.role" +
            "    INNER JOIN role_permission rolePermission ON userRole.role = rolePermission.role " +
            "    INNER JOIN user_permission userPermission ON rolePermission.permission = userPermission.permission")
    UserDTO selectUserDTOById(@Param("userId")Integer userId);
}