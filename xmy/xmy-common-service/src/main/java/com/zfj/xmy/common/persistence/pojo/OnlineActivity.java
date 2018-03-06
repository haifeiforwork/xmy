package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;

public class OnlineActivity implements Serializable {

    /**
     * online_activity.id
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "id")
    private Long id;


    /**
     * online_activity.activity_name
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "activity_name")
    private String activityName;


    /**
     * online_activity.begin_time
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "begin_time")
    private Date beginTime;


    /**
     * online_activity.end_time
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "end_time")
    private Date endTime;


    /**
     * online_activity.cq_begin_time
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "cq_begin_time")
    private Date cqBeginTime;


    /**
     * online_activity.cq_end_time
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "cq_end_time")
    private Date cqEndTime;


    /**
     * online_activity.user_limit_num (用户限购数)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "user_limit_num")
    private Integer userLimitNum;


    /**
     * online_activity.day_limit_num (每天限购订单数)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "day_limit_num")
    private Integer dayLimitNum;


    /**
     * online_activity.qg_discount (全国优惠折扣)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "qg_discount")
    private BigDecimal qgDiscount;


    /**
     * online_activity.cq_discount (重庆优惠折扣)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "cq_discount")
    private BigDecimal cqDiscount;


    /**
     * online_activity.limit_discount_price (最高优惠金额)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "limit_discount_price")
    private BigDecimal limitDiscountPrice;


    /**
     * online_activity.activity_remark (活动描述)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "activity_remark")
    private String activityRemark;


    /**
     * online_activity.present_caicaikan_couponid (猜猜看送券ID)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "present_caicaikan_couponid")
    private Long presentCaicaikanCouponid;


    /**
     * online_activity.present_couponid (登录赠送优惠券id)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "present_couponid")
    private Long presentCouponid;


    /**
     * online_activity.present_coupon_des (赠送优惠券的描述信息)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "present_coupon_des")
    private String presentCouponDes;


    /**
     * online_activity.present_coupon_begin_time (赠送优惠券开启时间)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "present_coupon_begin_time")
    private Date presentCouponBeginTime;


    /**
     * online_activity.present_coupon_end_time (赠送优惠券结束时间)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "present_coupon_end_time")
    private Date presentCouponEndTime;


    /**
     * online_activity.status (活动状态：0：启用 1：禁用)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "status")
    private Integer status;


    /**
     * online_activity.app_new_userid (APP新用户开始ID)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "app_new_userid")
    private Long appNewUserid;


    /**
     * online_activity.present_coupon_image (赠送优惠券活动图片)
     * @ibatorgenerated 2018-02-08 10:25:14
     */
    @Column(name = "present_coupon_image")
    private String presentCouponImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public Date getCqBeginTime() {
        return cqBeginTime;
    }

    public void setCqBeginTime(Date cqBeginTime) {
        this.cqBeginTime = cqBeginTime;
    }

    public Date getCqEndTime() {
        return cqEndTime;
    }

    public void setCqEndTime(Date cqEndTime) {
        this.cqEndTime = cqEndTime;
    }

    public Integer getUserLimitNum() {
        return userLimitNum;
    }

    public void setUserLimitNum(Integer userLimitNum) {
        this.userLimitNum = userLimitNum;
    }

    public Integer getDayLimitNum() {
        return dayLimitNum;
    }

    public void setDayLimitNum(Integer dayLimitNum) {
        this.dayLimitNum = dayLimitNum;
    }

    public BigDecimal getQgDiscount() {
        return qgDiscount;
    }

    public void setQgDiscount(BigDecimal qgDiscount) {
        this.qgDiscount = qgDiscount;
    }

    public BigDecimal getCqDiscount() {
        return cqDiscount;
    }

    public void setCqDiscount(BigDecimal cqDiscount) {
        this.cqDiscount = cqDiscount;
    }

    public BigDecimal getLimitDiscountPrice() {
        return limitDiscountPrice;
    }

    public void setLimitDiscountPrice(BigDecimal limitDiscountPrice) {
        this.limitDiscountPrice = limitDiscountPrice;
    }

    public String getActivityRemark() {
        return activityRemark;
    }

    public void setActivityRemark(String activityRemark) {
        this.activityRemark = activityRemark;
    }

    public Long getPresentCaicaikanCouponid() {
        return presentCaicaikanCouponid;
    }

    public void setPresentCaicaikanCouponid(Long presentCaicaikanCouponid) {
        this.presentCaicaikanCouponid = presentCaicaikanCouponid;
    }

    public Long getPresentCouponid() {
        return presentCouponid;
    }

    public void setPresentCouponid(Long presentCouponid) {
        this.presentCouponid = presentCouponid;
    }

    public String getPresentCouponDes() {
        return presentCouponDes;
    }

    public void setPresentCouponDes(String presentCouponDes) {
        this.presentCouponDes = presentCouponDes;
    }

    public Date getPresentCouponBeginTime() {
        return presentCouponBeginTime;
    }

    public void setPresentCouponBeginTime(Date presentCouponBeginTime) {
        this.presentCouponBeginTime = presentCouponBeginTime;
    }

    public Date getPresentCouponEndTime() {
        return presentCouponEndTime;
    }

    public void setPresentCouponEndTime(Date presentCouponEndTime) {
        this.presentCouponEndTime = presentCouponEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAppNewUserid() {
        return appNewUserid;
    }

    public void setAppNewUserid(Long appNewUserid) {
        this.appNewUserid = appNewUserid;
    }

    public String getPresentCouponImage() {
        return presentCouponImage;
    }

    public void setPresentCouponImage(String presentCouponImage) {
        this.presentCouponImage = presentCouponImage;
    }
}