package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;

public class UserSpendPoints implements Serializable {

    /**
     * user_spend_points.id
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "id")
    private Long id;


    /**
     * user_spend_points.user_id (用户ID)
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * user_spend_points.type (1.消费记录 2.积分记录)
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "type")
    private Integer type;


    /**
     * user_spend_points.money_point (根据消费类型决定是金额还是积分（0为金额，1为积分）)
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "money_point")
    private BigDecimal moneyPoint;


    /**
     * user_spend_points.no (如果消费类型为消费存订单编号，存入就存支付流水号)
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "no")
    private String no;


    /**
     * user_spend_points.spend_type (1.存入 2.消费)
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "spend_type")
    private Integer spendType;


    /**
     * user_spend_points.remarks
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "remarks")
    private String remarks;


    /**
     * user_spend_points.creat_time
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "creat_time")
    private Date creatTime;


    /**
     * user_spend_points.console
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "console")
    private String console;


    /**
     * user_spend_points.is_del (0.已删除1.未删除)
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "is_del")
    private Integer isDel;


    /**
     * user_spend_points.sign (0.表示签到得积分 1.其他)
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "sign")
    private Integer sign;


    /**
     * user_spend_points.days (连续签到天数)
     * @ibatorgenerated 2017-08-23 09:40:01
     */
    @Column(name = "days")
    private Integer days;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMoneyPoint() {
        return moneyPoint;
    }

    public void setMoneyPoint(BigDecimal moneyPoint) {
        this.moneyPoint = moneyPoint;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getSpendType() {
        return spendType;
    }

    public void setSpendType(Integer spendType) {
        this.spendType = spendType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}