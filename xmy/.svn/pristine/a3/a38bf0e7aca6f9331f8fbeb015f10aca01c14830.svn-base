package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class TradeChannels implements Serializable {

    /**
     * trade_channels.id
     * @ibatorgenerated 2017-08-12 10:59:19
     */
    @Column(name = "id")
    private Long id;


    /**
     * trade_channels.cCode (渠道编码)
     * @ibatorgenerated 2017-08-12 10:59:19
     */
    @Column(name = "cCode")
    private Integer ccode;


    /**
     * trade_channels.cName (渠道名称)
     * @ibatorgenerated 2017-08-12 10:59:19
     */
    @Column(name = "cName")
    private String cname;


    /**
     * trade_channels.status (状态 0 启用 1 停用 )
     * @ibatorgenerated 2017-08-12 10:59:19
     */
    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCcode() {
        return ccode;
    }

    public void setCcode(Integer ccode) {
        this.ccode = ccode;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}