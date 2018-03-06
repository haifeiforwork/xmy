package com.zfj.xmy.order.persistence.common.pojo.dto;

import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.AdInfo;

public class AdInfoImage {
	private AdInfo adInfo;
	private AdImage adImage;
	public AdInfo getAdInfo() {
		return adInfo;
	}
	public void setAdInfo(AdInfo adInfo) {
		this.adInfo = adInfo;
	}
	public AdImage getAdImage() {
		return adImage;
	}
	public void setAdImage(AdImage adImage) {
		this.adImage = adImage;
	}
}
