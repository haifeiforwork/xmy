package com.zfj.xmy.pc.web.controller.fruits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.BorderGoodsDto;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcAdDto;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDir;
import com.zfj.xmy.goods.service.pc.PcCategoryService;
import com.zfj.xmy.goods.service.pc.PcGoodsService;

@RestController
@RequestMapping("/fruits")
public class FruitsController {
	
	@Autowired
	private PcGoodsService pcGoodsService;
	
	@Autowired
	private PcCategoryService pcCategoryService;   
	
	//水果商品
	private final static String FRUITS="水果";
	
	@RequestMapping("/index")
	public ModelAndView customization(HttpServletRequest request){
		List<TermData> category=pcGoodsService.findBorder(FRUITS);
		List<AdImage> topImg = pcCategoryService.findAdimageById(category.get(0).getParentId());
		List<BorderGoodsDto> borderGoodsDtos=new ArrayList<BorderGoodsDto>();
		for(TermData name:category){
			BorderGoodsDto borderGoodsDto=new BorderGoodsDto();
			borderGoodsDto.setAdDto(pcGoodsService.findAd(name.getId()));
			borderGoodsDto.setGoodsDirs(pcGoodsService.findBorderGoods(name.getName()));
			borderGoodsDtos.add(borderGoodsDto);
		}
		request.setAttribute("topImg",topImg);  // top图
		request.setAttribute("list", borderGoodsDtos);
		return new ModelAndView("/fruits/fruits");
	}
}
