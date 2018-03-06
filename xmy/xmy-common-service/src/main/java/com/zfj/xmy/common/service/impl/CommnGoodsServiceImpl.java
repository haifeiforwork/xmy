package com.zfj.xmy.common.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.CriteriaParameter.Criteria;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.collection.CollectionExtUtils;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CommentMapper;
import com.zfj.xmy.common.persistence.dao.GoodsImageMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsImage;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.config.persistence.dao.CommonLimitActivityExMapper;

@Service
public class CommnGoodsServiceImpl extends BaseService implements CommonGoodsService {
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private GoodsImageMapper goodsImageMapper;
	
	@Autowired
	private TermDataMapper termDataMapper;
	
	@Autowired
	private CommonLimitActivityExMapper commonLimitActivityExMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	
	/**
	 * 根据订单id修改商品的商品成交量
	 */
	@Override
	public void updateGoodsSumDealByOrderId(long orderId) {
		ReqData reqData = new ReqData();
		reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderGoods> orGoods = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (OrderGoods orderGoods : orGoods) {
			GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(orderGoods.getGoodsId());
			if(ObjectUtils.isEmpty(goods.getSumDeal())){
				goods.setSumDeal(orderGoods.getNum());
			}else{
				goods.setSumDeal(goods.getSumDeal()+orderGoods.getNum());
			}
			goodsMapper.updateByPrimaryKey(goods);
		}
	}
	
