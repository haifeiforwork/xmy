package com.zfj.xmy.cms.web.controller.goods;  


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.WatermarkImage;
import com.zfj.xmy.goods.service.cms.WaterMarkService;

/** 
 * @Title: WaterMarkController.java 
 * @Package com.zfj.xmy.cms.web.controller 
 * @Description:  水印图片
 * @author hexw
 * @date 2017年6月20日 下午6:55:28 
 */
@RequestMapping("waterMark")
@RestController
public class WaterMarkController extends BaseController{
	
	@Autowired
	private WaterMarkService waterMarkServie;
	
	/**
	 * 添加水印图片
	 * @param watermarkImage
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月14日 上午11:01:23
	 */
	@RequestMapping("/addWaterImage")
	public ModelAndView addWaterImage(WatermarkImage watermarkImage){
		waterMarkServie.insertWatermarkImg(watermarkImage);
		return new ModelAndView("redirect:/waterMark/waterMarkList");
	}
	
	/**
	 * 水印图片列表
	 * @param request
	 * @param reqData
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月20日 下午7:05:33
	 */
	@RequestMapping("/waterMarkList")
	public ModelAndView waterMarkList(HttpServletRequest request, ReqData reqData){
		PageBean pageBean = new PageBean();
		reqData2Params(reqData);
		waterMarkServie.findWatermarkList(pageBean,reqData); 
		request.setAttribute("totalPage", pageBean.getTotalPage());
		return new ModelAndView("/goods/watermark/watermark_list");
	}
	
	@RequestMapping("/waterMarkList/{pageIndex}")
	public ModelAndView waterMarkList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request,PageBean pageBean){
		reqData2Params(reqData);
		reqData.setSortname("create_time");
		reqData.setSortorder("desc");
		waterMarkServie.findWatermarkList(pageBean,reqData); 
		request.setAttribute("wList", pageBean.getData());
		return new ModelAndView("/goods/watermark/watermark_list_page");
	}
	
	/**
	 * 图片删除
	 * @param id
	 * @param request
	 * @return    
	 * Date:2017年6月21日 上午9:12:55
	 */
	@RequestMapping("/deleteImg")
	public RespData deleteImg(String idStr, HttpServletRequest request){
		RespData respData = new RespData();
		String [] idArry = idStr.split("\\,");
		respData.setData(waterMarkServie.deleteImg(idArry));
		return respData;
	}
	
	/**
	 * 选择水印图片弹窗
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月14日 下午2:43:35
	 */
	@RequestMapping("/chooseWatermarkList")
	public ModelAndView chooseWatermarkList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		PageBean pageBean = new  PageBean();
		waterMarkServie.waterImgList(pageBean,reqData);
		request.setAttribute("totalPage", pageBean.getTotalPage());
		return new ModelAndView("/goods/watermark/choose_watermarkImg_list");      
	}
	@RequestMapping("/chooseWatermarkList/{pageIndex}")
	public ModelAndView chooseWatermarkList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request,PageBean pageBean){
		reqData2Params(reqData);
		reqData.setSortname("create_time");
		reqData.setSortorder("desc"); 
		waterMarkServie.waterImgList(pageBean,reqData);
		request.setAttribute("data",pageBean.getData());
		return new ModelAndView("/goods/watermark/choose_watermarkImg_list_page");      
	}
	
}
  