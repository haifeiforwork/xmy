package com.zfj.xmy.wap.web.controller.classfy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.activity.service.wap.WapColumService;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppAdImageDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppTermDataDir;
import com.zfj.xmy.goods.service.app.AppCategoryService;
import com.zfj.xmy.wap.web.controller.common.CommonController;

/**
 * 商品分类控制层
 * @author cj
 * @createDate 2017年10月24日
 *
 */
@Controller
@RequestMapping("/classfy")
public class ClassfyController extends CommonController{
	
	@Autowired
	private WapColumService columService;
	@Autowired
	private AppCategoryService appCategoryService ;
	
	/**分类主页面路径*/
	private final static String MAIN_PAGE = "classfy/classfy" ;
	/**分类子页面路径*/
	private final static String CLASSFY_SECOND = "classfy/classfy_second" ;
	/**模板标志*/
	private final static String MODEL = "model" ;
	
	/**
	 * 分类主页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/classfy")
	public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse response){
		
		//查询商品导航
		List<AppTermDataDir> goodsNavigated = appCategoryService.findCategory();
		
		request.setAttribute("navigation", goodsNavigated);
		request.setAttribute(MODEL, "classfy");
		
		return new ModelAndView(MAIN_PAGE);
	}
	
	/**
	 * 查询子分类
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/classfySecond")
	public ModelAndView classfySecond(HttpServletRequest request, HttpServletResponse response, String parentId){
		
		//查询子分类
		List<PcDownColumDto> child = columService.findChildrenColum(parentId,VocabularyConstant.VOC_CATEGORY);
		List<AppAdImageDir> imgList = appCategoryService.findCategoryTopImg(Long.valueOf(parentId));
		request.setAttribute("child", child);
		request.setAttribute("imgList", imgList);
		
		return new ModelAndView(CLASSFY_SECOND);
	}
}
