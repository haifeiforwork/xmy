package com.zfj.xmy.cms.web.controller.mallsetting;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.activity.service.cms.ContainerService;
import com.zfj.xmy.activity.service.cms.IndexSettingService;
import com.zfj.xmy.activity.service.cms.PcSecondLevelClassificationService;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Container;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;
import com.zfj.xmy.common.persistence.pojo.PcSecondLevelClassification;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.Vocabulary;
import com.zfj.xmy.config.service.NotificationService;
import com.zfj.xmy.goods.service.cms.TermDataService;
import com.zfj.xmy.goods.service.cms.VocabularyService;
import com.zfj.xmy.order.service.cms.PayLogService;
import com.zfj.xmy.oss.OSSManager;
/**
 * 商城设置
 * @author liuw
 *
 */
@RequestMapping("/mallSetting")
@RestController
public class MallSettingController extends BaseController{

	@Autowired
	private PayLogService payLogService ;
	
	@Autowired
	private OSSManager ossManager;

	@Autowired
	private IndexSettingService indexSettingService;
	@Autowired
	private PcSecondLevelClassificationService pcSecondLevelClassificationService;
	@Autowired
	private ContainerService containerService;
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
		            new CustomDateEditor(new SimpleDateFormat("YYYY-MM-DD hh:mm:ss"), true));  
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
	 * @Description 邮件模板列表
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年8月3日上午11:27:02
	 */
	@RequestMapping("/smsTemplate/list")
	public ModelAndView senderList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		ModelAndView mAndView=new ModelAndView();
		mAndView.setViewName("/smsTemplate/smsTemplate_list");
		PageBean pageBeanColumn=new PageBean();
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK,SystemConstant.TERMDATA.SENDER, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		pageBeanColumn.setPageSize(SystemConstant.CONTENT.PAGE_SIZE);
		termService.findTerm( reqData, pageBeanColumn);
		int totalCount = termService.getCountTermData(reqData);
		pageBeanColumn.setTotalCount(totalCount);
		Integer totalPage = pageBeanColumn.getTotalPage();
		request.setAttribute("pageCount", totalPage);
		return mAndView ;
	}
	@RequestMapping("/smsTemplate/list/{pageIndex}")
	public ModelAndView senderList(@PathVariable("pageIndex") Integer pageIndex,ReqData reqData	,HttpServletRequest request){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(SystemConstant.CONTENT.PAGE_SIZE);
		ReqData reqData2=new ReqData();
		reqData2.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.SENDER, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqData2);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		int totalCount = termService.getCountTermData(reqData);
		pageBean.setTotalCount(totalCount);
		termService.findTermWithBlob(reqData, pageBean);
		request.setAttribute("pageBean",pageBean) ;
		return new ModelAndView("/smsTemplate/smsTemplate_list_page") ;
	}
	/**
	 * 
	 * @Description 邮件模板新增
	 * @param termData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年8月3日上午11:27:14
	 */
	@RequestMapping("/smsTemplate/add")
	public ModelAndView addSender(TermData termData,HttpServletRequest request){
		ReqData reqData2=new ReqData();
		reqData2.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.SENDER, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqData2);
		termData.setVid(vocabulary.getId());
		termService.insertTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 邮件模板删除
	 * @Description 
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年8月3日上午11:27:26
	 */
	@RequestMapping("/smsTemplate/del/{id}")
	public ModelAndView delSender(@PathVariable("id") Long id,HttpServletRequest request){
		termService.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/mallSetting/smsTemplate/list") ;
	}
	@RequestMapping("/smsTemplate/edit/{id}")
	public ModelAndView editSender(@PathVariable("id") Long id,ReqData reqData,HttpServletRequest request){
		reqData2Params(reqData);
		TermData termData = termService.getTermData(id);
		request.setAttribute("termData", termData);
		return new ModelAndView("/smsTemplate/smsTemplate_edit") ;
	}
	/**
	 * 
	 * @Description 邮件模板更新
	 * @param termData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年8月3日上午11:27:39
	 */
	@RequestMapping("/smsTemplate/update")
	public ModelAndView updateSender(TermData termData,HttpServletRequest request){
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK,SystemConstant.TERMDATA.SENDER, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		termData.setVid(vocabulary.getId());
		termService.updateTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 
	 * @Description 配送方式列表显示
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午2:54:36
	 */
	@RequestMapping("/deliveryMode/list")
	public ModelAndView deliveryModeList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		reqData2Params(reqData);
		ModelAndView mAndView=new ModelAndView();
		mAndView.setViewName("/deliveryMode/deliveryMode_list");
		PageBean pageBeanColumn=new PageBean();
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue("mark", "delivery_mode", SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		pageBeanColumn.setPageSize(SystemConstant.CONTENT.PAGE_SIZE);
		termService.findTerm( reqData, pageBeanColumn);
		int totalCount = termService.getCountTermData(reqData);
		pageBeanColumn.setTotalCount(totalCount);
		Integer totalPage = pageBeanColumn.getTotalPage();
		request.setAttribute("pageCount", totalPage);
		return mAndView ;
	}
	@RequestMapping("/deliveryMode/list/{pageIndex}")
	public ModelAndView deliveryModeList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(SystemConstant.CONTENT.PAGE_SIZE);
		ReqData reqData2=new ReqData();
		reqData2.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.DELIVERY_MODE, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqData2);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		int totalCount = termService.getCountTermData(reqData);
		pageBean.setTotalCount(totalCount);
		termService.findTermWithBlob(reqData, pageBean);
		request.setAttribute("pageBean",pageBean) ;
		return new ModelAndView("/deliveryMode/deliveryMode_list_page") ;
	}
	@RequestMapping("/deliveryMode/add")
	public ModelAndView addDeliveryMode(TermData termData,HttpServletRequest request){
		ReqData reqData2=new ReqData();
		reqData2.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.DELIVERY_MODE, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqData2);
		termData.setVid(vocabulary.getId());
		termService.insertTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	@RequestMapping("/deliveryMode/del/{id}")
	public ModelAndView delDeliveryMode(@PathVariable("id") Long id,HttpServletRequest request){
		termService.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/mallSetting/deliveryMode/list") ;
	}
	@RequestMapping("/deliveryMode/edit/{id}")
	public ModelAndView editDeliveryMode(@PathVariable("id") Long id,ReqData reqData,HttpServletRequest request){
		reqData2Params(reqData);
		TermData termData = termService.getTermData(id);
		request.setAttribute("termData", termData);
		return new ModelAndView("/deliveryMode/deliveryMode_edit") ;
	}
	@RequestMapping("/deliveryMode/update")
	public ModelAndView updateDeliveryMode(TermData termData,HttpServletRequest request){
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK,SystemConstant.TERMDATA.DELIVERY_MODE, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		termData.setVid(vocabulary.getId());
		termService.updateTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 
	 * @Description 发票设置
	 * @param termData
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午2:54:15
	 */
	@RequestMapping("/invoiceSetting")
	public ModelAndView invoiceSetting(TermData termData,HttpServletRequest request,ReqData reqData)
	{	
		reqData2Params(reqData);
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.INVOICE_SETTING, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(),SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean=new PageBean();
		termService.findTermWithBlob(reqData, pageBean);
		Object terData=null;
		if(pageBean.getData().size()>0){
			terData= pageBean.getData().get(0);
		}
		request.setAttribute("terData", terData);
		return new ModelAndView("/invoiceSetting/invoiceSetting") ;
	}
	@RequestMapping("/invoiceSetting/update")
	public ModelAndView invoiceSettingUpdate(TermData termData,HttpServletRequest request,ReqData reqData)
	{	
		reqData2Params(reqData);
		termService.updateTermData(termData);
		return new ModelAndView("redirect:/mallSetting/invoiceSetting") ;
	}
	/**
	 * 
	 * @Description 邮件模板列表显示
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午2:53:16
	 */
	@RequestMapping("/emailTemplate/list")
	public ModelAndView emailList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		ModelAndView mAndView=new ModelAndView();
		mAndView.setViewName("/email/emailTemplate_list");
		PageBean pageBeanColumn=new PageBean();
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.EMAIL, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		pageBeanColumn.setPageSize(SystemConstant.CONTENT.PAGE_SIZE);
		termService.findTerm( reqData, pageBeanColumn);
		int totalCount = termService.getCountTermData(reqData);
		pageBeanColumn.setTotalCount(totalCount);
		Integer totalPage = pageBeanColumn.getTotalPage();
		request.setAttribute("pageCount", totalPage);
		return mAndView ;
	}
	@RequestMapping("/emailTemplate/list/{pageIndex}")
	public ModelAndView emailList(@PathVariable("pageIndex") Integer pageIndex,ReqData reqData,HttpServletRequest request){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean() ;
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(SystemConstant.CONTENT.PAGE_SIZE);
		ReqData reqData2=new ReqData();
		reqData2.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.EMAIL, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqData2);
		reqData.putValue(SystemConstant.TERMDATA.VID, vocabulary.getId(), SystemConstant.REQ_PARAMETER_EQ);
		int totalCount = termService.getCountTermData(reqData);
		pageBean.setTotalCount(totalCount);
		termService.findTermWithBlob(reqData, pageBean);
		request.setAttribute("pageBean",pageBean) ;
		return new ModelAndView("/email/emailTemplate_list_page") ;
	}
	/**
	 * 
	 * @Description 邮件模板添加
	 * @param termData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午2:53:04
	 */
	@RequestMapping("/emailTemplate/add")
	public ModelAndView addEmailTemplate(TermData termData,HttpServletRequest request){
		ReqData reqData2=new ReqData();
		reqData2.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.EMAIL, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqData2);
		termData.setVid(vocabulary.getId());
		termData.setStatus(SystemConstant.CONTENT.ZERO);
		termService.insertTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 
	 * @Description 邮件模板删除
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午2:52:54
	 */
	@RequestMapping("/emailTemplate/del/{id}")
	public ModelAndView deleEmailTemplate(@PathVariable("id") Long id,HttpServletRequest request){
		termService.deleteByPrimaryKey(id);
		return new ModelAndView("redirect:/mallSetting/emailTemplate/list") ;
	}
	/**
	 * 
	 * @Description 邮件模板编辑
	 * @param id
	 * @param reqData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午2:52:34
	 */
	@RequestMapping("/emailTemplate/edit/{id}")
	public ModelAndView editEmailTemplate(@PathVariable("id") Long id,ReqData reqData,HttpServletRequest request){
		reqData2Params(reqData);
		TermData termData = termService.getTermData(id);
		request.setAttribute("termData", termData);
		return new ModelAndView("/email/emailTemplate_edit") ;
	}
	/**
	 * 
	 * @Description 邮件模板更新
	 * @param termData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午2:52:21
	 */
	@RequestMapping("/emailTemplate/update")
	public ModelAndView updateEmail(TermData termData,HttpServletRequest request){
		ReqData reqDataVocabulary=new ReqData();
		reqDataVocabulary.putValue(SystemConstant.TERMDATA.MARK, SystemConstant.TERMDATA.EMAIL, SystemConstant.REQ_PARAMETER_EQ);
		Vocabulary vocabulary = vocabularyService.getVocabularyByMark(reqDataVocabulary);
		termData.setVid(vocabulary.getId());
		termService.updateTermData(termData);
		return new ModelAndView("redirect:list") ;
	}
	/**
	 * 货柜保存
	 * @Description 
	 * @param container
	 * @return
	 * @Author liuw
	 * @Date 2017年8月11日下午6:05:53
	 */
	@RequestMapping("/saveAddContainer")
	public RespData saveAddContainer(Container container){
		Map<String, Object> data=new HashMap<String, Object>();
		indexSettingService.insert(container);
		data.put("result", new String("保存成功，请点击确定!"));
		data.put("containerId", container.getId());
		RespData respData=new RespData();
		respData.setData(data);
		return respData;
	}
	/**
	 * 货柜更新
	 * @Description 
	 * @param container
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日下午3:12:04
	 */
	@RequestMapping("/saveUpdateContainer")
	public RespData saveUpdateContainer(Container container){
		containerService.updateContainer(container);
		Map<String, Object> data=new HashMap<>();
		data.put("containerId", container.getId());
		RespData respData=new RespData();
		respData.setData(data);
		return respData;
	}
	/**
	 * 设置新增首页（先预留着）
	 * @Description 
	 * @param indexConfig
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日下午1:57:07
	 */
	@RequestMapping("/insertIndexConfig")
	public RespData insertIndexConfig(IndexConfig indexConfig){
		indexSettingService.insert(indexConfig);
		RespData respData=new RespData();
		respData.setData("ok，首页设置成功");
		return respData;
	}
	/**
	 * 更新首页设置
	 * @Description 
	 * @param indexConfig
	 * @param isIndex 用于区分到底是修改的首页配置还是二级分类配置 0首页 1二级分类 2 app首页 4 精选水果
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日下午1:56:58
	 */
	@RequestMapping("/setIndexConfig")
	public ModelAndView upateIndexConfig(IndexConfig indexConfig,PcSecondLevelClassification pcSecondLevelClassification,Integer isIndex,HttpServletRequest request){
		//如果不为空，说明是已经设置了的，只需要更新即可
		if(!ObjectUtils.isEmpty(indexConfig.getId())){
			indexSettingService.updateIndexConfig(indexConfig);
		}else{
			//为空，说明是需要插入，为新增加
			indexSettingService.insert(indexConfig);
			Long id = indexConfig.getId();
		    //设置配置记录表id
			pcSecondLevelClassification.setConfigId(id);
			pcSecondLevelClassificationService.insert(pcSecondLevelClassification);
		}
		if(isIndex==2){
			return new ModelAndView("redirect:/advertisement/pcIndexSetting?isSuccess=0");
		}else if (isIndex == 0) {
			return new ModelAndView("redirect:/advertisement/appIndexSetting?isSuccess=0");
		} else if(isIndex == 3){
		    return new ModelAndView("redirect:/advertisement/crossBorderSetting?isSuccess=0");
		}else if (isIndex == 4) {
			 return new ModelAndView("redirect:/advertisement/choiceFruitSetting?isSuccess=0");
		}else {
            return new ModelAndView("redirect:/advertisement/secondLevelClassificationSetting?categoryId="+pcSecondLevelClassification.getCategoryId()+"&isSuccess=0");
        } 
		
	}
		
	
}
