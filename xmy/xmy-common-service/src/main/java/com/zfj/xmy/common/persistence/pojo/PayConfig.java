package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class PayConfig implements Serializable {

    /**
     * pay_config.id
     * @ibatorgenerated 2017-08-22 10:09:36
     */
    @Column(name = "id")
    private Long id;


    /**
     * pay_config.ke (字段)
     * @ibatorgenerated 2017-08-22 10:09:36
     */
    @Column(name = "ke")
    private String ke;


    /**
     * pay_config.ke_des (字段描述)
     * @ibatorgenerated 2017-08-22 10:09:36
     */
    @Column(name = "ke_des")
    private String keDes;


    /**
     * pay_config.val (值)
     * @ibatorgenerated 2017-08-22 10:09:36
     */
    @Column(name = "val")
    private String val;


    /**
     * pay_config.pay_type (支付渠道)
     * @ibatorgenerated 2017-08-22 10:09:36
     */
    @Column(name = "pay_type")
    private Integer payType;


    /**
     * pay_config.created_time (创建时间)
     * @ibatorgenerated 2017-08-22 10:09:36
     */
    @Column(name = "created_time")
    private Date createdTime;


    /**
     * pay_config.merchant_type (商户类型 (0、微信 1、支付宝 2、银联 ))
     * @ibatorgenerated 2017-08-22 10:09:36
     */
    @Column(name = "merchant_type")
    private Integer merchantType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKe() {
        return ke;
    }

    public void setKe(String ke) {
        this.ke = ke;
    }

    public String getKeDes() {
        return keDes;
    }

    public void setKeDes(String keDes) {
        this.keDes = keDes;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }
}