package com.zfj.xmy.cms.web.controller.topicPage;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.activity.service.cms.TopicPageService;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.TopicPage;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;


/**
 * 专题页面设置
 * @Description 
 * @Author liuw
 * @Date 2017年8月28日上午10:09:20
 */
@RestController
@RequestMapping("/topicPage")
public class TopicPageController extends BaseController{
	
	
	@Autowired
	private TopicPageService topicPageService;
	
	/**
	 * 跳转到专题页面上传页面
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月29日上午9:53:21
	 */
	@RequestMapping("/addTopicPage")
	public ModelAndView topicPageAdd(){
		ModelAndView modelAndView=new  ModelAndView();
		modelAndView.setViewName("/topicPage/addTopicPage");
		return modelAndView;
	}
	/**
	 * 保存专题页面
	 * @Description 
	 * @param topicPage
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年8月29日上午9:53:37
	 */
	@RequestMapping("/saveAddTopicPage")
	public ModelAndView saveAddTopicPage(TopicPage topicPage,HttpServletRequest request){
		String unFilePath="/uploadhtml/topicPage/";
		ModelAndView modelAndView=new  ModelAndView();
		String  basePath = request.getSession().getServletContext().getRealPath("") ;
		//1 解压
		Map<String, Object> unZipFile = topicPageService.unZipFile(topicPage.getFilepath(),basePath+unFilePath);
		String filePath = (String) unZipFile.get("filePath");
		//获取路径的最后一级文件夹名称，也就是加压后的文件夹名称，利用Java自动获取的linux还是windows的分隔符
		String substring = filePath.substring(filePath.lastIndexOf(System.getProperty("file.separator"))+1);
		String finalUnFilePath=unFilePath+substring;
		topicPage.setUnFilepath(finalUnFilePath);
		topicPage.setCreateTime(new Date());
		boolean object = (boolean) unZipFile.get("success");
		if(object){
			topicPage.setStatus(SystemConstant.TOPICPAGE.UNZIP);
		}else{
			topicPage.setStatus(SystemConstant.TOPICPAGE.ISZIP);
		}
		
		//2 保存主题页面
		topicPageService.saveTopicPage(topicPage);
		modelAndView.setViewName("/topicPage/addTopicPage");
		return new ModelAndView("redirect:/topicPage/list");
	}
	@RequestMapping("/list")
	public ModelAndView topicPageList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		ModelAndView mAndView=new ModelAndView();
		PageBean pageBean=new PageBean();
		topicPageService.findTopicPages(reqData, pageBean);
		request.setAttribute("pageCount", pageBean.getTotalPage());
		mAndView.setViewName("/topicPage/topicPage_list");
		return mAndView ;
	}
	@RequestMapping("/delTopicPagex/{id}")
	public ModelAndView delTopicPagex(HttpServletRequest request,ReqData reqData,@PathVariable("id") Long id){
		reqData2Params(reqData);
		ModelAndView mAndView=new ModelAndView();
		topicPageService.deleteTopicPage(id);
		mAndView.setViewName("redirect:/topicPage/list");
		return mAndView ;
	}
	
	@RequestMapping("/list/{pageIndex}")
	public ModelAndView topicPageList(@PathVariable("pageIndex") Integer pageIndex,ReqData reqData,HttpServletRequest request){
		reqData2Params(reqData);
		PageBean pageBean=new PageBean();
		pageBean.setPageIndex(pageIndex);
		topicPageService.findTopicPages(reqData, pageBean);
		request.setAttribute("data", pageBean.getData());
		return new ModelAndView("/topicPage/topicPage_list_page") ;
	}
}
