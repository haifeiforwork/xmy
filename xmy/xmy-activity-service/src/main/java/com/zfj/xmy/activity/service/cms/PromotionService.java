package com.zfj.xmy.activity.service.cms;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PromotionGoodsDto;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.PromotionActivity;

/** 
 * @Title: PromotionService.java 
 * @Package package com.zfj.xmy.activity.service
 * @Description: 
 * @author zhangh
 * @date 2017年7月10日 下午7:26:21 
 */
public interface PromotionService {
	
	/**
	 * 查询商品
	 * @param idStr
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年7月10日 下午7:49:52
	 */
	List<Goods> findGoods(String idStr);
	
	/**
	 * 添加一个促销活动
	 * @param promotionActivity
	 * @return    
	 * @return int    
	 * Date:2017年7月10日 下午7:50:38
	 */
	Long insertPromotion(PromotionActivity promotionActivity);
	
	/**
	 * 添加促销商品
	 * @param proId
	 * @param proGoods    
	 * @return void    
	 * Date:2017年7月10日 下午7:55:51
	 */
	void addPromotionGoods(String proId,String proGoods);
	
	/**
	 * 添加分类商品
	 * @param proId
	 * @param codeIds    
	 * @return void    
	 * Date:2017年7月10日 下午7:55:51
	 */
	void addCodeGoods(String proId,String codeIds);
	
	/**
	 * 添加供应商商品
	 * @param proId
	 * @param supIds    
	 * @return void    
	 * Date:2017年7月19日 下午7:55:51
	 */
	void addSupGoods(String proId,String supIds);
	
	/**
	 * 查询促销列表
	 * @param reqData
	 * @param pageBean    
	 * @return void    
	 * Date:2017年7月10日 下午7:13:52
	 */
	void selectPromotionList(ReqData reqData, PageBean pageBean);
	
	/**
	 * 删除促销
	 * @param id
	 * @return    
	 * @return String    
	 * Date:2017年7月10日 下午7:54:36
	 */
	int delPromotion(String id);
	
	/**
	 * 根据id查询专题促销
	 * @param id
	 * @return    
	 * @return PromotionActivity    
	 * Date:2017年7月10日 下午8:31:34
	 */
	PromotionActivity getPromotionActivity(String id);
	
	/**
	 * 查询专题促销商品
	 * @param id
	 * @return    
	 * @return List<PromotionGoodsDto>    
	 * Date:2017年7月11日 上午10:09:33
	 */
	List<PromotionGoodsDto> selectProGoodsByActId(String id);
	
	/**
	 * 修改专题促销
	 * @param promotionActivity
	 * @return    
	 * @return long    
	 * Date:2017年7月11日 上午11:01:37
	 */
	long updatePromotion(PromotionActivity promotionActivity);
	
	/**
	 * 移除促销商品
	 * @param goodsId
	 * @param actId    
	 * @return void    
	 * Date:2017年7月11日 下午2:31:02
	 */
	void delPromotionGoods(String goodsId, String proId);
}
