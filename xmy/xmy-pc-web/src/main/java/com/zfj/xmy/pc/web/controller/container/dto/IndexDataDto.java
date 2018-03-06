package com.zfj.xmy.pc.web.controller.container.dto;

import java.io.Serializable;
import java.util.List;

import com.zfj.xmy.activity.persistence.common.pojo.dto.LimitActivityDir;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcBuyandPresentDto;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcContainerDto;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcIndexDto;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.PcGoodsSeting;

public class IndexDataDto implements Serializable {

	/**
	 * session数据
	 */
	private indexSessionDto session;
	
	private LimitActivityDir iceActivity ;
	private LimitActivityDir daydayActivity;
	private LimitActivityDir weekActivity;
	
	private List<PcGoodsSeting> sealGoods;
	private List<AdImage> firstImage;
	
	private PcIndexDto findPcAd;
	private List<PcContainerDto> container;
	private List<PcBuyandPresentDto> buyAndPresentActivity;
	private List<PcGoodsSeting> hotGoods;
	private List<PcGoodsSeting> newGoods;
	
	
	public indexSessionDto getSession() {
		return session;
	}
	public void setSession(indexSessionDto session) {
		this.session = session;
	}
	public LimitActivityDir getIceActivity() {
		return iceActivity;
	}
	public void setIceActivity(LimitActivityDir iceActivity) {
		this.iceActivity = iceActivity;
	}
	public LimitActivityDir getDaydayActivity() {
		return daydayActivity;
	}
	public void setDaydayActivity(LimitActivityDir daydayActivity) {
		this.daydayActivity = daydayActivity;
	}
	public LimitActivityDir getWeekActivity() {
		return weekActivity;
	}
	public void setWeekActivity(LimitActivityDir weekActivity) {
		this.weekActivity = weekActivity;
	}
	public PcIndexDto getFindPcAd() {
		return findPcAd;
	}
	public void setFindPcAd(PcIndexDto findPcAd) {
		this.findPcAd = findPcAd;
	}
	public List<PcContainerDto> getContainer() {
		return container;
	}
	public void setContainer(List<PcContainerDto> container) {
		this.container = container;
	}
	public List<PcBuyandPresentDto> getBuyAndPresentActivity() {
		return buyAndPresentActivity;
	}
	public void setBuyAndPresentActivity(
			List<PcBuyandPresentDto> buyAndPresentActivity) {
		this.buyAndPresentActivity = buyAndPresentActivity;
	}
	public List<PcGoodsSeting> getHotGoods() {
		return hotGoods;
	}
	public void setHotGoods(List<PcGoodsSeting> hotGoods) {
		this.hotGoods = hotGoods;
	}
	public List<PcGoodsSeting> getNewGoods() {
		return newGoods;
	}
	public void setNewGoods(List<PcGoodsSeting> newGoods) {
		this.newGoods = newGoods;
	}
	public List<PcGoodsSeting> getSealGoods() {
		return sealGoods;
	}
	public void setSealGoods(List<PcGoodsSeting> sealGoods) {
		this.sealGoods = sealGoods;
	}
	public List<AdImage> getFirstImage() {
		return firstImage;
	}
	public void setFirstImage(List<AdImage> firstImage) {
		this.firstImage = firstImage;
	}
	
	
}
