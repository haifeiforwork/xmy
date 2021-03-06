package com.zfj.xmy.oa;  

import com.zfj.xmy.oa.dto.OaGoodsDto;

/** 
 * @Title: XmyOaGoodsService.java 
 * @Package com.zfj.xmy.oa 
 * @Description: 
 * @author hexw
 * @date 2018年1月15日 下午3:26:34 
 */
public interface XmyOaGoodsService {
	
	/**
	 * xmyOA系统商品调价
	 * @param dto    
	 * @return void    
	 * Date:2018年1月15日 下午3:36:05 
	 * @author hexw
	 */
	int xmyOaUpdateGoods(OaGoodsDto dto);

}
  