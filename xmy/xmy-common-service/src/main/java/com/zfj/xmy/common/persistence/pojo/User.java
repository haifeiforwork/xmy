package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class User implements Serializable {

    private Long id;


    private String name;


    private String password;


    private Date createTime;


    private Integer status;


    private Integer level;


    private String appToken;


    private Date appTokenExpire;


    private String qqOpenid;


    private String wechatOpenid;


    private Date lastLoginTime;


    private String openimUserid;


    private String scancode;


    private String wechatUnionid;


    private String qqUnionid;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public Date getAppTokenExpire() {
        return appTokenExpire;
    }

    public void setAppTokenExpire(Date appTokenExpire) {
        this.appTokenExpire = appTokenExpire;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getOpenimUserid() {
        return openimUserid;
    }

    public void setOpenimUserid(String openimUserid) {
        this.openimUserid = openimUserid;
    }

    public String getScancode() {
        return scancode;
    }

    public void setScancode(String scancode) {
        this.scancode = scancode;
    }

    public String getWechatUnionid() {
        return wechatUnionid;
    }

    public void setWechatUnionid(String wechatUnionid) {
        this.wechatUnionid = wechatUnionid;
    }

    public String getQqUnionid() {
        return qqUnionid;
    }

    public void setQqUnionid(String qqUnionid) {
        this.qqUnionid = qqUnionid;
    }
}