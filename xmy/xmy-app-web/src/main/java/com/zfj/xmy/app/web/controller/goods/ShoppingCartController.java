package com.zfj.xmy.app.web.controller.goods;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.commons.mini.bean.ReqSecurityVo;
import com.zfj.xmy.activity.service.common.LimitActivityService;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppShoppingCartDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppShoppingCartSecrityInDto;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppShoppingInCartDto;
import com.zfj.xmy.goods.service.app.AppShoppingCartService;

/** 
 * @Title: ShoppingCartController.java 
 * @Package com.zfj.xmy.app.web.controller.goods 
 * @Description: 
 * @author hexw
 * @date 2017年7月28日 下午3:56:28 
 */
@RequestMapping(value="/shoppingCart",params=SystemConstant.MOBILE_VERSION_V10)
@RestController
public class ShoppingCartController extends BaseController {
	
	@Autowired
	private AppShoppingCartService appShoppingCartService;
	
	@Autowired
	private LimitActivityService limitActivityService;
	
	/**
	 * 新增商品到购物车（登录状态下）
	 * @param shoppingCart
	 * @return    
	 * @return ResponseEntity<String>    
	 * Date:2017年7月28日 下午4:03:05 
	 * @author hexw
	 */
	@RequestMapping(value="/addGoodsToCart",method=RequestMethod.POST)
	public ResponseEntity<AppResp> addGoodsToCart(@RequestBody List<ShoppingCart> list ){
		appShoppingCartService.addGoodsToShoppingCart(list,AppLocalInfo.getUserId());
		return AppResp.get();
	}
	
	/**
	 * 添加商品到购物车 未登录
	 * @param shoppingCart
	 * @return
	 * @Description 
	 * @date 2017年8月4日  上午10:23:45
	 * @author wy
	 * 2017
	 * @return ResponseEntity<String>
	 */
	/*@RequestMapping(value="/addGoodsToCartNoLogin",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<String> addGoodsToCartNoLogin(@RequestBody ShoppingCart shoppingCart){
		//1.根据 dev_id 生成用户 并返回匿名用户信息
		
		shoppingCart.setUserId(AppLocalInfo.getUserId());
		
		
		appShoppingCartService.addGoodsToShoppingCartNoLogin(shoppingCart);
		ResponseEntity<String> resp=new ResponseEntity<String>(HttpStatus.OK);
		return resp;
	}*/
	
	
	/**
	 * 购物车查询
	 * @return    
	 * @return ResponseEntity<List<AppShoppingCartDir>>    
	 * Date:2017年7月28日 下午4:06:28 
	 * @author hexw
	 */
	@RequestMapping(value="/findGoodsCart",method=RequestMethod.POST)
	public ResponseEntity<AppResp> findGoodsCart(){
		long userId = AppLocalInfo.getUserId(); 
		return AppResp.get(appShoppingCartService.findShoppingCarts(userId)) ;
	}
	
	
	/**
	 * 从购物车删除商品
	 * @param id
	 * @return 
	 * @return ResponseEntity<String>    
	 * Date:2017年7月28日 下午4:18:32 
	 * @author hexw
	 */
	@RequestMapping(value="/deleteGoodFromCart",method=RequestMethod.POST)
	public ResponseEntity<AppResp> deleteGoodFromCart(@RequestBody List<AppShoppingInCartDto> list ){
		long userId = AppLocalInfo.getUserId(); 
		appShoppingCartService.deleteShoppingCartGoods(userId, list);
		return AppResp.get() ;
	}
	
	/**
	 * 修改购物车商品数量
	 * @param goodId
	 * @param num
	 * @return    
	 * @return ResponseEntity<AppResp>    
	 * Date:2017年8月15日 下午4:27:26 
	 * @author hexw
	 */
	@RequestMapping(value="/updateGoodsNum",method=RequestMethod.POST)
	public ResponseEntity<AppResp> updateGoodsNum(@RequestBody AppShoppingInCartDto dto){
		long userId = AppLocalInfo.getUserId();
		appShoppingCartService.updateGoodsNum(dto.getGoodsId(), userId, dto.getNum());
		return AppResp.get() ;
	}
	
}
  