package com.zfj.xmy.cms.web.controller.push;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.annotation.CheckLogin;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.cms.web.common.ims.annotation.SystemControllerLog;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.JiguangPushLabel;
import com.zfj.xmy.common.persistence.pojo.JiguangPushRecord;
import com.zfj.xmy.common.persistence.pojo.common.push.PushInDto;
import com.zfj.xmy.common.persistence.pojo.common.push.findJiguangPushRecordListResultDto;
import com.zfj.xmy.common.service.CommonPushLabelService;
import com.zfj.xmy.common.service.CommonPushUtilService;
import com.zfj.xmy.quartz.dto.PushReturnDto;
import com.zfj.xmy.user.service.common.UserInfoService;

@RequestMapping("/push")
@CheckLogin
@RestController
public class PushController extends BaseController {

	@Autowired
	private CommonPushUtilService commonPushUtilService;
	
	@Autowired
	private CommonPushLabelService commonPushLabelService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	
	// 自定义类型转换器
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	/**
	 * 定时推送列表页面
	 * @return
	 * @Description 
	 * @date 2017年12月20日  下午4:46:58
	 * @author wy
	 * 2017
	 * @return ModelAndView
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request){
		List<PushReturnDto> returnDtos = commonPushUtilService.queryAllPush();
		request.setAttribute("pushs", returnDtos);
		
		//2.所有标签数据
		request.setAttribute("labels", commonPushLabelService.getAllJiguangPushLabelList());
		
		return new ModelAndView("/push/push_list");
	}
	
	
	
	/**
	 * 定时推送列表数据测试
	 * @param request
	 * @return
	 * @Description 
	 * @date 2018年1月8日  上午9:13:25
	 * @author wy
	 * 2018
	 * @return RespData
	 */
	@RequestMapping("/listJson")
	public RespData listjson(HttpServletRequest request){
		
		RespData respData = new RespData();
		try {
			List<PushReturnDto> returnDtos = commonPushUtilService.queryAllPush();
			respData.setData(returnDtos);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "查询失败");
		}
		return respData;
	}
	
	

	
	/**
	 * 推送主方法
	 * @param request
	 * @return
	 * @Description 
	 * @date 2018年1月31日  上午9:43:36
	 * @author wy
	 * 2018
	 * @return RespData
	 */
	@RequestMapping("/push")
	public RespData push(HttpServletRequest request,PushInDto pushInDto ){
		
		RespData respData = new RespData();
		try {
			commonPushUtilService.Push(pushInDto);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "推送失败");
		}
		return respData;
	}
	
	/**
	 * 推送页面
	 * @param request
	 * @param userid
	 * @param search
	 * @return
	 * @Description 
	 * @date 2018年1月29日  上午11:27:22
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping(value="/pushindex",method=RequestMethod.GET)
	public ModelAndView pushIndex(HttpServletRequest request){
		List<JiguangPushLabel> jiguangPushLabels = commonPushLabelService.getAllJiguangPushLabelList(); //所有标签
		request.setAttribute("labels", jiguangPushLabels);
		return new ModelAndView("/push/push_index");
	}		

	
	/**
	 * 删除定时推送任务
	 * @param request
	 * @param jobName
	 * @param triggerName
	 * @return
	 * @Description 
	 * @date 2018年1月8日  上午9:14:20
	 * @author wy
	 * 2018
	 * @return RespData
	 */
	@RequestMapping("/del")
	public RespData del(HttpServletRequest request,
			@RequestParam(value="jobName",required=true) String jobName,
			@RequestParam(value="triggerName",required=true) String triggerName){
		
		RespData respData = new RespData();
		try {
			commonPushUtilService.delPush(jobName, triggerName);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "删除失败");
		}
		return respData;
	}
	
	/**
	 * 推送标签管理
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午2:54:14
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping("/labelList")
	public ModelAndView labellist(HttpServletRequest request){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(1);
		
		CriteriaParameter parameter = new CriteriaParameter();
		commonPushLabelService.getJiguangPushLabelList(parameter, pageBean);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/push/push_label_list");
	}
			
	
	/**
	 * 推送标签管理数据
	 * @param request
	 * @return
	 * @Description 
	 * @date 2018年1月8日  上午10:18:25
	 * @author wy
	 * 2018
	 * @param pageIndex 
	 * @return ModelAndView
	 */
	@RequestMapping("/labelList/{pageIndex}")
	public ModelAndView labelList(HttpServletRequest request,
			@PathVariable("pageIndex") Integer pageIndex){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(pageIndex);
		
		CriteriaParameter parameter = new CriteriaParameter();
		List<JiguangPushLabel> jiguangPushLabels = commonPushLabelService.getJiguangPushLabelList(parameter, pageBean);
		request.setAttribute("labels", jiguangPushLabels);
		
		return new ModelAndView("/push/push_label_list_page");
	}
	
	/**
	 * 添加推送标签
	 * @param request
	 * @param labelName
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午2:55:29
	 * @author wy
	 * 2018
	 * @return RespData
	 */
	@RequestMapping("/addLabel")
	public RespData addLabel(HttpServletRequest request,
			@RequestParam(value="label",required=true) String labelName){
		
		RespData respData = new RespData();
		try {
			commonPushLabelService.addLabel(labelName);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "添加失败");
		}
		return respData;
	}
	/**
	 * 修改推送标签
	 * @param request
	 * @param id
	 * @param labelName
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午3:45:06
	 * @author wy
	 * 2018
	 * @return RespData
	 */
	@RequestMapping("/modifyLabel")
	public RespData modifyLabel(HttpServletRequest request,
			@RequestParam(value="id",required=true) Long id,
			@RequestParam(value="label",required=true) String labelName){
		
		RespData respData = new RespData();
		try {
			commonPushLabelService.modifyLabel(id,labelName);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "修改失败");
		}
		return respData;
	}
	/**
	 * 删除推送标签
	 * @param request
	 * @param id
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午3:46:52
	 * @author wy
	 * 2018
	 * @return RespData
	 */
	@RequestMapping("/delLabel")
	public RespData delLabel(HttpServletRequest request,
			@RequestParam(value="id",required=true) Long id){
		
		RespData respData = new RespData();
		try {
			commonPushLabelService.delLabel(id);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "删除失败");
		}
		return respData;
	}
	
	/**
	 * 用户推送标签管理
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午4:55:23
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping("/userlabelList")
	public ModelAndView userlabellist(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData) ;
		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(1);
		
		userInfoService.findPushLabelUserList(reqData, pageBean);
		request.setAttribute("countPage", pageBean.getTotalPage());
		
		return new ModelAndView("/push/push_user_label_list");
	}
	
	/**
	 * 用户推送标签管理数据
	 * @param request
	 * @param pageIndex
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午4:56:16
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping("/userlabelList/{pageIndex}")
	public ModelAndView userlabelList(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request,
			ReqData reqData){
		
		//1.用户列表
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(pageIndex);
		
		reqData2Params(reqData) ;
		userInfoService.findPushLabelUserList(reqData, pageBean);
		request.setAttribute("pageCount",pageBean.getTotalPage()) ;
		request.setAttribute("userList", pageBean.getData());
		
		//2.所有标签数据
		request.setAttribute("labels", commonPushLabelService.getAllJiguangPushLabelList());
		
		return new ModelAndView("/push/push_user_label_list_page");
	}
	
	
	/**
	 * 给用户绑定(添加)标签 操作
	 * @param request
	 * @param userid
	 * @return
	 * @Description 
	 * @date 2018年1月23日  上午11:41:39
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@SystemControllerLog(description="给用户绑定(添加)标签 操作")
	@RequestMapping(value="/userlabelList/add",method=RequestMethod.POST)
	public RespData userlabelListAdd(HttpServletRequest request,
			@RequestParam(value="userid",required=true) Long userid,
			@RequestParam(value="labelid",required=true) Long labelid){
		RespData respData = new RespData();
		try {
			commonPushLabelService.bindLabelToUser(userid, labelid);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "添加失败");
		}
		return respData;
	}
	

	/**
	 * 给用户绑定(添加)标签 页面 的 (标签数据) 可搜索
	 * @param request
	 * @return
	 * @Description 
	 * @date 2018年1月8日  上午10:18:25
	 * @author wy
	 * 2018
	 * @param pageIndex 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/userlabelList/add",method=RequestMethod.GET)
	public ModelAndView userlabelListAdd(HttpServletRequest request,
			@RequestParam(value="userid",required=true) String userid,
			 @RequestParam(value="search",required=false) String search){
		
		request.setAttribute("userid", userid);
		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(1);//只取第一页数据
		
		List<JiguangPushLabel> jiguangPushLabels = commonPushLabelService.getJiguangPushLabelList(search, pageBean);
		request.setAttribute("labels", jiguangPushLabels);
		request.setAttribute("pageBean", pageBean);
		return new ModelAndView("/push/push_user_label_list_add_page");
	}		
	
	
	/**
	 * 给用户解除绑定标签 操作
	 * @param request
	 * @param userid
	 * @return
	 * @Description 
	 * @date 2018年1月23日  上午11:41:39
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@SystemControllerLog(description="给用户解除绑定标签 操作")
	@RequestMapping(value="/userlabelList/del",method=RequestMethod.POST)
	public RespData userlabelListDel(HttpServletRequest request,
			@RequestParam(value="userid",required=true) Long userid,
			@RequestParam(value="labelid",required=true) Long labelid){
		RespData respData = new RespData();
		try {
			commonPushLabelService.unbindLabelToUser(userid, labelid);
		} catch (Exception e) {
			e.printStackTrace();
			return RespData.getRespData(e, "删除失败");
		}
		return respData;
	}
	
	
	
	/**
	 * 推送记录
	 * @return
	 * @Description 
	 * @date 2018年1月29日  上午9:32:18
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping("/log")
	public ModelAndView log(ReqData reqData, HttpServletRequest request){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(1);
		
		CriteriaParameter parameter = new CriteriaParameter();
		commonPushUtilService.findJiguangPushRecordList(parameter, pageBean);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/push/log");
	}
			
	
	/**
	 * 推送记录
	 * @param request
	 * @param pageIndex
	 * @return
	 * @Description 
	 * @date 2018年1月29日  上午9:32:11
	 * @author wy
	 * 2018
	 * @return ModelAndView
	 */
	@RequestMapping("/log/{pageIndex}")
	public ModelAndView log(HttpServletRequest request,
			@PathVariable("pageIndex") Integer pageIndex){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(pageIndex);
		
		CriteriaParameter parameter = new CriteriaParameter();
		List<findJiguangPushRecordListResultDto> findJiguangPushRecordListResultDtos = commonPushUtilService.findJiguangPushRecordList(parameter, pageBean);
		request.setAttribute("records", findJiguangPushRecordListResultDtos);
		request.setAttribute("pageBean", pageBean);
		
		return new ModelAndView("/push/log_page");
	}
}
