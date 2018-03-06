package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class SysMenu implements Serializable {

    /**
     * sys_menu.id
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "id")
    private Long id;


    /**
     * sys_menu.parent_id (父级ID)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "parent_id")
    private Long parentId;


    /**
     * sys_menu.sn (编号)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "sn")
    private String sn;


    /**
     * sys_menu.name (菜单名称)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "name")
    private String name;


    /**
     * sys_menu.des (菜单描述)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "des")
    private String des;


    /**
     * sys_menu.target_url (菜单URL链接，支持Spring AntPathMatcher表达式)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "target_url")
    private String targetUrl;


    /**
     * sys_menu.icon_url (菜单图标路径)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "icon_url")
    private String iconUrl;


    /**
     * sys_menu.seq (显示顺序)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "seq")
    private Integer seq;


    /**
     * sys_menu.type (类型，0：菜单，1：按钮)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "type")
    private Integer type;


    /**
     * sys_menu.status (状态，0：启用，1：禁用)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "status")
    private Integer status;


    /**
     * sys_menu.creator (创建者)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "creator")
    private String creator;


    /**
     * sys_menu.updator (最后修改者)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "updator")
    private String updator;


    /**
     * sys_menu.create_time (创建时间)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * sys_menu.update_time (最后更新时间)
     * @ibatorgenerated 2017-06-08 15:27:17
     */
    @Column(name = "update_time")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}