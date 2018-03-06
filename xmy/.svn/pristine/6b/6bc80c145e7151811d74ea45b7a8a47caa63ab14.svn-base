package com.zfj.xmy.cms.web.controller.usermanagement;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.order.service.cms.UserAddreesService;
import com.zfj.xmy.order.service.cms.UserSpendPointsService;
import com.zfj.xmy.user.persistence.pojo.dto.UserInfoDto;
import com.zfj.xmy.user.service.common.UserInfoService;
import com.zfj.xmy.user.service.common.UserService;
/**
 * 
 * @Description 用户管理(用户资料，账单流水，积分流水)
 * @Author liuw
 * @Date 2017年6月29日上午9:51:18
 */
@RequestMapping("/userManagement")
@RestController
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserAddreesService userAddreesService;
	@Autowired
	private UserSpendPointsService userSpendPointsService;
	// 自定义类型转换器  
		@InitBinder  
		public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
		      
		    binder.registerCustomEditor(Date.class,  
		            new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
		} 
		@RequestMapping("/index")
		public RespData index(HttpServletRequest request){
			RespData respData=new RespData();
			respData.setResultCode(200);
			respData.setData("ok mallSetting");
			return respData ;
		}
	
	/**
	 * 
	 * @Description 用户列表分页显示
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:45:01
	 */
	@RequestMapping("/userList")
	public ModelAndView userList(HttpServletRequest request,ReqData reqData){
		PageBean pageBean = new PageBean() ;
		reqData2Params(reqData) ;
		userInfoService.findUserInfo(reqData, pageBean);
		request.setAttribute("pageCount",pageBean.getTotalPage()) ;
		return new ModelAndView("/userManagement/userManagement_list");
	}
	
	@RequestMapping("/userList/{pageIndex}")
	public ModelAndView userList(@PathVariable("pageIndex") Integer pageIndex,	HttpServletRequest request,ReqData reqData){
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex) ;
		userInfoService.findUserInfo(reqData, pageBean);
