package com.zfj.xmy.cms.web.controller.feedback;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.comment.persistence.app.pojo.dto.FeedbackDto;
import com.zfj.xmy.comment.service.cms.CmsFeedbackService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.SysUser;

@RestController
public class FeedbackController extends BaseController{
	
	@Autowired
	private CmsFeedbackService feedbackService;
	
	@RequestMapping("/feedback/list")
	public ModelAndView findFeedbackList(ReqData reqData,HttpServletRequest request){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		feedbackService.findAllFeedbackDtoList(reqData, pageBean);
		request.setAttribute("countPage", pageBean.getTotalPage());
		reqData2Params(reqData);
		return new ModelAndView("/feedback/feedback");
	}
	
	@RequestMapping("/feedback/list/{pageIndex}")
	public ModelAndView findFeedbackPage(@PathVariable("pageIndex") Integer pageIndex ,ReqData reqData,HttpServletRequest request){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(pageIndex);
		List<FeedbackDto> list = feedbackService.findAllFeedbackDtoList(reqData, pageBean);
		request.setAttribute("feedbackList", list);
		return new ModelAndView("/feedback/feedback_page");
	}
	
	@RequestMapping("/updateFeedBack/{id}")
	public ModelAndView updateFeedBack(@PathVariable("id") Long id ,ReqData reqData,HttpServletRequest request){
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		feedbackService.updateFeedBack(id, sysUser);
		
		return new ModelAndView("redirect:/feedback/list");
	}
	
}
