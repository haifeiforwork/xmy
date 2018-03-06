package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class TermData implements Serializable {

    /**
     * term_data.id
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "id")
    private Long id;


    /**
     * term_data.parent_id (父级id)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "parent_id")
    private Long parentId;


    /**
     * term_data.name (名称)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "name")
    private String name;


    /**
     * term_data.vid (词汇的Id)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "vid")
    private Long vid;


    /**
     * term_data.sn (编码)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "sn")
    private String sn;


    /**
     * term_data.status (状态:0:启用,1:禁用)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "status")
    private Integer status;


    /**
     * term_data.weight (权重)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "weight")
    private Integer weight;


    /**
     * term_data.icon (分类图标)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "icon")
    private String icon;


    /**
     * term_data.is_show (是否首页显示 0：是 1：否)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "is_show")
    private Integer isShow;


    /**
     * term_data.url (地址)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "url")
    private String url;


    /**
     * term_data.app_icon (移动端的分类图标)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "app_icon")
    private String appIcon;


    /**
     * term_data.app_on_icon (app一级分类 选中的图标)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "app_on_icon")
    private String appOnIcon;


    /**
     * term_data.pc_backgroud_img (pc一级分类背景图)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "pc_backgroud_img")
    private String pcBackgroudImg;


    /**
     * term_data.background_img_url (背景图跳转链接)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "background_img_url")
    private String backgroundImgUrl;


    /**
     * term_data.description_img (分类描述图)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "description_img")
    private String descriptionImg;


    /**
     * term_data.des (描述)
     * @ibatorgenerated 2017-12-14 14:28:17
     */
    @Column(name = "des")
    private String des;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVid() {
        return vid;
    }

    public void setVid(Long vid) {
        this.vid = vid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppOnIcon() {
        return appOnIcon;
    }

    public void setAppOnIcon(String appOnIcon) {
        this.appOnIcon = appOnIcon;
    }

    public String getPcBackgroudImg() {
        return pcBackgroudImg;
    }

    public void setPcBackgroudImg(String pcBackgroudImg) {
        this.pcBackgroudImg = pcBackgroudImg;
    }

    public String getBackgroundImgUrl() {
        return backgroundImgUrl;
    }

    public void setBackgroundImgUrl(String backgroundImgUrl) {
        this.backgroundImgUrl = backgroundImgUrl;
    }

    public String getDescriptionImg() {
        return descriptionImg;
    }

    public void setDescriptionImg(String descriptionImg) {
        this.descriptionImg = descriptionImg;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}