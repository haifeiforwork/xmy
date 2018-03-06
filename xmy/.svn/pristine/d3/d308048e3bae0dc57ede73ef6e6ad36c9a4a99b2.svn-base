package com.zfj.xmy.order.service.cms.impl;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.druid.util.StringUtils;
import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CouponUserMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.LimitActivityMapper;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.CouponUser;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsWithBLOBs;
import com.zfj.xmy.common.persistence.pojo.LimitActivity;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.order.persistence.cms.pojo.dto.ParentGoods;
import com.zfj.xmy.order.persistence.common.dao.OrderGoodsExMapper;
import com.zfj.xmy.order.persistence.common.pojo.dto.LableDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderGoodsDto;
import com.zfj.xmy.order.persistence.common.pojo.dto.ShoppingDto;
import com.zfj.xmy.order.service.cms.OrderGoodsService;
import com.zfj.xmy.order.service.cms.OrderService;
@Service
public class OrderGoodsServiceImpl extends BaseService implements OrderGoodsService{

	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private OrderGoodsExMapper orderGoodsExMapper;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Autowired
	private LimitActivityMapper limitActivityMapper;
	
	@Autowired
	private CouponUserMapper couponUserMapper;
	
	@Override
	public List<OrderGoods> findOrderGoodsByOrderId(ReqData reqData,Boolean parent) {
		List<OrderGoods> list = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		List<ParentGoods> parentList = new ArrayList<ParentGoods>();
		for (OrderGoods orderGoods : list) {
			if(!ObjectUtils.isEmpty(orderGoods.getPresentGoodsId())){
				ParentGoods goods = new ParentGoods();
				goods.setGoodsId(orderGoods.getPresentGoodsId());
				goods.setNum(orderGoods.getNum());
				parentList.add(goods);
			}
		}
		if(parent){
			if(!ObjectUtils.isEmpty(parentList)){
				for (ParentGoods parentGoods : parentList) {
					Goods parentGood = goodsMapper.selectByPrimaryKey(parentGoods.getGoodsId());
					if(!ObjectUtils.isEmpty(parentGood)){
						OrderGoods orderGoods = new OrderGoods();
						orderGoods.setGoodsId(parentGood.getId());
						orderGoods.setNum(parentGoods.getNum());
						orderGoods.setName(parentGood.getName());
						orderGoods.setPrice(new BigDecimal("0.00"));
						orderGoods.setSumPrice(new BigDecimal("0.00"));
						list.add(orderGoods);
					}
				}
			}
		}
		for (OrderGoods orderGoods : list) {
			//commonGoodsService
			orderGoods.setNo(Long.parseLong(commonGoodsService.findGoodsNo(orderGoods.getGoodsId())));
		}
		return list;
	}
	

	@Override
	public List<OrderGoodsDto> findShoppingList(ReqData reqData, PageBean pageBean,List<Object> ordersid) {
		List<OrderGoodsDto> dto = null;
		if(ObjectUtils.isEmpty(pageBean)){
			dto = orderGoodsExMapper.shoppingList(ReqUtil.reqParameterToCriteriaParameter(reqData)); //查询主商品的集合
			
		}else{
			dto = orderGoodsExMapper.shoppingList(ReqUtil.reqParameterToCriteriaParameter(reqData),pageBean.getRowBounds()); //查询主商品的集合
		}
		List<OrderGoodsDto> presentGoods = orderGoodsExMapper.shoppingPresentList(ReqUtil.reqParameterToCriteriaParameter(reqData)); //需采购的赠品的清单
		for (OrderGoodsDto orderGoodsDto : presentGoods) {
			orderGoodsDto.setName(orderGoodsDto.getName()+"(赠品)");
		}
		reqData.clearValue();
		/*for (OrderGoodsDto orderGoodsDto : dto) {  //订单编号 和 数量
			reqData.putValue("og.order_id", ordersid, SystemConstant.REQ_PARAMETER_IN);
			reqData.putValue("og.goods_id", orderGoodsDto.getId(), SystemConstant.REQ_PARAMETER_IN);
			orderGoodsDto.setsDto(orderGoodsExMapper.shoppingDtoList(ReqUtil.reqParameterToCriteriaParameter(reqData)));
			reqData.clearValue();
		}*/
		dto.addAll(presentGoods);
		return dto;
	}
	

