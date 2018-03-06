package com.zfj.xmy.cms.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.util.JSONUtils;

import org.apache.commons.io.FileUtils;
import org.quartz.DateBuilder;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.service.BaseCommonService;
import com.zfj.xmy.common.service.CommonPayOrderService;
import com.zfj.xmy.common.service.CommonPushUtilService;
import com.zfj.xmy.elasticsearch.document.GoodsDoc;
import com.zfj.xmy.elasticsearch.persistence.pojo.NativeSearchQueryParameter;
import com.zfj.xmy.elasticsearch.service.GoodsDocService;
import com.zfj.xmy.goods.service.cms.CmsGoodsService;
import com.zfj.xmy.goods.service.common.GoodsTestService;
import com.zfj.xmy.order.service.cms.OrderService;
import com.zfj.xmy.order.service.cms.PayLogService;
import com.zfj.xmy.oss.OSSManager;
import com.zfj.xmy.quartz.QuartzManager;
import com.zfj.xmy.quartz.QuartzTest;
import com.zfj.xmy.util.DataPage;

@RequestMapping("/test")
@RestController
public class TestController {

	@Autowired
	private PayLogService payLogService ;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OSSManager ossManager;
	
	@Autowired
	private CmsGoodsService cmsGoodsService;
	
	@Autowired
	private GoodsDocService goodsDocService;
	
	@Autowired
	private QuartzManager quartzManager;
	
	@Autowired
	private CommonPushUtilService commonPushUtilService;
	
	@Autowired
	private BaseCommonService baseCommonService;
	
	@Autowired
	private CommonPayOrderService commonPayOrderService;
	
	@Autowired
	private GoodsTestService  goodsTestService;
	
	@RequestMapping("/index")
	public RespData index(){
		
		System.out.println("ss");
		RespData respData = new RespData() ;
		respData.setData("ok cms") ;
		return respData ;
	}


	

	@RequestMapping("/paylog/1")
	public PageBean payLogTest(){
		RespData respData = new RespData() ;
		ReqData reqData = new ReqData() ;
		int count = orderService.findAllorderCount(reqData) ;
		reqData.putValue("user_name","张",SystemConstant.REQ_PARAMETER_CN) ;
		PageBean pageBean = new PageBean() ;
		pageBean.setTotalCount(count);
		
		pageBean.setPageIndex(2) ;
		pageBean.setPageSize(5) ;
		payLogService.findTest2(pageBean, reqData) ;
		return pageBean ;
		
	}

	/*@RequestMapping("/order")
	public ModelAndView payLogTest1(){
		
		PayLog paylog=new PayLog();
		paylog.setId((long)1);
		paylog.setUserName("李杰");
		payLogService.updatePaylog(paylog);
		return new ModelAndView("redirect:/order.jsp");
	}*/
	
	@RequestMapping("/order/{pageNo}")
	public ModelAndView payLogTest2(HttpServletRequest request,@PathVariable("pageNo") Integer pageNos){
		RespData respData=new RespData();
		ReqData reqData=new ReqData();
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(5);
		pageBean.setPageIndex(pageNos);
		payLogService.findTest2(pageBean, reqData);
		respData.setData(pageBean);
		request.getSession().setAttribute("pageModel", respData);
		return new ModelAndView("redirect:/order.jsp");
	}
	@RequestMapping("/queryAll")
	public RespData queryAllorders(){
		ReqData reqData=new ReqData();
		RespData respData=new RespData();
		PageBean pageBean=new PageBean();
		reqData.putValue("pay_type", 1,SystemConstant.REQ_PARAMETER_EQ);
		pageBean.setPageSize(5);
		pageBean.setPageIndex(1);
		orderService.findAllorders(pageBean, reqData);
		respData.setData(pageBean);
		return respData;
	}
	
	@RequestMapping("/paylog/list")
	public ModelAndView payLogList(HttpServletRequest request,ReqData reqData){
		System.out.println("============================================");
		request.setAttribute("totalCount",10) ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		//map.put("name","dengq") ;
		//map.put("age","27") ;
		reqData.putValue("name","dengq") ;
		reqData.putValue("age",27,SystemConstant.REQ_PARAMETER_GE) ;
		String json = JSON.toJSONString(reqData) ;
		System.out.println(json) ;
		request.setAttribute("req",json);
		return new ModelAndView("/native/native_list") ;
	}
	
