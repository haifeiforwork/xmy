package com.zfj.xmy.cms.web.controller.pcgoodset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.PcGoodsSeting;
import com.zfj.xmy.common.service.BaseCommonService;
import com.zfj.xmy.goods.service.cms.PcGoodsSetService;

@RestController
public class PcGoodsSetController extends BaseController{
	
	@Autowired
	private PcGoodsSetService pcGoodsSetService;
	
	@Autowired
	private BaseCommonService baseCommonService;
	
	@RequestMapping("/pcgoodslist")
	public ModelAndView finAllsys(HttpServletRequest request, ReqData reqData) {
		reqData2Params(reqData);
		PageBean pageBean = new PageBean();
		List<PcGoodsSeting> findPcGoodsSeting = pcGoodsSetService.findPcGoodsSeting(reqData, pageBean);
		request.setAttribute("countPage", pageBean.getTotalPage());
		return new ModelAndView("/pcgoodset/pc_goods_list");
	}
	/**
	 * @param pageIndex
	 * @param request
	 * @param reqData
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年8月15日 下午7:32:21
	 * 分页查询前台设置商品名称
	 */
	@RequestMapping("/pcgoodslist/{pageIndex}")
	public ModelAndView getPcGoodsList(@PathVariable("pageIndex") Integer pageIndex,HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(10);
		List<PcGoodsSeting> findPcGoodsSeting = pcGoodsSetService.findPcGoodsSeting(reqData, pageBean);
		request.setAttribute("pcGoodsList", findPcGoodsSeting);
		return new ModelAndView("/pcgoodset/pc_goods_list_page");
	}
	/**
	 * @param type
	 * @param goodsId
	 * @return String
	 * @author lij
	 * @date 2017年8月15日 下午7:46:37
	 * 添加商品
	 */
	@RequestMapping("/addPcgoods/{type}/{goodsid}")
	public String addPcGoods(@PathVariable("type") Integer type,@PathVariable("goodsid") Long goodsId){
		pcGoodsSetService.addPcGoods(type, goodsId);
		baseCommonService.refreshIndexHomePage();//更新首页redis
		return null;
	}
	/**
	 * 删除单个
	 * @param goodsid
	 * @param typeId
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月14日 下午4:26:04
	 */
	@RequestMapping("/delPcGoods/{goodsid}/{typeId}")
	public ModelAndView delPcGoods(@PathVariable("goodsid") Long goodsid,@PathVariable("typeId") Integer typeId, HttpServletRequest request){
		pcGoodsSetService.deletePcGoods(typeId, goodsid);
		baseCommonService.refreshIndexHomePage();//更新首页redis
		return null;
	}
	/**
	 * 批量删除
	 * @param goodsid
	 * @param typeId
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月14日 下午4:26:22
	 */
	@RequestMapping("/delPcGood")
	public ModelAndView delPcGood( @Param("ids") String ids,HttpServletRequest request){
		pcGoodsSetService.deletePcGood(ids);
		baseCommonService.refreshIndexHomePage();//更新首页redis
		return null;
	}
}