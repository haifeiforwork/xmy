package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class BuyAndPresent implements Serializable {

    /**
     * buy_and_present.id (此关系表的id)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "id")
    private Long id;


    /**
     * buy_and_present.name (此买即赠的促销名称)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "name")
    private String name;


    /**
     * buy_and_present.start_time (此买即赠的开始时间)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "start_time")
    private Date startTime;


    /**
     * buy_and_present.end_time (此买即赠的结束时间)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "end_time")
    private Date endTime;


    /**
     * buy_and_present.watermark_img (水印图片)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "watermark_img")
    private String watermarkImg;


    /**
     * buy_and_present.gift_count (赠品数量)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "gift_count")
    private Integer giftCount;


    /**
     * buy_and_present.activity_desc_url (活动详情页面地址)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "activity_desc_url")
    private String activityDescUrl;


    /**
     * buy_and_present.promotion_mark (促销备注)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "promotion_mark")
    private String promotionMark;


    /**
     * buy_and_present.main_goods_ids (主商品ID集合)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "main_goods_ids")
    private String mainGoodsIds;


    /**
     * buy_and_present.is_superposition (是否叠加（1代表叠加，2代表不叠加）)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "is_superposition")
    private Integer isSuperposition;


    /**
     * buy_and_present.gift_goods_ids (赠品的ID集合)
     * @ibatorgenerated 2017-07-11 16:35:15
     */
    @Column(name = "gift_goods_ids")
    private String giftGoodsIds;


    /**
     * buy_and_present.status (此买即赠的状态（0代表启用，1代表禁用）)
     * @ibatorgenerated 2017-07-11 16:35:15
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWatermarkImg() {
        return watermarkImg;
    }

    public void setWatermarkImg(String watermarkImg) {
        this.watermarkImg = watermarkImg;
    }

    public Integer getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(Integer giftCount) {
        this.giftCount = giftCount;
    }

    public String getActivityDescUrl() {
        return activityDescUrl;
    }

    public void setActivityDescUrl(String activityDescUrl) {
        this.activityDescUrl = activityDescUrl;
    }

    public String getPromotionMark() {
        return promotionMark;
    }

    public void setPromotionMark(String promotionMark) {
        this.promotionMark = promotionMark;
    }

    public String getMainGoodsIds() {
        return mainGoodsIds;
    }

    public void setMainGoodsIds(String mainGoodsIds) {
        this.mainGoodsIds = mainGoodsIds;
    }

    public Integer getIsSuperposition() {
        return isSuperposition;
    }

    public void setIsSuperposition(Integer isSuperposition) {
        this.isSuperposition = isSuperposition;
    }

    public String getGiftGoodsIds() {
        return giftGoodsIds;
    }

    public void setGiftGoodsIds(String giftGoodsIds) {
        this.giftGoodsIds = giftGoodsIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}