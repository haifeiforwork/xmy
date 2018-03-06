package com.zfj.xmy.pc.web.controller.customization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDir;
import com.zfj.xmy.goods.service.pc.PcGoodsService;

@RestController
@RequestMapping("/customization")
public class CustomizationController {
	
	@Autowired
	private PcGoodsService pcGoodsService;
	
	private final static String CUSTOMIZATION="企业定制";
	
	@RequestMapping("/index")
	public ModelAndView customization(HttpServletRequest request){
		List<TermData> category=pcGoodsService.findBorder(CUSTOMIZATION);
		Map<String, List<PcGoodsDir>> map=new HashMap<String, List<PcGoodsDir>>();
		for(TermData name:category){
			map.put(name.getName(),pcGoodsService.findBorderGoods(name.getName()));
		}
		request.setAttribute("map", map);
		return new ModelAndView("/customization/customization");
	}
}
