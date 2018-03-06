package com.zfj.xmy.order.service.cms.impl;  

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.AdImageMapper;
import com.zfj.xmy.common.persistence.dao.AdInfoMapper;
import com.zfj.xmy.common.persistence.dao.AdPositionMapper;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.AdInfo;
import com.zfj.xmy.common.persistence.pojo.AdPosition;
import com.zfj.xmy.order.persistence.common.dao.AdInfoExMapper;
import com.zfj.xmy.order.persistence.common.pojo.dto.AdInfoDto;
import com.zfj.xmy.order.service.cms.AdvertisementService;

/** 
 * @Title: AdvertisementServiceImpl.java 
 * @Package com.zfj.xmy.order.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年7月12日 下午2:03:22 
 */
@Service
public class AdvertisementServiceImpl extends BaseService implements AdvertisementService  {
	
	@Autowired
	private AdPositionMapper adPositionMapper;
	
	@Autowired
	private AdInfoMapper adInfoMapper;
	
	@Autowired
	private AdInfoExMapper adInfoExMapper;
	
	@Autowired
	private AdImageMapper adImageMapper;
	
	@Override
	public void insertAdvertisementPosition(AdPosition adPosition ) {
		adPosition.setStatus(0);
		adPosition.setCreateTime(new Date());
		adPositionMapper.insert(adPosition);
		
		
	}
	
	@Override
	public List<AdPosition> findAdPosition(ReqData reqData){
		List<AdPosition> list = adPositionMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	
	@Override
	public void insertAdvertisement(AdInfo adInfo){
		adInfo.setStatus(0);
		adInfo.setCreateTime(new Date());
		adInfo.setUpdateTime(new Date());
		adInfoMapper.insert(adInfo);
	}
	
	@Override
	public void findAdInfo(ReqData reqData, PageBean pageBean){
		ReqData lastReqData=reqData;
		pageBean.setTotalCount(adInfoMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(lastReqData)));
		String value = (String) reqData.getValue("name");
		if(!StringUtil.isEmpty(value)){
			reqData.removeValue("name");
			reqData.putValue("a.name", value, SystemConstant.REQ_PARAMETER_CN);
		}
		List<AdInfoDto> list = adInfoExMapper.findAdInfo(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(list) ;
		
	}
	@Override
	public List<AdInfo> findAdInfo(ReqData reqData)
	{
		List<AdInfo> selectByExample = adInfoMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}
	@Override
	public AdInfoDto getAdInfo(String id){
		ReqData reqData = new  ReqData();
		reqData.putValue("a.id", id, SystemConstant.REQ_PARAMETER_EQ);
		AdInfoDto adInfoDto = adInfoExMapper.getAdInfo(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return adInfoDto;
	}
	
	@Override
	public List<AdImage> findAdImage(String aId){
		ReqData reqData = new  ReqData();
		reqData.putValue("ad_id", aId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("weight");
		reqData.setSortorder("asc");
		List<AdImage> list = adImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return list;
	}
	
	@Override
	public AdImage getAdImageByAdId(Long aId){
		ReqData reqData = new  ReqData();
		AdImage adImage=null;
		reqData.putValue("ad_id", aId, SystemConstant.REQ_PARAMETER_EQ);
		List<AdImage> selectByExample = adImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if(selectByExample.size()>0)
			adImage =selectByExample.get(0);
		return adImage;
	}
	@Override
	public AdImage getAdImageById(Long id){
		AdImage selectByPrimaryKey = adImageMapper.selectByPrimaryKey(id);
	
		return selectByPrimaryKey;
	}

	@Override
	public void updateAdInfo(AdInfo adInfo ){
		adInfo.setUpdateTime(new Date());
		adInfoMapper.updateByPrimaryKey(adInfo);
		ReqData reqData = new ReqData();
		reqData.putValue("ad_id", adInfo.getId(), SystemConstant.REQ_PARAMETER_EQ);
		adImageMapper.deleteByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)); 
	}

	@Override
	public int insertAdImage(AdImage adImage) {
		adImage.setCreateTime(new Date());
		int insert = adImageMapper.insert(adImage);
		return insert;
	}

	
	/**
	 * 添加多张广告图片
	 * @param adId
	 * @param imgPath
	 * @param imgSrc    
	 * @return void    
	 * Date:2017年7月18日 上午9:27:42
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void batchInsertAdImage(long adId, String[] imgPath ,String[] imgSrc,String[] imgWeight,String[] imgType){
		if (ObjectUtil.isNotNull(imgPath) && ObjectUtil.isNotNull(imgSrc) && ObjectUtil.isNotNull(imgWeight) && ObjectUtil.isNotNull(imgType)){
			 if (imgPath.length == imgSrc.length && imgPath.length == imgWeight.length && imgPath.length == imgType.length) {
				 for (int i = 0; i < imgPath.length; i++) {
						if (!StringUtil.isEmpty(imgPath[i]) && !StringUtil.isEmpty(imgType[i]) ) {
						 	AdImage adImage = new AdImage();
							adImage.setAdId(adId);
							adImage.setImgPath(imgPath[i]);
							adImage.setData(imgSrc[i]);
							adImage.setCreateTime(new Date());
							adImage.setWeight(i);
							adImage.setType(Integer.parseInt(imgType[i]));
							adImageMapper.insert(adImage);
						}
					}
			 }
		}
	}
	
	@Override
	public int deleteAdInfo(Long id) {
		int deleteByPrimaryKey = adInfoMapper.deleteByPrimaryKey(id);
		return deleteByPrimaryKey;
	}

	@Override
	public AdInfoDto getAdInfoImage(ReqData reqData) {
		AdInfoDto adInfoImage = adInfoExMapper.getAdInfoImage(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return adInfoImage;
	}
	@Override
	public int updateAdInfoImage(AdInfo adInfo,AdImage adImage)
	{
		int adInfoUpdate = adInfoMapper.updateByPrimaryKeySelective(adInfo);
		int adImageUpdate = adImageMapper.updateByPrimaryKeySelective(adImage);
		return adInfoUpdate==adImageUpdate?(adInfoUpdate+adImageUpdate)/2:0;
	}
	@Override
	public int updateAdImage(AdImage adImage)
	{
		int adImageUpdate = adImageMapper.updateByPrimaryKeySelective(adImage);
		return adImageUpdate;
	}
	@Override
	public int deleteAdImage(Long id){
		int deleteByPrimaryKey = adImageMapper.deleteByPrimaryKey(id);
		return deleteByPrimaryKey;
	}
}
  