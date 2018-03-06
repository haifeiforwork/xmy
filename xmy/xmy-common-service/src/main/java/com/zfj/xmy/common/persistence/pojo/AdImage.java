package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class AdImage implements Serializable {

    /**
     * ad_image.id
     * @ibatorgenerated 2017-08-29 19:19:25
     */
    @Column(name = "id")
    private Long id;


    /**
     * ad_image.img_path (图片路径)
     * @ibatorgenerated 2017-08-29 19:19:25
     */
    @Column(name = "img_path")
    private String imgPath;


    /**
     * ad_image.ad_id (广告id)
     * @ibatorgenerated 2017-08-29 19:19:25
     */
    @Column(name = "ad_id")
    private Long adId;


    /**
     * ad_image.weight (权重)
     * @ibatorgenerated 2017-08-29 19:19:25
     */
    @Column(name = "weight")
    private Integer weight;


    /**
     * ad_image.create_time (新增时间)
     * @ibatorgenerated 2017-08-29 19:19:25
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * ad_image.data (图片跳转链接（如果是type=1，则为商品id，如果为2就是跳转地址）)
     * @ibatorgenerated 2017-08-29 19:19:25
     */
    @Column(name = "data")
    private String data;


    /**
     * ad_image.type (是商品id还是跳转url（0为商品id，1为跳转url）)
     * @ibatorgenerated 2017-08-29 19:19:25
     */
    @Column(name = "type")
    private Integer type;


    /**
     * ad_image.img_type (0 大图 1 小图)
     * @ibatorgenerated 2017-08-29 19:19:25
     */
    @Column(name = "img_type")
    private Integer imgType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }
}