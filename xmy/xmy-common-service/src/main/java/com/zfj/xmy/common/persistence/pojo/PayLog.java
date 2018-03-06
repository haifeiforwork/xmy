package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class PayLog implements Serializable {

    /**
     * pay_log.id
     * @ibatorgenerated 2017-06-09 11:09:59
     */
    @Column(name = "id")
    private Long id;


    /**
     * pay_log.order_id
     * @ibatorgenerated 2017-06-09 11:09:59
     */
    @Column(name = "order_id")
    private Long orderId;


    /**
     * pay_log.status (״̬)
     * @ibatorgenerated 2017-06-09 11:09:59
     */
    @Column(name = "status")
    private Integer status;


    /**
     * pay_log.user_id
     * @ibatorgenerated 2017-06-09 11:09:59
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * pay_log.user_name (ף)
     * @ibatorgenerated 2017-06-09 11:09:59
     */
    @Column(name = "user_name")
    private String userName;


    /**
     * pay_log.create_time
     * @ibatorgenerated 2017-06-09 11:09:59
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * pay_log.des
     * @ibatorgenerated 2017-06-09 11:09:59
     */
    @Column(name = "des")
    private String des;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}