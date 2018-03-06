package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class EmailLog implements Serializable {

    /**
     * email_log.id
     * @ibatorgenerated 2017-09-18 15:59:34
     */
    @Column(name = "id")
    private String id;


    /**
     * email_log.email (邮箱号码)
     * @ibatorgenerated 2017-09-18 15:59:34
     */
    @Column(name = "email")
    private String email;


    /**
     * email_log.status (发送状态：0：成功，1：失败)
     * @ibatorgenerated 2017-09-18 15:59:34
     */
    @Column(name = "status")
    private Integer status;


    /**
     * email_log.message (邮件内容)
     * @ibatorgenerated 2017-09-18 15:59:34
     */
    @Column(name = "message")
    private String message;


    /**
     * email_log.code (验证码)
     * @ibatorgenerated 2017-09-18 15:59:34
     */
    @Column(name = "code")
    private String code;


    /**
     * email_log.create_timestamp (发送时间)
     * @ibatorgenerated 2017-09-18 15:59:34
     */
    @Column(name = "create_timestamp")
    private Date createTimestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}