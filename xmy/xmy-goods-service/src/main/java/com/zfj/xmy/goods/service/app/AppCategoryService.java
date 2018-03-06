package com.zfj.xmy.goods.service.app;  

import java.util.List;

import com.zfj.xmy.goods.persistence.app.pojo.dto.AppAdImageDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppEnterpriseDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppTermDataDir;

/** 
 * @Title: CategoryService.java 
 * @Package com.zfj.xmy.goods.service.app 
 * @Description: 
 * @author hexw
 * @date 2017年7月21日 上午9:44:41 
 */
public interface AppCategoryService {
	
	/**
	 * 查询分类
	 * @return    
	 * @return List<AppTermDataDir>    
	 * Date:2017年7月21日 下午4:08:27 
	 * @author hexw
	 */
	List<AppTermDataDir> findCategory();
	
	/**
	 * 查询商品栏目
	 * @param identifying
	 * @return    
	 * @return List<AppTermDataDir>    
	 * Date:2017年7月25日 上午9:37:13 
	 * @author hexw
	 */
	List<AppTermDataDir> findGoodsPrograma(String identifying);
	
	/**
	 * 企业定制
	 * @param obj
	 * @return    
	 * @return List<AppGoodsDir>    
	 * Date:2017年7月26日 上午9:29:16 
	 * @author hexw
	 */
	List<AppEnterpriseDir> findEnterprise();
	
	/**
	 * 查找分类轮播图
	 * @param id
	 * @return
	 */
	List<AppAdImageDir> findCategoryTopImg(long id);
	
	/**
	 * 查询企业定制商品第一张图片
	 * @param goodsId
	 * @return    
	 * @return String    
	 * Date:2017年9月29日 下午3:47:28 
	 * @author hexw
	 */
	String getCustomizationImage(long goodsId);

}
  