package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class UserAddrees implements Serializable {

    /**
     * user_addrees.id
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "id")
    private Long id;


    /**
     * user_addrees.user_id (用户id)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * user_addrees.name (收货(配送)人姓名)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "name")
    private String name;


    /**
     * user_addrees.mobile_phone (收货(配送)人联系方式)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;


    /**
     * user_addrees.phone (备用联系方式)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "phone")
    private String phone;


    /**
     * user_addrees.province (收货(配送)人所属省)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "province")
    private String province;


    /**
     * user_addrees.city (收货(配送)人所属市)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "city")
    private String city;


    /**
     * user_addrees.area (收货(配送)人所属区)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "area")
    private String area;


    /**
     * user_addrees.province_code
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "province_code")
    private String provinceCode;


    /**
     * user_addrees.city_code
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "city_code")
    private String cityCode;


    /**
     * user_addrees.area_code
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "area_code")
    private String areaCode;


    /**
     * user_addrees.address (收货(配送)人详细地址)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "address")
    private String address;


    /**
     * user_addrees.is_default (是否为默认收货地址：0是 1.否)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "is_default")
    private Integer isDefault;


    /**
     * user_addrees.type (0:用户收货信息 1：商家配送人信息)
     * @ibatorgenerated 2017-06-09 15:01:29
     */
    @Column(name = "type")
    private Integer type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}