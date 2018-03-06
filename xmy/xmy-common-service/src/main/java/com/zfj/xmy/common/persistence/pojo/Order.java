package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;

public class Order implements Serializable {

    /**
     * `order`.id
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "id")
    private Long id;


    /**
     * `order`.no (订单编号)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "no")
    private String no;


    /**
     * `order`.serial_number (订单流水号
            )
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "serial_number")
    private String serialNumber;


    /**
     * `order`.user_id (用户ID)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * `order`.pay_type (1.支付宝 2.微信 3.银联 4.购物卡 5.货到付款)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "pay_type")
    private Integer payType;


    /**
     * `order`.pay (余额支付/购物卡)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "pay")
    private BigDecimal pay;


    /**
     * `order`.actual (第三方实际支付)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "actual")
    private BigDecimal actual;


    /**
     * `order`.used_points (消耗积分)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "used_points")
    private Integer usedPoints;


    /**
     * `order`.total (总金额)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "total")
    private BigDecimal total;


    /**
     * `order`.email (用户邮箱)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "email")
    private String email;


    /**
     * `order`.payment_name (下单人名称)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "payment_name")
    private String paymentName;


    /**
     * `order`.consignee_name (收货人名称)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "consignee_name")
    private String consigneeName;


    /**
     * `order`.consignee_idcard
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "consignee_idcard")
    private String consigneeIdcard;


    /**
     * `order`.consignee_phone (收货人联系方式
            )
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "consignee_phone")
    private String consigneePhone;


    /**
     * `order`.consignee_address (收货人地址)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "consignee_address")
    private String consigneeAddress;


    /**
     * `order`.delivery_name (配送人名称)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "delivery_name")
    private String deliveryName;


    /**
     * `order`.delivery_phone (配送人联系方式)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "delivery_phone")
    private String deliveryPhone;


    /**
     * `order`.delivery_method (配送方式：0.买家自提 1.平台配送)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "delivery_method")
    private Integer deliveryMethod;


    /**
     * `order`.payment_status (0.已支付 1.待支付)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "payment_status")
    private Integer paymentStatus;


    /**
     * `order`.ship_status (1.待审核：支付方式为货到付款时为待审核状态
            2.等待付款：支付方式不是货到付款时为等待付款
            3.供货确认：以上两种形式均完成为供货确认
            4.已供货：管理员确认审核通过的订单（核实商品库存，商品商家信息）更改为已供货
            5.已备货：为已供货的订单进行收货人信息核实后（备货确认）改为已备货
            6.已发货：管理员给每个订单添加配送人，状态改为已发货
            7.已收货：用户确认收到货后，在前台确认收货后
            
            )
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "ship_status")
    private Integer shipStatus;


    /**
     * `order`.status (订单状态：1、待付款 ，2、待发货 ，3、待收货， 4、待评价 5、已完成)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "status")
    private Integer status;


    /**
     * `order`.weight (订单商品总重量)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "weight")
    private Integer weight;


    /**
     * `order`.acc_points (订单积分)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "acc_points")
    private Long accPoints;


    /**
     * `order`.is_del (删除标识：0.已删除 1.未删除)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "is_del")
    private Integer isDel;


    /**
     * `order`.prepare_time (备货确认时间)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "prepare_time")
    private Date prepareTime;


    /**
     * `order`.extend_time (订单扩展时间)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "extend_time")
    private Date extendTime;


    /**
     * `order`.create_time
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * `order`.pay_time (生成订单时间)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "pay_time")
    private Date payTime;


    /**
     * `order`.update_time (订单改动时间)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "update_time")
    private Date updateTime;


    /**
     * `order`.send_time (订单发货时间)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "send_time")
    private Date sendTime;


    /**
     * `order`.arrival_time (订单送到用户手中的时间，即用户确认收货时间)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "arrival_time")
    private Date arrivalTime;


    /**
     * `order`.parset_time (用户指定配送的时间)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "parset_time")
    private Date parsetTime;


    /**
     * `order`.updator (订单修改人)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "updator")
    private String updator;


    /**
     * `order`.order_remark (备注信息)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "order_remark")
    private String orderRemark;


    /**
     * `order`.business_remark (商家备注)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "business_remark")
    private String businessRemark;


    /**
     * `order`.return_status (0:未退货，1:已退货 2:退货中)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "return_status")
    private Integer returnStatus;


    /**
     * `order`.return_reason (退货原因)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "return_reason")
    private String returnReason;


    /**
     * `order`.company_name (发票信息 企业名称)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "company_name")
    private String companyName;


    /**
     * `order`.invoice_content
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "invoice_content")
    private String invoiceContent;


    /**
     * `order`.invoice_type (发票类别 （0：个人 1：公司）)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "invoice_type")
    private Integer invoiceType;


    /**
     * `order`.taxpayer_num (纳税人识别号)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "taxpayer_num")
    private String taxpayerNum;


    /**
     * `order`.device_id (设备号)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "device_id")
    private String deviceId;


    /**
     * `order`.logistics_no (物流客户单号)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "logistics_no")
    private String logisticsNo;


    /**
     * `order`.coupon_id (优惠券id)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "coupon_id")
    private Long couponId;


    /**
     * `order`.freight (运费)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "freight")
    private BigDecimal freight;


    /**
     * `order`.province (收货人所属省)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "province")
    private String province;


    /**
     * `order`.city (收货人所属市)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "city")
    private String city;


    /**
     * `order`.area (收货人所属区)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "area")
    private String area;


    /**
     * `order`.third_party_pay_type (第三方支付方式 )
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "third_party_pay_type")
    private Integer thirdPartyPayType;


    /**
     * `order`.order_source (订单来源： 1pc , 2app , 3wap)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "order_source")
    private Integer orderSource;


    /**
     * `order`.fee (税费)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "fee")
    private BigDecimal fee;


    /**
     * `order`.discount_price (折扣价)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "discount_price")
    private BigDecimal discountPrice;


    /**
     * `order`.logistics_type (物流类型  0 普通仓配系统 ，1 第三方物流系统)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "logistics_type")
    private Long logisticsType;


    /**
     * `order`.kdniao_exp_code (快递鸟-物流公司编码)
     * @ibatorgenerated 2018-01-08 14:29:07
     */
    @Column(name = "kdniao_exp_code")
    private String kdniaoExpCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public Integer getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(Integer usedPoints) {
        this.usedPoints = usedPoints;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeIdcard() {
        return consigneeIdcard;
    }

    public void setConsigneeIdcard(String consigneeIdcard) {
        this.consigneeIdcard = consigneeIdcard;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public Integer getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(Integer deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(Integer shipStatus) {
        this.shipStatus = shipStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getAccPoints() {
        return accPoints;
    }

    public void setAccPoints(Long accPoints) {
        this.accPoints = accPoints;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(Date prepareTime) {
        this.prepareTime = prepareTime;
    }

    public Date getExtendTime() {
        return extendTime;
    }

    public void setExtendTime(Date extendTime) {
        this.extendTime = extendTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getParsetTime() {
        return parsetTime;
    }

    public void setParsetTime(Date parsetTime) {
        this.parsetTime = parsetTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getBusinessRemark() {
        return businessRemark;
    }

    public void setBusinessRemark(String businessRemark) {
        this.businessRemark = businessRemark;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTaxpayerNum() {
        return taxpayerNum;
    }

    public void setTaxpayerNum(String taxpayerNum) {
        this.taxpayerNum = taxpayerNum;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getThirdPartyPayType() {
        return thirdPartyPayType;
    }

    public void setThirdPartyPayType(Integer thirdPartyPayType) {
        this.thirdPartyPayType = thirdPartyPayType;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getLogisticsType() {
        return logisticsType;
    }

    public void setLogisticsType(Long logisticsType) {
        this.logisticsType = logisticsType;
    }

    public String getKdniaoExpCode() {
        return kdniaoExpCode;
    }

    public void setKdniaoExpCode(String kdniaoExpCode) {
        this.kdniaoExpCode = kdniaoExpCode;
    }
}