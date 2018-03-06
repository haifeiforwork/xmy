package com.zfj.xmy.pc.web.controller.commons;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.activity.service.pc.PcColumService;
import com.zfj.xmy.common.UUIDUtil;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.elasticsearch.document.GoodsDoc;
import com.zfj.xmy.elasticsearch.persistence.pojo.NativeSearchQueryParameter;
import com.zfj.xmy.elasticsearch.service.GoodsDocService;
import com.zfj.xmy.goods.service.pc.PcElasticGoodsService;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.service.pc.ShoppingCartService;
import com.zfj.xmy.order.service.pc.impl.PcShoppingCartServiceImpl;
import com.zfj.xmy.pc.web.Lijie;
import com.zfj.xmy.pc.web.common.SessionInfo;
import com.zfj.xmy.pc.web.controller.BaseController;
import com.zfj.xmy.user.service.common.OpenImService;
import com.zfj.xmy.util.DataPage;
@RestController
@RequestMapping("/commons")
public class CommonsController extends BaseController{
	
	@Autowired
	private PcColumService columService;
	
	@Autowired
	private PcShoppingCartServiceImpl cartServiceImpl;
	
	@Autowired
	private ShoppingCartService cartService;

	@Autowired
	private PcElasticGoodsService elasticGoodsService;
	
	@Autowired
	private OpenImService openImService;
	
	@RequestMapping("/findCommons")
	public ModelAndView findCommons(HttpServletRequest request){
		List<PcDownColumDto> findDownColum = columService.findDownColum(VocabularyConstant.VOC_COLUMN);
		request.getSession().setAttribute("findDownColum", findDownColum);
		return null;
	}
	
	@RequestMapping("/findCart")
	public ModelAndView findCart(HttpServletRequest request ){
		SessionInfo sessionInfo = SessionInfo.get();
		HttpSession session = request.getSession();
		session.removeAttribute("shoppingCart");
		session.removeAttribute("countCart");
		session.removeAttribute("sumPrice");
		BigDecimal sumPrice = new BigDecimal("0.00");//所有购物车商品总价
		int countCart = 0;//购物车商品总数
		List<PcGoodsDto> findUnLoadShoppingCart = null;
		//查询购物车信息
		if(ObjectUtils.isEmpty(sessionInfo)){
			findUnLoadShoppingCart = cartService.findUnLoadShoppingCart(null);
		}else{
			findUnLoadShoppingCart = cartServiceImpl.findShoppingCartByUserId(sessionInfo.getUserId());
		}
		//计算总价
		for (PcGoodsDto pcGoodsDto : findUnLoadShoppingCart) {
			if(pcGoodsDto.getSumPoints()==null||pcGoodsDto.getSumPoints()==0){
				sumPrice = sumPrice.add(pcGoodsDto.getSumPrice());
			}
		}
		countCart = findUnLoadShoppingCart.size();//购物车商品总页数
		session.setAttribute("shoppingCart", findUnLoadShoppingCart);
		session.setAttribute("countCart", countCart);
		session.setAttribute("sumPrice", sumPrice);
		request.setAttribute("shoppingCart", findUnLoadShoppingCart);
		request.setAttribute("countCart", countCart);
		request.setAttribute("sumPrice", sumPrice);
		return new ModelAndView("/commons/nav/top_cart_list");
	}
	
	@RequestMapping("cart/count")
	public Integer getCartCount(HttpServletRequest request){
		SessionInfo sessionInfo = SessionInfo.get();
		int countCart = 0;//购物车商品总数
		List<PcGoodsDto> findUnLoadShoppingCart = null;
		//查询购物车信息
		if(ObjectUtils.isEmpty(sessionInfo)){
			findUnLoadShoppingCart = cartService.findUnLoadShoppingCart(null);
		}else{
			findUnLoadShoppingCart = cartServiceImpl.findShoppingCartByUserId(sessionInfo.getUserId());
		}
		countCart = findUnLoadShoppingCart.size();//购物车商品总页数
		return countCart;
	}
	
	/**
	 * @return Integer 0：已登录 1：未登录
	 * @author lij
	 * @date 2017年8月25日 下午5:32:44
	 * 查询用户是否登录
	 */
	@RequestMapping("/user")
	public Integer findUserIsLoad(){
		Integer relust = 0;
		SessionInfo sessionInfo = SessionInfo.get();
		if(ObjectUtils.isEmpty(sessionInfo)){
			relust = 1;
		}
		return relust;
	}
	
	@RequestMapping("/findRightCart")
	public ModelAndView findRightCart(HttpServletRequest request ){
		SessionInfo sessionInfo = SessionInfo.get();
		HttpSession session = request.getSession();
		session.removeAttribute("shoppingCart");
		session.removeAttribute("countCart");
		session.removeAttribute("sumPrice");
		BigDecimal sumPrice = new BigDecimal("0.00");//所有购物车商品总价
		int countCart = 0;//购物车商品总数
		List<PcGoodsDto> findUnLoadShoppingCart = null;
		//查询购物车信息
		if(ObjectUtils.isEmpty(sessionInfo)){
			findUnLoadShoppingCart = cartService.findUnLoadShoppingCart(null);
		}else{
			findUnLoadShoppingCart = cartServiceImpl.findShoppingCartByUserId(sessionInfo.getUserId());
		}
		//计算总价
		for (PcGoodsDto pcGoodsDto : findUnLoadShoppingCart) {
			if(pcGoodsDto.getSumPoints()==null||pcGoodsDto.getSumPoints()==0){
				sumPrice = sumPrice.add(pcGoodsDto.getSumPrice());
			}
		}
		countCart = findUnLoadShoppingCart.size();//购物车商品总页数
		session.setAttribute("shoppingCart", findUnLoadShoppingCart);
		session.setAttribute("countCart", countCart);
		session.setAttribute("sumPrice", sumPrice);
		request.setAttribute("shoppingCart", findUnLoadShoppingCart);
		request.setAttribute("countCart", countCart);
		request.setAttribute("sumPrice", sumPrice);
		return new ModelAndView("/commons/nav/right_cart_list");
	}
	
	@RequestMapping("searchGoods")
	public ModelAndView searchGoods(@Param("goodsName") String goodsName,HttpServletRequest request){
		List<String> keysList = elasticGoodsService.searchTopGoods(goodsName);
		
		request.setAttribute("keysList", keysList);
		
		return new ModelAndView("/commons/nav/comm_search_list");
	}
	
	//获取在线客户的相关数据
	@RequestMapping("qianniu")
	@Lijie
	public Map<String,Object> testQianniu(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		SessionInfo sessionInfo = SessionInfo.get();
		Object users = openImService.getOpenImUsers(sessionInfo.getUserId());
		String appkey = openImService.getOpenImAppkey();
		String customid = openImService.getOpenImCustomid();
		map.put("users", users);
		map.put("appkey", appkey);
		map.put("customid", customid);
		return map;
	}
}