//		request.setAttribute("pageCount", pageBean);
		request.setAttribute("pageCount",pageBean.getTotalPage()) ;
		request.setAttribute("userList", pageBean.getData());
		return new  ModelAndView("/userManagement/userManagement_list_page");
	}
	/**
	 * 
	 * @Description 编辑用户资料（包括账单和积分的流水）
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月4日下午4:53:45
	 */
	@RequestMapping("/editUser/{id}")
	public ModelAndView editUser(@PathVariable("id") Integer id,HttpServletRequest request){
		//取出userinfo的基本信息
		ReqData reqData=new ReqData();
		int pageSize=SystemConstant.CONTENT.PAGE_SIZE;
		reqData.putValue("user.id",id, SystemConstant.REQ_PARAMETER_EQ);
		UserInfoDto findUserInfo = userInfoService.findUserInfo(reqData);
		request.setAttribute("UserInfo", findUserInfo);
		
		//取出用户的地址
		ReqData addressReqDate=new ReqData();
		addressReqDate.putValue(SystemConstant.USERINFO.USER_ID, id, SystemConstant.REQ_PARAMETER_EQ);
		List<UserAddrees> finAllUserAddrees = userAddreesService.finAllUserAddrees(addressReqDate);
		request.setAttribute("userAddrees", finAllUserAddrees);
		
		//取出用户的账单流水
		ReqData billReqData=new ReqData();
		billReqData.putValue(SystemConstant.USERINFO.USER_ID, id, SystemConstant.REQ_PARAMETER_EQ);
		billReqData.putValue(SystemConstant.USERINFO.TYPE, SystemConstant.userSpendPoints.TYPE_SPEND, SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> findUserSpendPoints = userSpendPointsService.findUserSpendPoints(billReqData);//账单列表
		int billSize = findUserSpendPoints.size();
		
		//取出用户的积分流水
		ReqData pointReqData=new ReqData();
		pointReqData.putValue(SystemConstant.USERINFO.USER_ID, id, SystemConstant.REQ_PARAMETER_EQ);
		pointReqData.putValue(SystemConstant.USERINFO.TYPE, SystemConstant.userSpendPoints.TYPE_POINT, SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> userPoints = userSpendPointsService.findUserSpendPoints(pointReqData);//积分列表
		int pointSize = userPoints.size();
		int pointPageCount=pointSize/pageSize;
		pointPageCount=pointSize%pageSize==SystemConstant.CONTENT.ZERO?pointPageCount:pointPageCount+1;
		int billPageCount=billSize/pageSize;
		billPageCount=billSize%pageSize==SystemConstant.CONTENT.ZERO?billPageCount:billPageCount+1;
		
		//取出总消费积分条数
		ReqData totalCostPointReqData=new ReqData();
		totalCostPointReqData.putValue(SystemConstant.USERINFO.USER_ID, id,  SystemConstant.REQ_PARAMETER_EQ);
		totalCostPointReqData.putValue(SystemConstant.USERINFO.TYPE, SystemConstant.userSpendPoints.TYPE_POINT, SystemConstant.REQ_PARAMETER_EQ);
		totalCostPointReqData.putValue(SystemConstant.USERINFO.SPEND_TYPE, SystemConstant.userSpendPoints.SPEND_TYPE_SPEND, SystemConstant.REQ_PARAMETER_EQ);
		int totalCostPoint=userSpendPointsService.findTotalCost(totalCostPointReqData);
		request.setAttribute("totalCostPoint", totalCostPoint);
		
		
		request.setAttribute("pointPageCount", pointPageCount);
		request.setAttribute("billPageCount", billPageCount);
		return new ModelAndView("/userManagement/userManagement_edit");
	}
	/**
	 * 
	 * @Description 更新用户资料 
	 * @param request
	 * @param reqData
	 * @param userInfoDto
	 * @return
	 * @Author liuw
	 * @Date 2017年7月4日下午4:53:59
	 */
	@RequestMapping("/updateUser")
	public ModelAndView updateUser(HttpServletRequest request
			,ReqData reqData,UserInfoDto userInfoDto){
		userService.updateUser(reqData, userInfoDto);
		userInfoService.updateUserInfo(reqData, userInfoDto);
		return new  ModelAndView("redirect:userList");
	}
	/**
	 * 
	 * @Description 批量修改用户状态 
	 * @param idStr
	 * @param status
	 * @return
	 * @Author liuw
	 * @Date 2017年7月3日下午4:06:19
	 */
	@RequestMapping("/updateUserBitch")
	public JSONObject updateUserWithBatch(String idStr, String status){
		JSONObject obj = new  JSONObject();
		String [] idArry = idStr.split("\\,");
		int updateUserStatus = userService.updateUserStatus(idArry, status);
		obj.put("state", updateUserStatus);
		return obj;
	}
	/**
	 * 
	 * @Description 批量删除用户 
	 * @param idStr
	 * @return
	 * @Author liuw
	 * @Date 2017年7月3日下午4:06:03
	 */
	@RequestMapping("/deleteUsers")
	@ResponseBody
	public JSONObject deleteUsers(String idStr){
		JSONObject obj = new  JSONObject();
		String [] idArry = idStr.split("\\,");
		userInfoService.deleteUsersInfo(idArry);
		int deleteUsers = userService.deleteUsers(idArry);
		obj.put("num",deleteUsers );
		return obj;
	}
	
	/***
	 * 
	 * @Description 账单流水
	 * @param pageIndex
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月4日下午4:52:48
	 */
	@RequestMapping("/userBill/{pageIndex}")
	public ModelAndView userLBillFlow(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request
			,ReqData reqData){
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex) ;
		pageBean.setPageSize(SystemConstant.CONTENT.PAGE_SIZE) ;
		reqData.putValue("type", SystemConstant.userSpendPoints.TYPE_SPEND, SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> findSpendPoints = userSpendPointsService.findSpendPoints(reqData, pageBean);
		request.setAttribute("userBillFlow", findSpendPoints);
		return new  ModelAndView("/userManagement/userBillFlow_page");
	}
	/**
	 * 
	 * @Description  账单充值
	 * @param userSpendPoints
	 * @return
	 * @Author liuw
	 * @Date 2017年7月4日下午5:04:36
	 */
	@RequestMapping("/updateBillPoint")
	public JSONObject updateBillPoint(UserSpendPoints userSpendPoints){
		JSONObject obj = new  JSONObject();
		Date date=new Date();
		userSpendPoints.setCreatTime(date);
		userSpendPoints.setIsDel(SystemConstant.CONTENT.ONE);
		/*if(userSpendPoints.getMoneyPoint() >= BigDecimal.ZERO)//表示充值
		{
			userSpendPoints.setSpendType(SystemConstant.userSpendPoints.SPEND_TYPE_SAVE);
		}
		else
		{
			userSpendPoints.setSpendType(SystemConstant.userSpendPoints.SPEND_TYPE_SPEND);//表示扣款
			userSpendPoints.setMoneyPoint(userSpendPoints.getMoneyPoint());
		}*/
		
		ReqData reqData=new ReqData(); 
		reqData.putValue("user_info.id", userSpendPoints.getUserId(),SystemConstant.REQ_PARAMETER_EQ);
		UserInfoDto userInfo = userInfoService.findUserInfo(reqData);
		try{
			//userInfo.setBalance(userInfo.getBalance()+userSpendPoints.getMoneyPoint());//重新修改账户总余额
			 userInfoService.updateUserInfo(reqData, userInfo);
			userSpendPointsService.saveSpendPoints(userSpendPoints);
			obj.put("state",SystemConstant.CONTENT.ONE);
		}
		catch(Exception e){
			obj.put("state",SystemConstant.CONTENT.ZERO);
		}
		return obj;
	}
	@RequestMapping("/userPoint/{pageIndex}")
	public ModelAndView userPointFlow(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request
			,ReqData reqData){
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex) ;
		pageBean.setPageSize(SystemConstant.CONTENT.PAGE_SIZE) ;
		reqData.putValue("type", SystemConstant.userSpendPoints.TYPE_POINT, SystemConstant.REQ_PARAMETER_EQ);
		List<UserSpendPoints> findSpendPoints = userSpendPointsService.findSpendPoints(reqData, pageBean);
		request.setAttribute("userPointFlow", findSpendPoints);
		return new  ModelAndView("/userManagement/userPointFlow_page");
	}
	
}
