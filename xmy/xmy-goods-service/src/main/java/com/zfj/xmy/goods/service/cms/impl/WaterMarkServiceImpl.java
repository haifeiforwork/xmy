package com.zfj.xmy.goods.service.cms.impl;  

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.WatermarkImageMapper;
import com.zfj.xmy.common.persistence.pojo.WatermarkImage;
import com.zfj.xmy.goods.service.cms.WaterMarkService;

/** 
 * @Title: WaterMarkServiceImpl.java 
 * @Package com.zfj.xmy.goods.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年6月20日 下午6:58:03 
 */
@Service
public class WaterMarkServiceImpl extends BaseService implements WaterMarkService{

	@Autowired
	private WatermarkImageMapper waterMarkImgMapper;
	
	@Override
	public void insertWatermarkImg(WatermarkImage watermarkImage){
		watermarkImage.setCreateTime(new Date());
		waterMarkImgMapper.insert(watermarkImage);
	}
	
	@Override
	public void findWatermarkList(PageBean pageBean,ReqData reqData){
		List<WatermarkImage> list = waterMarkImgMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(list);
		pageBean.setTotalCount(waterMarkImgMapper.countByExample(null));
	}
	
	@Override
	public int deleteImg(String[] idArry){
		int status = 0;
		for (String id : idArry) {
			status = status+waterMarkImgMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		return status;
	}
	
	@Override
	public void waterImgList(PageBean pageBean,ReqData reqData){
		List<WatermarkImage> list = waterMarkImgMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setTotalCount(waterMarkImgMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
		pageBean.setData(list);
	}
}
  