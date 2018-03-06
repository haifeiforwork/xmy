package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;

public class PromotionGoods implements Serializable {

    /**
     * promotion_goods.id
     * @ibatorgenerated 2017-07-20 19:57:10
     */
    @Column(name = "id")
    private Long id;


    /**
     * promotion_goods.promotion_id (促销活动id)
     * @ibatorgenerated 2017-07-20 19:57:10
     */
    @Column(name = "promotion_id")
    private Long promotionId;


    /**
     * promotion_goods.goods_id (商品id)
     * @ibatorgenerated 2017-07-20 19:57:10
     */
    @Column(name = "goods_id")
    private Long goodsId;


    /**
     * promotion_goods.code (编码)
     * @ibatorgenerated 2017-07-20 19:57:10
     */
    @Column(name = "code")
    private String code;


    /**
     * promotion_goods.price (促销价)
     * @ibatorgenerated 2017-07-20 19:57:10
     */
    @Column(name = "price")
    private BigDecimal price;


    /**
     * promotion_goods.limit_num (单个会员购买量)
     * @ibatorgenerated 2017-07-20 19:57:10
     */
    @Column(name = "limit_num")
    private Integer limitNum;


    /**
     * promotion_goods.all_num (总数量)
     * @ibatorgenerated 2017-07-20 19:57:10
     */
    @Column(name = "all_num")
    private Integer allNum;


    /**
     * promotion_goods.complete_num (交易完成量)
     * @ibatorgenerated 2017-07-20 19:57:10
     */
    @Column(name = "complete_num")
    private Integer completeNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }
}