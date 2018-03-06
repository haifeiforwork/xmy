package com.zfj.xmy.order.service.cms;  

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.AdInfo;
import com.zfj.xmy.common.persistence.pojo.AdPosition;
import com.zfj.xmy.order.persistence.common.pojo.dto.AdInfoDto;

/** 
 * @Title: AdvertisementService.java 
 * @Package com.zfj.xmy.order.service 
 * @Description: 
 * @author hexw
 * @date 2017年7月12日 下午2:01:07 
 */
public interface AdvertisementService {
	
	/**
	 * 插入广告位置记录
	 * @param adPosition    
	 * @return void    
	 * Date:2017年7月12日 下午2:02:04
	 */
	void insertAdvertisementPosition(AdPosition adPosition);
	
	/**
	 * 查询广告位置集合
	 * @param reqData
	 * @return    
	 * @return List<AdPosition>    
	 * Date:2017年7月12日 下午2:17:39
	 */
	List<AdPosition> findAdPosition(ReqData reqData);
	
	/**
	 * 插入一条广告信息记录
	 * @param adInfo    
	 * @return void    
	 * Date:2017年7月12日 下午5:37:51
	 */
	void insertAdvertisement(AdInfo adInfo);
	
	/**
	 * 查询广告列表
	 * @param reqData
	 * @param pageBean    
	 * @return void    
	 * Date:2017年7月12日 下午7:13:17
	 */
	void findAdInfo(ReqData reqData, PageBean pageBean);
	
	/**
	 * 根据id得到广告
	 * @param reqData
	 * @return    
	 * @return AdInfoDto    
	 * Date:2017年7月12日 下午8:26:51
	 */
	AdInfoDto getAdInfo(String id);
	
	/**
	 * 查询广告图片
	 * @param reqData
	 * @return    
	 * @return List<AdImage>    
	 * Date:2017年7月12日 下午8:33:38
	 */
	List<AdImage> findAdImage(String aId);
	
	/**
	 * 修改广告
	 * @param adInfo    
	 * @return void    
	 * Date:2017年7月12日 下午9:02:45
	 */
	void updateAdInfo(AdInfo adInfo);
	/**
	 * 
	 * @Description 插入一条图片信息
	 * @param adImage
	 * @return
	 * @Author liuw
	 * @Date 2017年7月17日下午4:01:56
	 */
	int insertAdImage(AdImage adImage);
	/**
	 * 
	 * @Description 根据ad_id从ad_image中查询出单条记录（APP端类别Top图）
	 * @param aId
	 * @return
	 * @Author liuw
	 * @Date 2017年7月17日下午4:39:26
	 */
	AdImage getAdImageByAdId(Long aId);
	/**
	 * 
	 * @Description 查询adInfo列表（不分页）
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月17日下午5:04:59
	 */
	List<AdInfo> findAdInfo(ReqData reqData);


	/**
	 * 
	 * @Description 根据ID删除AdInfo信息表
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月17日下午8:08:24
	 */
	int deleteAdInfo(Long id);
	/**
	 * 
	 * @Description 根据条件取出adInfo和adimage扩展
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月17日下午9:04:37
	 */
	AdInfoDto getAdInfoImage(ReqData reqData);

	/**
	 * 
	 * @Description 更新adInfo和adiamge的信息
	 * @param adInfo
	 * @param adImage
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日上午9:44:11
	 */
	int updateAdInfoImage(AdInfo adInfo, AdImage adImage);
	
	/**
	 * 上传多张商品图片
	 * @param adId
	 * @param imgPath
	 * @param imgSrc    
	 * @return void    
	 * Date:2017年7月18日 上午10:33:22
	 */
	void batchInsertAdImage(long adId, String[] imgPath, String[] imgSrc,String[] imgWeight, String[] imgType);

	/**
	 * 
	 * @Description 根据ID删除adImage
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日下午3:50:26
	 */
	int deleteAdImage(Long id);

	/**
	 * 
	 * @Description 根据Id查询adImage
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日下午4:16:11
	 */
	AdImage getAdImageById(Long id);

	/**
	 * 
	 * @Description 更新单条adImage
	 * @param adImage
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日下午4:47:04
	 */
	int updateAdImage(AdImage adImage);

	
	
}
  