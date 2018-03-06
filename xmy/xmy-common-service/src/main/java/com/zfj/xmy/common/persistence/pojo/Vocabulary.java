package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class Vocabulary implements Serializable {

    /**
     * vocabulary.id
     * @ibatorgenerated 2017-06-14 18:11:34
     */
    @Column(name = "id")
    private Long id;


    /**
     * vocabulary.name (名称)
     * @ibatorgenerated 2017-06-14 18:11:34
     */
    @Column(name = "name")
    private String name;


    /**
     * vocabulary.is_hierarchy (是否层级:0:是,1:否)
     * @ibatorgenerated 2017-06-14 18:11:34
     */
    @Column(name = "is_hierarchy")
    private Integer isHierarchy;


    /**
     * vocabulary.mark (标识(也可当作模块))
     * @ibatorgenerated 2017-06-14 18:11:34
     */
    @Column(name = "mark")
    private String mark;


    /**
     * vocabulary.weight (权重)
     * @ibatorgenerated 2017-06-14 18:11:34
     */
    @Column(name = "weight")
    private Integer weight;


    /**
     * vocabulary.status (状态:0:启用,1:禁用)
     * @ibatorgenerated 2017-06-14 18:11:34
     */
    @Column(name = "status")
    private Integer status;


    /**
     * vocabulary.des (描述)
     * @ibatorgenerated 2017-06-14 18:11:34
     */
    @Column(name = "des")
    private String des;

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

    public Integer getIsHierarchy() {
        return isHierarchy;
    }

    public void setIsHierarchy(Integer isHierarchy) {
        this.isHierarchy = isHierarchy;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}