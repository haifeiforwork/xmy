package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;

import javax.persistence.Column;

public class PointTactics implements Serializable{
	
	private static final long serialVersionUID = 8931353796558758860L;

	/**
     * point_tactics.id
     * @ibatorgenerated 2017-07-03 17:46:44
     */
    @Column(name = "id")
	private Long id;
    
    /**
     * point_tactics.name
     * @ibatorgenerated 2017-07-03 17:46:44
     */
    @Column(name = "name")
	private String name;
    
    /**
     * point_tactics.type
     * @ibatorgenerated 2017-07-03 17:46:44
     */
    @Column(name = "type")
	private int type;
    
    /**
     * point_tactics.unit
     * @ibatorgenerated 2017-07-03 17:46:44
     */
    @Column(name = "unit")
	private int unit;
    
    /**
     * point_tactics.value
     * @ibatorgenerated 2017-07-03 17:46:44
     */
    @Column(name = "value")
	private int value;
    
    /**
     * point_tactics.status
     * @ibatorgenerated 2017-07-03 17:46:44
     */
    @Column(name = "status")
	private int status;

	public PointTactics() {
		super();
	}

	public PointTactics(Long id, String name, int type, int unit, int value,
			int status) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.unit = unit;
		this.value = value;
		this.status = status;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PointTactics [id=" + id + ", name=" + name + ", type=" + type
				+ ", unit=" + unit + ", value=" + value + ", status=" + status
				+ "]";
	}
    
}
