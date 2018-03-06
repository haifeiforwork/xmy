package com.zfj.xmy.app.web.controller.goods;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.commons.mini.bean.ReqSecurityVo;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.base.encryption.DESUtil;
import com.zfj.base.util.common.StringUtil;
import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppActivityGoodsVo;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppCollectVo;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppIndexAdvertisementGoodsDto;
import com.zfj.xmy.goods.service.app.AppGoodsService;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppGoodsVo;
import com.zfj.xmy.redis.TokenManager;
@RestController
@RequestMapping(value="/goods",params=SystemConstant.MOBILE_VERSION_V10)
public class GoodsController extends BaseController {
	
	@Autowired
	private AppGoodsService appGoodsService;
	
	@Autowired
	private TokenManager tokenManager;
	
	/**
	 * 查询商品详细信息
	 * @param goodsId
	 * @return    
	 * @return ResponseEntity<AppGoodsDir>    
	 * Date:2017年7月28日 下午5:10:19 
	 * @author hexw
	 */
	@RequestMapping(value="/getGoods",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> getGoods(@RequestBody AppActivityGoodsVo vo){
		Long userId = null;
		if (!StringUtil.isEmpty(vo.getUserToken())) {
			 userId = Long.parseLong(tokenManager.get(vo.getUserToken()));
		}
		return AppResp.get(appGoodsService.getGoodsDetails(vo.getGoodsId(),vo.getActivityId(),vo.getActivityType(),userId)) ;
	}
	
	/**
	 * 收藏商品
	 * @param vo
	 * @return    
	 * @return ResponseEntity<AppResp>    
	 * Date:2017年8月24日 下午2:47:31 
	 * @author hexw
	 */
	@RequestMapping(value="/collectGoods",method=RequestMethod.POST)
	public ResponseEntity<AppResp> collectGoods(@RequestBody AppCollectVo vo){
		Long userId = AppLocalInfo.getUserId();
		appGoodsService.updateCollect(vo,userId);
		return AppResp.get();
	}
	
	/**
	 * 加密数据
	 * @param reqSecurityVo
	 * @return
	 */
	@RequestMapping(value="/secur/add",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<Goods> testAdd2(@RequestBody ReqSecurityVo reqSecurityVo){
		
		Goods goods = reqSecurityVo.get(Goods.class) ;
		
		return new ResponseEntity<Goods>(goods,HttpStatus.OK) ;
		//DESUtil
	}
	/**
	 * 获取首页一级分类以及对应的广告、商品
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月9日下午5:44:21
	 */
	@RequestMapping("/indexAdvertisementGoods")
	public ResponseEntity<AppResp> IndexAdvertisementGoods(){
		List<AppIndexAdvertisementGoodsDto> appIndexAdvertisementGoodsDto = appGoodsService.getAppIndexAdvertisementGoodsDto();
		return AppResp.get(appIndexAdvertisementGoodsDto);
	}
	
	
}
