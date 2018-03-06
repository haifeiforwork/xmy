package com.zfj.xmy.goods.service.common.impl;  

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StrMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoleilu.hutool.util.StrUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsImageMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.pojo.GoodsImage;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.goods.service.common.GoodsTestService;

/** 
 * @Title: GoodsTestServiceImpl.java 
 * @Package com.zfj.xmy.goods.service.common.impl 
 * @Description: 
 * @author hexw
 * @date 2018年1月17日 下午2:50:33 
 */
@Service
public class GoodsTestServiceImpl extends BaseService implements GoodsTestService  {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsImageMapper goodsImageMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String,Object> goodsImageCorrelation(){
		 String filePath = "C:/Users/Administrator/Desktop/testgoodsimage/01/" ;  //读取文件路径
		 Map<String,Object> result = new  HashMap<String,Object>();
		 String imgName = "";
		 File file = new File(filePath);
		 File[] files = file.listFiles();
		 for (int i = 0; i < files.length; i++){
			GoodsWithBLOBs goods = new  GoodsWithBLOBs();
		 	imgName = files[i].getName();
            if (imgName.indexOf("-") > -1){
            	String goodsCode = imgName.substring(0, imgName.indexOf("-",imgName.indexOf("-")) );  //商品编码
                List<GoodsWithBLOBs> list = findGoodsByCode(goodsCode); 
    			if (list.size() > 0) {
    				for (GoodsWithBLOBs goodsWithBLOBs : list) {
    					goods = goodsWithBLOBs;
        				if (imgName.substring(imgName.indexOf("-"),imgName.indexOf(".")).length() == 3) {  //商品详情图
        					batchAddDetailImg(files[i],goods);
        				}
        				if (imgName.substring(imgName.indexOf("-"),imgName.indexOf(".")).length() == 4) {  //商品主图
        					batchAddMainImg(files[i], goods);
        				} 
					}
    			}else{
    				System.out.println("未找到商品");
    				result.put("error", "未能找到商品");
    			}
            }
		 }
		return result;
	}
	
	
	
	
	/**
	 * 批量添加商品主图
	 * Date:2017年8月29日 下午8:11:50 
	 * @author hexw
	 */
	public void batchAddMainImg(File file, GoodsWithBLOBs goods){
		String dir = "http://img.xmy365.com/goods/goods/20180118/" ; //商品图片前缀
		boolean isRepetition = false ; // 是否有重复的图片上传
		// 1. 查询商品主图判断是否有重复的图片上传 有就直接替换
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id", goods.getId(), SystemConstant.REQ_PARAMETER_EQ);
		List<GoodsImage> imgs = goodsImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (GoodsImage goodsImage : imgs) {
			if (goodsImage.getPath().contains(file.getName())) {
				goodsImage.setPath(dir+file.getName());
				goodsImageMapper.updateByPrimaryKeySelective(goodsImage); 
				isRepetition = true ;
			}
		}
		// 2. 新增商品图片
		if (!isRepetition) {
			GoodsImage goodsImage = new GoodsImage();
			goodsImage.setGoodsId(goods.getId());
			goodsImage.setPath(dir+file.getName());  
			goodsImage.setSeq(0);
			goodsImage.setCreateTime(new Date());
			goodsImage.setType(SystemConstant.GOODS_IMAGE.GOODS_MIAN_IMAGE);
			goodsImageMapper.insert(goodsImage);
		}
	}
	
	/**
	 * 批量添加商品详情图
	 * Date:2017年8月29日 下午8:12:45 
	 * @author hexw
	 */
	public void batchAddDetailImg(File file, GoodsWithBLOBs goods){
		String dir = "http://img.xmy365.com/goods/goods/20180118/" ; //商品图片前缀
		if (StringUtils.isEmpty(goods.getPcIntroduced()) && StringUtils.isEmpty(goods.getPhIntroduced())) {
			String html = "<p><img src="+dir+file.getName()+" style='float:left'><p>";
			goods.setPcIntroduced(html);
			goods.setPhIntroduced(html);
		} else {
			// 判断商品描述图是否有重复的图片上传
			String pcIntroduced = goods.getPcIntroduced();
			if (pcIntroduced.contains(file.getName())) { //包含重复的
				goods.setPcIntroduced(updateIntroduced(dir,file.getName(),goods.getPcIntroduced()));
				goods.setPhIntroduced(updateIntroduced(dir,file.getName(),goods.getPhIntroduced()));
			} else {  //未重复
				String html = "<img src="+dir+file.getName()+" style='float:left'>";
				StringBuffer pc = new StringBuffer(goods.getPcIntroduced());
				StringBuffer ph = new StringBuffer(goods.getPhIntroduced());
				goods.setPcIntroduced(pc.insert(pc.lastIndexOf("<p>"),html).toString());
				goods.setPhIntroduced(ph.insert(ph.lastIndexOf("<p>"), html).toString());	
			}
		}
		goodsMapper.updateByPrimaryKeyWithBLOBs(goods);
	}
	
	
	
	public static String updateIntroduced(String dir,String fileName,String goodsIntroduced){
		String newIntroduced = "" ;
		//1. 剔除p标签并取出所有的img标签
		String[] pArray = StrUtil.split(goodsIntroduced, "<p>");
		String imgStr = "" ;
		for (String p : pArray) {
			if (p.contains("<img")) {
				imgStr = p.replaceAll("</p>", "");
			}
		}
		//2. 找出是那个img重复上传了
		String imgArray[] = StrUtil.split(imgStr, ">");
		List<String> imgs = new ArrayList<String>();
		for (int i = 0; i < imgArray.length-1; i++) {
			imgs.add(imgArray[i]+"/>");
		}
		for (String img : imgs) {
			if (img.contains(fileName)) {
				img = "<img src="+dir+fileName+" style='float:left'>" ;
			}
			newIntroduced = newIntroduced +img;
		}
		return newIntroduced;
	}
	
	
	public static void main(String[] args) {
		String dir = "http://img.xmy365.com/goods/goods/20180117/" ;
		String fileName= "010207000103-03.jpg" ;
		String newIntroduced = "<p><img src=http://img.xmy365.com/goods/goods/20180118/010207000103-01.jpg style='float:left'><img src=http://img.xmy365.com/goods/goods/20180118/010207000103-02.jpg style='float:left'><img src=http://img.xmy365.com/goods/goods/20180118/010207000103-03.jpg style='float:left'></p>";
		System.out.println(updateIntroduced(dir,fileName,newIntroduced));
	
	}
	
	
	/**
	 * 根据商品编码查询商品
	 * @param code
	 * @return    
	 * @return List<GoodsWithBLOBs>    
	 * Date:2017年7月19日 下午2:35:35 
	 * @author hexw
	 */
	public List<GoodsWithBLOBs> findGoodsByCode(String code){
		ReqData reqData = new  ReqData();
		reqData.putValue("code", code, SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("id");
		reqData.setSortorder("desc");
		return goodsMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
}
  