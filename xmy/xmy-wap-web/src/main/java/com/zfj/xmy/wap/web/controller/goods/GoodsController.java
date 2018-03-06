package com.zfj.xmy.wap.web.controller.goods;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.activity.service.pc.PcPointsStoreService;
import com.zfj.xmy.activity.service.wap.WapContainerService;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.PointsActivity;
import com.zfj.xmy.common.persistence.pojo.PointsGoods;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.service.CommonLimitActivityService;
import com.zfj.xmy.elasticsearch.document.GoodsDoc;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcSearchDto;
import com.zfj.xmy.goods.persistence.wap.SearchGoodsDto;
import com.zfj.xmy.goods.service.app.AppGoodsService;
import com.zfj.xmy.goods.service.pc.PcElasticGoodsService;
import com.zfj.xmy.goods.service.pc.PcGoodsService;
import com.zfj.xmy.goods.service.pc.PcSearchService;
import com.zfj.xmy.goods.service.wap.WapGoodsService;
import com.zfj.xmy.user.service.pc.UserCenterService;
import com.zfj.xmy.util.DataPage;
import com.zfj.xmy.wap.web.common.AjaxResult;
import com.zfj.xmy.wap.web.common.SessionInfo;
import com.zfj.xmy.wap.web.controller.common.CommonController;

