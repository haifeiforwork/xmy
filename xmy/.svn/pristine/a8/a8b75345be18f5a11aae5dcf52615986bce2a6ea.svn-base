package com.zfj.xmy.cms.web.controller.activity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.service.cms.PointTacticsService;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.PointTactics;


/**
 * @Title: PointTacticsController.java
 * @Package com.zfj.xmy.cms.web.controller.activity
 * @Description:
 * @author zhangh
 * @date 2017年7月4日 下午7:10:47
 */

@RequestMapping("/pointTactics")
@Controller
public class PointTacticsController extends BaseController{
	@Autowired
	private PointTacticsService pointTacticsService;

	@RequestMapping("/pointList/{pageIndex}")
	public ModelAndView userList(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request, ReqData reqData) {
		reqData2Params(reqData);
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(SystemConstant.CONTENT.THREE);
		pointTacticsService.selectPointTacticsAndPage(reqData, pageBean);
		request.setAttribute("tacticsList", pageBean.getData());
		return new ModelAndView("/activity/pointTactics/pointTactics_list_page");
	}

	@RequestMapping("/editPoint/{id}")
	public ModelAndView editUser(@PathVariable("id") long id,
			HttpServletRequest request) {
		ReqData reqData = new ReqData();

		reqData.putValue("tactics.id", id, SystemConstant.REQ_PARAMETER_EQ);
		PointTactics pt = pointTacticsService.findPointTactics(id);
		request.setAttribute("pointTactics", pt);
		return new ModelAndView("/activity/pointTactics/pointTactics_edit");
	}
	
	/**
	 * 跳转到新增策略界面
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月5日 上午10:40:13
	 */
	@RequestMapping("/toAddTactics")
	public ModelAndView toAddGoods(HttpServletRequest request , ReqData reqData){
		reqData2Params(reqData);
		return new  ModelAndView("/activity/pointTactics/pointTactics_add");
	}
	
	/**
	 * 添加策略
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月5日 上午11:29:58
	 */
	@RequestMapping("/addTactics")
	public ModelAndView addGoods(HttpServletRequest request, ReqData reqData,
			 PointTactics pointTactics) {
		reqData2Params(reqData);
		pointTacticsService.insertPointTactics(pointTactics);
		return new ModelAndView("redirect:/pointTactics/tacticsList");
	}
	
	/**
	 * 策略列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月5日 上午10:55:04
	 */
	@RequestMapping("/tacticsList")
	public ModelAndView goodsList(HttpServletRequest request,ReqData reqData){
		PageBean pageBean=new PageBean();
		reqData2Params(reqData);
		pointTacticsService.selectPointTacticsAndPage(reqData, pageBean);
		int size=pageBean.getData().size();
		int pageCount=size%SystemConstant.CONTENT.THREE==SystemConstant.CONTENT.ZERO?size/SystemConstant.CONTENT.THREE:size/SystemConstant.CONTENT.THREE+1;
		request.setAttribute("pageCount", pageCount);
		return new ModelAndView("/activity/pointTactics/pointTactics_list");
	}
	
	/**
	 * 修改积分策略
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月5日 下午3:55:04
	 */
	@RequestMapping("/updateTactics")
	public ModelAndView updateUser(HttpServletRequest request
			,PointTactics pointTactics){
		pointTacticsService.update(pointTactics);
		System.out.println(pointTactics.toString());
		return new  ModelAndView("redirect:/pointTactics/tacticsList");
	}
	
	/**
	 * 修改策略状态
	 * @param id
	 * @param requset
	 * @param reqData
	 * @return    
	 * @return JSONObject    
	 * Date:2017年7月5日 下午8:40:26
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public JSONObject updateStatus(long id,HttpServletRequest request,int status){
		JSONObject json=new JSONObject();
		json.put("status", pointTacticsService.updateStatus(id, status));
		return json;
	}

}
