package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class ClientVersion implements Serializable {

    private Long id;


    private String lastVerCode;


    private String lastVerName;


    private String lowestVerCode;


    private Date addtime;


    private String androidUrl;


    private String iosUrl;


    private String iosLastVerCode;


    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastVerCode() {
        return lastVerCode;
    }

    public void setLastVerCode(String lastVerCode) {
        this.lastVerCode = lastVerCode;
    }

    public String getLastVerName() {
        return lastVerName;
    }

    public void setLastVerName(String lastVerName) {
        this.lastVerName = lastVerName;
    }

    public String getLowestVerCode() {
        return lowestVerCode;
    }

    public void setLowestVerCode(String lowestVerCode) {
        this.lowestVerCode = lowestVerCode;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }

    public String getIosLastVerCode() {
        return iosLastVerCode;
    }

    public void setIosLastVerCode(String iosLastVerCode) {
        this.iosLastVerCode = iosLastVerCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}