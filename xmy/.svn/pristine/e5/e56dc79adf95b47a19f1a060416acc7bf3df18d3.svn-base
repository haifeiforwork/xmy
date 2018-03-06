package com.zfj.xmy.order.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CollectionGoodsMapper;
import com.zfj.xmy.common.persistence.dao.GoodsImageMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.GoodsImage;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.order.service.app.CollectionGoodsService;

@Service
public class CollectionGoodsServiceImpl implements CollectionGoodsService {
	@Autowired
	private CollectionGoodsMapper collectionGoodsMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsImageMapper goodsImageMapper;
	
	
	@Override
   public List<CollectionGoods> findsAllCollectionGoods(ReqData reqData){
	   reqData.putValue(SystemConstant.COLLECTIONGOODS.TYPE, SystemConstant.COLLECTIONGOODS.TYPE_GOODS, SystemConstant.REQ_PARAMETER_EQ);
	   List<CollectionGoods> selectByExample = collectionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	   return selectByExample;
   }
	@Override
	public List<GoodsWithBLOBs> findCollectionGoods(ReqData reqData){
		   List<GoodsWithBLOBs> collectionGoods=new ArrayList<GoodsWithBLOBs>();
		   reqData.putValue(SystemConstant.COLLECTIONGOODS.TYPE, SystemConstant.COLLECTIONGOODS.TYPE_GOODS, SystemConstant.REQ_PARAMETER_EQ);
		   List<CollectionGoods> selectByExample = collectionGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		   if(!ObjectUtils.isEmpty(selectByExample)){
			   for (CollectionGoods collectionGood : selectByExample) {//循环遍历收藏夹，按照收藏夹里面的goodsid取得图片，
				   Long goodsId = collectionGood.getGoodsId();
				   
				   GoodsWithBLOBs selectByPrimaryKey = goodsMapper.selectByPrimaryKey(goodsId);
				   if(!ObjectUtils.isEmpty(selectByPrimaryKey)){
					 //设置收藏的商品对应的收藏夹的id
					   Long collectionId = collectionGood.getId();
					   selectByPrimaryKey.setCollectionId(collectionId);
					   
					   //活动类类型
					   Integer activityType = collectionGood.getActivityType();
					   //活动id
					   Long activityId = collectionGood.getActivityId();
					   selectByPrimaryKey.setActivityId(activityId);
					   selectByPrimaryKey.setActivityType(activityType);
					   
					   
					   ReqData reqDataGoodsImage=new ReqData();
					   reqDataGoodsImage.putValue(SystemConstant.COLLECTIONGOODS.GOODS_ID, goodsId, SystemConstant.REQ_PARAMETER_EQ);
					   //取出商品图片
					   List<GoodsImage> goodsImages = goodsImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqDataGoodsImage));
					   if(goodsImages.size()>=1)
					   selectByPrimaryKey.setImgPath(goodsImages.get(0).getPath());
					   collectionGoods.add(selectByPrimaryKey);
				   }
			}
		   }
		   return collectionGoods;
	}
	@Override
	public void delCollectionGoodsById(Long id){
		Integer deleteCount = collectionGoodsMapper.deleteByPrimaryKey(id);
		if(ObjectUtils.isEmpty(deleteCount)||deleteCount<1){
			throw new BusinessException("删除收藏夹单个商品失败");
		}
	}
	@Override
	public void addCollectionGoods(CollectionGoods collectionGoods){
		Integer insert = collectionGoodsMapper.insert(collectionGoods);
		if(ObjectUtils.isEmpty(insert)||insert<1){
			throw new BusinessException("添加收藏夹单个商品失败");
		}
	}
	@Override
	public void delAndCheckCollectionGoods(Long userId,Long collectionId){
		//1 先判断需要删除的收藏夹单个商品的id是不是属于这个用户id的
		CriteriaParameter parameter=new CriteriaParameter();
		Criteria criteria=parameter.createCriteria();
		criteria.equals(userId);
		criteria.equalTo(SystemConstant.COLLECTIONGOODS.USER_ID, userId);
		criteria.equalTo(SystemConstant.COLLECTIONGOODS.ID, collectionId);
		List<CollectionGoods> collectionGoods = collectionGoodsMapper.selectByExample(parameter);
		
		// 1.1 说明这个用户id和收藏夹商品id、对不上，也就是说不是本人的
		if(ObjectUtils.isEmpty(collectionGoods)){
			throw new BusinessException("对不起，删除失败，这个收藏夹商品不属于你!!");
		}else{
			// 1.2 说明这个用户id和收藏夹商品id是一个人
			collectionGoodsMapper.deleteByPrimaryKey(collectionId);
		}
		
	}
}
