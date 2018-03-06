package com.zfj.xmy.cms.web.controller.goods;  

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.cms.web.common.GoodsExcel;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.service.BaseCommonService;
import com.zfj.xmy.goods.persistence.common.pojo.dto.TermDataDir;
import com.zfj.xmy.goods.service.cms.CategoryService;
import com.zfj.xmy.goods.service.cms.CategoryWordSegService;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;
import com.zfj.xmy.goods.service.cms.TermDataService;

/** 
 * @Title: GoodsController.java 
 * @Package com.zfj.xmy.cms.web.controller 
 * @Description: 
 * @author hexw
 * @date 2017年6月9日 下午4:54:30 
 */
@RequestMapping("/goods")
@RestController
public class GoodsController extends BaseController{
	
	@Autowired
	private CmsGoodsService goodsService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TermDataService termDataService;
	
	@Autowired
	private CategoryWordSegService categoryWordSegService;
	
	@Autowired
	private BaseCommonService baseCommonService;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	/**
	 * 选择分类列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月21日 下午5:52:08
	 */
	@RequestMapping("/toCategoryList")
	public ModelAndView toCategoryList(HttpServletRequest request){
		return new ModelAndView("/goods/category_list");
	}
	
	/**
	 * 选择分类列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月21日 下午5:52:08
	 */
	@RequestMapping("/toChooseCategoryWordSeg")
	public ModelAndView toChooseCategoryWordSeg(HttpServletRequest request){
		return new ModelAndView("/goods/category_word_seg_list");
	}
	
	@RequestMapping("/categoryList")
	public RespData categoryList(HttpServletRequest request){
		RespData respData = new RespData() ;
		List<TermDataDir> list = categoryService.findCategory(SystemConstant.TERMDATA.YES); 
		respData.setData(list);
		return respData;
	}
	
	
	/**
	 * 选择列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月23日 下午5:04:28
	 */
	@RequestMapping("/chooseList")
	public ModelAndView chooseList(HttpServletRequest request,String vid,ReqData reqData){
		reqData2Params(reqData);
		request.setAttribute("vid", vid);
		request.setAttribute("totalCount",10) ;
		return new ModelAndView("/goods/choose_list");
	}
	@RequestMapping("/chooseList/{pageIndex}")
	public ModelAndView chooseList(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request
			,ReqData reqData){
		reqData2Params(reqData);
		reqData.putValue("status","0",SystemConstant.REQ_PARAMETER_EQ) ;
		reqData.setSortname("name");
		reqData.setSortorder("desc");
		List<TermData> list = termDataService.selectTermDataByVid(reqData);
		request.setAttribute("list", list);
 		return new  ModelAndView("/goods/choose_list_page");
	}
	
	/**
	 * 跳转到新增商品界面
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月19日 上午9:40:13
	 */
	@RequestMapping("/toAddGoods")
	public  ModelAndView toAddGoods(HttpServletRequest request , ReqData reqData){
		reqData2Params(reqData);
		return new  ModelAndView("/goods/addgoods");
	}
	
	/**
	 * 添加商品
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月19日 上午9:29:58
	 */
	@RequestMapping("/addGoods")
	public ModelAndView addGoods(HttpServletRequest request, ReqData reqData,
			GoodsWithBLOBs goods,String specIdStr) {
		reqData2Params(reqData);
		String[] fileName = request.getParameterValues("goodsImgPath");
		String[] sort = request.getParameterValues("sort");
		goodsService.insertGood(goods,fileName,sort,specIdStr);
		baseCommonService.refreshIndexHomePage();//更新redis
		return new ModelAndView("redirect:/goods/goodsList");
	}
	
	
	/**
	 * 商品列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月19日 上午10:55:04
	 */
	@RequestMapping("/goodsList")
	public ModelAndView goodsList(HttpServletRequest request,ReqData reqData,Integer pageIndex){
		reqData2Params(reqData);
		PageBean pageBean = new  PageBean();
		reqData2Params(reqData);
		reqData.putValue("is_putway", SystemConstant.GoodsConstant.PUTWAY, SystemConstant.REQ_PARAMETER_EQ);
		goodsService.selectGoodsAndPage(reqData, pageBean);
		request.setAttribute("totalPage",pageBean.getTotalPage()) ;
		request.setAttribute("pageIndex", pageIndex);
		return new ModelAndView("/goods/goods_list");
	}
	
