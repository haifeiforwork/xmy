package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class GoodsImage implements Serializable {

    /**
     * goods_image.id
     * @ibatorgenerated 2017-06-13 14:25:12
     */
    @Column(name = "id")
    private Long id;


    /**
     * goods_image.path
     * @ibatorgenerated 2017-06-13 14:25:12
     */
    @Column(name = "path")
    private String path;


    /**
     * goods_image.code (图片编码)
     * @ibatorgenerated 2017-06-13 14:25:12
     */
    @Column(name = "code")
    private String code;


    /**
     * goods_image.goods_id (商品的id)
     * @ibatorgenerated 2017-06-13 14:25:12
     */
    @Column(name = "goods_id")
    private Long goodsId;


    /**
     * goods_image.type (图片类型(暂没使用))
     * @ibatorgenerated 2017-06-13 14:25:12
     */
    @Column(name = "type")
    private Integer type;


    /**
     * goods_image.create_time (创建时间)
     * @ibatorgenerated 2017-06-13 14:25:12
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * goods_image.seq (排序)
     * @ibatorgenerated 2017-06-13 14:25:12
     */
    @Column(name = "seq")
    private Integer seq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}