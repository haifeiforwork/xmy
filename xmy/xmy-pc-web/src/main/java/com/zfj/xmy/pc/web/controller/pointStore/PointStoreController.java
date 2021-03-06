package com.zfj.xmy.pc.web.controller.pointStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcPointsGoodsDto;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PointsActivityDto;
import com.zfj.xmy.activity.service.pc.PcPointsStoreService;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.goods.service.pc.PcCategoryService;
import com.zfj.xmy.goods.service.pc.PcGoodsService;
import com.zfj.xmy.pc.web.common.SessionInfo;
import com.zfj.xmy.user.service.pc.UserCenterService;

@RestController
@RequestMapping("/point")
public class PointStoreController {
	
	@Autowired
	private PcPointsStoreService pointsStoreService;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private PcGoodsService goodsService;
	
	@Autowired
	private UserCenterService userCenterService;
	
	@Autowired
	private PcCategoryService pcCategoryService;  
	
	@Autowired
	private PcGoodsService pcGoodsService;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		
		return new ModelAndView("/pointStore/point_store") ;
	}
	
	/**
	 * @param request
	 * @return ModelAndView
	 * @author zhagh
	 * @date 2017年8月1日 下午2:40:02
	 * 积分商城
	 */
	@RequestMapping("/pointStore")
	public ModelAndView pointStore(HttpServletRequest request){
		Map<PointsActivity, List<PcPointsGoodsDto>> map=new HashMap<PointsActivity, List<PcPointsGoodsDto>>();
		List<PointsActivity> pointsActivities=pointsStoreService.findPointsActivity();
		
		List<PointsActivityDto> activityDtos=new ArrayList<PointsActivityDto>();
		for(PointsActivity pointsActivitiy:pointsActivities){
			List<PcPointsGoodsDto> pointsGoodsDtos=pointsStoreService.findPointsGoodsDto(pointsActivitiy.getId());
			if(!pointsGoodsDtos.isEmpty()){
				PointsActivityDto activityDto=new PointsActivityDto();
				BeanUtils.copyProperties(pointsActivitiy, activityDto);
				activityDto.setPcPointsGoodsDtos(pointsGoodsDtos);
				activityDtos.add(activityDto);
			}
		}
		List<AdImage> topImg = pcCategoryService.findPointsAd("积分商城");
		SessionInfo sessionInfo=SessionInfo.get();
		UserInfo userInfo=null;
		int status=-1;
		if(sessionInfo!=null){
			userInfo=userInfoMapper.selectByPrimaryKey(sessionInfo.getUserId());
			status=pointsStoreService.findPoints(sessionInfo.getUserId());
		}
		
		request.setAttribute("status", status);//-1.未登录 0.没签过 1.签过
		
		request.setAttribute("pointStore", activityDtos);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("pointsActivities", pointsActivities);
		request.setAttribute("topImg",topImg);  // top图
		return new ModelAndView("/pointStore/point_store");
	}
	
	/**
	 * @param request
	 * @return ModelAndView
	 * @author zhangh
	 * @date 2017年9月4日 上午10:20:02
	 * 会员签到
	 */
	@RequestMapping("/sign")
	public int sign(HttpServletRequest request){
		SessionInfo sessionInfo=SessionInfo.get();
		int status=pointsStoreService.findPoints(sessionInfo.getUserId());
		if(status==0){
			pointsStoreService.addPoints(sessionInfo.getUserId());
		}
		
		return pointsStoreService.findDays(sessionInfo.getUserId());
	}
	
	
	/**
	 * 初始化 签到信息
	 * @return    
	 * @return RespData    
	 * Date:2017年10月30日 下午9:05:56 
	 * @author hexw
	 */
	@RequestMapping("/initSing")
	public int initSing(HttpServletRequest request){
		int result = 0;
		SessionInfo sessionInfo=SessionInfo.get();
		if (ObjectUtil.isNotNull(sessionInfo)) {
			result = pointsStoreService.findDays(sessionInfo.getUserId());
		}
		return result;
	}
	
	/**
	 * @param request
	 * @return ModelAndView
	 * @author zhangh
	 * @date 2017年9月9日 上午10:20:02
	 * 会员积分
	 */
	@RequestMapping("/points")
	public int points(HttpServletRequest request){
		SessionInfo sessionInfo=SessionInfo.get();
		UserInfo userInfo=userInfoMapper.selectByPrimaryKey(sessionInfo.getUserId());
		
		return userInfo.getAccPoints();
	}
	
	/**
	 * @param goodsId
	 * @param request
	 * @param actId PointsGoods类的id
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月1日 下午2:40:02
	 * 查询商品详情
	 */
	@RequestMapping("/{goodsId}/{typeId}/{activityId}")
	public ModelAndView getGoodsDetail(@PathVariable("goodsId") Long goodsId,@PathVariable("typeId") Integer typeId,
			@PathVariable("activityId") Long activityId,long actId, HttpServletRequest request){
		PageBean pageBean = new PageBean();
		int relust=1;
		UserInfo userInfo=null;
		SessionInfo sessionInfo=SessionInfo.get();
		if(sessionInfo!=null){
			userCenterService.addFootprint(sessionInfo.getUserId(), goodsId);
			userInfo=userInfoMapper.selectByPrimaryKey(sessionInfo.getUserId());
			relust=0;
		}
		PointsGoods pointsGoods=pointsStoreService.getPointsGoods(actId);
		if (ObjectUtil.isNotNull(pointsGoods)) {
			PointsActivity pointsActivity=pointsStoreService.findPointsAct(pointsGoods.getPointsId());
			
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
			List<Goods> newPutwayGoods = goodsService.findYouLike(pageBean);
			
			//6.猜你喜欢的商品
			pageBean.setPageSize(3);
			List<Goods> yourLike = goodsService.findYouLike(pageBean);
			
			//7.滑动商品
			pageBean.setPageSize(8);
			List<Goods> huaGoods = goodsService.findYouLike(pageBean);
			
			request.setAttribute("huaGoods", huaGoods);
			request.setAttribute("yourLike", yourLike);
			request.setAttribute("newPutwayGoods", newPutwayGoods);
			request.setAttribute("typeSealGoods", findTypeSealTopGoods);
			request.setAttribute("allSealGoods", findAllSealTopGoods);
			request.setAttribute("typeNameList", findGoodsLikeTtype);
			request.setAttribute("goods", goodsDetail);
			request.setAttribute("activityId", activityId);
			request.setAttribute("typeId", typeId);
			request.setAttribute("userInfo", userInfo);
			request.setAttribute("relust", relust);
			request.setAttribute("pointsActivity", pointsActivity);
			request.setAttribute("pointsGoods", pointsGoods);
		}
		return new ModelAndView("/pointStore/point_goods_detail");
	}

}
