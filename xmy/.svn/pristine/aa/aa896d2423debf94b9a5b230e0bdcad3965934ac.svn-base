package com.zfj.xmy.pc.web.controller.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcSearchDto;
import com.zfj.xmy.goods.service.pc.PcSearchService;

@RestController
@RequestMapping("/search")
public class SearchGoodsController {
	
	@Autowired
	private  PcSearchService pcSearchService;
	
	@RequestMapping("/goods/{pageIndex}/{orderMethod}")
	public ModelAndView searchGoodsByName(@PathVariable("pageIndex") Integer pageIndex,@Param("goodsName") String goodsName
			,@PathVariable("orderMethod") Integer orderMethod,HttpServletRequest request){
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(3);
		
		List<Integer> page = new ArrayList<Integer>();
		
		List<PcSearchDto> findSearchDto = pcSearchService.findSearchDto(goodsName);
		List<Goods> findTopFiveGoods = pcSearchService.findTopFiveGoods();
		//查询总页数
		Integer countPage = pcSearchService.findCountGoods(goodsName);
		
		for(int i = 1;i<=countPage;i++){
			page.add(i);
		}
		request.setAttribute("goodsName", goodsName);
		request.setAttribute("searchList", findSearchDto);
		request.setAttribute("topGoods", findTopFiveGoods);
		request.setAttribute("page", page);
		request.setAttribute("countPage", countPage);
		return new ModelAndView("/search/goods_search");
	}
	@RequestMapping("/goods/list/{pageIndex}/{orderMethod}")
	public ModelAndView searchGoodsList(@PathVariable("pageIndex") Integer pageIndex,@Param("goodsName") String goodsName,
			@PathVariable("orderMethod") Integer orderMethod,@Param("searchName") String searchName, HttpServletRequest request,
			@Param("isDelvi") Integer isDelvi,@Param("beginPrice") BigDecimal beginPrice,@Param("endPrice") BigDecimal endPrice,
			@Param("priceOrder") Boolean priceOrder){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(10);
		pageBean.setPageIndex(pageIndex);
		List<Goods> findSearchGoods = pcSearchService.findSearchGoods(goodsName, orderMethod, pageBean,searchName,isDelvi,beginPrice,endPrice,priceOrder);
		request.setAttribute("searhGoods", findSearchGoods);
		request.setAttribute("pageIndex", pageIndex);
		return new ModelAndView("/search/goods_list");
	}
	
	
}
