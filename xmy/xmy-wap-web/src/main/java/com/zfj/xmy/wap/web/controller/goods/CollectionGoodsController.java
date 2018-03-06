package com.zfj.xmy.wap.web.controller.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppCollectVo;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.CategoryAmountVo;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.GoodsVo;
import com.zfj.xmy.goods.service.app.AppGoodsService;
import com.zfj.xmy.goods.service.pc.PcGoodsService;
import com.zfj.xmy.goods.service.wap.WapCollectionGoodsService;
import com.zfj.xmy.goods.service.wap.WapGoodsService;
import com.zfj.xmy.order.persistence.app.pojo.dto.GuessLikeGoodsOutVO;
import com.zfj.xmy.order.service.wap.WapBrowsedGoodsService;
import com.zfj.xmy.wap.web.common.AjaxResult;
import com.zfj.xmy.wap.web.common.SessionInfo;
import com.zfj.xmy.wap.web.common.SessionUtils;
import com.zfj.xmy.wap.web.controller.BaseController;

@Controller
@RequestMapping("/collectionGoods")
public class CollectionGoodsController extends BaseController{
	
	@Autowired
	private WapCollectionGoodsService collectionGoodsService;
	
	@Autowired
	private WapBrowsedGoodsService browsedGoodsService;
	
	@Autowired
	private AppGoodsService appGoodsService;
	
	@Autowired
    private PcGoodsService goodsService;

	@Autowired
    private WapGoodsService wapGoodsService;
	
	/**
	 * 
	 * @Description 删除收藏夹商品数据
	 * @param collectionGoods
	 * @return
	 * @Author liuw
	 * @Date 2017年7月31日上午11:40:59
	 */
	@RequestMapping("/delCollectionGoods")
	@ResponseBody
	public AjaxResult delCollectionGoods(HttpServletRequest req, CollectionGoods collectionGoods){
		Long userId = SessionUtils.getUser(req).getId();
		collectionGoodsService.delAndCheckCollectionGoods(userId, collectionGoods.getId());
		return new AjaxResult(1, null, null);
	}
	
	@RequestMapping("/getGuessLike")
	@ResponseBody
	public AjaxResult getGuessLike(HttpServletRequest req) {
		Long id = SessionUtils.getUser(req).getId();
		try {
			//List<GuessLikeGoodsOutVO> result = browsedGoodsService.findGuessLikeGoods(id);
			List<Goods> result = wapGoodsService.roundGoods(8,null);
			//PageBean pageBean = new PageBean();
			//pageBean.setPageSize(3);
	        //List<Goods> yourLike = goodsService.findYouLike(pageBean);
			return new AjaxResult(1, null, result);
		} catch(Exception e) {
			e.printStackTrace();
			return new AjaxResult(0, null, null);
		}
	}
	
	@RequestMapping("/getCategorys")
	@ResponseBody
	public AjaxResult getCates(HttpServletRequest req) {
		Long id = SessionUtils.getUser(req).getId();
		try {
			List<CategoryAmountVo> categoryAndAmount = collectionGoodsService.getCategoryAndAmount(id);
			return new AjaxResult(1, null, categoryAndAmount);
		} catch(Exception e) {
			return new AjaxResult(0, null, null);
		}
	}
	
	@RequestMapping("/getGoods")
	@ResponseBody
	public AjaxResult getGoods(String goodsCategoryName, HttpServletRequest req) {
		Long id = SessionUtils.getUser(req).getId();
		try {
			List<GoodsVo> favorite = collectionGoodsService.getFavorite(id, goodsCategoryName);
			return new AjaxResult(1, null, favorite);
		} catch(Exception e) {
			e.printStackTrace();
			return new AjaxResult(-1, null, null);
		}
	}
	
	/**
	 * 收藏或取消收藏
	 * @param req
	 * @param resp
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/addOrCancelCollection")
	@ResponseBody
	public RespData addOrCancelCollection(HttpServletRequest req, HttpServletResponse resp, AppCollectVo vo){
		
		RespData respData = new RespData();
		Long userId = SessionInfo.get().getUserId();
		appGoodsService.updateCollect(vo,userId);
		respData.setResultCode(RespData.CODE_SUCCESS);
		
		return respData;
	}
	
//	/**
//	 *@author cj
//	 * @param req 
//	 * 获取分类和数量信息
//	 * 返回
//	 * @return
//	 */
//	/**
//	 * 
//	 * @Description 获取收藏夹分类数据（收藏夹中有哪些一级分类以及该分类的数量）
//	 * @param userId
//	 * @return
//	 * @Author liuw
//	 * @Date 2017年7月28日下午5:52:25
//	 */
//	@RequestMapping(value="/getCategorys",method=RequestMethod.POST)
//	public ResponseEntity<AppResp> getCollectionCategorys(HttpServletRequest req){
//		//获取userId
//		Long userId=SessionUtils.getUser(req).getId();
//		ReqData reqData=new ReqData();
//		reqData.putValue(SystemConstant.COLLECTIONGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
//		//1 查找所有商品
//		List<GoodsWithBLOBs> findCollectionGoods = collectionGoodsService.findCollectionGoods(reqData);
//		//2 查找商品对应的分类
//		 List<Map<String, Object>> findCollectionCategorys = termDataExService.findCollectionCategorys(findCollectionGoods);
//		 return AppResp.get(findCollectionCategorys);
//	}
//	
//	/**
//	 * 
//	 * @Description 获取收藏夹商品数据（可带条件查询分类下的收藏夹商品）
//	 * @param userId
//	 * @return
//	 * @Author liuw
//	 * @Date 2017年7月31日上午9:26:27
//	 */
//	@RequestMapping(value="/getGoods",method=RequestMethod.POST)
//	public ResponseEntity<AppResp> getCollectionGoods(@RequestBody CollectionGoods reqCollectionGoods, HttpServletRequest req){
//		//获取userId
//		Long userId=SessionUtils.getUser(req).getId();
//		ReqData reqData=new ReqData();
//		reqData.putValue(SystemConstant.COLLECTIONGOODS.GOODS_CATEGORY_NAME, reqCollectionGoods.getGoodsCategoryName(), SystemConstant.REQ_PARAMETER_CN);
//		reqData.putValue(SystemConstant.COLLECTIONGOODS.USER_ID, userId, SystemConstant.REQ_PARAMETER_EQ);
//		//1 查找所有商品
//		List<GoodsWithBLOBs> findCollectionGoods = collectionGoodsService.findCollectionGoods(reqData);
//		for (GoodsWithBLOBs goodsWithBLOBs : findCollectionGoods) {
//			Long activityId = goodsWithBLOBs.getActivityId();
//			Integer activityType = goodsWithBLOBs.getActivityType();
//			Long id = goodsWithBLOBs.getId();
//			//是否是活动商品
//			if(!ObjectUtils.isEmpty(activityId)&&!ObjectUtils.isEmpty(activityType))
//			{
//				AppActivityInfoDir activityInfo = appGoodsService.getActivityInfo(activityId, id, activityType);
//				goodsWithBLOBs.setActivityName(activityInfo.getActivityName());
//				goodsWithBLOBs.setLimitTotalNum(activityInfo.getLimitTotalNum());
//			}
//		}
//		 return AppResp.get(findCollectionGoods);
//	}
}
