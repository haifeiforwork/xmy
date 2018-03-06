package com.zfj.xmy.cms.web.controller.mallsetting;  

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PcSecondLevelClassificationDto;
import com.zfj.xmy.activity.service.cms.ContainerService;
import com.zfj.xmy.activity.service.cms.IndexSettingService;
import com.zfj.xmy.activity.service.cms.PcSecondLevelClassificationService;
import com.zfj.xmy.activity.service.cms.impl.IndexSettingServiceImpl;
import com.zfj.xmy.cms.web.controller.BaseController;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.AdInfo;
import com.zfj.xmy.common.persistence.pojo.AdPosition;
import com.zfj.xmy.common.persistence.pojo.Container;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;
import com.zfj.xmy.common.persistence.pojo.PcSecondLevelClassification;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.service.BaseCommonService;
import com.zfj.xmy.goods.persistence.common.pojo.dto.TermDataDir;
import com.zfj.xmy.goods.service.cms.CategoryService;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;
import com.zfj.xmy.goods.service.cms.TermDataService;
import com.zfj.xmy.goods.service.cms.impl.CmsGoodsServiceImpl;
import com.zfj.xmy.order.persistence.common.pojo.dto.AdInfoDto;
import com.zfj.xmy.order.service.cms.AdvertisementService;

/** 
 * @Title: AdvertisementController.java 
 * @Package com.zfj.xmy.cms.web.controller.mallsetting 
 * @Description:  广告设置
 * @author hexw
 * @date 2017年7月12日 上午9:21:07 
 */
@RequestMapping("/advertisement")
@RestController
public class AdvertisementController extends BaseController{
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private IndexSettingService indexSettingService; 
	@Autowired
	private ContainerService containerService;
	@Autowired
	private CmsGoodsService cmsGoodsService;
	@Autowired
	private PcSecondLevelClassificationService pcSecondLevelClassificationService;
	@Autowired
	private TermDataService termDataService;
	@Autowired
	private BaseCommonService baseCommonService;
	
	/**
	 * 跳转到添加广告位置设置界面
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 上午9:24:54
	 */
	@RequestMapping("/toAddAdvertisementPosition")
	public ModelAndView toAddAdvertisementPosition(HttpServletRequest request){
		return new ModelAndView("/advertisement/advertisement_position_setting");
	}
	
	/**
	 * 添加广告位置设置
	 * @param adPosition
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 下午1:52:18
	 */
	@RequestMapping("/addAdvertisementPosition")
	public ModelAndView addAdvertisementPosition(AdPosition adPosition, HttpServletRequest request){
		advertisementService.insertAdvertisementPosition(adPosition );
		return new ModelAndView("redirect:/advertisement/advertisementList");
	}
	