	@RequestMapping("/paylog/list/{pageIndex}")
	public ModelAndView payLogList(@PathVariable("pageIndex") Integer pageIndex
			,ReqData reqData
			,HttpServletRequest request){
		String value = (String) reqData.getValue("username");
		System.out.println(value+"--------------------------------------");
		List<Map<String,Object>> dList = new ArrayList<Map<String,Object>>() ;
		for(int i=0 ;i<20 ;i++){
			Map<String,Object> map = new HashMap<String,Object>() ;
			map.put("id",(pageIndex-1)*20 + (i+1)) ;
			map.put("name","dengq" + ((pageIndex-1)*20 + (i+1))) ;
			dList.add(map) ;
		}
		try {
			Thread.sleep(500) ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.setAttribute("dList",dList) ;
		return new ModelAndView("/native/native_list_page") ;
	}
	
	
	/*@RequestMapping("/upload")
	public RespData upload(@RequestParam("file") MultipartFile file
			,HttpServletRequest request){
		InputStream is = null ;
		try {
			is = file.getInputStream() ;
			FileUtils.copyInputStreamToFile(is,new File(request.getServletContext().getRealPath("/") + "testtttt.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(is) ;
		}
		RespData respData = new RespData() ;
		respData.setData("testtttt.jpg");
		return respData ;
	}*/

	@RequestMapping("/oss")
	public boolean ossTest(){
		
		System.out.println("accessId=" + ossManager.getAccessId()) ;
//		System.out.println("accessKey=" + ossManager.get)) ;
		System.out.println("bucket=" + ossManager.getBucket()) ;
		
		return true ;
	}
	
	@RequestMapping("/editorTest")
	public ModelAndView editorTest(){
		
		return new  ModelAndView("/article/ueditor/ueditor");
	}
	
	/**
	 * 批量关联商品图片
	 * @return    
	 * @return DataPage<GoodsDoc>    
	 * Date:2017年12月11日 下午1:40:06 
	 * @author hexw
	 */
	@RequestMapping("/addGoodsImage")
	public void addGoodsImage(){
		cmsGoodsService.goodsImageCorrelation();
	}
	
	/**
	 * 获取文件里的所有商品图片的编码
	 *     
	 * @return void    
	 * Date:2017年12月15日 下午4:24:10 
	 * @author hexw
	 */
	@RequestMapping("/getFileGoodsCode")
	public void getFileGoodsCode(){
		cmsGoodsService.getFileGoodsCode();
	}
	
	@RequestMapping("/elastic")
	public DataPage<GoodsDoc> selectGoods(){
		NativeSearchQueryParameter nqp = new NativeSearchQueryParameter();
		DataPage<GoodsDoc> fullTextSearch = goodsDocService.fullTextSearch(nqp);
		return fullTextSearch;
	}
	
	@RequestMapping("/test")
	public void test() throws SchedulerException{
		//quartzManager.addJob("test", "test", "test", "test", QuartzTest.class, "0/5 * * * * ?");
		//quartzManager.removeJob("test", "test", "test", "test");

//		Map<Object, Object> map = new HashMap<>();
//		map.put("1", "数据1");
//		map.put("2", "数据2");
//		quartzManager.addJob(RandomUtil.randomUUID(), "test1", RandomUtil.randomUUID(), "test1", QuartzTest.class, DateBuilder.nextGivenMinuteDate(new Date(), 59),map);
//		
//		commonPushUtilService.queryAllPush();
		//quartzManager.queryAllPush();
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(1);
		commonPushUtilService.findRecordsByUser(221189L, pageBean);
		System.out.println(pageBean);
	//	baseCommonService.refreshIndexHomePage();
	}

	
	@RequestMapping("/text20180109")
	public void tt(){
		/*commonPayOrderService.extensionOrderTimerPRO();*/
	
	}
	
	/*@RequestMapping("/updateGoodsSupplierName")
	public void updateGoodsSupplierName(){
		cmsGoodsService.updateGoodsSupplierName();
	}*/
}
