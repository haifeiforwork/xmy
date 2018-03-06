package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class PointsGoods implements Serializable {

    /**
     * points_goods.id
     * @ibatorgenerated 2017-07-11 15:55:39
     */
    @Column(name = "id")
    private Long id;


    /**
     * points_goods.points_id (积分促销活动id)
     * @ibatorgenerated 2017-07-11 15:55:39
     */
    @Column(name = "points_id")
    private Long pointsId;


    /**
     * points_goods.goods_id (商品id)
     * @ibatorgenerated 2017-07-11 15:55:39
     */
    @Column(name = "goods_id")
    private Long goodsId;


    /**
     * points_goods.code (编码)
     * @ibatorgenerated 2017-07-11 15:55:39
     */
    @Column(name = "code")
    private String code;


    /**
     * points_goods.limit_num (金额)
     * @ibatorgenerated 2017-07-11 15:55:39
     */
    @Column(name = "limit_num")
    private Integer limitNum;


    /**
     * points_goods.all_num (总数量)
     * @ibatorgenerated 2017-07-11 15:55:39
     */
    @Column(name = "all_num")
    private Integer allNum;


    /**
     * points_goods.complete_num (交易完成量)
     * @ibatorgenerated 2017-07-11 15:55:39
     */
    @Column(name = "complete_num")
    private Integer completeNum;


    /**
     * points_goods.points (积分)
     * @ibatorgenerated 2017-07-11 15:55:39
     */
    @Column(name = "points")
    private Integer points;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getPointsId() {
		return pointsId;
	}


	public void setPointsId(Long pointsId) {
		this.pointsId = pointsId;
	}


	public Long getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Integer getLimitNum() {
		return limitNum;
	}


	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}


	public Integer getAllNum() {
		return allNum;
	}


	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}


	public Integer getCompleteNum() {
		return completeNum;
	}


	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}


	public Integer getPoints() {
		return points;
	}


	public void setPoints(Integer points) {
		this.points = points;
	}

    
}