package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;

public class EntityCoupon implements Serializable {

    /**
     * entity_coupon.id
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "id")
    private Long id;


    /**
     * entity_coupon.coupon_code (优惠券编码)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "coupon_code")
    private String couponCode;


    /**
     * entity_coupon.coupon_password (优惠券密码)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "coupon_password")
    private String couponPassword;


    /**
     * entity_coupon.user_id
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * entity_coupon.name (优惠券名称)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "name")
    private String name;


    /**
     * entity_coupon.coupon_count (优惠券数量)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "coupon_count")
    private Long couponCount;


    /**
     * entity_coupon.coupon_value (优惠券面值)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "coupon_value")
    private BigDecimal couponValue;


    /**
     * entity_coupon.quota (需要满足多少元此优惠券才生效)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "quota")
    private Integer quota;


    /**
     * entity_coupon.coupon_img (优惠券图片)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "coupon_img")
    private String couponImg;


    /**
     * entity_coupon.effective_start_time (优惠券有限时间开始)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "effective_start_time")
    private Date effectiveStartTime;


    /**
     * entity_coupon.effective_end_time (优惠券有效时间结束)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "effective_end_time")
    private Date effectiveEndTime;


    /**
     * entity_coupon.use_strategy (优惠券使用策略(1代表一笔交易仅使用一张优惠券，2代表一笔交易可使用多张优惠券）)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "use_strategy")
    private Integer useStrategy;


    /**
     * entity_coupon.use_with_others (能否和其他优惠券一起使用（1代表不能和其他类型优惠券一起使用，2代表可以和其他类型优惠券一起使用）)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "use_with_others")
    private Integer useWithOthers;


    /**
     * entity_coupon.supplier_ids (供应商的ID集合)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "supplier_ids")
    private String supplierIds;


    /**
     * entity_coupon.use_range (使用范围（1：全场通用；2：分类使用；3：限定商品；4：排队商品）)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "use_range")
    private Integer useRange;


    /**
     * entity_coupon.use_range_ids (使用范围的集合)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "use_range_ids")
    private String useRangeIds;


    /**
     * entity_coupon.status (0代表没有绑定激活，1代表绑定激活了，2代表已经使用了)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "status")
    private Integer status;


    /**
     * entity_coupon.type (类型（1代表电子优惠券，2代表纸质优惠券）)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "type")
    private Integer type;


    /**
     * entity_coupon.des (优惠券介绍)
     * @ibatorgenerated 2017-10-25 18:13:19
     */
    @Column(name = "des")
    private String des;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponPassword() {
        return couponPassword;
    }

    public void setCouponPassword(String couponPassword) {
        this.couponPassword = couponPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Long couponCount) {
        this.couponCount = couponCount;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getCouponImg() {
        return couponImg;
    }

    public void setCouponImg(String couponImg) {
        this.couponImg = couponImg;
    }

    public Date getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(Date effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public Date getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(Date effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public Integer getUseStrategy() {
        return useStrategy;
    }

    public void setUseStrategy(Integer useStrategy) {
        this.useStrategy = useStrategy;
    }

    public Integer getUseWithOthers() {
        return useWithOthers;
    }

    public void setUseWithOthers(Integer useWithOthers) {
        this.useWithOthers = useWithOthers;
    }

    public String getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(String supplierIds) {
        this.supplierIds = supplierIds;
    }

    public Integer getUseRange() {
        return useRange;
    }

    public void setUseRange(Integer useRange) {
        this.useRange = useRange;
    }

    public String getUseRangeIds() {
        return useRangeIds;
    }

    public void setUseRangeIds(String useRangeIds) {
        this.useRangeIds = useRangeIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}