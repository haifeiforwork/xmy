package com.zfj.xmy.pc.web.controller.container.dto;

import java.io.Serializable;
import java.util.List;

import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.common.persistence.pojo.Notification;

public class indexSessionDto implements Serializable{

	private List<Notification> notifications;
	
	private List<String> keyWords;
	
	private  List<PcDownColumDto> findDownColum;
	
	private  List<PcDownColumDto> goodsNavigated;

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<String> keyWords) {
		this.keyWords = keyWords;
	}

	public List<PcDownColumDto> getFindDownColum() {
		return findDownColum;
	}

	public void setFindDownColum(List<PcDownColumDto> findDownColum) {
		this.findDownColum = findDownColum;
	}

	public List<PcDownColumDto> getGoodsNavigated() {
		return goodsNavigated;
	}

	public void setGoodsNavigated(List<PcDownColumDto> goodsNavigated) {
		this.goodsNavigated = goodsNavigated;
	}
	
	
}
