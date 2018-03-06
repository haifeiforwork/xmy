package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class TopicPage implements Serializable {

    /**
     * topic_page.id
     * @ibatorgenerated 2017-08-28 16:13:02
     */
    @Column(name = "id")
    private Long id;


    /**
     * topic_page.name (名称)
     * @ibatorgenerated 2017-08-28 16:13:02
     */
    @Column(name = "name")
    private String name;


    /**
     * topic_page.filepath (压缩文件路径)
     * @ibatorgenerated 2017-08-28 16:13:02
     */
    @Column(name = "filepath")
    private String filepath;


    /**
     * topic_page.un_filepath (解压后文件路径)
     * @ibatorgenerated 2017-08-28 16:13:02
     */
    @Column(name = "un_filepath")
    private String unFilepath;


    /**
     * topic_page.status (状态（0 代表未解压，1代表已经解压）)
     * @ibatorgenerated 2017-08-28 16:13:02
     */
    @Column(name = "status")
    private Integer status;


    /**
     * topic_page.create_time (上传时间)
     * @ibatorgenerated 2017-08-28 16:13:02
     */
    @Column(name = "create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getUnFilepath() {
        return unFilepath;
    }

    public void setUnFilepath(String unFilepath) {
        this.unFilepath = unFilepath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}