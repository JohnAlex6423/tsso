package com.olcow.tsso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 角色描述
     */
    private String roleDesc;

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
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Role{" +
            "id=" + id +
            ", role=" + role +
            ", roleDesc=" + roleDesc +
            ", createDate=" + createDate +
        "}";
    }
}
