package com.zfj.xmy.goods.service.cms;  

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.WatermarkImage;

/** 
 * @Title: WaterMarkService.java 
 * @Package com.zfj.xmy.goods.service 
 * @Description: 
 * @author hexw
 * @date 2017年6月20日 下午6:57:35 
 */
public interface WaterMarkService {
	
	/**
	 * 查询水印图片列表
	 * @return    
	 * @return List<WatermarkImage>    
	 * Date:2017年6月20日 下午7:03:01
	 */
	void findWatermarkList(PageBean pageBean,ReqData reqData);
	
	/**
	 * 批量删除图片
	 * @param idArry
	 * @return    
	 * @return int    
	 * Date:2017年6月21日 下午4:05:25
	 */
	int deleteImg(String[] idArry);
	
	/**
	 * 插入水印图片
	 * @param watermarkImage    
	 * @return void    
	 * Date:2017年7月14日 上午10:58:23
	 */
	void insertWatermarkImg(WatermarkImage watermarkImage);
	
	/**
	 * 水印图片列表
	 * @return    
	 * @return List<WatermarkImage>    
	 * Date:2017年7月14日 下午2:50:16
	 */
	void waterImgList(PageBean pageBean,ReqData reqData);

	


}
  