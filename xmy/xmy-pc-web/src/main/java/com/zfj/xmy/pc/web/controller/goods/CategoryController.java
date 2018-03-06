package com.zfj.xmy.pc.web.controller.goods; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDir;
import com.zfj.xmy.goods.service.pc.PcCategoryService;

/** 商品类别
 * @Title: CategoryController.java 
 * @Package com.zfj.xmy.pc.web.controller.goods 
 * @Description: 
 * @author hexw
 * @date 2017年8月10日 下午4:16:54 
 */
@RestController
@RequestMapping("/category")
public class CategoryController  {

	@Autowired
	private PcCategoryService pcCategoryService;   
	
	/**
	 * 查询一级分类的货柜及广告
	 * @param id
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年9月4日 下午2:19:00 
	 * @author hexw
	 */
	@RequestMapping("/findCategoryGoods")
	public ModelAndView findCategoryGoods(long id,HttpServletRequest request){
		Map<String,Object> result = pcCategoryService.findSecondCategoryContainer(id);
		request.setAttribute("topImg",result.get("topImg"));  // top图
		request.setAttribute("firstAdImg",result.get("firstAdImg"));  // 一号广告位(top图右侧广告位)
		request.setAttribute("containers",result.get("containers"));  // 货柜商品及广告
		request.setAttribute("menuData", pcCategoryService.findCategoryMenu(id));
		List<Goods> findChangeGoods = pcCategoryService.findChangeGoods(id, 1);
		request.setAttribute("freshGoods", findChangeGoods);
		return new  ModelAndView("/category/category_goods");
	}
	
	
	/**
	 * 鲜一下 (查询一级分类下销量前20的商品)
	 * @param request
	 * @return    
	 * @return RespData    
	 * Date:2017年10月21日 上午10:16:10 
	 * @author hexw
	 */
	@RequestMapping("/findFreshGoods/{id}")
	public RespData findFreshGoods(@PathVariable("id") Long id,HttpServletRequest request){
		RespData respData = new RespData();
		List<PcGoodsDir> list = pcCategoryService.findFreshGoods(id);
		Collections.shuffle(list);  //随机打乱集合顺序
		if (list.size() > 4) {
			request.setAttribute("freshGoods",list.subList(0, 4));
		} else {
			request.setAttribute("freshGoods",list);
		}
		return respData;
	}
	/**
	 * 更换商品
	 * @param id
	 * @param pageIndex
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月24日 下午9:36:25
	 */
	@RequestMapping("/changeGoods/{id}/{pageIndex}")
	public ModelAndView changeGoods(@PathVariable("id") Long id,@PathVariable("pageIndex") Integer pageIndex,HttpServletRequest request){
		
		List<Goods> findChangeGoods = pcCategoryService.findChangeGoods(id, pageIndex);
		
		/*Collections.shuffle(findChangeGoods);
		List<Goods> subList = findChangeGoods.subList(0, 4);*/
		
		request.setAttribute("freshGoods", findChangeGoods);
		
		return new ModelAndView("/category/freshGoods_list");
	}
	
}
  