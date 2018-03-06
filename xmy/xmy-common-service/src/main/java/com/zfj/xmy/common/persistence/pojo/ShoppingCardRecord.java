package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;

public class ShoppingCardRecord implements Serializable {

    /**
     * shopping_card_record.id (购物卡使用明细记录表id)
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "id")
    private Long id;


    /**
     * shopping_card_record.shopping_card_id (购物卡id)
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "shopping_card_id")
    private Long shoppingCardId;


    /**
     * shopping_card_record.user_id (用户id)
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * shopping_card_record.use_time (使用时间)
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "use_time")
    private Date useTime;


    /**
     * shopping_card_record.value (消费金额)
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "value")
    private BigDecimal value;


    /**
     * shopping_card_record.remark
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "remark")
    private String remark;


    /**
     * shopping_card_record.order_id (订单id)
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "order_id")
    private Long orderId;


    /**
     * shopping_card_record.type (类型 1消费 2 退还)
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "type")
    private Integer type;


    /**
     * shopping_card_record.status (有效性 1失效 2有效 3冻结)
     * @ibatorgenerated 2017-12-12 17:14:34
     */
    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingCardId() {
        return shoppingCardId;
    }

    public void setShoppingCardId(Long shoppingCardId) {
        this.shoppingCardId = shoppingCardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}