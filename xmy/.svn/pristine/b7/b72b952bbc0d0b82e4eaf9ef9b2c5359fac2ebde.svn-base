package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class JiguangPushRecord implements Serializable {

    /**
     * jiguang_push_record.id
     * @ibatorgenerated 2018-02-05 10:57:19
     */
    @Column(name = "id")
    private Long id;


    /**
     * jiguang_push_record.user_id (推送目标 全体广播时为空)
     * @ibatorgenerated 2018-02-05 10:57:19
     */
    @Column(name = "user_id")
    private String userId;


    /**
     * jiguang_push_record.content (推送内容)
     * @ibatorgenerated 2018-02-05 10:57:19
     */
    @Column(name = "content")
    private String content;


    /**
     * jiguang_push_record.result (推送结果)
     * @ibatorgenerated 2018-02-05 10:57:19
     */
    @Column(name = "result")
    private String result;


    /**
     * jiguang_push_record.create_timestamp (创建时间)
     * @ibatorgenerated 2018-02-05 10:57:19
     */
    @Column(name = "create_timestamp")
    private Date createTimestamp;


    /**
     * jiguang_push_record.type (推送业务类型 0:全体广播 1：分组标签广播 2:一对一推送)
     * @ibatorgenerated 2018-02-05 10:57:19
     */
    @Column(name = "type")
    private Integer type;


    /**
     * jiguang_push_record.label_id (推送业务类型 为2时， jiguang_push_label 表 标签id )
     * @ibatorgenerated 2018-02-05 10:57:19
     */
    @Column(name = "label_id")
    private Long labelId;


    /**
     * jiguang_push_record.ext (推送附带信息)
     * @ibatorgenerated 2018-02-05 10:57:19
     */
    @Column(name = "ext")
    private String ext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}