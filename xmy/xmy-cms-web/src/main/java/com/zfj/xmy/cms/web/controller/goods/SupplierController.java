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
 * @Title: SupplierController.java 
 * @Package com.zfj.xmy.cms.web.controller 
 * @Description:   商品供货商控制类
 * @author hexw
 * @date 2017年6月23日 上午9:22:48 
 */
@RequestMapping("/supplier")
@RestController
public class SupplierController extends BaseController {

	
	@Autowired
	private TermDataService termDataService;
	
	@Autowired
	private VocabularyService vocabularyService;
	
	/**
	 * 跳转到添加供应商的界面
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月23日 上午11:33:34
	 */
	@RequestMapping("/toAddSupplier")
	public ModelAndView toAddSupplier(HttpServletRequest request){
		return new ModelAndView("/goods/supplier/addsupplier");
	}
	
	/**
	 * 
	 * @param tremData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月23日 上午11:48:37
	 */
	@RequestMapping("/addSupplier")
	public ModelAndView addSupplier(TermData tremData){
		tremData.setVid(vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.SUPPLIER));
		termDataService.insertTermData(tremData);
		return new ModelAndView("redirect:/supplier/supplierList");
	}
	
	/**
	 * 供应商列表
	 * @param request
	 * @param reqData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月23日 下午1:43:11
	 */
	@RequestMapping("/supplierList")
	public ModelAndView supplierList(HttpServletRequest request,ReqData reqData){
		PageBean pageBean = new PageBean();
		reqData2Params(reqData) ;
		reqData.setSortname("name");
		reqData.setSortorder("desc");
		termDataService.selectTermDataList(reqData,vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.SUPPLIER),pageBean);
		request.setAttribute("totalPage", pageBean.getTotalPage());
		return new ModelAndView("/goods/supplier/supplier_list");
	}
	@RequestMapping("/supplierList/{pageIndex}")
	public ModelAndView supplierList(
			@PathVariable("pageIndex") Integer pageIndex, ReqData reqData,
			HttpServletRequest request, PageBean pageBean) {
		String parameter = request.getParameter("name");
		System.out.println(parameter+"--------------");
		pageBean.setPageIndex(pageIndex);
		termDataService.selectTermDataList(reqData, vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.SUPPLIER),pageBean);
		request.setAttribute("list", pageBean.getData());
		return new ModelAndView("/goods/supplier/supplier_list_page");
	}
	
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return    
	 * Date:2017年6月23日 下午2:00:58
	 */
	@RequestMapping("/updateStatus")
	public RespData updateStatus(long id ,String status){
		RespData respData = new RespData() ;
		respData.setData(termDataService.updateStatus(id, status));
		return respData;
	}
	
	/**
	 * 修改供应商界面
	 * @param id
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月23日 下午2:07:42
	 */
	@RequestMapping("/toUpdateSupplier")
	public ModelAndView toUpdateSupplier(long id,HttpServletRequest request){
		request.setAttribute("termData",termDataService.getTermData(id) );
		return new ModelAndView("/goods/supplier/editsupplier");
	}
	
	/**
	 * 修改供应商
	 * @param termData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月23日 下午2:11:49
	 */
	@RequestMapping("/updateSupplier")
	public ModelAndView updateSupplier(TermData termData){
		termDataService.updateTermData(termData);
		return new ModelAndView("redirect:/supplier/supplierList");
	}
	
	/**
	 * 批量修改状态
	 * @param idStr
	 * @param status
	 * @return    
	 * Date:2017年6月29日 上午9:37:41
	 */
	@RequestMapping("/batchUpdateStatus")
	public RespData batchUpdateStatus(String idStr, String status){
		RespData respData = new RespData();
		String [] idArry = idStr.split("\\,");
		respData.setData(termDataService.updateStatus(idArry,status));
		return respData;
	}
}  