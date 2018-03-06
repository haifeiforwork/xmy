package com.zfj.xmy.cms.web.controller.oa;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.service.BaseCommonService;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;
import com.zfj.xmy.oa.XmyOaGoodsService;
import com.zfj.xmy.oa.dto.OaGoodsDto;
/** 
 * @Title: XmyOaController.java 
 * @Package com.zfj.xmy.cms.web.controller.oa 
 * @Description: 
 * @author hexw
 * @date 2018年1月15日 下午3:04:57 
 */
@RequestMapping("/xmyOa")
@RestController
public class XmyOaController {
	
	@Autowired
	private CmsGoodsService goodsService;

	@Autowired
	private BaseCommonService baseCommonService;
	
	@Autowired
	private XmyOaGoodsService xmyOaGoodsService ;
	
	/**
	 * OA系统修改商品为下架状态
	 * @param goodsId
	 * @return    
	 * @return RespData    
	 * Date:2018年1月15日 上午11:34:57 
	 * @author hexw
	 */
	@RequestMapping("/xmyOaUpdateGoodsPutway/{goodsId}")
	public RespData xmyOaUpdateGoodsPutway(@PathVariable("goodsId") String goodsId){
		RespData respData = new RespData() ;
		String [] idArry = goodsId.split("\\,");
		Integer outWay = SystemConstant.GoodsConstant.NOT_PUTWAY; 
		respData.setData(goodsService.updatePutwayStatus(idArry, outWay+""));
		baseCommonService.refreshIndexHomePage();//更新redis
		return respData;
	}
	
	/**
	 * OA 系统商品调价
	 * @param dto
	 * @return    
	 * @return RespData    
	 * Date:2018年1月15日 下午3:19:57 
	 * @author hexw
	 */
	@RequestMapping("/xmyOaUpdateGoods")
	public RespData xmyOaUpdateGoods(OaGoodsDto dto ){
		System.out.println("商品编码："+dto.getGoodsCode());
		RespData respData = new RespData() ;
		respData.setData(xmyOaGoodsService.xmyOaUpdateGoods(dto));
		return respData;
	}
	
	
	
	
}
  