package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class PresentCouponActivity implements Serializable {

    /**
     * present_coupon_activity.id
     * @ibatorgenerated 2018-01-11 16:15:02
     */
    @Column(name = "id")
    private Long id;


    /**
     * present_coupon_activity.uid
     * @ibatorgenerated 2018-01-11 16:15:02
     */
    @Column(name = "uid")
    private Long uid;


    /**
     * present_coupon_activity.status (活动开启状态： 0开启 1关闭)
     * @ibatorgenerated 2018-01-11 16:15:02
     */
    @Column(name = "status")
    private Integer status;


    /**
     * present_coupon_activity.begin_time (活动开始时间)
     * @ibatorgenerated 2018-01-11 16:15:02
     */
    @Column(name = "begin_time")
    private Date beginTime;


    /**
     * present_coupon_activity.end_time (活动结束时间)
     * @ibatorgenerated 2018-01-11 16:15:02
     */
    @Column(name = "end_time")
    private Date endTime;


    /**
     * present_coupon_activity.coupon_id (赠送优惠券id集合的字符串)
     * @ibatorgenerated 2018-01-11 16:15:02
     */
    @Column(name = "coupon_id")
    private String couponId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
}