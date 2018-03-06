package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class PointsActivity implements Serializable {

    /**
     * points_activity.id
     * @ibatorgenerated 2017-07-19 15:59:23
     */
    @Column(name = "id")
    private Long id;


    /**
     * points_activity.name (促销名称)
     * @ibatorgenerated 2017-07-19 15:59:23
     */
    @Column(name = "name")
    private String name;


    /**
     * points_activity.port (端口，wap/APP/pc三种分别组合以及单独。其中wap为 1 ，APP 为2 ，pc为 数字3.用1,2,3数字进行组合)
     * @ibatorgenerated 2017-07-19 15:59:23
     */
    @Column(name = "port")
    private String port;


    /**
     * points_activity.begin_time (开始时间)
     * @ibatorgenerated 2017-07-19 15:59:23
     */
    @Column(name = "begin_time")
    private Date beginTime;


    /**
     * points_activity.end_time (结束时间)
     * @ibatorgenerated 2017-07-19 15:59:23
     */
    @Column(name = "end_time")
    private Date endTime;


    /**
     * points_activity.img_id (水印图片id)
     * @ibatorgenerated 2017-07-19 15:59:23
     */
    @Column(name = "img_id")
    private Long imgId;


    /**
     * points_activity.img_path (图片路径)
     * @ibatorgenerated 2017-07-19 15:59:23
     */
    @Column(name = "img_path")
    private String imgPath;


    /**
     * points_activity.status (活动状态 0：启用 1：禁用)
     * @ibatorgenerated 2017-07-19 15:59:23
     */
    @Column(name = "status")
    private Integer status;

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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}