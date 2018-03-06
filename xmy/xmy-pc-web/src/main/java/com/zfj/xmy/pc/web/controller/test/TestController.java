package com.zfj.xmy.pc.web.controller.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.annotation.CheckLogin;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcBuyandPresentDto;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;
import com.zfj.xmy.activity.service.pc.PcBuyandPresentService;
import com.zfj.xmy.activity.service.pc.PcColumService;
import com.zfj.xmy.common.VocabularyConstant;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.elasticsearch.XmyIndex;
import com.zfj.xmy.elasticsearch.document.CategoryDoc;
import com.zfj.xmy.elasticsearch.document.CategoryDocDTO;
import com.zfj.xmy.elasticsearch.document.GoodsDoc;
import com.zfj.xmy.elasticsearch.persistence.pojo.NativeSearchQueryParameter;
import com.zfj.xmy.elasticsearch.persistence.pojo.SearchConstant.SearchOperation;
import com.zfj.xmy.elasticsearch.service.CategoryDocService;
import com.zfj.xmy.elasticsearch.service.GoodsDocService;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcSearchDto;
import com.zfj.xmy.goods.service.pc.PcSearchService;
import com.zfj.xmy.order.service.pc.ShoppingCartService;
import com.zfj.xmy.pc.web.Lijie;
import com.zfj.xmy.util.DataPage;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private PcColumService columService;
	
	@Autowired
	private PcBuyandPresentService buyandPresentService;
	
	@Autowired
	private PcSearchService pcSearchService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private GoodsDocService goodsDocService;
	
	@Autowired
	private CategoryDocService categoryDocService;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	@RequestMapping("/index")
	public ModelAndView index(){
		
		return new ModelAndView("/home/index") ;
	}
	
	@RequestMapping("/goods")
	public List<PcBuyandPresentDto>  goodsDetail(){
		List<PcBuyandPresentDto> findPcBuyandPresentDto = buyandPresentService.findPcBuyandPresentDto(new PageBean());
		return findPcBuyandPresentDto;
	}
	
	@RequestMapping("/center")
	public ModelAndView center(HttpServletResponse response){
		shoppingCartService.addShoppingCartCookie(new Goods(), null, response);
		return new ModelAndView("/center/center_myorder");
	}
	
	@RequestMapping("/new")
	@CheckLogin
	public ModelAndView order(int ter,int chd,HttpServletRequest request){
		List<PcDownColumDto> findDownColum = columService.findDownColum(VocabularyConstant.VOC_COLUMN);
		request.getSession().setAttribute("findDownColum", findDownColum);
		request.setAttribute("ter", ter);
		request.setAttribute("chd", chd);
		return new ModelAndView("/news/about_xmy");
	}
	
	@RequestMapping("/testWord/{id}")
	public Map<String, Object> testWord(@PathVariable("id") Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		String findTestString = columService.findTestString(id);
		map.put("typeName", findTestString);
		return map;
	}
	
	@RequestMapping("/search")
	public ModelAndView searchGoods(HttpServletRequest request){
		List<PcSearchDto> findSearchDto = pcSearchService.findSearchDto("果");
		//List<Goods> findSearchGoods = pcSearchService.findSearchGoods("果", 1);
		request.setAttribute("searchList", findSearchDto);
		//request.setAttribute("searhGoods", findSearchGoods);
		return new ModelAndView("/search/goods_search");
	}
	
	@RequestMapping("/elastic")
	public DataPage<GoodsDoc> elastic(){
		NativeSearchQueryParameter nbq = new NativeSearchQueryParameter();
		DataPage<GoodsDoc> fullTextSearch = goodsDocService.fullTextSearch(nbq);
		
		return fullTextSearch;
	}
	
	@RequestMapping("/cory")
	public List<CategoryDocDTO> cory(){
		NativeSearchQueryParameter nbq = new NativeSearchQueryParameter();
		List<CategoryDocDTO> listCategoryData = categoryDocService.listCategoryData(nbq);
		return listCategoryData;
	}
	
	@RequestMapping("/delete")
	public String testDelete(){
		
		goodsDocService.rebuildAllIndex();
		return "success";
	}
	
	@RequestMapping("/add")
	public String save(){
		//删除索引
		Client client = elasticsearchTemplate.getClient();
		//client.admin().indices().prepareDelete(XmyIndex.GOODS_INDEX_NAME).execute().actionGet();
		//创建索引
		client.prepareIndex(XmyIndex.GOODS_INDEX_NAME, XmyIndex.GOODS_TYPE).execute().actionGet();
		//登录时添加到elasticsearch以前商品全部添加后面新增的商品可以不用管
		return "success";
	}
	
	@RequestMapping("/del")
	public String delete(){
		//删除索引
		Client client = elasticsearchTemplate.getClient();
		client.admin().indices().prepareDelete(XmyIndex.GOODS_INDEX_NAME).execute().actionGet();
		return "success";
	}
	@RequestMapping("/putway")
	public String putway(){
		int putwayGoods = goodsDocService.putwayGoods();
		return "success";
	}
	
	@RequestMapping("/activityPutway")
	public String activityPutway(){
		goodsDocService.putwayActivtyGoods();
		return "success";
	}
	
	@RequestMapping("/testPutway")
	public String testPutway(){
		goodsDocService.buyAndPresentPutway();
		goodsDocService.limitActivityPutway();
		goodsDocService.promotionActivityPutway();
		return "success";
	}
	
	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(){
		
		return new ModelAndView("/center/center_order_detail");
	}
	
	@RequestMapping("/query")
	public List<GoodsDoc> getIndexGoods(HttpServletRequest request){
		List<GoodsDoc> allGoods = goodsDocService.queryAllGoods();
		return allGoods;
	}
	
	@RequestMapping("/findPwd")
	public ModelAndView findPwd(){
		
		return new ModelAndView("/home/find_password");
	}
}
