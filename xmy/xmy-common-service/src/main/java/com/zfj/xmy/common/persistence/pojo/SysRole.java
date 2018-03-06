package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class SysRole implements Serializable {

    /**
     * sys_role.id
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "id")
    private Long id;


    /**
     * sys_role.name (角色名)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "name")
    private String name;


    /**
     * sys_role.mark (权限标识)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "mark")
    private String mark;


    /**
     * sys_role.des (角色描述)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "des")
    private String des;


    /**
     * sys_role.status (状态，0：启用，1：禁用)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "status")
    private Integer status;


    /**
     * sys_role.seq (显示顺序)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "seq")
    private Integer seq;


    /**
     * sys_role.creator (创建者)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "creator")
    private String creator;


    /**
     * sys_role.updator (最后更新者)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "updator")
    private String updator;


    /**
     * sys_role.create_time (创建时间)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * sys_role.update_time (最后更新时间)
     * @ibatorgenerated 2017-06-08 15:27:18
     */
    @Column(name = "update_time")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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