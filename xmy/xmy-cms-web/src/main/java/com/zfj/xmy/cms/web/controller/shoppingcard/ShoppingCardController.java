package com.zfj.xmy.cms.web.controller.shoppingcard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.ShoppingCardDto;
import com.zfj.xmy.activity.service.cms.ShoppingCardService;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;

@RestController
public class ShoppingCardController extends BaseController {
	@Autowired
	private ShoppingCardService cardService;
	
	/**
	 * 查商品购物卡总页数
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年10月31日 下午5:50:41
	 */
	@RequestMapping("/card/shoppingCardList")
	public ModelAndView shoppingCardList(ReqData reqData,HttpServletRequest request){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		cardService.findAllShoppingCard(reqData, pageBean);
		request.setAttribute("countPage", pageBean.getTotalPage());
		reqData2Params(reqData);
		return new ModelAndView("/shoppingcard/shopping_card");
	}
	
	@RequestMapping("/card/shoppingCardList/{pageIndex}")
	public ModelAndView shoppCardListPage(@PathVariable("pageIndex") Integer pageIndex,ReqData reqData,HttpServletRequest request){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(pageIndex);
		List<ShoppingCardDto> list = cardService.findAllShoppingCard(reqData, pageBean);
		request.setAttribute("cardList", list);
		return new ModelAndView("/shoppingcard/shopping_card_page");
	}
	
	/**
	 * 导入购物卡
	 * @param reqData
	 * @param request
	 * @return
	 * @Description 
	 * @date 2017年12月14日  下午2:41:24
	 * @author wy
	 * 2017
	 * @return ModelAndView
	 */
	@RequestMapping("/card/import")
	public RespData importShoppCard(HttpServletRequest request,@RequestParam("file") MultipartFile file){
		
		
		RespData respData = new RespData();
		try {
		    cardService.importShoppingCard(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RespData.getRespData(e, "导入失败");
		}
		//刷新页面
		//return new ModelAndView("/shoppingcard/shopping_card");
		return respData;
	}
}
