package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class SysRoleMenu implements Serializable {

    /**
     * sys_role_menu.id
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "id")
    private Long id;


    /**
     * sys_role_menu.role_id (角色(权限)ID)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "role_id")
    private Long roleId;


    /**
     * sys_role_menu.sys_menu_id (菜单ID)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "sys_menu_id")
    private Long sysMenuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }
}