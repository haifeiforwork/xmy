package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class AdPosition implements Serializable {

    /**
     * ad_position.id
     * @ibatorgenerated 2017-07-11 20:40:52
     */
    @Column(name = "id")
    private Long id;


    /**
     * ad_position.name (广告位置名称)
     * @ibatorgenerated 2017-07-11 20:40:52
     */
    @Column(name = "name")
    private String name;


    /**
     * ad_position.img_num (图片数量)
     * @ibatorgenerated 2017-07-11 20:40:52
     */
    @Column(name = "img_num")
    private Integer imgNum;


    /**
     * ad_position.show_ways (展示方式 1 一张图片 2.左上下 3.上下右 4 上下 5横排)
     * @ibatorgenerated 2017-07-11 20:40:52
     */
    @Column(name = "show_ways")
    private Integer showWays;


    /**
     * ad_position.create_time (新增时间)
     * @ibatorgenerated 2017-07-11 20:40:52
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * ad_position.status (状态 0 启用 1 禁用)
     * @ibatorgenerated 2017-07-11 20:40:52
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

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public Integer getShowWays() {
        return showWays;
    }

    public void setShowWays(Integer showWays) {
        this.showWays = showWays;
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
}