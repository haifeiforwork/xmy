package com.zfj.xmy.wap.web.controller.border;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zfj.xmy.activity.persistence.common.pojo.dto.WapContainerOutDto;
import com.zfj.xmy.activity.service.cms.ContainerService;
import com.zfj.xmy.activity.service.cms.IndexSettingService;
import com.zfj.xmy.activity.service.wap.WapContainerService;
import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;
import com.zfj.xmy.goods.service.pc.PcCategoryService;
import com.zfj.xmy.goods.service.pc.PcGoodsService;
import com.zfj.xmy.order.service.cms.AdvertisementService;


@Controller
@RequestMapping("/border")
public class BorderController {
	
	@Autowired
	private PcGoodsService pcGoodsService;
	@Autowired
	private PcCategoryService pcCategoryService;
	@Autowired
	private IndexSettingService indexSettingService;
	@Autowired
	private ContainerService containerService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private CmsGoodsService cmsGoodsService;
	@Autowired
	private WapContainerService wapContainerService;
	//跨境商品
	private final static String BORDER="跨境专区";
	
	/**
	 * 跨国专区
	 * @param request
	 * @return
	 */
	@RequestMapping("/findBorderGoodsAll")
	public ModelAndView findBorderGoodsAll(HttpServletRequest request){
		
		Map<String, Object> data=new HashMap<String, Object>();
		IndexConfig indexConfig = indexSettingService.getCrossBorderSetting();
		//获取top轮播图
		Long topAdId = indexConfig.getTopAdId();
		List<AdImage> adInfo = advertisementService.findAdImage(String.valueOf(topAdId));
		data.put("topAdInfo", adInfo);
		//1号广告位
		Long firstAdId = indexConfig.getFirstAdId();
		List<AdImage> firstAdInfo = advertisementService.findAdImage(String.valueOf(firstAdId));
		data.put("firstAdInfo", firstAdInfo);
		//获取货柜
		String containerIds = indexConfig.getContainerIds();
		
		List<WapContainerOutDto> containerOuts = wapContainerService.findAllContainer(containerIds);
		
/*		List<Container> containers = containerService.findsContainerWithContainerIdString(containerIds);
		
		List<ContainerOutDto> containerOuts=new ArrayList<ContainerOutDto>();
		for (Container container : containers) {
			ContainerOutDto containerOutDto=new ContainerOutDto();
			BeanUtils.copyProperties(container, containerOutDto);
			//货柜的广告
			Long adId = container.getAdId();
			List<AdImage> adImage = advertisementService.findAdImage(String.valueOf(adId));
			containerOutDto.setContainerAd(adImage);
			//货柜的商品
			String goodsIdsString = container.getGoodsIds();
			List<Object> goodsIds = StringUtils.exchangeSplit(goodsIdsString);
			List<Goods> goods = cmsGoodsService.findsGoodsWithIds(goodsIds);
			List<AppGoodsCondense> goodsCondenses=new ArrayList<>();
			for (Goods goods2 : goods) {
				AppGoodsCondense appGoodsCondense=new AppGoodsCondense();
				BeanUtils.copyProperties(goods2, appGoodsCondense);
				goodsCondenses.add(appGoodsCondense);
			}
			containerOutDto.setGoods(goodsCondenses);
			containerOuts.add(containerOutDto);
		}*/
		
		data.put("containers", containerOuts);
		request.setAttribute("data", data);
		
		return new ModelAndView("border/border");
	}
}
