package com.zfj.xmy.pc.web.controller.nationwide;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.service.pc.PcGoodsService;
import com.zfj.xmy.goods.service.pc.PcSearchService;

@RestController
@RequestMapping("/nationwide")
public class Nationwide {
	
	@Autowired
	private  PcSearchService pcSearchService;
	
	@Autowired
	private PcGoodsService pcGoodsService;
	
	private final static int PAGESIZE=15;
	
	@RequestMapping("/index")
	public ModelAndView toNationwide(int pageIndex,HttpServletRequest request){
		PageBean pageBean=new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(PAGESIZE);
		List<Goods> findTopFiveGoods = pcSearchService.findTopFiveGoods();
		
		request.setAttribute("topGoods", findTopFiveGoods);
		return new ModelAndView("/nationwide/nationwide");
	}
	
	@RequestMapping("/list")
	public ModelAndView toNationwideSort(int pageIndex,String sort,HttpServletRequest request){
		PageBean pageBean=new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(PAGESIZE);
		pcGoodsService.findNation(pageBean,sort);
		
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("sort", sort);
		return new ModelAndView("/nationwide/goods_list");
	}
}
