package com.zfj.xmy.cms.web.controller.goods;  

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.common.persistence.pojo.CategoryWordSeg;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegDto;
import com.zfj.xmy.goods.persistence.common.pojo.dto.CategoryWordSegVo;
import com.zfj.xmy.goods.persistence.common.pojo.dto.TermDataDir;
import com.zfj.xmy.goods.service.app.AppCategoryService;
import com.zfj.xmy.goods.service.cms.CategoryService;
import com.zfj.xmy.goods.service.cms.CategoryWordSegService;
import com.zfj.xmy.goods.service.cms.TermDataService;
import com.zfj.xmy.goods.service.cms.VocabularyService;

/** 
 * @Title: categoryController.java 
 * @Package com.zfj.xmy.cms.web.controller 
 * @Description: 
 * @author hexw
 * @date 2017年6月19日 上午11:19:39 
 */
@RequestMapping("/category")
@RestController
public class CategoryController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TermDataService termDataService;
	
	
	@Autowired
	private VocabularyService vocabularyService;
	
	@Autowired
	private CategoryWordSegService categoryWordSegService;
	

	/**
	 * 跳转到添加分类列表
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月15日 上午8:50:19
	 */
	@RequestMapping("/toAddCategory")
	public ModelAndView  toAddCategory(HttpServletRequest request){
		List<TermDataDir> list = categoryService.findCategory(null);
		request.setAttribute("cList", list);
		return new ModelAndView("/goods/category/addCategory");
	}
	
	/**
	 * 新增分类
	 * @param termData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月15日 下午2:52:39
	 */
	@RequestMapping("/addCategory")
	public  ModelAndView addCategory(TermData termData , HttpServletRequest request){
		termData.setStatus(0);
		termData.setVid(vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.CATEGORY));
		termDataService.insertTermData(termData);
		return new  ModelAndView("redirect:/category/categoryList");
		
	}
	
	/**
	 * 分类列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月19日 下午2:15:33
	 */
	@RequestMapping("/categoryList")
	public ModelAndView categoryList(HttpServletRequest request ,ReqData reqData){
		PageBean  pageBean = new  PageBean();
		reqData2Params(reqData);
		reqData.putValue("vid",vocabularyService.getVocabularyVid(SystemConstant.TERMDATA.CATEGORY),SystemConstant.REQ_PARAMETER_EQ) ;
		request.setAttribute("totalCount", termDataService.getCountTermData(reqData));
		return new  ModelAndView("/goods/category/category_list");
	}
	
	@RequestMapping("/categoryList/{pageIndex}")
	public ModelAndView categoryList(
			@PathVariable("pageIndex") Integer pageIndex, ReqData reqData,
			HttpServletRequest request,PageBean pageBean) {
		reqData2Params(reqData);
		List<TermDataDir> dList = categoryService.selectCategory(reqData);
		
		request.setAttribute("dList", dList);
		return new ModelAndView("/goods/category/category_list_page");
	}
	

	@RequestMapping("/updateStatus")
	public RespData updateStatus(long id, int status){
		RespData respData = new RespData();
		respData.setData(categoryService.updateCategoryStatus(id, status));
		return respData;
	}
	
	
	
	/**
	 *  到编辑分类界面
	 * @param request
	 * @param req
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月19日 下午3:26:24
	 */
	@RequestMapping("/toEditCategory")
	public ModelAndView toEditCategory(HttpServletRequest request, long id,
			ReqData reqData) {
		reqData2Params(reqData);
		List<TermDataDir> list = categoryService.findCategory(null);//一级分类列表
		request.setAttribute("list", list);
		TermData termData = termDataService.getTermData(id); //类别基本信息
		request.setAttribute("t", termData);
		List<CategoryWordSegDto> specList = categoryWordSegService.findCategoryWordSegDto(id); //类别  种类分词
		request.setAttribute("slist", specList);
		List<CategoryWordSeg>	wordSegList = categoryService.findCategoryWordSeg(id); //类别 规格分词
		request.setAttribute("wordSegList", wordSegList);
		return new ModelAndView("/goods/category/editCategory");
	}
	
	
	/**
	 * 修改分类
	 * @param termData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月19日 下午3:49:08
	 */
	@RequestMapping("/updateCategory")
	public ModelAndView updatecategory(TermData termData, HttpServletRequest request){
		categoryService.updateCategory(termData);
		return new ModelAndView("redirect:/category/categoryList");
	}
	
	/***************************************************类别规格模块开始******************************************************************************/
	
	/**
	 *  编辑分类规格
	 * @param id 规格id
	 * @param cid 分类id
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月28日 上午11:12:57
	 */
	@RequestMapping("/editSpec")
	public ModelAndView editSpec(String cid, String id,
			HttpServletRequest request) {
		request.setAttribute("cid", cid);
		if (!StringUtil.isEmpty(id)) {
			CategoryWordSegDto dto = categoryWordSegService.getCategoryWordSegDto(Long.parseLong(id));
			request.setAttribute("data", dto);
		}
		return new ModelAndView("/goods/category/editspec");
	}
	
	
	/**
	 * 新增分类规格
	 * @param spec
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月28日 上午11:42:36
	 */
	@RequestMapping("/addSpec")
	public RespData addSpec(CategoryWordSegVo vo){
		RespData respData = new  RespData();
		respData.setData(categoryWordSegService.insertCategorySpec(vo));
		return respData;
	}
	
	/**
	 * 修改分类规格
	 * @param spec
	 * @return    
	 * @return JSONObject    
	 * Date:2017年6月28日 下午3:44:37
	 */
	@RequestMapping("/updateSpec")
	public RespData updateSpec(CategoryWordSegVo vo){
		RespData respData = new  RespData();
		respData.setData(categoryWordSegService.updateCategoryWordSeg(vo));
		return respData;
	}
	
	/**
	 * 删除规格
	 * @param id
	 * @return    
	 * @return JSONObject    
	 * Date:2017年6月28日 下午4:00:58
	 */
	@RequestMapping("/deleteSpec")
	public RespData deleteSpec(long id){
		RespData respData = new  RespData();
		respData.setData(categoryWordSegService.deleteSpec(id));
		return respData;
	}
	
	/******************************************类别分词模块开始********************************************************************/
	
	/**
	 *  编辑类别分词
	 * @param id 分词id
	 * @param cid 分类id
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月28日 上午11:12:57
	 */
	@RequestMapping("/editWordSeg")
	public ModelAndView editWordSeg(String cid, String id,
			HttpServletRequest request) {
		request.setAttribute("cid", cid);
		if (!StringUtil.isEmpty(id)) {
			CategoryWordSeg wordSeg  = categoryWordSegService.getCategoryWordSeg(Long.parseLong(id));
			request.setAttribute("data", wordSeg);
		}
		return new ModelAndView("/goods/category/editWordSeg");
	}
	
	/**
	 * 添加分词
	 * @param vo
	 * @return    
	 * @return RespData    
	 * Date:2017年8月12日 下午1:35:53 
	 * @author hexw
	 */
	@RequestMapping("/addWordSeg")
	public RespData addWordSeg(CategoryWordSegVo vo){
		RespData respData = new RespData();
		respData.setData(categoryService.insertCategoryWordSeg(vo));
		return respData;
		
	}
	
	/**
	 * 修改类别分词
	 * @param categoryWordSeg
	 * @return    
	 * @return RespData    
	 * Date:2017年8月12日 下午2:18:41 
	 * @author hexw
	 */
	@RequestMapping("/updateWordSeg")
	public RespData updateWordSeg(CategoryWordSeg categoryWordSeg){
		RespData respData = new RespData();
		respData.setData(categoryService.updateCategoryWordSeg(categoryWordSeg));
		return respData;
	}
	
	/**
	 * 删除分词
	 * @param id    
	 * @return void    
	 * Date:2017年8月12日 下午2:20:34 
	 * @author hexw
	 */
	@RequestMapping("/deleteWordSeg")
	public RespData deleteWordSeg(long id){
		RespData respData = new RespData();
		respData.setData(categoryService.deleteCategoryWordSeg(id));
		return respData;
	}
}
  