package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class PromotionActivity implements Serializable {

    /**
     * promotion_activity.id (促销ID)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "id")
    private Long id;


    /**
     * promotion_activity.name (促销名称)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "name")
    private String name;


    /**
     * promotion_activity.discount (打折折扣)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "discount")
    private Integer discount;


    /**
     * promotion_activity.port (端口，wap/APP/pc三种分别组合以及单独。其中wap为 1 ，APP 为2 ，pc为 数字3.用1,2,3数字进行组合)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "port")
    private String port;


    /**
     * promotion_activity.join_condition (参与条件,1代表会员，2代表匿名，可以组合)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "join_condition")
    private String joinCondition;


    /**
     * promotion_activity.code_ids (前台分类编码)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "code_ids")
    private String codeIds;


    /**
     * promotion_activity.code_names
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "code_names")
    private String codeNames;


    /**
     * promotion_activity.supplier_ids (供应商id集合)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "supplier_ids")
    private String supplierIds;


    /**
     * promotion_activity.supplier_names
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "supplier_names")
    private String supplierNames;


    /**
     * promotion_activity.pro_type (选择商品方式类型)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "pro_type")
    private String proType;


    /**
     * promotion_activity.begin_time (开始时间)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "begin_time")
    private Date beginTime;


    /**
     * promotion_activity.end_time (结束时间)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "end_time")
    private Date endTime;


    /**
     * promotion_activity.img_id (水印图片ID)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "img_id")
    private Long imgId;


    /**
     * promotion_activity.img_path (水印图片路径)
     * @ibatorgenerated 2017-07-19 13:59:39
     */
    @Column(name = "img_path")
    private String imgPath;


    /**
     * promotion_activity.status (活动状态 0：启用 1：禁用)
     * @ibatorgenerated 2017-07-19 13:59:39
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getJoinCondition() {
        return joinCondition;
    }

    public void setJoinCondition(String joinCondition) {
        this.joinCondition = joinCondition;
    }

    public String getCodeIds() {
        return codeIds;
    }

    public void setCodeIds(String codeIds) {
        this.codeIds = codeIds;
    }

    public String getCodeNames() {
        return codeNames;
    }

    public void setCodeNames(String codeNames) {
        this.codeNames = codeNames;
    }

    public String getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(String supplierIds) {
        this.supplierIds = supplierIds;
    }

    public String getSupplierNames() {
        return supplierNames;
    }

    public void setSupplierNames(String supplierNames) {
        this.supplierNames = supplierNames;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}