	/**
	 * 根据商品id修改商品的商品评论数
	 */
	@Override
	public void updateGoodsSumCommentByGoodsId(long goodsId) {
		ReqData reqData = new ReqData();
		GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		goods.setSumComment(commentMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData)));
		goodsMapper.updateByPrimaryKey(goods);
	}
	
	@Override
	public String getFirstGoodsImg(long goodsId){
		String path = "";
		ReqData imgReqData = new  ReqData();
		imgReqData.putValue("goods_id",goodsId, SystemConstant.REQ_PARAMETER_EQ);
		imgReqData.setSortname("seq");
		imgReqData.setSortorder("asc");
		//3. 获取商品图片
		List<GoodsImage> imglist = goodsImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(imgReqData));
		if (imglist.size() > 0){
			path = imglist.get(0).getPath();
		}else{
			//设置默认无图的商品图片
			path="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/advertisement/201701021/defaultgoods.jpg";
		}
		return path;
	}
	
	@Override
	public String getSecondGoodsImg(long goodsId){
		String path = "";
		ReqData imgReqData = new  ReqData();
		imgReqData.putValue("goods_id",goodsId, SystemConstant.REQ_PARAMETER_EQ);
		imgReqData.setSortname("seq");
		imgReqData.setSortorder("asc");
		//3. 获取商品图片
		List<GoodsImage> imglist = goodsImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(imgReqData));
		if (imglist.size() > 0){
			path = imglist.get(0).getPath();
		}else{
			//设置默认无图的商品图片
			path="https://xmyoss.oss-cn-hangzhou.aliyuncs.com/advertisement/201701021/defaultgoods.jpg";
		}
		return path;
	}
	
	@Override
	public String getCustomizationImage(long goodsId){
		String path = "";
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id",goodsId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("seq");
		reqData.setSortorder("desc");
		//3. 获取商品图片
		List<GoodsImage> imglist = goodsImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (imglist.size() > 0){
			path = imglist.get(0).getPath();
		}
		return path;
	}
	
	@Override
	public String getCustomizationImageWap(long goodsId){
		String path = "";
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id",goodsId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.setSortname("seq");
		reqData.setSortorder("desc");
		//3. 获取商品图片
		List<GoodsImage> imglist = goodsImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (imglist.size() > 2){
			path = imglist.get(2).getPath();
		}
		return path;
	}
	
	
	@Override
	public List<Goods> findsGoodsWithIds(List<Object> ids){
		if(ids.size()<1){
			return null;
		}
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		createCriteria.in("id", ids);
		List<Goods> selectByExample = goodsMapper.selectByExample(para);
		//取一张图片出来给商品
		for (Goods goods : selectByExample) {
			goods.setImgPath(getFirstGoodsImg(goods.getId()));
		}
		return selectByExample;
	}
	
	

	@Override
	public BigDecimal getDiscountSumPrice(List<Object> ids,Long userId,Map<Long,Integer> map){
		DecimalFormat deicmalFormat =new DecimalFormat("#.00");
		BigDecimal sumPrice = new BigDecimal("0");
		// 1. 排除跨境专区商品 和企业定制商品
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		createCriteria.in("g.id", ids);
		List<Goods> list = commonLimitActivityExMapper.findOnlineActivityGoods(para);
		for (Goods goods : list) {
			ShoppingCart shoppingCart = null;
			ReqData reqData = new ReqData();
			reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("goods_id", goods.getId(), SystemConstant.REQ_PARAMETER_EQ);
			// 2. 查询购物车	
			List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if (shoppingCarts.size() > 0) {
				BigDecimal goodsSumPrice = new BigDecimal("0");
				shoppingCart = shoppingCarts.get(0);
				Integer integer = map.get(shoppingCart.getGoodsId());
				shoppingCart.setNum(integer);
				// 3. 活动商品取活动价格
				if(ObjectUtil.isNotNull(shoppingCart.getActivityId()) && 0 != shoppingCart.getActivityId()) {
					goodsSumPrice = shoppingCart.getActivityPrice().multiply(new BigDecimal(shoppingCart.getNum()));
					sumPrice = sumPrice.add(goodsSumPrice);
				} else {
					// 4. 积分商品不参与活动			
					if (ObjectUtil.isNull(shoppingCart.getActId()) || 0 == shoppingCart.getActId()) {
						if (goods.getPrice().compareTo(new BigDecimal("300")) == -1) { //商品单价大于三百的不参与
							goodsSumPrice = goods.getPrice().multiply(new BigDecimal(shoppingCart.getNum()));
							sumPrice = sumPrice.add(goodsSumPrice);
						}
						
					}
				}
			}
		}
		return new BigDecimal(deicmalFormat.format(sumPrice));
	}
	
	@Override
	public BigDecimal getShoppingCartAllGoodsPrice(List<Object> ids,Long userId){
		BigDecimal sumPrice = new BigDecimal("0");
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		createCriteria.in("id", ids);
		// 1. 查询购物车所有商品
		List<Goods> list = goodsMapper.selectByExample(para);
		for (Goods goods : list) {
			ShoppingCart shoppingCart = null;
			ReqData reqData = new ReqData();
			reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("goods_id", goods.getId(), SystemConstant.REQ_PARAMETER_EQ);
			// 2. 查询购物车	
			List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if (shoppingCarts.size() > 0) {
				BigDecimal goodsSumPrice = new BigDecimal("0");
				shoppingCart = shoppingCarts.get(0);
				// 3. 活动商品取活动价格
				if(ObjectUtil.isNotNull(shoppingCart.getActivityId()) && 0 != shoppingCart.getActivityId()) {
					goodsSumPrice = shoppingCart.getActivityPrice().multiply(new BigDecimal(shoppingCart.getNum()));
					sumPrice = sumPrice.add(goodsSumPrice);
				} else {
					// 4. 积分商品不去价格			
					if (ObjectUtil.isNull(shoppingCart.getActId()) || 0 == shoppingCart.getActId()) {
						goodsSumPrice = goods.getPrice().multiply(new BigDecimal(shoppingCart.getNum()));
						sumPrice = sumPrice.add(goodsSumPrice);
					}
				}
			}
		}
		return sumPrice;
	}
	
	@Override
	public BigDecimal getOrderGoodsSumPrice(Long orderId) {
		BigDecimal sumPrice = new BigDecimal("0");
		ReqData reqData = new ReqData();
		reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderGoods> list = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (OrderGoods orderGoods : list) {
			if (ObjectUtil.isNull(orderGoods.getPoints()) || orderGoods.getPoints() ==0  ) {  //排除积分商品
				sumPrice = sumPrice.add(orderGoods.getSumPrice()); 
			}
		}
		return sumPrice;
	}
	
	@Override
	public BigDecimal getOnlineOrderGoodsSumPrice(Long orderId,List<Object> ids){
		BigDecimal sumPrice = new BigDecimal("0");
		// 1. 排除跨境专区商品 和企业定制商品
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		createCriteria.in("g.id", ids);
		List<Goods> list = commonLimitActivityExMapper.findOnlineActivityGoods(para);
		// 2. 查询订单商品
		ReqData reqData = new ReqData();
		reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderGoods> allOrdergoods = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (OrderGoods orderGoods : allOrdergoods) {
			for (Goods goods : list) {
				if (ObjectUtil.isNull(orderGoods.getPoints()) || orderGoods.getPoints() ==0 ) { // 排除积分商品
					if (orderGoods.getGoodsId().equals(goods.getId())) {
						sumPrice = sumPrice.add(orderGoods.getSumPrice()); 
					}
				}
			}
		}
		return sumPrice;
	}
 	
	/**
	 * 获取子级分类
	 * @param categoryName
	 * @return    
	 * @return List<TermData>    
	 * Date:2017年11月12日 下午6:45:29 
	 * @author hexw
	 */
	public List<TermData> getChildTermDataByName(String categoryName){
		List<TermData> childTermData = null;
		TermData termData = null;
		ReqData reqData = new ReqData();
		reqData.putValue("name", categoryName, SystemConstant.REQ_PARAMETER_CN);
		reqData.putValue("parent_id", 0, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("vid", 2, SystemConstant.REQ_PARAMETER_EQ); //商品分类
		List<TermData> parentTermData = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (parentTermData.size() > 0) {
			termData = parentTermData.get(0);
			if (ObjectUtil.isNotNull(termData)) {
				ReqData childReqData = new ReqData();
				childReqData.putValue("parent_id", termData.getId(), SystemConstant.REQ_PARAMETER_EQ);
				childTermData = termDataMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(childReqData));
			}
		}
		return childTermData;
	}
	
	@Override
	public boolean findOnlineActivityGoods(Long id) {
		// 1. 排除跨境专区商品 和企业定制商品
		//List<Goods> goods = commonLimitActivityExMapper.findOnlineActivityGoods(null);
		Goods goods = commonLimitActivityExMapper.findOnlineActivityGoodsByGoodsId(id);
		
		if(goods != null) return true;
		else return false;
	}

	@Override
	public String findGoodsNo(Long id) {
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		return goods.getCode();
	}

	@Override
	public BigDecimal getDiscountSumPrice(List<Object> ids, Long userId) {
		DecimalFormat deicmalFormat =new DecimalFormat("#.00");
		BigDecimal sumPrice = new BigDecimal("0");
		// 1. 排除跨境专区商品 和企业定制商品
		CriteriaParameter para=new CriteriaParameter();
		Criteria createCriteria = para.createCriteria();
		createCriteria.in("g.id", ids);
		List<Goods> list = commonLimitActivityExMapper.findOnlineActivityGoods(para);
		for (Goods goods : list) {
			ShoppingCart shoppingCart = null;
			ReqData reqData = new ReqData();
			reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
			reqData.putValue("goods_id", goods.getId(), SystemConstant.REQ_PARAMETER_EQ);
			// 2. 查询购物车	
			List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if (shoppingCarts.size() > 0) {
				BigDecimal goodsSumPrice = new BigDecimal("0");
				shoppingCart = shoppingCarts.get(0);
				// 3. 活动商品取活动价格
				if(ObjectUtil.isNotNull(shoppingCart.getActivityId()) && 0 != shoppingCart.getActivityId()) {
					goodsSumPrice = shoppingCart.getActivityPrice().multiply(new BigDecimal(shoppingCart.getNum()));
					sumPrice = sumPrice.add(goodsSumPrice);
				} else {
					// 4. 积分商品不参与活动			
					if (ObjectUtil.isNull(shoppingCart.getActId()) || 0 == shoppingCart.getActId()) {
						if (goods.getPrice().compareTo(new BigDecimal("300")) == -1) { //排除商品单价大于300的
							goodsSumPrice = goods.getPrice().multiply(new BigDecimal(shoppingCart.getNum()));
							sumPrice = sumPrice.add(goodsSumPrice);
						}
					}
				}
			}
		}
		return new BigDecimal(deicmalFormat.format(sumPrice));
	}
}
