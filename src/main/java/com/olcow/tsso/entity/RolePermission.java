package com.olcow.tsso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author zfb
 * @since 2022-08-16
 */
@TableName("role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色id
     */
    private Integer role;

    /**
     * 权限id
     */
    private Integer permission;

    /**
     * 创建时间
     */
    private LocalDate createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
            "id=" + id +
            ", role=" + role +
            ", permission=" + permission +
            ", createDate=" + createDate +
        "}";
    }
}
