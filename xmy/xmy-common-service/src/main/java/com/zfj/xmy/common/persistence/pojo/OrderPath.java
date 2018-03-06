package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class OrderPath implements Serializable {

    /**
     * order_path.id
     * @ibatorgenerated 2017-06-28 14:52:44
     */
    @Column(name = "id")
    private Long id;


    /**
     * order_path.order_id (订单ID)
     * @ibatorgenerated 2017-06-28 14:52:44
     */
    @Column(name = "order_id")
    private Long orderId;


    /**
     * order_path.user_name (操作人)
     * @ibatorgenerated 2017-06-28 14:52:44
     */
    @Column(name = "user_name")
    private String userName;


    /**
     * order_path.change_content (订单变更内容)
     * @ibatorgenerated 2017-06-28 14:52:44
     */
    @Column(name = "change_content")
    private String changeContent;


    /**
     * order_path.change_time (变更时间)
     * @ibatorgenerated 2017-06-28 14:52:44
     */
    @Column(name = "change_time")
    private Date changeTime;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
}