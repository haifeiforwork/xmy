package com.zfj.xmy.goods.service.wap.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CollectionGoodsMapper;
import com.zfj.xmy.common.persistence.dao.GoodsImageMapper;
import com.zfj.xmy.common.persistence.pojo.CollectionGoods;
import com.zfj.xmy.common.persistence.pojo.GoodsImage;
import com.zfj.xmy.goods.persistence.wap.dao.CategoryAmountVoExMapper;
import com.zfj.xmy.goods.persistence.wap.dao.GoodsVoExMapper;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.CategoryAmountVo;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.GoodsQueryVo;
import com.zfj.xmy.goods.persistence.wap.pojo.dto.GoodsVo;
import com.zfj.xmy.goods.service.wap.WapCollectionGoodsService;

@Service
public class WapCollectionGoodsServiceImpl implements WapCollectionGoodsService{
	
	@Autowired
	private CategoryAmountVoExMapper categoryAmountMapper;
	@Autowired
	private GoodsVoExMapper goodsMapper;
	@Autowired
	private GoodsImageMapper imageMapper;
	@Autowired
	private CollectionGoodsMapper collectionGoodsMapper;
	
	@Override
	public List<GoodsVo> getFavorite(Long id, String categoryName) {
		List<GoodsVo> goods = null;
		if(StringUtil.isEmpty(categoryName)) {
			goods = goodsMapper.findGoodsByUserId(id);
			Iterator<GoodsVo> iterator = goods.iterator();
			while(iterator.hasNext()) {
				GoodsVo next = iterator.next();
				Long goodsId = next.getGoodsId();
				List<GoodsImage> images = imageMapper.selectByExample(new CriteriaParameter("goods_id", goodsId));
				if(images.size() != 0) {
					next.setImgPath(images.get(0).getPath());
				}
			}
		} else {
			goods = goodsMapper.findGoodsByUserIdAndCategoryName(new GoodsQueryVo(id, categoryName));
			Iterator<GoodsVo> iterator = goods.iterator();
			while(iterator.hasNext()) {
				GoodsVo next = iterator.next();
				Long goodsId = next.getGoodsId();
				List<GoodsImage> images = imageMapper.selectByExample(new CriteriaParameter("goods_id", goodsId));
				if(images.size() != 0) {
					next.setImgPath(images.get(0).getPath());
				}
			}
		}
		return goods;
	}

	@Override
	public List<CategoryAmountVo> getCategoryAndAmount(Long id) {
		return categoryAmountMapper.findCateAndAmount(id);
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
