package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class TradeProduction implements Serializable {

    private Long id;


    private String chanid;


    private String tranflowno;


    private Double happenmoney;


    private Double tranmoney;


    private Date created;


    private String transtate;


    private String noticestate;


    private Date trantime;


    private String flowno;


    private String flowtype;


    private Long businessOrderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChanid() {
        return chanid;
    }

    public void setChanid(String chanid) {
        this.chanid = chanid;
    }

    public String getTranflowno() {
        return tranflowno;
    }

    public void setTranflowno(String tranflowno) {
        this.tranflowno = tranflowno;
    }

    public Double getHappenmoney() {
        return happenmoney;
    }

    public void setHappenmoney(Double happenmoney) {
        this.happenmoney = happenmoney;
    }

    public Double getTranmoney() {
        return tranmoney;
    }

    public void setTranmoney(Double tranmoney) {
        this.tranmoney = tranmoney;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTranstate() {
        return transtate;
    }

    public void setTranstate(String transtate) {
        this.transtate = transtate;
    }

    public String getNoticestate() {
        return noticestate;
    }

    public void setNoticestate(String noticestate) {
        this.noticestate = noticestate;
    }

    public Date getTrantime() {
        return trantime;
    }

    public void setTrantime(Date trantime) {
        this.trantime = trantime;
    }

    public String getFlowno() {
        return flowno;
    }

    public void setFlowno(String flowno) {
        this.flowno = flowno;
    }

    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype;
    }

    public Long getBusinessOrderId() {
        return businessOrderId;
    }

    public void setBusinessOrderId(Long businessOrderId) {
        this.businessOrderId = businessOrderId;
    }
}