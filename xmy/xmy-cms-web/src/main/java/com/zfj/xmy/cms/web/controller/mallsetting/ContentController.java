package com.zfj.xmy.cms.web.controller.mallsetting;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Notification;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.config.service.NotificationService;
import com.zfj.xmy.goods.service.cms.TermDataService;
import com.zfj.xmy.goods.service.cms.VocabularyService;
import com.zfj.xmy.order.service.cms.PayLogService;
import com.zfj.xmy.oss.OSSManager;
/**
 * 内容管理，包括栏目，文章,通知的增删改查
 * @author liuw
 *
 */
@RequestMapping("/content")
@RestController
public class ContentController extends BaseController{

	@Autowired
	private PayLogService payLogService ;
	
	@Autowired
	private OSSManager ossManager;

	@Autowired
	private TermDataService termService;
	@Autowired
	private NotificationService notificationService;
	@Autowired 
	private VocabularyService vocabularyService;
	// 自定义类型转换器  
		@InitBinder  
		public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {  
		     
		    binder.registerCustomEditor(Date.class,  
		            new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
		} 
	
	@RequestMapping("/index")
	public RespData index(HttpServletRequest request){
		RespData respData=new RespData();
		respData.setResultCode(200);
		respData.setData("ok content");
		return respData ;
	}
	
	@RequestMapping("/column/list")
	public ModelAndView payLogTest(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		ModelAndView mAndView=new ModelAndView();
		reqData2Params(reqData);
		Integer pageSize=SystemConstant.CONTENT.PAGE_SIZE;
		Integer totalCount=SystemConstant.CONTENT.ZERO;
		Integer pageCount=SystemConstant.CONTENT.ZERO;
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK,SystemConstant.TERMDATA.COLUMN, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue(SystemConstant.TERMDATA.PARENT_ID,SystemConstant.TERMDATA.PARENT_IDIS0, SystemConstant.REQ_PARAMETER_EQ);
		totalCount = termService.getCountTermData(reqData);
		pageCount=totalCount/pageSize;
		if(totalCount%pageSize!=SystemConstant.CONTENT.ZERO)
			pageCount++;
		request.setAttribute("pageCount", pageCount);
		mAndView.setViewName("/article/column_list");
		return mAndView ;
	}
	
	@RequestMapping("/column/list/{pageIndex}")
	public ModelAndView payLogList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request){
		reqData2Params(reqData);
		Integer pageSize=SystemConstant.CONTENT.PAGE_SIZE;
		PageBean pageBean = new PageBean() ;
		Integer totalCount=SystemConstant.CONTENT.ZERO;
		Integer pageCount=SystemConstant.CONTENT.ZERO;
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.COLUMN, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue(SystemConstant.TERMDATA.PARENT_ID,SystemConstant.TERMDATA.PARENT_IDIS0, SystemConstant.REQ_PARAMETER_EQ);
		totalCount = termService.getCountTermData(reqData);
		pageCount=totalCount/pageSize;
		
		
		if(totalCount%pageSize!=SystemConstant.CONTENT.ZERO)
			pageCount++;
		pageBean.setPageIndex(pageIndex) ;
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue(SystemConstant.TERMDATA.PARENT_ID,SystemConstant.TERMDATA.PARENT_IDIS0, SystemConstant.REQ_PARAMETER_EQ);
		termService.findTerm(reqData, pageBean);
		
		
		request.setAttribute(SystemConstant.TERMDATA.PAGEBEAN,pageBean) ;
		request.setAttribute(SystemConstant.TERMDATA.PAGECOUNT, pageCount);
		
		String json = JSON.toJSONString(reqData) ;
		request.setAttribute("req",json);
		return new ModelAndView("/article/column_list_page") ;
	}
	/**
	 * 
	 * @Description 栏目添加
	 * @param termData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:26:10
	 */
	@RequestMapping("/column/add")
	public ModelAndView addColumn(TermData termData,HttpServletRequest request){
		//添加的时候设置栏目的parentid为0，表示一级
		termData.setParentId(new Long(SystemConstant.TERMDATA.PARENT_IDIS0));
		//从词汇表中查询vid
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.COLUMN, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		termData.setVid(vocabulary.getId());
		termService.insertTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 
	 * @Description 栏目更新
	 * @param termData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:25:50
	 */
	@RequestMapping("/column/update")
	public ModelAndView updateColumn(TermData termData,HttpServletRequest request){
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue("mark", "column", SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		termData.setVid(vocabulary.getId());
		termService.updateTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 
	 * @Description 栏目删除
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:26:01
	 */
	@RequestMapping("/column/del/{id}")
	public ModelAndView delColumn(@PathVariable("id") Long id,HttpServletRequest request){
		termService.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/content/column/list") ;
	}
	/**
	 * 
	 * @Description 栏目编辑
	 * @param id
	 * @param reqData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:25:40
	 */
	@RequestMapping("/column/edit/{id}")
	public ModelAndView editColumn(@PathVariable("id") Long id,ReqData reqData,HttpServletRequest request){
		reqData2Params(reqData);
		TermData termData = termService.getTermData(id);
		request.setAttribute("termData", termData);
		return new ModelAndView("/article/column_edit") ;
	}
	
	/**
	 * 
	 * @Description 文章分页显示
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:21:30
	 */
	@RequestMapping("/article/list")
	public ModelAndView articleList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		ModelAndView mAndView=new ModelAndView();
		Integer pageSize=10;
		Integer totalCount=0;
		Integer pageCount=0;

		ReqData reqDataVocabulary=new ReqData();
		//得到所有的栏目
		reqDataVocabulary.putValue("mark", "column", SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqData.putValue("vid", vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id",SystemConstant.TERMDATA.PARENT_IDIS0, SystemConstant.REQ_PARAMETER_NE);
		totalCount = termService.getCountTermData(reqData);
		pageCount=totalCount/pageSize;
		if(totalCount%pageSize!=SystemConstant.CONTENT.ZERO)
			pageCount++;
		request.setAttribute("pageCount", pageCount);
		mAndView.setViewName("/article/article_list");
		
		ReqData reqDataColumn=new ReqData();
		PageBean pageBeanColumn=new PageBean();
		reqDataColumn.setSortname("weight");
		reqDataColumn.setSortorder("asc");
		reqDataColumn.putValue("vid", vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		reqDataColumn.putValue("parent_id",SystemConstant.TERMDATA.PARENT_IDIS0, SystemConstant.REQ_PARAMETER_EQ);
		pageBeanColumn.setPageSize(SystemConstant.CONTENT.PAGE_SIZE);
		termService.findTerm(reqDataColumn, pageBeanColumn);
		request.setAttribute("pageBeanColumn", pageBeanColumn);
		return mAndView ;
	}
	@RequestMapping("/article/list/{pageIndex}")
	public ModelAndView articleList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(SystemConstant.CONTENT.PAGE_SIZE);
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue("mark", "column", SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqData.putValue("vid", vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("parent_id",SystemConstant.TERMDATA.PARENT_IDIS0, SystemConstant.REQ_PARAMETER_NE);
		int totalCount = termService.getCountTermData(reqData);
		pageBean.setTotalCount(totalCount);
		termService.findTerm(reqData, pageBean);
		request.setAttribute("pageBean",pageBean) ;
		
		ReqData reqDataColumn=new ReqData();
		PageBean paBeanColumn=new PageBean();
		reqDataColumn.putValue("vid", vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		reqDataColumn.putValue("parent_id",SystemConstant.TERMDATA.PARENT_IDIS0, SystemConstant.REQ_PARAMETER_EQ);
		termService.findTerm(reqDataColumn, paBeanColumn);
		request.setAttribute("paBeanColumn", paBeanColumn);
		return new ModelAndView("/article/article_list_page") ;
	}
	/**
	 * 
	 * @Description 文章添加
	 * @param termData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:21:20
	 */
	@RequestMapping("/article/add")
	public ModelAndView addArticle(TermData termData,HttpServletRequest request){
		System.out.println(termData.getName());
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue("mark", "column", SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		termData.setVid(vocabulary.getId());
		termService.insertTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 
	 * @Description 文章删除
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:21:12
	 */
	@RequestMapping("/article/del/{id}")
	public ModelAndView delArticle(@PathVariable("id") Long id,HttpServletRequest request){
		termService.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/content/article/list") ;
	}
	/**
	 * 
	 * @Description 文章编辑
	 * @param id
	 * @param reqData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:20:55
	 */
	@RequestMapping("/article/edit/{id}")
	public ModelAndView editArticle(@PathVariable("id") Long id,ReqData reqData,HttpServletRequest request){
		reqData2Params(reqData);
		PageBean paBeanColumn=new PageBean();
		TermData termData = termService.getTermData(id);
		request.setAttribute("termData", termData);
		ReqData reqDataColumn=new ReqData();
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue("mark", "column", SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqDataColumn.putValue("vid", vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		reqDataColumn.putValue("parent_id",SystemConstant.TERMDATA.PARENT_IDIS0, SystemConstant.REQ_PARAMETER_EQ);
		termService.findTerm(reqDataColumn, paBeanColumn);
		request.setAttribute("paBeanColumn", paBeanColumn);
		return new ModelAndView("/article/article_edit") ;
	}
	/**
	 * 
	 * @Description 文章更新
	 * @param termData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:20:47
	 */
	@RequestMapping("/article/update")
	public ModelAndView updateArticle(TermData termData,HttpServletRequest request){
		System.out.println(termData.getName());
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue("mark", "column", SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		termData.setVid(vocabulary.getId());
		termService.updateTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 
	 * @Description 通知分页
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:20:37
	 */
	@RequestMapping("/notification/list")
	public ModelAndView notificationList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		ModelAndView mAndView=new ModelAndView();
		Integer pageSize=SystemConstant.CONTENT.PAGE_SIZE;
		Integer totalCount=SystemConstant.CONTENT.ZERO;
		Integer pageCount=SystemConstant.CONTENT.ZERO;
		totalCount = notificationService.fingNotificationCount(reqData);
		pageCount=totalCount/pageSize;
		if(totalCount%pageSize!=SystemConstant.CONTENT.ZERO)
			pageCount++;
		request.setAttribute("pageCount", pageCount);
		mAndView.setViewName("/article/notification_list");
		
		return mAndView ;
	}
	@RequestMapping("/notification/list/{pageIndex}")
	public ModelAndView notificationList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex);
		Integer pageSize=SystemConstant.CONTENT.PAGE_SIZE;
		pageBean.setPageSize(pageSize);
		int totalCount = notificationService.fingNotificationCount(reqData);
		pageBean.setTotalCount(totalCount);
		notificationService.findNotification(reqData, pageBean);
		request.setAttribute("totalCount",totalCount) ;
		request.setAttribute("pageBean",pageBean) ;
		return new ModelAndView("/article/notification_list_page") ;
	}
	/**
	 * 
	 * @Description 置顶通知删除
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:20:13
	 */
	@RequestMapping("/notification/del/{id}")
	public ModelAndView delNotification(@PathVariable("id") Long id,HttpServletRequest request){
		notificationService.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/content/notification/list") ;
	}
	/**
	 * 
	 * @Description 置顶通知添加
	 * @param notification
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午8:20:26
	 */
	@RequestMapping("/notification/add")
	public ModelAndView addNotification(Notification notification,HttpServletRequest request){
		Date date=new Date();
		notification.setCreateTime(date);
		notificationService.insert(notification);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 
	 * @Description APP端关于香满园页面
	 * @param termData
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午4:07:04
	 */
	@RequestMapping("/appAboutXmyH5")
	public ModelAndView appAboutXmyH5(TermData termData,HttpServletRequest request,ReqData reqData){
		// 1先查出词汇表对应的vid，2再从termdata中查出具体的数据
		reqData2Params(reqData);
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.APP_ABOUTXMY, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		//根据vid查具体的关于香满园的整条数据
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(),SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean=new PageBean();
		TermData appAbouXmyH5=null;
		termService.findTermWithBlob(reqData, pageBean);
		
		
		if(!ObjectUtils.isEmpty(pageBean)&&pageBean.getData().size()>=1){//管理员设置了H5的页面
			appAbouXmyH5=(TermData) pageBean.getData().get(0);
			
		}
		request.setAttribute("appAbouXmyH5", appAbouXmyH5);
		return new ModelAndView("/appAboutXmyH5/appAboutXmyH5") ;
	}
	@RequestMapping("/appAboutXmyH5/update")
	public ModelAndView invoiceSettingUpdate(TermData termData,HttpServletRequest request,ReqData reqData)
	{	
		reqData2Params(reqData);
		termService.updateTermData(termData);
		return new ModelAndView("redirect:/content/appAboutXmyH5") ;
	}
}
