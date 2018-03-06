package com.zfj.xmy.cms.web.controller.goods;  

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.service.cms.TermDataService;
import com.zfj.xmy.goods.service.cms.VocabularyService;

/** 
 * @Title: BrandController.java 
 * @Package com.zfj.xmy.cms.web.controller 
 * @Description: 
 * @author hexw
 * @date 2017年6月20日 下午2:30:47 
 */
@RequestMapping("/brand")
@RestController
public class BrandController extends BaseController {
	
	
	@Autowired
	private TermDataService termDataService;
	
	@Autowired
	private VocabularyService vocabularyService;
	
	/**
	 * 跳转到添加品牌界面
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月27日 上午11:46:20
	 */
	@RequestMapping("/toAddBrand")
	public ModelAndView toAddBrand(){
		return new ModelAndView("/goods/brand/addbrand");
	}
	
	/**
	 * 新增品牌
	 * @param termData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月20日 下午2:40:07
	 */
	@RequestMapping("/addtBrand")
	public ModelAndView addBrand(TermData termData) {
		termData.setVid(vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.BRAND));
		termDataService.insertTermData(termData);
		return new ModelAndView("redirect:/brand/brandList");
	}
	
	/**
	 * 品牌列表
	 * @param request
	 * @param reqData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月20日 下午3:05:47
	 */
	@RequestMapping("/brandList")
	public ModelAndView brandList(HttpServletRequest request ,ReqData reqData){
		PageBean pageBean = new  PageBean();
		reqData2Params(reqData);
		termDataService.selectTermDataList(reqData, vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.BRAND),pageBean);
		request.setAttribute("totalPage",pageBean.getTotalPage()) ;
		return new ModelAndView("/goods/brand/brand_list"); 
	}
	
	@RequestMapping("/brandList/{pageIndex}")
	public ModelAndView brandList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request,PageBean pageBean){
		reqData2Params(reqData);
		pageBean.setPageIndex(pageIndex);
		reqData.setSortname("sn");
		reqData.setSortorder("desc");
		termDataService.selectTermDataList(reqData,vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.BRAND),pageBean);
		request.setAttribute("tlist", pageBean.getData());
		return new ModelAndView("/goods/brand/brand_list_page"); 
	}
	
	/**
	 * 修改品牌状态
	 * @param id
	 * @param requset
	 * @param reqData
	 * @return    
	 * Date:2017年6月20日 下午3:57:26
	 */
	@RequestMapping("/updateStatus")
	public RespData updateStatus(long id,HttpServletRequest requset,
		ReqData reqData,String status){
		reqData2Params(reqData);
		RespData respData = new RespData() ; 
		respData.setData(termDataService.updateStatus(id, status));
		return respData;
	}
	
	/**
	 * 修改平牌状态
	 * @param idStr
	 * @param status
	 * @return    
	 * @return RespData    
	 * Date:2017年7月11日 下午3:00:22
	 */
	@RequestMapping("/batchUpdateStatus")
	public RespData batchUpdateStatus (String  idStr, String status){
		RespData respData = new  RespData();
		String [] idArry = idStr.split("\\,");
		respData.setData(termDataService.updateStatus(idArry,status));
		return respData;
	}
	
	
	/**
	 * 
	 * @param id
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月20日 下午4:45:53
	 */
	@RequestMapping("/toUpdateBrand")
	public ModelAndView toUpdateBrand(long id,HttpServletRequest request){
		request.setAttribute("termData", termDataService.getTermData(id));
		return new ModelAndView("/goods/brand/editbrand");
	}
	
	/**
	 * 修改品牌
	 * @param termData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月20日 下午5:04:24
	 */
	@RequestMapping("/updateBrand")
	public ModelAndView updateBrand(TermData termData){
		termDataService.updateTermData(termData);
		return new ModelAndView("redirect:/brand/brandList");
	}
}
  