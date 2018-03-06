package com.zfj.xmy.cms.web.controller.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.annotation.CheckLogin;
import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.activity.service.cms.BuyAndPresentService;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.service.BaseCommonService;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;

/**
 * 
 * @Description 买即赠
 * @Author liuw
 * @Date 2017年7月11日下午4:33:36
 */
@RequestMapping("/buyAndPresent")
@RestController
public class BuyAndPresentController extends BaseController{
	@Autowired
	private BuyAndPresentService buyAndPresentService;
	@Autowired
	private CmsGoodsService goodsService;
	@Autowired
	private BaseCommonService commonService;
	
	// 自定义类型转换器
		@InitBinder
		public void initBinder(HttpServletRequest request,
				ServletRequestDataBinder binder) throws Exception {

			binder.registerCustomEditor(Date.class, new CustomDateEditor(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		}
		
		@RequestMapping("/index")
		public RespData index() {
			RespData respData = new RespData();
			respData.setResultCode(200);
			respData.setData("ok buyAndPresent");
			return respData;
		}
		/**
		 * 
		 * @Description 列表显示
		 * @param reqData
		 * @param pageBean
		 * @param request
		 * @return
		 * @Author liuw
		 * @Date 2017年7月12日下午2:07:53
		 */
		@RequestMapping("/buyAndPresent/list")
		public ModelAndView buyAndPresentList(ReqData reqData,PageBean pageBean,HttpServletRequest request)
		{
			ModelAndView modelAndView=new ModelAndView();
			reqData2Params(reqData);
			buyAndPresentService.findBuyAndPresentS(reqData, pageBean);
			Integer pageCount = pageBean.getTotalPage();
			request.setAttribute("pageCount", pageCount);
			modelAndView.setViewName("/buyAndPresent/buyAndPresent_list");
			return modelAndView;
		}
		@RequestMapping("/buyAndPresent/list/{pageIndex}")
		public ModelAndView buyAndPresentList(@PathVariable("pageIndex") Integer pageIndex,HttpServletRequest request,PageBean pageBean,ReqData reqData)
		{	
			reqData2Params(reqData);
			ModelAndView modelAndView=new ModelAndView();
			
			pageBean.setPageIndex(pageIndex);
			buyAndPresentService.findBuyAndPresentS(reqData, pageBean);
			
			request.setAttribute("buyAndPresent", pageBean);
			
			modelAndView.setViewName("/buyAndPresent/buyAndPresent_list_page");
			return modelAndView;
		}
		/**
		 * 
		 * @Description 
		 * @param buyAndPresent添加
		 * @return
		 * @Author liuw
		 * @Date 2017年7月12日下午2:08:01
		 */
		@RequestMapping("/buyAndPresent/addSave")
		public ModelAndView addBuyAndPresent(BuyAndPresent buyAndPresent)
		{
			ModelAndView  modelAndView=new ModelAndView();
			buyAndPresentService.insert(buyAndPresent);
			modelAndView.setViewName("redirect:list");
			commonService.refreshIndexHomePage();//更新首页redis
			return modelAndView;
		}
		@RequestMapping("/buyAndPresent/editSave")
		public ModelAndView editSaveBuyAndPresent(BuyAndPresent buyAndPresent)
		{
			ModelAndView  modelAndView=new ModelAndView();
			buyAndPresentService.updateBuyAndPresent(buyAndPresent);
			commonService.refreshIndexHomePage();//更新首页redis
			modelAndView.setViewName("redirect:list");
			return modelAndView;
		}
		@RequestMapping("/delBuyAndPresent")
		public RespData coupnDel(@RequestParam("id") Long id,
				HttpServletRequest request) {
			RespData respData = new RespData();
			int deleteByPrimaryKey = buyAndPresentService.deleteByPrimaryKey(id);
			respData.setData("成功删除了" + deleteByPrimaryKey + "条数据");
			respData.setResultCode(200);
			commonService.refreshIndexHomePage();//更新首页redis
			return respData;
		}
		/**
		 * 
		 * @Description 编辑单条买即赠
		 * @param id
		 * @param request
		 * @param reqData
		 * @param pageBean
		 * @return
		 * @Author liuw
		 * @Date 2017年7月12日下午2:23:32
		 */
		@RequestMapping("/buyAndPresent/edit/{id}")
		public ModelAndView editBuyAndPresent(@PathVariable("id") Long id,HttpServletRequest request,ReqData reqData,PageBean pageBean)
		{	
			reqData2Params(reqData);
			ModelAndView  modelAndView=new ModelAndView();
			BuyAndPresent buyAndPresent = buyAndPresentService.findBuyAndPresent(id);
			request.setAttribute("buyAndPresent", buyAndPresent);
			
			List<Goods> mainGoods=null;
			List<Goods> giftGoods =null;
					
			ReqData reqDataMainGoods=new ReqData();
			ReqData reqDataGiftGoods=new ReqData();
			String mainGoodsIds = buyAndPresent.getMainGoodsIds()+"";
			String giftGoodsIds = buyAndPresent.getGiftGoodsIds()+"";
			
			if(mainGoodsIds.length()>0)//主商品的IDs集合不为空（有主商品）
			{
				reqDataMainGoods.putValue("id", mainGoodsIds, SystemConstant.REQ_PARAMETER_IN);
				mainGoods=goodsService.findGoods(reqDataMainGoods);
			}
			
			if(giftGoodsIds.length()>0)//赠品的IDS集合不为空的情况下（有赠品）
			{
				reqDataGiftGoods.putValue("id", giftGoodsIds, SystemConstant.REQ_PARAMETER_IN);
				giftGoods=goodsService.findGoods(reqDataGiftGoods);
			}
			
			
			
			buyAndPresentService.findBuyAndPresentS(reqData, pageBean);
			Integer pageCount = pageBean.getTotalPage();
			request.setAttribute("mainGoods", mainGoods);
			request.setAttribute("giftGoods", giftGoods);
			request.setAttribute("pageCount", pageCount);
			commonService.refreshIndexHomePage();//更新首页redis
			modelAndView.setViewName("/buyAndPresent/editBuyAndPresent");
			return modelAndView;
		}
}
