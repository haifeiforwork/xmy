package com.zfj.xmy.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.AdImageMapper;
import com.zfj.xmy.common.persistence.dao.AdInfoMapper;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.AdInfo;
import com.zfj.xmy.common.persistence.pojo.app.AppAdInfoDto;
import com.zfj.xmy.common.persistence.pojo.app.AppAdvertisementOutDto;
import com.zfj.xmy.common.persistence.pojo.app.AppClassificationTopImgDto;
import com.zfj.xmy.common.service.AppAdvertisementService;

@Service
public class AppAdvertisementServiceImpl implements AppAdvertisementService {
	
	@Autowired
	private AdInfoMapper adInfoMapper;
	@Autowired
	private AdImageMapper adImageMapper;
	
	@Override
	public AppAdInfoDto getGrabTopImg(){
		AppAdInfoDto adInfoDto=new AppAdInfoDto();
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		createCriteria.equalTo(SystemConstant.AdInfoImage.TYPE, SystemConstant.AdInfoImage.TYPE_OPENGRAB);
		List<AdInfo> selectByExample = adInfoMapper.selectByExample(para);
		//如果查询到有个多个开抢啦首页adinfo，那就取第一个(应该只有一个的)
		if(selectByExample.size()>0){
			AdInfo adInfo = selectByExample.get(0);
			BeanUtils.copyProperties(adInfo, adInfoDto);
		}
		Long adId = adInfoDto.getId();
		//取出所有对应ad_id的图片
		CriteriaParameter adImageCriteria=new CriteriaParameter();
		Criteria adImageCreateCriteria = adImageCriteria.createCriteria();
		adImageCreateCriteria.equalTo(SystemConstant.AdInfoImage.AD_ID, adId);
		adImageCreateCriteria.equalTo("img_type", SystemConstant.AdInfoImage.BIG_IMAGE);
		List<AdImage> selectByExample2 = adImageMapper.selectByExample(adImageCriteria);
		adInfoDto.setAdImages(selectByExample2);
		return adInfoDto;
	}
	@Override
	public List<AppClassificationTopImgDto> getClassificationTopImgDto(Integer type){
		List<AppClassificationTopImgDto> appClassificationTopImgDtos=new ArrayList<>();
		//1 取出adinfo表中代表分类页top图的
		CriteriaParameter para=new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		criteria.equalTo(SystemConstant.AdInfoImage.TYPE, type);
		List<AdInfo> adInfos = adInfoMapper.selectByExample(para);
		for (AdInfo adInfo : adInfos) {
			Long id = adInfo.getId();
			//2 根据取出来的ad_id再去ad_image表的取出对应的图片
			CriteriaParameter adImagepara=new CriteriaParameter();
			Criteria adImageCreateCriteria = adImagepara.createCriteria();
			adImageCreateCriteria.equalTo(SystemConstant.AdInfoImage.AD_ID, id);
			List<AdImage> adImages = adImageMapper.selectByExample(adImagepara);
			// 如果存在多张图片，就只取第一张
			if(adImages.size()>0){
				AppClassificationTopImgDto appClassificationTopImgDto=new AppClassificationTopImgDto();
				AdImage adImage = adImages.get(0);
				BeanUtils.copyProperties(adImage, appClassificationTopImgDto);
				BeanUtils.copyProperties(adInfo, appClassificationTopImgDto);
				appClassificationTopImgDtos.add(appClassificationTopImgDto);
			}
		}
		return appClassificationTopImgDtos;
	}
	@Override
	public List<AppAdvertisementOutDto> getAdvertisement(Integer type,Integer positionId,Long categoryId){
		List<AppAdvertisementOutDto> appClassificationTopImgDtos=new ArrayList<>();
		//1 取出adinfo表中代表分类页top图的
		CriteriaParameter para=new CriteriaParameter();
		Criteria criteria = para.createCriteria();
		criteria.equalTo(SystemConstant.AdInfoImage.TYPE, type);
		//判断是否传入了位置的id
		if(!ObjectUtils.isEmpty(positionId)){
			criteria.equalTo(SystemConstant.AdInfoImage.POSITION_ID, positionId);
		}
		if(!ObjectUtils.isEmpty(categoryId)){
			criteria.equalTo(SystemConstant.AdInfoImage.CATEGORY_ID, categoryId);
		}
		//判断是否传入了categoryId(分类id)
		List<AdInfo> adInfos = adInfoMapper.selectByExample(para);
		for (AdInfo adInfo : adInfos) {
			Long id = adInfo.getId();
			//2 根据取出来的ad_id再去ad_image表的取出对应的图片
			CriteriaParameter adImagepara=new CriteriaParameter();
			Criteria adImageCreateCriteria = adImagepara.createCriteria();
			adImageCreateCriteria.equalTo(SystemConstant.AdInfoImage.AD_ID, id);
			List<AdImage> adImages = adImageMapper.selectByExample(adImagepara);
			// 如果存在多张图片，就只取第一张
			AppAdvertisementOutDto appAdvertisementTopImgDto=new AppAdvertisementOutDto();
			BeanUtils.copyProperties(adInfo, appAdvertisementTopImgDto);
			appAdvertisementTopImgDto.setAdImages(adImages);
			appClassificationTopImgDtos.add(appAdvertisementTopImgDto);
		}
		return appClassificationTopImgDtos;
	}
}