	@RequestMapping("/goodsList/{pageIndex}")
	public ModelAndView goodsList(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request, ReqData reqData, PageBean pageBean) {
		reqData2Params(reqData);
		pageBean.setPageIndex(pageIndex);
		reqData.setSortname("putwayTime");
		reqData.setSortorder("desc");
		reqData.putValue("is_putway", SystemConstant.GoodsConstant.PUTWAY, SystemConstant.REQ_PARAMETER_EQ);
		goodsService.selectGoodsAndPage(reqData, pageBean);
		request.setAttribute("glist", pageBean.getData());
		request.setAttribute("xmyPcUrl", SystemConstant.XMYONLINEURL.XMY_PC_URL); //香满园线上pc地址
		request.setAttribute("pageIndex", pageIndex);
		return new ModelAndView("/goods/goods_list_page");
	}
	
	/**
	 * 下架商品列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月29日 下午2:24:42
	 */
	
	@RequestMapping("/outWayGoodsList")
	public ModelAndView outWayGoodsList(HttpServletRequest request,ReqData reqData,Integer pageIndex){
		PageBean pageBean = new  PageBean();
		reqData2Params(reqData);
		reqData.putValue("is_putway", "1", SystemConstant.REQ_PARAMETER_EQ);
		goodsService.selectGoodsAndPage(reqData, pageBean);
		request.setAttribute("totalPage",pageBean.getTotalPage()) ;
		request.setAttribute("pageIndex", pageIndex);
		return new ModelAndView("/goods/outway_goods_list");
	}
	
	@RequestMapping("/outWayGoodsList/{pageIndex}")
	public ModelAndView outWayGoodsList(
			@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request, ReqData reqData, PageBean pageBean) {
		reqData2Params(reqData);
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("is_putway", "1", SystemConstant.REQ_PARAMETER_EQ);
		goodsService.selectGoodsAndPage(reqData, pageBean);
		request.setAttribute("glist", pageBean.getData());
		request.setAttribute("pageIndex", pageIndex);
		return new ModelAndView("/goods/outway_goods_list_page");
	}
			
	
	/**
	 * 修改上架状态
	 * @param idStr id字符串
	 * @param status
	 * @return    
	 * Date:2017年6月22日 下午4:59:40
	 */
	@RequestMapping("/updatePutWayStatus")
	public RespData updatePutwayStatus(String idStr, String status){
		RespData respData = new RespData() ;
		String [] idArry = idStr.split("\\,");
		respData.setData(goodsService.updatePutwayStatus(idArry, status));
		baseCommonService.refreshIndexHomePage();//更新redis
		return respData;
	}
	
	/**
	 * 批量删除商品  (只做逻辑修改)
	 * @param idStr
	 * @return    
	 * Date:2017年6月27日 上午9:54:55
	 */
	@RequestMapping("/deleteGoods")
	public RespData deleteGoods(String idStr){
		RespData respData = new RespData() ;
		String [] idArry = idStr.split("\\,");
		respData.setData(goodsService.deleteGoods(idArry));
		baseCommonService.refreshIndexHomePage();//更新redis
		return respData;
	}
	
	/**
	 * 跳转到修改商品界面
	 * @param id
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月26日 下午4:54:30
	 */
	@RequestMapping("/toUpdateGoods")
	public ModelAndView toUpdateGoods(long id, HttpServletRequest request,Integer pageIndex){
		request.setAttribute("goods", goodsService.getGoods(id));
		request.setAttribute("imgData", goodsService.findImage(id));
		//商品搜索词汇
		List<String> list = categoryWordSegService.findGoodsSpec(id);
		request.setAttribute("specData", list);
		request.setAttribute("pageIndex", pageIndex);
		return new ModelAndView("/goods/editgoods");
	}
	
