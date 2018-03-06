package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonInclude(value=Include.NON_NULL)
public class MsgPushInfo implements Serializable {

    /**
     * msg_push_info.id
     * @ibatorgenerated 2017-07-31 11:33:56
     */
    @Column(name = "id")
    private Long id;


    /**
     * msg_push_info.user_id (用户ID)
     * @ibatorgenerated 2017-07-31 11:33:56
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * msg_push_info.title (推送标题)
     * @ibatorgenerated 2017-07-31 11:33:56
     */
    @Column(name = "title")
    private String title;


    /**
     * msg_push_info.body (推送内容)
     * @ibatorgenerated 2017-07-31 11:33:56
     */
    @Column(name = "body")
    private String body;


    /**
     * msg_push_info.addtime (添加时间)
     * @ibatorgenerated 2017-07-31 11:33:56
     */
    @Column(name = "addtime")
    private Date addtime;


    /**
     * msg_push_info.del (是否删除（0存在，1删除）)
     * @ibatorgenerated 2017-07-31 11:33:56
     */
    @Column(name = "del")
    private Integer del;


    /**
     * msg_push_info.readstatus (0 未读 ,1 已读)
     * @ibatorgenerated 2017-07-31 11:33:56
     */
    @Column(name = "readstatus")
    private Integer readstatus;


    /**
     * msg_push_info.imgurl (图片地址)
     * @ibatorgenerated 2017-07-31 11:33:56
     */
    @Column(name = "imgurl")
    private String imgurl;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getReadstatus() {
        return readstatus;
    }

    public void setReadstatus(Integer readstatus) {
        this.readstatus = readstatus;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}