	/**
	 * 跳转到添加广告界面
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 下午2:14:48
	 */
	@RequestMapping("/toAddAdvertisement")
	public ModelAndView toAddAdvertisement(HttpServletRequest request) {
		ReqData reqData = new ReqData();
		reqData.putValue("status", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<AdPosition> list = advertisementService.findAdPosition(reqData);
		request.setAttribute("data", list);
		return new ModelAndView("/advertisement/add_advertisement");
	}
	
	/**
	 * 选择分类列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年6月21日 下午5:52:08
	 */
	@RequestMapping("/toCategoryList")
	public ModelAndView toCategoryList(HttpServletRequest request){
		List<TermDataDir> list = categoryService.findCategory(SystemConstant.TERMDATA.YES); 
		request.setAttribute("cList", list);
		return new ModelAndView("/advertisement/category_list");
	}
	
	/**
	 * 添加广告
	 * @param adInfo
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 下午5:34:41
	 */
	@RequestMapping("/addAdvertisement")
	public ModelAndView addAdvertisement(AdInfo adInfo, HttpServletRequest request){
		String[] imgPath = request.getParameterValues("imgPath");
		String[] imgUrl = request.getParameterValues("imgUrl");
		String[] imgWeight = request.getParameterValues("imgWeight");
		String[] imgType = request.getParameterValues("imgType");
		advertisementService.insertAdvertisement(adInfo);
		advertisementService.batchInsertAdImage(adInfo.getId(), imgPath, imgUrl,imgWeight,imgType); //上传广告图片
		return new ModelAndView("redirect:/advertisement/advertisementList");
	}
	
	
	/**
	 * 广告列表
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 下午5:53:48
	 */
	@RequestMapping("/advertisementList")
	public ModelAndView advertisementList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		reqData.putValue("type", 1, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean = new PageBean();
		advertisementService.findAdInfo(reqData, pageBean);
		request.setAttribute("totalPage",pageBean.getTotalPage()) ;
		return new ModelAndView("/advertisement/advertisement_list");
	}
	
	@RequestMapping("/advertisementList/{pageIndex}")
	public ModelAndView advertisementList(@PathVariable("pageIndex") Integer pageIndex,
			HttpServletRequest request, ReqData reqData, PageBean pageBean) {
		pageBean.setPageIndex(pageIndex);
		reqData.putValue("type", 1, SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("name");
		reqData.setSortorder("desc");
		advertisementService.findAdInfo(reqData, pageBean);
		request.setAttribute("data", pageBean.getData());
		return new ModelAndView("/advertisement/advertisement_list_page");
	}
	
	
	/**
	 * 跳转到广告编辑界面
	 * @param id
	 * @param request
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 下午8:11:17
	 */
	@RequestMapping("/toEditAdvertisement")
	public ModelAndView toEditAdvertisement(String id, HttpServletRequest request){
		AdInfoDto adInfoDto = advertisementService.getAdInfo(id);
		List<AdImage> list = advertisementService.findAdImage(id);
		ReqData reqData = new ReqData();
		reqData.putValue("status", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<AdPosition> positionList = advertisementService.findAdPosition(reqData);
		request.setAttribute("positionData", positionList);
		request.setAttribute("data", adInfoDto);
		request.setAttribute("imageData", list);
		return new ModelAndView("/advertisement/edit_advertisement");
	}
	
	/**
	 * 修改广告
	 * @param adInfo
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年7月12日 下午9:01:06
	 */
	@RequestMapping("/updateAdvertisement")
	public ModelAndView updateAdvertisement(AdInfo adInfo, HttpServletRequest request){
		String[] imgPath = request.getParameterValues("imgPath");
		String[] imgSrc = request.getParameterValues("imgUrl");
		String[] imgWeight = request.getParameterValues("imgWeight");
		String[] imgType = request.getParameterValues("imgType");
		advertisementService.updateAdInfo(adInfo);
		advertisementService.batchInsertAdImage(adInfo.getId(), imgPath, imgSrc,imgWeight,imgType); //上传广告图片
		baseCommonService.refreshIndexHomePage();//更新redis首页数据
		return new ModelAndView("redirect:/advertisement/advertisementList");
	}
	//设置app首页
	@RequestMapping("/appIndexSetting")
	public ModelAndView appIndexSetting(HttpServletRequest request,String isSuccess){
		//获得首页配置
		IndexConfig indexConfig = indexSettingService.getIndexConfig();
		//获得货柜的id
		List<Object> parseIndexConfigToList = indexSettingService.parseIndexConfigToList(indexConfig);
		//获得货柜的信息
		List<Container> containers = containerService.findsContainersWithContainerIds(parseIndexConfigToList);
		
		request.setAttribute("containerIds", indexConfig.getContainerIds());
		request.setAttribute("indexConfig", indexConfig);
		request.setAttribute("containers", containers);
		if (!StringUtil.isEmpty(isSuccess)) {
			request.setAttribute("isSuccess", "修改成功！");
		}
		baseCommonService.refreshIndexHomePage();//更新redis首页数据
		return new ModelAndView("/advertisement/appIndexSetting");
	}
	/**
	 * pc首页设置
	 * @Description 
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年8月25日下午2:40:45
	 */
	@RequestMapping("/pcIndexSetting")
	public ModelAndView pcIndexSetting(HttpServletRequest request,String isSuccess){
		//获得首页配置
		IndexConfig indexConfig = indexSettingService.getPCIndexConfig();
		
		//获得货柜的id
		List<Object> parseIndexConfigToList = indexSettingService.parseIndexConfigToList(indexConfig);
		//获得货柜的信息
		List<Container> containers = containerService.findsContainersWithContainerIds(parseIndexConfigToList);
		//获取搜索关键字配置
		ReqData reqData = new ReqData();
		reqData.putValue("vid", SystemConstant.TERMDATA.PC_SEARCHVID, SystemConstant.REQ_PARAMETER_EQ);
		List<TermData> terDataList = termDataService.selectTermDataByVid(reqData);
		if(!ObjectUtils.isEmpty(terDataList)){
			request.setAttribute("term", terDataList.get(0));
		}
		request.setAttribute("containerIds", indexConfig.getContainerIds());
		request.setAttribute("indexConfig", indexConfig);
		request.setAttribute("containers", containers);
		if (!StringUtil.isEmpty(isSuccess)) {
			request.setAttribute("isSuccess", "修改成功！");
		}
		baseCommonService.refreshIndexHomePage();//更新redis首页数据
		return new ModelAndView("/advertisement/pcIndexSetting");
	}
	/**
	 * 修改pc首页搜索配置
	 * @param request
	 * @param termData
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年11月22日 上午11:32:23
	 */
	@RequestMapping("/pcIndexSearchSetting")
	public ModelAndView pcIndexSearchSetting(HttpServletRequest request,TermData termData){
		
		termDataService.updateTermData(termData);
		baseCommonService.refreshIndexHomePage();//更新redis首页数据
		return new ModelAndView("redirect:/advertisement/pcIndexSetting");
	}
	
	/**
	 * 精选水果
	 * @Description 
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年8月30日上午10:31:44
	 */
	@RequestMapping("/choiceFruitSetting")
	public ModelAndView choiceFruitSetting(HttpServletRequest request){
		//获得app精选水果配置
		IndexConfig indexConfig = indexSettingService.getChoiceFruitSetting();
		//获得货柜的id
		List<Object> parseIndexConfigToList = indexSettingService.parseIndexConfigToList(indexConfig);
		//获得货柜的信息
		List<Container> containers = containerService.findsContainersWithContainerIds(parseIndexConfigToList);
		
		request.setAttribute("containerIds", indexConfig.getContainerIds());
		request.setAttribute("indexConfig", indexConfig);
		request.setAttribute("containers", containers);
		return new ModelAndView("/advertisement/choiceFruitSetting");
	}
	/**
	 * 配置app跨境专区
	 * @Description 
	 * @param request
	 * @return
	 * @Author liuw
	 * @Date 2017年8月31日上午9:56:41
	 */
	@RequestMapping("/crossBorderSetting")
	public ModelAndView crossBorderSetting(HttpServletRequest request){
		//获得app精选水果配置
		IndexConfig indexConfig = indexSettingService.getCrossBorderSetting();
		//获得货柜的id
		List<Object> parseIndexConfigToList = indexSettingService.parseIndexConfigToList(indexConfig);
		//获得货柜的信息
		List<Container> containers = containerService.findsContainersWithContainerIds(parseIndexConfigToList);
		
		request.setAttribute("containerIds", indexConfig.getContainerIds());
		request.setAttribute("indexConfig", indexConfig);
		request.setAttribute("containers", containers);
		return new ModelAndView("/advertisement/crossBorderSetting");
	}
	/**
	 * PC二级分类页配置
	 * @Description 
	 * @param request
	 * @param isSuccess  是否修改成功
	 * @return
	 * @Author liuw
	 * @Date 2017年8月31日下午7:46:17
	 */
	@RequestMapping("/secondLevelClassificationSetting")
	public ModelAndView secondLevelClassificationSetting(HttpServletRequest request,String isSuccess){
		Long categoryId=null;
		String parameter = request.getParameter("categoryId");
		if(!ObjectUtils.isEmpty(parameter)){
			categoryId = Long.valueOf(parameter);
			request.setAttribute("categoryId", categoryId);
		}
		if (!StringUtil.isEmpty(isSuccess)) {
			request.setAttribute("isSuccess", "设置成功！");
		}
		//查找到分类页
		List<TermDataDir> findCategory = categoryService.findCategory(SystemConstant.TERMDATA.YES);
		request.setAttribute("categorys", findCategory);
		
		
		//获取pc二级分类页的记录
		List<PcSecondLevelClassification> pcSecondLevel = pcSecondLevelClassificationService.getPcSecondLevel();
		
		List<PcSecondLevelClassificationDto> result=new ArrayList<PcSecondLevelClassificationDto>();
		
		//遍历二级分类页
		for (PcSecondLevelClassification pcSecondLevelClassification : pcSecondLevel) {
			
		/*	PcSecondLevelClassificationDto secondLevelClassificationDto=new PcSecondLevelClassificationDto();
			
			Long configId = pcSecondLevelClassification.getConfigId();
			IndexConfig indexConfig = indexSettingService.getIndexConfigById(configId);
			//获得货柜的id
			List<Object> parseIndexConfigToList = indexSettingService.parseIndexConfigToList(indexConfig);
			//获得货柜的信息
			List<Container> containers = containerService.findsContainersWithContainerIds(parseIndexConfigToList);
			
			//封装货柜的信息
			secondLevelClassificationDto.setContainers(containers);
			//封装配置信息
			secondLevelClassificationDto.setIndexConfig(indexConfig);
			//封装二级分类页信息
			secondLevelClassificationDto.setPcSecondLevelClassification(pcSecondLevelClassification);
			result.add(secondLevelClassificationDto);*/
			if(pcSecondLevelClassification.getCategoryId().equals(categoryId)){
				Long configId = pcSecondLevelClassification.getConfigId();
				
				IndexConfig indexConfig = indexSettingService.getIndexConfigById(configId);
				
			
				//获得货柜的id
				List<Object> parseIndexConfigToList = indexSettingService.parseIndexConfigToList(indexConfig);
				//获得货柜的信息
				List<Container> containers = containerService.findsContainersWithContainerIds(parseIndexConfigToList);
				
				request.setAttribute("containerIds", indexConfig.getContainerIds());
				request.setAttribute("indexConfig", indexConfig);
				request.setAttribute("containers", containers);
				
				
				break;
			}
		}
		
		return new ModelAndView("/advertisement/secondLevelClassificationSetting");
	}
	/**
	 * 选择广告
	 * @Description 
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年8月11日上午10:16:25
	 */
	@RequestMapping("/toChooseAdvertisementList")
	public ModelAndView toChooseAdvertisementList(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		//1 代表广告
		reqData.putValue("type", 1, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean = new PageBean();
		
		advertisementService.findAdInfo(reqData, pageBean);
		request.setAttribute("pageCount",pageBean.getTotalPage()) ;
		return new ModelAndView("/advertisement/chooseAd_list");
	}
	@RequestMapping("/toChooseAdvertisementList/{pageIndex}")
	public ModelAndView toChooseAdvertisementList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request){
		reqData2Params(reqData);
		//1 代表广告
		reqData.putValue("type", 1, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		advertisementService.findAdInfo(reqData, pageBean);
		request.setAttribute("data",pageBean.getData()) ;
		return new ModelAndView("/advertisement/chooseAd_list_page");
	}
	
	/**
	 * 去添加货柜页面（弹窗）
	 * @Description 
	 * @param request
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年8月11日下午4:17:03
	 */
	@RequestMapping("/toAddContainer")
	public ModelAndView toAddContainer(HttpServletRequest request,ReqData reqData){
		reqData2Params(reqData);
		String parameter = request.getParameter("containerId");
		//如果带有containerId这个参数，说明是更新货柜，否则就是新增
		if(!StringUtil.isEmpty(parameter)&&!parameter.equals("undefined")){
			Long containerId = Long.valueOf(parameter);
			Container container = containerService.getContainersById(containerId);
			request.setAttribute("container", container);
			//根据货柜保存的商品集合，取出所有的商品
			String goodsIds = container.getGoodsIds();
			Object[] ids = goodsIds.split(",");
			List<Goods> goods = cmsGoodsService.findsGoodsWithIds(Arrays.asList(ids));
			request.setAttribute("goods", goods);
		}
		return new ModelAndView("/advertisement/addContainer");
	}
	
	/**
	 * 到上传广告图片界面
	 * @return    
	 * @return ModelAndView    
	 * Date:2017年8月15日 上午9:29:52 
	 * @author hexw
	 */
	@RequestMapping("/toAdImageUpload")
	public ModelAndView toAdImageUpload(String imgPath,String imgUrl,String imgWeight,String type,HttpServletRequest request){
		request.setAttribute("imgPath", imgPath);
		request.setAttribute("imgUrl", imgUrl);
		request.setAttribute("imgWeight", imgWeight);
		request.setAttribute("type", type);
		return new  ModelAndView("/advertisement/adImageUpload");
	}
}
  