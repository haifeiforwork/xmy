package com.zfj.xmy.cms.web.controller.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.service.cms.CmsOnlineService;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.ActivityQuestionMapper;
import com.zfj.xmy.common.persistence.pojo.ActivityQuestion;
import com.zfj.xmy.common.persistence.pojo.OnlineActivity;

/**
 * 全场优惠活动控制层
 * @author Administrator
 *
 */
@RequestMapping("/onlineActivity")
@RestController
public class OnlieActivityController extends BaseController{
	
	@Autowired
	private CmsOnlineService cmsOnlineService;
	
	
	@Autowired
	private ActivityQuestionMapper activityQuestionMapper;
	
	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	/**
	 * 全场活动商品名称
	 * @param request
	 * @param reqData
	 * @return ModelAndView
	 * @author lij
	 * @date 2018年1月11日 下午2:41:19
	 */
	@RequestMapping("/list")
	public ModelAndView onLineAcitivtyList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean();
		cmsOnlineService.listActivity(reqData, pageBean);
		request.setAttribute("totalPage",pageBean.getTotalPage()) ;
		return new ModelAndView("/activity/onlineActivity/onlineActivity_list");
	}
	
	@RequestMapping("/list/{pageIndex}")
	public ModelAndView onLineActivityContent(@PathVariable("pageIndex") Integer pageIndex,HttpServletRequest request,ReqData reqData){
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		cmsOnlineService.listActivity(reqData, pageBean);;
		request.setAttribute("alist", pageBean.getData());
		return new ModelAndView("/activity/onlineActivity/onlineActivity_list_page");
	}
	/**
	 * 去修改页面
	 * @return ModelAndView
	 * @author lij
	 * @date 2018年1月11日 下午3:27:03
	 */
	@RequestMapping("updateActvity")
	public ModelAndView toUpdateActivity(@Param("id") Long id,HttpServletRequest request){
		OnlineActivity onlineActivityById = cmsOnlineService.getOnlineActivityById(id);
		request.setAttribute("data", onlineActivityById);
		return new ModelAndView("/activity/onlineActivity/edit_onlineActivity");
	}
	/**
	 * 修改或新增时的方法
	 * @param request
	 * @param onlineActivity
	 * @return ModelAndView
	 * @author lij
	 * @date 2018年1月11日 下午3:54:01
	 */
	@RequestMapping("addOnlineActivity")
	public ModelAndView addOnlineActivity(HttpServletRequest request,OnlineActivity onlineActivity){
		if(ObjectUtils.isEmpty(onlineActivity.getId())){
			cmsOnlineService.addOnlineActivity(onlineActivity);
		}else{
			cmsOnlineService.updateOnlineActivity(onlineActivity);
		}
		return new ModelAndView("redirect:/onlineActivity/list");
	}
	/**
	 * 根据活动id查询所有问题
	 * @param id
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2018年1月30日 下午2:46:35
	 */
	@RequestMapping("questionList")
	public ModelAndView questionList(@Param("id") Long id,HttpServletRequest request){
		ReqData reqData = new ReqData();
		reqData.putValue("activity_id", id, SystemConstant.REQ_PARAMETER_EQ);
		List<ActivityQuestion> questionList = activityQuestionMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		request.setAttribute("questionList", questionList);
		request.setAttribute("activityId", id);
		return new ModelAndView("/activity/onlineActivity/question_list");
	}
	/**
	 * 去修改问题
	 * @param id
	 * @param activityId
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2018年1月30日 下午3:01:11
	 */
	@RequestMapping("updateQuestion")
	public ModelAndView updateQuestion(@Param("id") Long id,@Param("activityId") Long activityId, HttpServletRequest request){
		ActivityQuestion question = activityQuestionMapper.selectByPrimaryKey(id);
		if(!ObjectUtils.isEmpty(activityId)){
			ActivityQuestion activityQuestion = new ActivityQuestion();
			question=activityQuestion;
			question.setActivityId(activityId);
		}
		request.setAttribute("data", question);
		return new ModelAndView("/activity/onlineActivity/edit_question");
	}
	
	/**
	 * 修改或新增时的方法
	 * @param request
	 * @param onlineActivity
	 * @return ModelAndView
	 * @author lij
	 * @date 2018年1月11日 下午3:54:01
	 */
	@RequestMapping("addQuestion")
	public ModelAndView addQuestion(HttpServletRequest request,ActivityQuestion question){
		if(ObjectUtils.isEmpty(question.getId())){
			activityQuestionMapper.insert(question);
		}else{
			activityQuestionMapper.updateByPrimaryKey(question);
		}
		return new ModelAndView("redirect:/onlineActivity/questionList?id="+question.getActivityId());
	}
	
}