	/**
	 * 
	 * @param request
	 * @param reqData
	 * @param goods
	 * @param specIdStr 商品分类规格
	 * @param pageIndex
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年11月7日 上午10:46:27 
	 * @author hexw
	 */
	@RequestMapping("/updateGoods")
	public ModelAndView updateGoods(HttpServletRequest request, ReqData reqData,
			GoodsWithBLOBs goods,String specIdStr,Integer pageIndex ) {
		reqData2Params(reqData);
		String[] fileName = request.getParameterValues("goodsImgPath"); //商品图片
		String[] sort = request.getParameterValues("sort"); //商品图片权重
		goodsService.updateGoods(goods,fileName,sort, specIdStr);
		//return new ModelAndView("redirect:/goods/goodsList?pageIndex="+pageIndex);
		baseCommonService.refreshIndexHomePage();//更新redis
		return new ModelAndView("redirect:/goods/goodsList");
	}
	
	
	/**
	 * 复制商品
	 * @param request
	 * @param goods
	 * @return    
	 * @return RespData    
	 * Date:2017年11月7日 上午10:53:03 
	 * @author hexw
	 */
	@RequestMapping("/copyGoods")
	public RespData copyGoods(HttpServletRequest request,Long goodsId){
		RespData respData = new RespData();
		respData.setData(goodsService.copyGoods(goodsId));
		baseCommonService.refreshIndexHomePage();//更新redis
		return respData;
	}
	
	
	/**
	 * 删除图片商品
	 * @param id
	 * @return    
	 * @return RespData    
	 * Date:2017年7月13日 下午8:20:12
	 */
	@RequestMapping("/deleteGoodsImg")
	public RespData deleteGoodsImg(String id){
		RespData respData = new RespData();
		respData.setData(goodsService.deleteGoodsImage(id));
		baseCommonService.refreshIndexHomePage();//更新redis
		return respData;
	}
	
	/**
	 * 跳转到商品图片关联界面
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月29日 上午10:50:05
	 */
	@RequestMapping("/toRelevanceGoodsImg")
	public ModelAndView toRelevanceGoodsImg(HttpServletRequest request){
		
		return new ModelAndView("/goods/relevance_goodsImg");
	}
	
	/**
	 * 商品图片关联
	 * @param zipFile    
	 * @return void    
	 * Date:2017年7月19日 上午10:18:23 
	 * @author hexw
	 */
	@RequestMapping("/relevanceGoodsImg")
	public RespData relevanceGoodsImg(String  zipFile,String ossPath,HttpServletRequest request){
		RespData respData = new  RespData();
		String targetPath = request.getSession().getServletContext().getRealPath("");
		Map<String,Object> result = goodsService.ImgCorrelation(zipFile,targetPath,ossPath);
		respData.setData(result);
		//baseCommonService.refreshIndexHomePage();//更新redis
		return respData;
	}
	
	/**
	 * 查询所选分类的分词
	 * @param id
	 * @return    
	 * @return RespData    
	 * Date:2017年8月9日 下午2:51:40 
	 * @author hexw
	 */
	@RequestMapping("/findCategoryWordSeg")
	public RespData findCategoryWordSeg(long id){
		RespData respData = new  RespData();
		respData.setData(categoryWordSegService.findCategoryWordSegByCid(id));
		return respData;
	}
	
	/**
	 * 查找商品code是否存在 0.不存在1.存在
	 * @param code
	 * @return
	 */
	@RequestMapping("/findGoodsCode")
	public RespData findGoodsCode(String code){
		RespData respData = new  RespData();
		respData.setData(goodsService.findGoodsCode(code));
		return respData;
	}
	/**
	 * 导出商品数据
	 * @param typeName
	 * @param brandName
	 * @param supplierName
	 * @param code
	 * @param goodsName
	 * @return Integer
	 * @author lij
	 * @date 2018年1月22日 下午3:59:01
	 */
	@RequestMapping("/excle")
	public Integer excleGodos(String typeName,String brandName,String supplierName,String code,String goodsName,HttpServletResponse response){
		ReqData reqData = new ReqData();
		if(!ObjectUtils.isEmpty(typeName)){
			reqData.putValue("category_name", typeName, SystemConstant.REQ_PARAMETER_CN);
		}
		if(!ObjectUtils.isEmpty(brandName)){
			reqData.putValue("brand_name", brandName, SystemConstant.REQ_PARAMETER_CN);
		}
		if(!ObjectUtils.isEmpty(supplierName)){
			reqData.putValue("supplier_name", supplierName, SystemConstant.REQ_PARAMETER_CN);
		}
		if(!ObjectUtils.isEmpty(code)){
			reqData.putValue("code", code, SystemConstant.REQ_PARAMETER_CN);
		}
		if(!ObjectUtils.isEmpty(goodsName)){
			reqData.putValue("name", goodsName, SystemConstant.REQ_PARAMETER_CN);
		}
		List<Goods> goodsList = goodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		GoodsExcel excel = new GoodsExcel();
		excel.excleGoods(goodsList, response);
		return null;
	}
	
	
	

}
  