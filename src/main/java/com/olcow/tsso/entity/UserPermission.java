package com.olcow.tsso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author zfb
 * @since 2022-08-16
 */
@TableName("user_permission")
public class UserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限
     */
    private Integer permission;

    /**
     * 权限描述
     */
    private String permissionDesc;

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
    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserPermission{" +
            "id=" + id +
            ", permission=" + permission +
            ", permissionDesc=" + permissionDesc +
            ", createDate=" + createDate +
        "}";
    }
}
