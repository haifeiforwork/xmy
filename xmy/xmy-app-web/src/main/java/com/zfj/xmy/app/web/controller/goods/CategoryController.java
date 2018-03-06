package com.zfj.xmy.app.web.controller.goods;  

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppTermDataDir;
import com.zfj.xmy.goods.service.app.AppCategoryService;

/** 
 * @Title: CategoryController.java 
 * @Package com.zfj.xmy.app.web.controller.goods 
 * @Description:  分类接口
 * @author hexw
 * @date 2017年7月27日 下午5:04:15 
 */
@RequestMapping(value="/category",params=SystemConstant.MOBILE_VERSION_V10)
@RestController
public class CategoryController extends BaseController {

	@Autowired
	private AppCategoryService appCategoryService ;
	
	
	/**
	 * 查询分类
	 * @return    
	 * @return ResponseEntity<List<AppTermDataDir>>    
	 * Date:2017年7月27日 下午5:24:09 
	 * @author hexw
	 */
	@RequestMapping(value="/findCategory",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> findCategory(){
		return AppResp.get(appCategoryService.findCategory());
	}
	
	/**
	 * 查询商品栏目
	 * @param programa
	 * @return    
	 * @return ResponseEntity<List<AppTermDataDir>>    
	 * Date:2017年7月27日 下午6:33:15 
	 * @author hexw
	 */
	@RequestMapping(value="/findGoodsPrograma/{programa}",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> findGoodsPrograma(@PathVariable("programa") String programa){
		return AppResp.get(appCategoryService.findGoodsPrograma(programa));
	}
	
	
	/**
	 * 企业定制
	 * @return    
	 * @return ResponseEntity<List<AppGoodsDir>>    
	 * Date:2017年7月28日 下午3:44:24 
	 * @author hexw
	 */
	@RequestMapping(value="/findEnterprise",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> findEnterprise(){
		return AppResp.get(appCategoryService.findEnterprise());
	}
	
}
  