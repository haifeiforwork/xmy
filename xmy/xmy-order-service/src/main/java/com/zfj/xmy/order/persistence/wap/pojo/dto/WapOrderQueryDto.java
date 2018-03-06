package com.zfj.xmy.order.persistence.wap.pojo.dto;

import com.appdev.db.common.pojo.PageBean;

public class WapOrderQueryDto {
	
	private Long userId;
	
	private Integer status;
	
	private Integer startRow;
	
	private Integer endRow;
	
	
	public WapOrderQueryDto(){}

	/**
	 * @param userId
	 * @param status
	 * @param startRow
	 * @param endRow
	 */
	public WapOrderQueryDto(Long userId, Integer status, Integer startRow,
			Integer endRow) {
		super();
		this.userId = userId;
		this.status = status;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