	@Override
	public int updateOrderGoods(long id,int goodnum) {
		OrderGoods orderGoods = orderGoodsMapper.selectByPrimaryKey(id);
		//改变订单商品
		orderGoods.setNum(goodnum);
		orderGoods.setSumPrice(orderGoods.getPrice().multiply(new BigDecimal(goodnum)));
		orderGoodsMapper.updateByPrimaryKey(orderGoods);
		return 1;
	}

	/**
	 * 查询需要导出采购清单的数据
	 */
	@Override
	public List<OrderGoodsDto> exleGoodsList(ReqData reqData, Integer type, String orderIds) {
		reqData.putValue("og.order_id", orderIds, SystemConstant.REQ_PARAMETER_IN);
		if(type<2){//指定导出某个商品
			reqData.putValue("g.market", type, SystemConstant.REQ_PARAMETER_EQ);
		}
		List<OrderGoodsDto> shoppingList = orderGoodsExMapper.shoppingList(ReqUtil.reqParameterToCriteriaParameter(reqData));
		//添加活动名称
		for (OrderGoodsDto orderGoodsDto : shoppingList) {
			if (!ObjectUtils.isEmpty(orderGoodsDto.getActiveType())) {
				if(orderGoodsDto.getActiveType()==2){//限时限量活动
					LimitActivity activity = limitActivityMapper.selectByPrimaryKey(orderGoodsDto.getActiveId());
					if(!ObjectUtils.isEmpty(activity)){
						orderGoodsDto.setActiveName(activity.getName());
					}
				}
				if(orderGoodsDto.getActiveType() == 3){//积分活动
					orderGoodsDto.setActiveName("积分活动");
				}
			}
		}
		//查询赠品
		List<OrderGoodsDto> parentList = orderGoodsExMapper.shoppingPresentList(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (OrderGoodsDto orderGoodsDto : parentList) {
			orderGoodsDto.setActiveName("买即赠活动");
		}
		shoppingList.addAll(parentList);
		//为没有供应商的商品名称添加供应商名称（截取商品名称的）
		for (OrderGoodsDto orderGoodsDto : shoppingList) {
			if(ObjectUtils.isEmpty(orderGoodsDto.getSupplierName())){
				String name = orderGoodsDto.getName();
				String subString = StringUtils.subString(name, "[", "]", false);
				orderGoodsDto.setSupplierName("["+subString+"]");
			}
		}
		//排序
		listSort(shoppingList);
		reqData.clearValue();
		if(type==2){
			for (OrderGoodsDto orderGoodsDto : shoppingList) {
				reqData.putValue("og.order_id", orderIds, SystemConstant.REQ_PARAMETER_IN);
				reqData.putValue("og.goods_id", orderGoodsDto.getId(), SystemConstant.REQ_PARAMETER_IN);
				orderGoodsDto.setsDto(orderGoodsExMapper.shoppingDtoList(ReqUtil.reqParameterToCriteriaParameter(reqData)));
				reqData.clearValue();
			}
			//赠品
			for (OrderGoodsDto orderGoodsDto : shoppingList) {
				reqData.putValue("og.order_id", orderIds, SystemConstant.REQ_PARAMETER_IN);
				reqData.putValue("og.present_goods_id", orderGoodsDto.getId(), SystemConstant.REQ_PARAMETER_IN);
				List<ShoppingDto> shoppingDtoList = orderGoodsExMapper.shoppingDtoList(ReqUtil.reqParameterToCriteriaParameter(reqData));
				if(!ObjectUtils.isEmpty(shoppingDtoList)){
					orderGoodsDto.setsDto(shoppingDtoList);
				}
				reqData.clearValue();
			}
		}
		return shoppingList;
	}
	/**
	 * 排序
	 * @param resultList void
	 * @author lij
	 * @date 2018年1月6日 下午2:59:30
	 */
	public static void listSort(List<OrderGoodsDto> resultList) {  
        Collections.sort(resultList, new Comparator<OrderGoodsDto>() {  
            public int compare(OrderGoodsDto o1, OrderGoodsDto o2) {  
                String name1=o1.getSupplierName().toString() ;
                String name2=o2.getSupplierName().toString() ;
                Collator instance = Collator.getInstance(Locale.CHINA);  
                return instance.compare(name1, name2);  
  
            }  
        });  
    }  
	
	/**
	 * @param reqData
	 * @param orderIds
	 * @param type
	 * @return List<OrderDto>
	 * @author 查询需要导出的订单集合
	 * @date 2017年10月18日 上午10:04:28
	 */
	@Override
	public List<OrderDto> exleOrderDto(ReqData reqData,String orderIds,Integer type){
		reqData.putValue("id", orderIds, SystemConstant.REQ_PARAMETER_IN);
		List<OrderDto> findOrderDto = orderService.findOrderDto(reqData);
		//添加优惠卷总金额
		for (OrderDto orderDto : findOrderDto) {
			if (ObjectUtil.isNotNull(orderDto.getCouponId()) && 0 != orderDto.getCouponId()) {
				CouponUser couponUser = couponUserMapper.selectByPrimaryKey(orderDto.getCouponId());
				if(!ObjectUtils.isEmpty(couponUser)){
					Coupon coupon = orderService.getCoupon( couponUser.getCouponId()) ;
					if (ObjectUtil.isNotNull(coupon)) {
						//订单金额加上使用的优惠卷金额
						orderDto.setTotal(orderDto.getTotal().add(coupon.getCouponValue()));//为总金额加入优惠卷金额
					}
				}
			}
		}
		reqData.clearValue();
		for (OrderDto orderDto : findOrderDto) {
			reqData.putValue("og.order_id", orderDto.getId(), SystemConstant.REQ_PARAMETER_EQ);
			if(type<2){
				reqData.putValue("g.market", type, SystemConstant.REQ_PARAMETER_EQ);
			}
			List<OrderGoodsDto> findOrderGoodsDtos = orderGoodsExMapper.findOrderGoodsDtos(ReqUtil.reqParameterToCriteriaParameter(reqData));
			List<OrderGoodsDto> goodsParent = orderGoodsExMapper.findOrderGoodsParent(ReqUtil.reqParameterToCriteriaParameter(reqData));
			findOrderGoodsDtos.addAll(goodsParent);
			reqData.clearValue();
			orderDto.setGoodsList(findOrderGoodsDtos);
		}
		
		return findOrderDto;
		
	}

	/**
	 * 查询导出标签的实体
	 */
	@Override
	public List<LableDto> excleLable(ReqData reqData, String orderIds, Integer type) {
		reqData.putValue("og.order_id", orderIds, SystemConstant.REQ_PARAMETER_IN);
		reqData.putValue("g.market", type, SystemConstant.REQ_PARAMETER_EQ);
		List<LableDto> findLableDto = orderGoodsExMapper.findLableDto(ReqUtil.reqParameterToCriteriaParameter(reqData));
		List<LableDto> lableParentDto = orderGoodsExMapper.findLableParentDto(ReqUtil.reqParameterToCriteriaParameter(reqData));
		findLableDto.addAll(lableParentDto);
		return findLableDto;
	}


	@Override
	public int deleteOrderGoods(Long id) {
		orderGoodsMapper.deleteByPrimaryKey(id);
		return 1;
	}
	
}
