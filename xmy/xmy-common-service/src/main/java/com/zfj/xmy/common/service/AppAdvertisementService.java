package com.zfj.xmy.common.service;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.app.AppAdInfoDto;
import com.zfj.xmy.common.persistence.pojo.app.AppAdvertisementOutDto;
import com.zfj.xmy.common.persistence.pojo.app.AppClassificationTopImgDto;

public interface AppAdvertisementService {

	/**
	 * 取出开抢啦的top图
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月8日下午1:50:07
	 */
	AppAdInfoDto getGrabTopImg();

	/**
	 * 取出分类页top图
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月8日下午3:24:32
	 */
	List<AppClassificationTopImgDto> getClassificationTopImgDto(Integer type);

	/**
	 * 获取广告列表
	 * @Description 
	 * @param type
	 * @return
	 * @Author liuw
	 * @Date 2017年8月9日下午3:03:16
	 */
	List<AppAdvertisementOutDto> getAdvertisement(Integer type,Integer positionId,Long categoryId);

}
