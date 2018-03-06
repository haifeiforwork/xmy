package com.zfj.xmy.cms.web.controller.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.AdInfo;
import com.zfj.xmy.goods.persistence.common.pojo.dto.TermDataDir;
import com.zfj.xmy.goods.service.cms.CategoryService;
import com.zfj.xmy.order.persistence.common.pojo.dto.AdInfoDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.AdInfoImage;
import com.zfj.xmy.order.service.cms.AdvertisementService;


@RequestMapping("/appSetting")
@RestController
public class AppSettingController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AdvertisementService advertisementService;
	@RequestMapping("/index")
	public RespData index()
	{
		RespData respData=new RespData();
		respData.setData("ok appSetting!");
		respData.setResultCode(200);
		return respData;
	}
	/**
	 * 
	 * @Description initBinderAdInfo模仿struts2的精确数据绑定功能
	 * @param binder
	 * @Author liuw
	 * @Date 2017年7月18日下午2:25:37
	 */
	 @InitBinder("adInfo")  
	    public void initBinderAdInfo(WebDataBinder binder) {  
	        binder.setFieldDefaultPrefix("adInfo.");  
	    }  
	    // 绑定变量名字和属性，参数封装进类  
	 /**
	  * 
	  * @Description initBinderAdImage模仿struts2的精确数据绑定功能
	  * @param binder
	  * @Author liuw
	  * @Date 2017年7月18日下午2:26:15
	  */
	    @InitBinder("adImage")  
	    public void initBinderAdImage(WebDataBinder binder) {  
	        binder.setFieldDefaultPrefix("adImage.");  
	    }  
	    
	    
	/**
	 * app分类首页图展示
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年7月14日下午4:53:23
	 */
	@RequestMapping("/classificationTopImg")
	public ModelAndView classificationTopImgList(HttpServletRequest request)
	{
		ReqData reqDataCategory=new ReqData();
		ReqData reqDataAdInfo=new ReqData();
		//查询出分类
		ModelAndView modelAndView=new ModelAndView();
		List<AdInfoImage> adInfoImagesList=new ArrayList<AdInfoImage>();
		List<TermDataDir> categoryList = categoryService.selectCategory(reqDataCategory);
		
		reqDataAdInfo.putValue("type", SystemConstant.AdInfoImage.TYPE_CLASSIFICATIONTOPIMG, SystemConstant.REQ_PARAMETER_EQ);
		List<AdInfo> findAdInfo = advertisementService.findAdInfo(reqDataAdInfo);
		List<AdImage> adImageList=new ArrayList<AdImage>();
 		for (AdInfo adInfo : findAdInfo) {//分类Top图（adinfo和adimage）是一对一的关系
 			AdImage adImage =null;
 			adImage= advertisementService.getAdImageByAdId(adInfo.getId());//@根据adinfo的id，从adimage中取出对应的记录
			if(adImage!=null){//有些分类可能还没有设置Top图，这个时候就不会显示出来，直接跳过
				adImageList.add(adImage);
				AdInfoImage adInfoImage = new AdInfoImage();
				adInfoImage.setAdImage(adImage);
				adInfoImage.setAdInfo(adInfo);
				adInfoImagesList.add(adInfoImage);
			}
		}
		request.setAttribute("adInfoImagesList", adInfoImagesList);
		request.setAttribute("categoryList", categoryList);
		modelAndView.setViewName("/appSetting/classificationTopImg_list");
		return modelAndView;
	}
	/**
	 * 
	 * @Description 添加一级分类top图
	 * @param adInfo
	 * @param adImage
	 * @param reqData
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日上午9:21:28
	 */
	@RequestMapping("/addClassificationTopImg")
	public ModelAndView addClassificationTopImg(AdInfo adInfo,AdImage adImage,ReqData reqData,HttpServletRequest request)
	{	
		reqData2Params(reqData);
		ModelAndView modelAndView=new ModelAndView();
		adInfo.setIsCategory(SystemConstant.AdInfoImage.ISCATEGORY);
		adInfo.setType(SystemConstant.AdInfoImage.TYPE_CLASSIFICATIONTOPIMG);
		advertisementService.insertAdvertisement(adInfo);
		Long adInfoId = adInfo.getId();
		adImage.setAdId(adInfoId);
		advertisementService.insertAdImage(adImage);
		List<TermDataDir> categoryList = categoryService.selectCategory(reqData);
		request.setAttribute("categoryList", categoryList);
		modelAndView.setViewName("redirect:classificationTopImg");
		return modelAndView;
	}
	
	@RequestMapping("/updateClassificationTopImg")
	public ModelAndView updateClassificationTopImg(AdInfo adInfo,AdImage adImage,ReqData reqData,HttpServletRequest request)
	{	
		reqData2Params(reqData);
		ModelAndView modelAndView=new ModelAndView();
		advertisementService.updateAdInfoImage(adInfo, adImage);
		modelAndView.setViewName("redirect:classificationTopImg");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Description 删除分类Top图
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日上午9:21:08
	 */
	@RequestMapping("/delClassificationTopImg/{id}")
	public ModelAndView delClassificationTopImg(@PathVariable Long id,HttpServletRequest request)
	{
		ModelAndView modelAndView=new ModelAndView();
		
		advertisementService.deleteAdInfo(id);
		
		modelAndView.setViewName("redirect:/appSetting/classificationTopImg");
		return modelAndView;
	}
	/**
	 * 
	 * @Description 编辑分类页Top图
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日上午9:20:48
	 */
	@RequestMapping("/editClassificationTopImg/{id}")
	public ModelAndView editClassificationTopImg(@PathVariable Long id,HttpServletRequest request)
	{
		ReqData reqDataCategory=new ReqData();
		ModelAndView modelAndView=new ModelAndView();
		
		List<TermDataDir> categoryList = categoryService.selectCategory(reqDataCategory);
		
		ReqData reqData=new ReqData();
		reqData.putValue("a.id", id, SystemConstant.REQ_PARAMETER_EQ);
		AdInfoDto adInfoImage = advertisementService.getAdInfoImage(reqData);
		request.setAttribute("adInfoImage", adInfoImage);
		request.setAttribute("categoryList", categoryList);
		modelAndView.setViewName("/appSetting/editClassificationTopImg_list");
		return modelAndView;
	}
	/**
	 * 
	 * @Description 开抢啦显示
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日下午3:18:07
	 */
	@RequestMapping("/openGrabTopImg")
	public ModelAndView openGrab(HttpServletRequest request)
	{
		ModelAndView modelAndView=new ModelAndView();
		
		//先查询出adInfo里面的信息，根据开抢啦的Top标识。
		ReqData reqDataAdInfo=new ReqData();
		List<AdImage> findAdImage =null;
		reqDataAdInfo.putValue("type", SystemConstant.AdInfoImage.TYPE_OPENGRAB, SystemConstant.REQ_PARAMETER_EQ);
		List<AdInfo> findAdInfo = advertisementService.findAdInfo(reqDataAdInfo);
		if(findAdInfo.size()>0)//存在开抢啦的图片,开抢啦的Top图是一对多的关系。（adInfo和adImage）
		{
			findAdImage= advertisementService.findAdImage(findAdInfo.get(0).getId().toString());
			request.setAttribute("adInfo", findAdInfo.get(0));
		}
		
		request.setAttribute("adImageList", findAdImage);
		modelAndView.setViewName("/appSetting/openGrabTopImg_list");
		return modelAndView;
	}
	/**
	 * 
	 * @Description 添加开抢啦Top轮播图
	 * @param adImage
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日下午4:44:24
	 */
	@RequestMapping("/addOpenGrabTopImg")
	public ModelAndView addOpenGrabTopImg(AdImage adImage)
	{
		ModelAndView modelAndView=new ModelAndView();
		advertisementService.insertAdImage(adImage);
		modelAndView.setViewName("redirect:/appSetting/openGrabTopImg");
		return modelAndView;
	}
	/**
	 * 
	 * @Description 删除单张开抢啦Top图
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日下午4:44:41
	 */
	@RequestMapping("/delOpenGrabTopImg/{id}")
	public ModelAndView delOpenGrabTopImg(@PathVariable Long id)
	{
		ModelAndView modelAndView=new ModelAndView();
		advertisementService.deleteAdImage(id);
		modelAndView.setViewName("redirect:/appSetting/openGrabTopImg");
		return modelAndView;
	}
	/**
	 * 
	 * @Description 编辑单张开抢啦Top图
	 * @param id
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日下午4:45:09
	 */
	@RequestMapping("/editOpenGrabTopImg/{id}")
	public ModelAndView editOpenGrabTopImg(@PathVariable Long id,HttpServletRequest request)
	{
		ModelAndView modelAndView=new ModelAndView();
		AdImage adImage =advertisementService.getAdImageById(id);
		request.setAttribute("adImage", adImage);
		modelAndView.setViewName("/appSetting/editOpenGrabTopImg");
		return modelAndView;
	}
	/**
	 * 
	 * @Description 更新单张开抢啦Top轮播图
	 * @param adImage
	 * @return
	 * @Author liuw
	 * @Date 2017年7月18日下午4:45:39
	 */
	@RequestMapping("/updateOpenGrabTopImg")
	public ModelAndView updateOpenGrabTopImg(AdImage adImage)
	{
		ModelAndView modelAndView=new ModelAndView();
		int updateAdImage = advertisementService.updateAdImage(adImage);
		modelAndView.setViewName("redirect:/appSetting/openGrabTopImg");
		return modelAndView;
	}
}
