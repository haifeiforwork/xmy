package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;

public class OrderGoods implements Serializable {

    /**
     * order_goods.id
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "id")
    private Long id;


    /**
     * order_goods.order_id (订单id)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "order_id")
    private Long orderId;


    /**
     * order_goods.goods_id (商品ID)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "goods_id")
    private Long goodsId;


    /**
     * order_goods.name (商品名称)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "name")
    private String name;


    /**
     * order_goods.num (商品数量
            )
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "num")
    private Integer num;


    /**
     * order_goods.price (单个商品价格)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "price")
    private BigDecimal price;


    /**
     * order_goods.sum_price (合计价格)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "sum_price")
    private BigDecimal sumPrice;


    /**
     * order_goods.points (单个商品积分(消耗))
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "points")
    private Integer points;


    /**
     * order_goods.sum_points (总工积分(消耗))
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "sum_points")
    private Integer sumPoints;


    /**
     * order_goods.no (商品编号)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "no")
    private Long no;


    /**
     * order_goods.type_name (商品类型)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "type_name")
    private String typeName;


    /**
     * order_goods.activity_type (商品活动类型 0买即赠 1 专题促销 2 其他活动)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "activity_type")
    private Integer activityType;


    /**
     * order_goods.activity_id (商品所属活动)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "activity_id")
    private Long activityId;


    /**
     * order_goods.supplier (商品所属供货商)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "supplier")
    private String supplier;


    /**
     * order_goods.present_goods_id (赠品id)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "present_goods_id")
    private Long presentGoodsId;


    /**
     * order_goods.present_goods_name (赠品名称)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "present_goods_name")
    private String presentGoodsName;


    /**
     * order_goods.ddzt (oa日报状态)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "ddzt")
    private Integer ddzt;


    /**
     * order_goods.cost_price (商品成本价)
     * @ibatorgenerated 2018-01-22 16:44:06
     */
    @Column(name = "cost_price")
    private BigDecimal costPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getSumPoints() {
        return sumPoints;
    }

    public void setSumPoints(Integer sumPoints) {
        this.sumPoints = sumPoints;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Long getPresentGoodsId() {
        return presentGoodsId;
    }

    public void setPresentGoodsId(Long presentGoodsId) {
        this.presentGoodsId = presentGoodsId;
    }

    public String getPresentGoodsName() {
        return presentGoodsName;
    }

    public void setPresentGoodsName(String presentGoodsName) {
        this.presentGoodsName = presentGoodsName;
    }

    public Integer getDdzt() {
        return ddzt;
    }

    public void setDdzt(Integer ddzt) {
        this.ddzt = ddzt;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
}