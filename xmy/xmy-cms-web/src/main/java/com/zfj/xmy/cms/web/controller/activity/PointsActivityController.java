package com.zfj.xmy.cms.web.controller.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsGoodsDto;
import com.zfj.xmy.activity.service.cms.PointsActivityService;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;

/** 
 * @Title: PointsActivityController.java 
 * @Package com.zfj.xmy.cms.web.controller.activity 
 * @Description: 
 * @author zhangh
 * @date 2017年7月11日 下午8:11:50 
 */
@RequestMapping("/pointsActivity")
@RestController
public class PointsActivityController extends BaseController {
	@Autowired
	private CmsGoodsService goodsService;
	
	@Autowired
	private PointsActivityService pointsActivityService;
	
	// 自定义类型转换器  
	@InitBinder  
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
	    binder.registerCustomEditor(Date.class,  
	            new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));  
	} 
	
	/**
	 * 添加积分活动界面
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月11日 下午7:37:10
	 */
	@RequestMapping("/toAddActivity")
	public ModelAndView toAddActivity(){
		return new ModelAndView("/activity/pointsActivity/add_pointsActivity");
	}
	
	/**
	 * 商品列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月19日 上午10:55:04
	 */
	@RequestMapping("/goodsList")
	public ModelAndView goodsList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean();
		reqData.putValue("is_putway", 0, SystemConstant.REQ_PARAMETER_EQ);
		goodsService.selectGoodsAndPage(reqData, pageBean);
		request.setAttribute("totalPage",pageBean.getTotalPage()) ;
		return new ModelAndView("/activity/pointsActivity/goods_list");
	}
	
	@RequestMapping("/goodsList/{pageIndex}")
	public ModelAndView goodsList(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request, ReqData reqData, PageBean pageBean) {
		reqData2Params(reqData);
		pageBean.setPageIndex(pageIndex);
		reqData.setSortname("putwayTime");
		reqData.setSortorder("desc");
		reqData.putValue("is_putway", 0, SystemConstant.REQ_PARAMETER_EQ);
		goodsService.selectGoodsAndPage(reqData, pageBean);
		request.setAttribute("glist", pageBean.getData());
		return new ModelAndView("/activity/pointsActivity/goods_list_page");
	}
	
	/**
	 * 查询商品
	 * @param idStr  商品id字符串
	 * @param request
	 * @return    
	 * @return JSONObject    
	 * Date:2017年7月12日 下午8:21:51
	 */
	@RequestMapping("/findGoods")
	public RespData findGoods(String idStr, HttpServletRequest request) {
		RespData respData = new RespData() ; 
		List<Goods> list = pointsActivityService.findGoods(idStr);
		respData.setData(list);
		return respData;
	}
	
	/**
	 * 添加积分活动
	 * @param pointsActivity
	 * @return    
	 * @return JSONObject    
	 * Date:2017年7月12日 上午9:43:45
	 */
	@RequestMapping("/addActivity")
	public RespData addActivity(PointsActivity pointsActivity){
		RespData respData=new RespData();
		long id=pointsActivityService.insertActivity(pointsActivity);
		respData.setData(id);
		return respData;
	}
	
	/**
	 * 添加活动商品
	 * @param activityId
	 * @param pointsGoods
	 * @return JSONObject
	 * Date:2017年7月12日 上午9:43:45
	 */
	@RequestMapping("/addActivityGoods")
	public RespData addActivityGoods(String activityId,String pointsGoods){
		RespData respData=new RespData();
		pointsActivityService.addActivityGoods(activityId, pointsGoods);
		return null;
	}
	
	/**
	 * 积分活动列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 上午10:55:04
	 */
	@RequestMapping("/activityList")
	public ModelAndView activityList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		PageBean pageBean=new PageBean();
		pointsActivityService.selectActivityList(reqData, pageBean);
		int size=pageBean.getData().size();
		int pageCount=size%SystemConstant.CONTENT.THREE==SystemConstant.CONTENT.ZERO?size/SystemConstant.CONTENT.THREE:size/SystemConstant.CONTENT.THREE+1;
		request.setAttribute("pageCount", pageCount);
		return new ModelAndView("/activity/pointsActivity/pointsActivity_list");
	}
	@RequestMapping("/activityList/{pageIndex}")
	public ModelAndView activityList(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request, ReqData reqData, PageBean pageBean) {
		reqData2Params(reqData);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(SystemConstant.CONTENT.THREE);
		pointsActivityService.selectActivityList(reqData, pageBean);
		request.setAttribute("alist", pageBean.getData());
		return new ModelAndView("/activity/pointsActivity/pointsActivity_list_page");
	}
	
	/**
	 * 删除积分活动
	 * @param id
	 * @return    
	 * @return JSONObject    
	 * Date:2017年7月12日 下午7:47:58
	 */
	@RequestMapping("/delActivity")
	public RespData delActivity(String id){
		RespData respData = new RespData();
		respData.setData(pointsActivityService.delActivity(id));
		return respData;
	}
	
	/**
	 * 到编辑积分活动界面
	 * @param id
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 下午8:14:36
	 */
	@RequestMapping("/toUpdateActivity")
	public ModelAndView toUpdateActivity(String id,HttpServletRequest request){
		PointsActivity pointsActivity = pointsActivityService.getPointsActivity(id);
		List<PointsGoodsDto> goodsLit = pointsActivityService.selectPointsGoodsByActId(id);
		request.setAttribute("data", pointsActivity);
		request.setAttribute("goodsLit", goodsLit);
		return new ModelAndView("/activity/pointsActivity/edit_pointsActivity");
	}
	
	/**
	 * 修改活动
	 * @param pointsActivity
	 * @return    
	 * @return JSONObject    
	 * Date:2017年7月12日 上午11:00:12
	 */
	@RequestMapping("/updateActivity")
	public RespData updateActivity(PointsActivity pointsActivity){
		RespData respData = new RespData();
		respData.setData(pointsActivityService.updateActivity(pointsActivity));
		return respData;
	}
	
	/**
	 * 移除活动商品
	 * @param goodsId    
	 * @return void    
	 * Date:2017年7月12日 下午2:24:32
	 */
	@RequestMapping("/delActivityGoods")
	public void delActivityGoods(String goodsId,String actId){
		pointsActivityService.delActivityGoods(goodsId,actId);
	}
}