/**
 * 商品控制层
 * @author cj
 * @createDate 2017年10月24日
 *
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends CommonController{
	
	@Autowired
	private PcElasticGoodsService elasticGoodsService;
	@Autowired
	private PcSearchService pcSearchService;
	@Autowired
	private PcGoodsService goodsService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private CommonLimitActivityService commonLimitActivityService;
	@Autowired
	private AppGoodsService appGoodsService;
	@Autowired
	private PcPointsStoreService pointsStoreService;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private WapGoodsService wapGoodsService;
	@Autowired
	private WapContainerService containerService;
	
	
	/**商品列表页*/
	private final static String GOODS_LIST = "goods/goods_list" ;
	/**商品分页列表页*/
	private final static String GOODS_PAGE = "goods/goods_page" ;
	/**商品详情页*/
	private final static String GOODS_INFO = "goods/goods_info" ;
	/**商品搜索页*/
	private final static String SEARCH_GOODS = "goods/search_goods" ;
	/**商品分页搜索*/
	private final static String SEARCH_GOODS_PAGE = "goods/search_goods_page" ;
	/**商品分页搜索*/
	private final static String SEARCH_GOODS_PAGE_TWO = "goods/search_goods_page_two" ;
	/**商品积分详情页面*/
	private final static String GOODS_POINTS_INFO = "goods/goods_points_info" ;
	/**搜索条件页面*/
	private final static String GOODS_TYPE = "goods/search_type" ;
	
	/**
	 * 跳转商品列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goodsList")
	public ModelAndView goodsList(HttpServletRequest request, HttpServletResponse response, Long typeId){
		
		request.setAttribute("typeId", typeId);
		
		return new ModelAndView(GOODS_LIST);
	}
	
	/**
	 * 分页查找商品列表
	 * @param request
	 * @param response
	 * @param typeId
	 * @param pageBean
	 * @return
	 */
	@RequestMapping("/goodsPage")
	public ModelAndView goodsPage(HttpServletRequest request, HttpServletResponse response, Long typeId, PageBean pageBean){
		
		List<Integer> page = new ArrayList<Integer>();
		pageBean.setPageSize(10);
		List<Goods> goodsList = pcSearchService.findSearchGoodsByTypeId(typeId,null,0,pageBean,null,false,null,null,null);
		int countPage = pcSearchService.countPage(goodsList.size());
		for(int i = 1;i<=countPage;i++){
			page.add(i);
		}
		request.setAttribute("page", page);
		request.setAttribute("pageNum", pageBean.getPageIndex());
		request.setAttribute("goodsList", goodsList);
		
		return new ModelAndView(GOODS_PAGE);
	}
	
	/**
	 * 获取商品详情(普通)
	 * @param request
	 * @param response
	 * @param goodsId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/goodsInfo")
	public ModelAndView goodsInfo(HttpServletRequest request, HttpServletResponse response, Long goodsId, Long activityId, Integer activityType, Long pointsId, String from, Integer isappinstalled) throws ParseException{
		
		//判断是否是活动商品
		PageBean pageBean = new PageBean();
		Long userId = null ;
		Boolean isPutaway = wapGoodsService.isPutaway(goodsId);
		if(isPutaway) throw new BusinessException("该商品已下架");
		if(ObjectUtils.isEmpty(activityId)) activityId = 0L;
		if(!ObjectUtils.isEmpty(SessionInfo.get())) userId = SessionInfo.get().getUserId() ;
		//查询商品详细信息
		if(pointsId != null){
			PointsActivity pointsActivity=pointsStoreService.findPointsAct(pointsId);
			PointsGoods pointsGoods=pointsStoreService.findPoGoods(goodsId, pointsId);
			request.setAttribute("pointsId", pointsId);
			request.setAttribute("pointsGoods", pointsGoods);
			request.setAttribute("pointsActivity", pointsActivity);
		}
		boolean limitGoods = commonLimitActivityService.checkGoodsIsLimitActivityGoods(goodsId);
		Map<String,Object> map = new HashMap<String,Object>();
		Long millis = null;
		//Long sum = null;
		if(limitGoods){
			if(activityType == null || activityType != 2){
				throw new BusinessException("限时限量商品activityType不正确");
			}else{
				LimitGoods limit = wapGoodsService.getActivityNum(activityId, goodsId);
				if(limit == null){
					throw new BusinessException("限时限量商品activityId不正确");
				}
				//冰点价
				if(activityId == 35){
					map = getLimitData(limit.getActivityTime());
					if(Long.parseLong(map.get("nowTime").toString()) < Long.parseLong(map.get("startTimes").toString()) || 
							Long.parseLong(map.get("nowTime").toString()) > Long.parseLong(map.get("endTime").toString())){
						throw new BusinessException("限时限量商品活动时间不正确");
					}
				}
				//天天特价
				if(activityId == 36){
					millis = getDate(limit.getActivityTime());
					if(millis <= 0){
						throw new BusinessException("限时限量商品活动时间不正确");
					}
				}
				
				request.setAttribute("limit", limit);
			}
		}
		AppGoodsDir goodsDetail = appGoodsService.getGoodsDetails(goodsId,activityId,activityType,userId);
		/*if(activityType != 0L){
			LimitGoods limit = wapGoodsService.getActivityNum(activityId, goodsId);
			request.setAttribute("limit", limit);
		}*/
		request.setAttribute("goods", goodsDetail);
		//猜你喜欢的商品
		pageBean.setPageSize(3);
		List<Goods> yourLike = wapGoodsService.roundGoods(3,goodsId);
		request.setAttribute("huaGoods", yourLike);
		request.setAttribute("activityType", activityType);
		request.setAttribute("activityId", activityId);
		Map<String, Object> data=new HashMap<String, Object>();
		data.putAll(map);
		request.setAttribute("data", data);
		request.setAttribute("millis", millis);
		request.setAttribute("sum", getWeek());
		request.setAttribute("from", from);
		request.setAttribute("isappinstalled", isappinstalled);
		return new ModelAndView(GOODS_INFO);
	}
	
	/**
	 * 冰点价（每日秒杀）时间
	 * @return
	 */
	private Map<String,Object> getLimitData(Date dates){
		
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dates);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		/*if(cal.getTime().getTime() < date.getTime()){
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}*/
		cal.add(Calendar.DAY_OF_YEAR, 1);
		map.put("nowTime", date.getTime());
		map.put("endTime", cal.getTime().getTime());
		cal.add(Calendar.DAY_OF_YEAR, -1);
		map.put("startTime", cal.getTime());
		map.put("startTimes", cal.getTime().getTime());
		return map;
	}
	
	/**
	 * 天天特价的当天剩余时间
	 * @return
	 */
	private long getDate(Date data){
		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		long now = new Date().getTime();

		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		long millis = c.getTimeInMillis() - now;
		//超过一天时间
		if(millis > 24*60*60*1000) millis = 0L;
		return millis;
	}
	
	
	/**
	 * 每周特价倒计时
	 * @return
	 * @throws ParseException 
	 */
	private long getWeek() throws ParseException{
	     Calendar c = Calendar.getInstance(); 
	     long dayWeek = c.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
	     if (dayWeek==1) {  
	    	 dayWeek=7; 
	     }else{
	    	 dayWeek=dayWeek-1;
	     }
	     long day=7-dayWeek;
	     long sum1;
	     sum1=day*24*60*60*1000+getDate(new Date());
	     return sum1;
	}
	
	
	
	/**
	 * 获取商品详情(积分)
	 * @param request
	 * @param response
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/goodsPointsInfo")
	public ModelAndView goodsPointsInfo(HttpServletRequest request, HttpServletResponse response, long goodsId, Long pointsId){
		
		PageBean pageBean = new PageBean();
		int relust=1;
		Integer typeId = 0;
		Long activityId = 0L;
		UserInfo userInfo=null;
		SessionInfo sessionInfo=SessionInfo.get();
		if(sessionInfo!=null){
			userCenterService.addFootprint(sessionInfo.getUserId(), goodsId);
			userInfo=userInfoMapper.selectByPrimaryKey(sessionInfo.getUserId());
			relust=0;
		}
		
		PointsActivity pointsActivity=pointsStoreService.findPointsAct(pointsId);
		PointsGoods pointsGoods=pointsStoreService.findPoGoods(goodsId, pointsId);
		
		//1.查询商品详细信息
		PcGoodsDto goodsDetail = goodsService.getGoodsDetail(goodsId,typeId,activityId);
		//6.猜你喜欢的商品
		pageBean.setPageSize(3);
		List<Goods> yourLike = goodsService.findYouLike(pageBean);
		
		request.setAttribute("yourLike", yourLike);
		request.setAttribute("goods", goodsDetail);
		request.setAttribute("activityId", activityId);
		request.setAttribute("typeId", typeId);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("relust", relust);
		request.setAttribute("pointsActivity", pointsActivity);
		request.setAttribute("pointsGoods", pointsGoods);
		
		return new ModelAndView(GOODS_POINTS_INFO);
	}
	
	
	
	
	/**
	 * 跳转搜索商品（分类）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchGoods")
	public ModelAndView searchGoods(HttpServletRequest request, HttpServletResponse response,SearchGoodsDto searchGoodsDto){
		
		if(searchGoodsDto.getWordId() != null) {
			request.setAttribute("needReload", 1);
		} else {
			request.setAttribute("needReload", 0);
		}
		
		List<PcSearchDto> searchDto = new ArrayList<>();
		if(ObjectUtils.isEmpty(searchGoodsDto.getWordId())){
			searchDto = pcSearchService.findSearchDtoByTypeId(searchGoodsDto.getTypeId());
		}else{
			searchDto = pcSearchService.findSearchDtoByWord(searchGoodsDto.getTypeId(),searchGoodsDto.getWordId());
		}
		request.setAttribute("searchList", searchDto);
		request.setAttribute("searchGoodsDto", searchGoodsDto);
		//通道（通过点击搜索按钮是0， 通过分类进来是1）
		request.setAttribute("gateWay", 1);
		
		return new ModelAndView(SEARCH_GOODS);
	}
	
	@RequestMapping("/searchGoods/{categoryId}")
	public ModelAndView serchGoods(@PathVariable("categoryId") Long categoryId, HttpServletRequest request, HttpServletResponse response) {
		boolean flag = containerService.checkIsLevelOne(categoryId);
		if(flag) {
			SearchGoodsDto searchGoodsDto = new SearchGoodsDto();
			searchGoodsDto.setOneId(categoryId);
			return searchGoodsByTop(request, response, searchGoodsDto);
		} else {
			SearchGoodsDto searchGoodsDto = new SearchGoodsDto();
			searchGoodsDto.setTypeId(categoryId);;
			return searchGoods(request, response, searchGoodsDto);
		}
	}
	
	/**
	 * 跳转搜索商品（二级级分类）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchGoodsByTop")
	public ModelAndView searchGoodsByTop(HttpServletRequest request, HttpServletResponse response,SearchGoodsDto searchGoodsDto){
		
		List<PcSearchDto> searchDtoByoneId = pcSearchService.findSearchDtoByoneId(searchGoodsDto.getOneId());
		request.setAttribute("searchList", searchDtoByoneId);
		request.setAttribute("searchGoodsDto", searchGoodsDto);
		request.setAttribute("gateWay", 1);
		
		return new ModelAndView(SEARCH_GOODS);
	}
	
	
	/**
	 * 根据二级分类ID查询商品名称
	 * @param pageIndex
	 * @param orderMethod
	 * @param typeId
	 * @param priceOrder
	 * @param search
	 * @param isDivle
	 * @param beginPrice
	 * @param endPrice
	 * @param wordId
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年11月2日 下午8:35:55
	 */
	@RequestMapping("typeSearch/{pageIndex}/{orderMethod}")
	public ModelAndView searchTypeGoods(@PathVariable("pageIndex") Integer pageIndex,@PathVariable("orderMethod") Integer orderMethod,@Param("typeId") Long typeId,
			@Param("priceOrder") boolean priceOrder,@Param("search") String search,@Param("isDivle") Integer isDivle,@Param("beginPrice") BigDecimal beginPrice,
			@Param("endPrice") BigDecimal endPrice,@Param("wordId") Long wordId,  HttpServletRequest request){
		List<Integer> page = new ArrayList<Integer>();
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(20);
		pageBean.setPageIndex(pageIndex);
		List<Goods> goodsList = pcSearchService.findSearchGoodsByTypeId(typeId,wordId,orderMethod, pageBean, search, priceOrder,isDivle,beginPrice,endPrice);
		int countPage = pcSearchService.countPage(goodsList.size());
		for(int i = 1;i<=countPage;i++){
			page.add(i);
		}
		request.setAttribute("page", page);
		request.setAttribute("pageNum", pageIndex);
		request.setAttribute("goodsList", goodsList);
		
		return new ModelAndView("/search/type_list");
	}
	
	
	/**
	 * 搜索商品分页请求（分类）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchGoodsPage/{status}")
	public ModelAndView searchGoodsPage(@PathVariable("status")Integer status, HttpServletRequest request, HttpServletResponse response, SearchGoodsDto searchGoodsDto, PageBean pageBean){
		
		Long typeId = searchGoodsDto.getTypeId();
		if(containerService.checkIsLevelOne(typeId)) {
			//searchGoodsDto.setTypeId(null);
			searchGoodsDto.setOneId(typeId);
		}
		
		if(ObjectUtils.isEmpty(pageBean.getPageIndex())) pageBean.setPageIndex(1);
		pageBean.setPageSize(20);
		List<?> goodsList = null;
		if(!StringUtil.isEmpty(searchGoodsDto.getGoodsName()) || (ObjectUtils.isEmpty(searchGoodsDto.getTypeId()) && ObjectUtils.isEmpty(searchGoodsDto.getWordId()))){
			DataPage<GoodsDoc> dataPage = elasticGoodsService.searchGoods(searchGoodsDto.getOrderMethod(),searchGoodsDto.getGoodsName(), pageBean.getPageIndex(),
					searchGoodsDto.getPriceOrder(),searchGoodsDto.getMianLand(),searchGoodsDto.getWordSeg(),
					ObjectUtils.isEmpty(searchGoodsDto.getBeginPrice()) ? null : searchGoodsDto.getBeginPrice().intValue(),
					ObjectUtils.isEmpty(searchGoodsDto.getEndPrice()) ? null : searchGoodsDto.getEndPrice().intValue(),searchGoodsDto.getTypeName());
			goodsList = dataPage.getList();
			pageBean.setTotalCount((int) dataPage.getTotal());
			request.setAttribute("searchType", "name");
		}else if(searchGoodsDto.getOneId() != null){
			goodsList = pcSearchService.findSearchGoodsByOneId(searchGoodsDto.getOneId(), searchGoodsDto.getOrderMethod(), pageBean, 
					null, searchGoodsDto.getWordSeg(), searchGoodsDto.getPriceOrder(), searchGoodsDto.getIsDivle(), searchGoodsDto.getBeginPrice(), searchGoodsDto.getEndPrice());
			request.setAttribute("searchType", "type");
		}else{
			String search = searchGoodsDto.getWordSeg();
/*			String[] split = search.split(",");
			try {
				search = split[split.length - 1] == null || split[split.length - 1].trim().length() == 0 ? split[split.length - 2] : split[split.length - 1];
			} catch(Exception e) {
				search = "";
			}*/
			goodsList = pcSearchService.findSearchGoodsByTypeId(searchGoodsDto.getTypeId(),searchGoodsDto.getWordId(),
					searchGoodsDto.getOrderMethod(), pageBean,search,searchGoodsDto.getPriceOrder(),
					searchGoodsDto.getIsDivle(),searchGoodsDto.getBeginPrice(),searchGoodsDto.getEndPrice());
			request.setAttribute("searchType", "type");
		}
		request.setAttribute("pageFlag", searchGoodsDto.getFlag());
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("goodsList", goodsList);
		if(status==1){
			return new ModelAndView(SEARCH_GOODS_PAGE_TWO);
		}else
			return new ModelAndView(SEARCH_GOODS_PAGE);
	}
	
	/**
	 * 跳转搜索商品（名称）
	 * @param request
	 * @param response
	 * @param searchGoodsDto
	 * @return
	 */
	@RequestMapping("/searchGoodsByName")
	public ModelAndView searchGoodsByName(HttpServletRequest request, HttpServletResponse response,SearchGoodsDto searchGoodsDto){
		
		List<PcSearchDto> searchDto = pcSearchService.querySearchDto(searchGoodsDto.getGoodsName(),searchGoodsDto.getTypeName(),null);
		if(ObjectUtils.isEmpty(searchGoodsDto.getGoodsName())){
			request.setAttribute("goodsName", searchGoodsDto.getGoodsName());
		}
		request.setAttribute("searchList", searchDto);
		request.setAttribute("searchGoodsDto", searchGoodsDto);
		
		//通道（通过点击搜索按钮是0， 通过分类进来是1）
		request.setAttribute("gateWay", 0);
		
		return new ModelAndView(SEARCH_GOODS);
	}
	
	private String pcSearchDtoToString(List<PcSearchDto> dtos) {
		String result = "";
		for(PcSearchDto dto : dtos) {
			List<PcSearchDto> childList = dto.getChildList();
			for(PcSearchDto dto1 : childList) {
				result += dto1.getSearchName();
			}
		}
		return result;
	}
	
	@RequestMapping("/searchDtoByWordsAndTypeId")
	@ResponseBody
	public AjaxResult searchDtoByWordsAndTypeId(String words, Long typeId) {
		String result = null;
		boolean flag = containerService.checkIsLevelOne(typeId);
		if(flag) {
			List<PcSearchDto> querySearchDto = pcSearchService.querySearchDto(null, typeId, words);
			result = pcSearchDtoToString(querySearchDto);
		} else {
			List<PcSearchDto> querySearchDto = pcSearchService.querySearchDto(typeId, null, words);
			result = pcSearchDtoToString(querySearchDto);
		}
		return AjaxResult.success(null, result);
	}
	
	/**
	 * 反查筛选条件
	 * @param goodsName
	 * @param searchName
	 * @param typeName
	 * @param mainLand
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年11月8日 上午10:18:56
	 */
	@RequestMapping("/searchDto")
	public ModelAndView findSearchDto(HttpServletRequest request, SearchGoodsDto searchGoodsDto){
		Integer mainLand = searchGoodsDto.getMianLand();
		String[] typeName = null;
		if(ObjectUtils.isEmpty(mainLand) || mainLand < 0){
			mainLand = null;
		}
		List<PcSearchDto> searchDto = pcSearchService.querySearchDto(searchGoodsDto.getGoodsName(),searchGoodsDto.getTypeName(), null);// 暂时改为null
		request.setAttribute("searchList", searchDto);
		if(!StringUtil.isEmpty(searchGoodsDto.getTypeName())) typeName = searchGoodsDto.getTypeName().split(",");
		request.setAttribute("typeName", typeName);
		
		return new ModelAndView(GOODS_TYPE);
	}
	
	@RequestMapping("/searchDtoByTypeId")
	public String searchDtoByTypeId(Long typeId, ModelMap model) {
		boolean flag = containerService.checkIsLevelOne(typeId);
		List<PcSearchDto> searchDto = new ArrayList<PcSearchDto>();
		if(flag) {
			searchDto = pcSearchService.findSearchDtoByoneId(typeId);
		} else {
			searchDto = pcSearchService.findSearchDtoByTypeId(typeId);
		}
		model.addAttribute("searchList", searchDto);
		return GOODS_TYPE;
	}
	
	/**
	 * 判断商品是否下架
	 * @Description 
	 * @param request
	 * @param searchGoodsDto
	 * @return
	 * @Author cj
	 * @Date 2017年11月6日下午10:47:22
	 */
	@RequestMapping("/isPutaway")
	@ResponseBody
	public RespData isPutaway(HttpServletRequest request, Long goodsId){
		
		RespData resp = new RespData();
		Boolean isPutaway = wapGoodsService.isPutaway(goodsId);
		resp.setData(isPutaway);
		
		return resp;
	}
	
	
}
