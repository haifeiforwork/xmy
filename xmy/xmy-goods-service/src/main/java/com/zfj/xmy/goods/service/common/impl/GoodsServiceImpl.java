package com.zfj.xmy.goods.service.common.impl;  

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.collection.CollectionExtUtils;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.GoodsImageMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsImage;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.goods.persistence.common.dao.GoodsSoldExMapper;
import com.zfj.xmy.goods.persistence.common.pojo.dto.GoodsSoldDir;
import com.zfj.xmy.goods.service.common.GoodsService;

/** 
 * @Title: GoodsServiceImpl.java 
 * @Package com.zfj.xmy.goods.service.common.impl 
 * @Description: 
 * @author hexw
 * @date 2017年8月25日 上午10:03:21 
 */
@Service
public class GoodsServiceImpl extends BaseService implements GoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsSoldExMapper goodsSoldExMapper;

	@Autowired
	private GoodsImageMapper goodsImageMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	/**
	 * 查询销量前20的商品
	 * @param parentId
	 * @return    
	 * @return List<Goods>    
	 * Date:2017年7月25日 上午9:58:56 
	 * @author hexw
	 */
	@Override
	public List<Goods> findGoodsSold(long parentId){
		List<Goods> goodslist = new ArrayList<Goods>();
		List<Object> ids = new ArrayList<Object>() ;
		// 1. 根据一级分类id 查询销量表前20的商品
		List<GoodsSoldDir> list = goodsSoldExMapper.findGoodsSolad(parentId); 
		for (GoodsSoldDir appGoodsSoldDto : list) {
			Goods goods = goodsMapper.selectByPrimaryKey(appGoodsSoldDto.getGoodsId()); //根据商品id 得到商品
			if (SystemConstant.GoodsConstant.PUTWAY == goods.getIsPutway() && SystemConstant.GoodsConstant.NOT_RECYCLE == goods.getIsRecycle() ){
				//获取商品图片
				goods.setImgPath(commonGoodsService.getFirstGoodsImg(goods.getId()));
				ids.add(appGoodsSoldDto.getGoodsId());
				goodslist.add(goods);
			}
		}
		// 2. 商品数量没满20 从商品里面取 差数来补充
		if ( goodslist.size() < 20  ){
			CriteriaParameter parameter = new CriteriaParameter() ;
			Criteria criteria = parameter.createCriteria() ;
			criteria.equalTo("a.is_putway",SystemConstant.GoodsConstant.PUTWAY) ;
			criteria.equalTo("a.is_recycle", SystemConstant.GoodsConstant.NOT_RECYCLE);
			criteria.equalTo("c.parent_id",parentId) ;
			if(!CollectionExtUtils.isEmpty(ids)){ //不包括销量表已经查出的商品
				criteria.notIn("a.id",ids) ;
			}
			parameter.setOrderByClause("a.id ASC") ;
			PageBean pageBean = new PageBean() ;
			pageBean.setPageIndex(1) ;
			pageBean.setPageSize(20-list.size()) ;
			List<Goods> remainGoodslist =  goodsSoldExMapper.findRemainGoodsSold(parameter,pageBean.getRowBounds());
			
			// 3. 拼接数据
			for (Goods goods : remainGoodslist) {
				ReqData imgReqData = new ReqData();
				imgReqData.putValue("goods_id", goods.getId(), SystemConstant.REQ_PARAMETER_EQ);
				List<GoodsImage> imglist = goodsImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(imgReqData));  
				if(imglist.size() > 0){
					goods.setImgPath(imglist.get(0).getPath());
				}
				goodslist.add(goods);
			}
		}
 		return goodslist;
	}
}
  