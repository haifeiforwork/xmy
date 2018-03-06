package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class OrderCommit implements Serializable {

    /**
     * order_commit.id
     * @ibatorgenerated 2017-12-27 17:34:53
     */
    @Column(name = "id")
    private Long id;


    /**
     * order_commit.name (展示名称)
     * @ibatorgenerated 2017-12-27 17:34:53
     */
    @Column(name = "name")
    private String name;


    /**
     * order_commit.status (0：启用 1：禁用)
     * @ibatorgenerated 2017-12-27 17:34:53
     */
    @Column(name = "status")
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}