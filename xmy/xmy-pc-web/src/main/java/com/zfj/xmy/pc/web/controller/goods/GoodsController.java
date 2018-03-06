package com.zfj.xmy.pc.web.controller.goods;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.BrowsedGoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Comment;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcOrderDto;
import com.zfj.xmy.goods.service.pc.PcGoodsService;
import com.zfj.xmy.order.service.pc.PcShoppingCartService;
import com.zfj.xmy.pc.web.common.SessionInfo;
import com.zfj.xmy.pc.web.controller.center.UserCenterController;
import com.zfj.xmy.user.service.pc.UserCenterService;

@RestController
@RequestMapping("/goods")
public class GoodsController{
	
	@Autowired
	private PcGoodsService goodsService;
	
	@Autowired
	private UserCenterService userCenterService;
	
	@Autowired
	private CommonLimitActivityService commonLimitActivityService;
	
	@Autowired
	private PcShoppingCartService pcShoppingCartService;
	
	/**
	 * @param goodsId
	 * @param typeId 活动类型  
	 * @param activityId 活动id  0 买即赠 2 限时限量活动
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月1日 下午2:40:02
	 * 查询商品详情
	 */
	@RequestMapping("/{goodsId}/{typeId}/{activityId}")
	public ModelAndView getGoodsDetail(@PathVariable("goodsId") Long goodsId,@PathVariable("typeId") Integer typeId,
			@PathVariable("activityId") Long activityId, HttpServletRequest request){
		PageBean pageBean = new PageBean();
		SessionInfo sessionInfo=SessionInfo.get();
		Integer isCollection = 0; //是否收藏过该商品  0 否 1是
		if(sessionInfo!=null){
			isCollection = pcShoppingCartService.findCollectinoGoodsIsDel(sessionInfo.getUserId(),goodsId);
			userCenterService.addFootprint(sessionInfo.getUserId(), goodsId);
			request.setAttribute("isLogin", 0);
		} else {
			request.setAttribute("isLogin", 1);
		}
		//1.查询商品详细信息
		PcGoodsDto goodsDetail = goodsService.getGoodsDetail(goodsId,typeId,activityId);
		//2.查询相关的分类名称
		List<TermData> findGoodsLikeTtype = goodsService.findGoodsLikeTtype(goodsId);
		//3.查询全部商品销售的前2位
		List<Goods> findAllSealTopGoods = goodsService.findAllSealTopGoods();
		
		//4.查询分类商品销售的前2位
		List<Goods> findTypeSealTopGoods = goodsService.findTypeSealTopGoods(goodsId);
		
		//5.查询最新上架商品
		pageBean.setPageSize(4);
		List<Goods> newPutwayGoods = goodsService.findLike(pageBean);
		
		//6.猜你喜欢的商品
		pageBean.setPageSize(3);
		List<Goods> yourLike = goodsService.findLike(pageBean);
		
		//7.滑动商品
		pageBean.setPageSize(8);
		List<Goods> huaGoods = goodsService.findLike(pageBean);
		
		//8.看了又看
		pageBean.setPageSize(3);
		List<Goods> kGoods = goodsService.findLike(pageBean);
		
		request.setAttribute("kGoods", kGoods);
		request.setAttribute("huaGoods", huaGoods);
		request.setAttribute("yourLike", yourLike);
		request.setAttribute("newPutwayGoods", newPutwayGoods);
		request.setAttribute("typeSealGoods", findTypeSealTopGoods);
		request.setAttribute("allSealGoods", findAllSealTopGoods);
		request.setAttribute("typeNameList", findGoodsLikeTtype);
		request.setAttribute("goods", goodsDetail);
		request.setAttribute("activityId", activityId);
		request.setAttribute("typeId", typeId);
		request.setAttribute("isCollection", isCollection);
		request.setAttribute("time", new Date());
		if(SystemConstant.GoodsConstant.NOT_PUTWAY.equals(goodsDetail.getIsPutway())){
			return new ModelAndView("resource/commons/error/404.jsp");
		}else{
			return new ModelAndView("/goods/goods_detail");
		}
		
	}
	/**
	 * @param goodsId
	 * @param pageIndex
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月1日 下午2:40:14
	 * 分页查询商品的的全部评论
	 */
	@RequestMapping("/comment/list/{goodsId}/{pageIndex}")
	public ModelAndView findCommentList(@PathVariable("goodsId") Long goodsId,@PathVariable Integer pageIndex,
			HttpServletRequest request){
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(10);
		List<Comment> goodsComment = goodsService.findCommentByGoodsId(goodsId, pageBean);
		request.setAttribute("commentList", goodsComment);
		request.setAttribute("pageIndex", pageIndex);
		return new ModelAndView("/goods/comment_list");
	}
	/**
	 * @param goodsId
	 * @param pageIndex
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月1日 下午4:00:06
	 * 查询商品的销售记录
	 */
	@RequestMapping("/deal/list/{goodsId}/{pageIndex}")
	public ModelAndView findDealList(@PathVariable("goodsId") Long goodsId,@PathVariable Integer pageIndex,
			HttpServletRequest request){
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(10);
		List<PcOrderDto> findNewDealByGoodsId = goodsService.findNewDealByGoodsId(goodsId, pageBean);
		for(PcOrderDto dto:findNewDealByGoodsId){
			StringBuffer name=new StringBuffer(dto.getPaymentName());
			if(name.length()==11){
				name=new StringBuffer(name.substring(0, 3));
				name.append("********");
				dto.setPaymentName(name+"");
			}
			
		}
		request.setAttribute("dealList", findNewDealByGoodsId);
		request.setAttribute("pageIndex", pageIndex);
		return new ModelAndView("/goods/deal_list");
	}
	
	@RequestMapping("/test")
	public List<Goods> getsss(){
		
		List<Goods> findTypeSealTopGoods = goodsService.findTypeSealTopGoods(11);
		
		return findTypeSealTopGoods;
	}
	
	/**
	 * 判断是否符合活动规则
	 * @return    
	 * @return RespData    
	 * Date:2017年10月18日 下午5:28:41 
	 * @author hexw
	 */
	@RequestMapping("/checkActivityGoods")
	public RespData checkActivityGoods(ShoppingCart shoppingCart){
		RespData respData = new RespData();
		int result = 0;
		SessionInfo sessionInfo=SessionInfo.get();
		if (ObjectUtil.isNotNull(sessionInfo)) {
			shoppingCart.setUserId(sessionInfo.getUserId());
			result = commonLimitActivityService.checkLimitActivity(shoppingCart);
			respData.setData(result);
		}
		return respData;
		
	}
	
}
