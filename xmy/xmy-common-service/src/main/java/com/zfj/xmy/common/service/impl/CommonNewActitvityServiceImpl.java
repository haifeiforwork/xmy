package com.zfj.xmy.common.service.impl;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.appdev.db.common.CriteriaParameter;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.xmy.common.persistence.dao.ShoppingCartMapper;
import com.zfj.xmy.common.persistence.pojo.ShoppingCart;
import com.zfj.xmy.common.service.CommonNewActitvityService;
import com.zfj.xmy.config.persistence.dao.CommonLimitActivityExMapper;

/** 
 * @Title: CommonNewActitvityServiceImpl.java 
 * @Package com.zfj.xmy.common.service.impl 
 * @Description: 
 * @author zhangh
 * @date 2018年1月3日 上午11:10:41 
 */
@Service
public class CommonNewActitvityServiceImpl implements CommonNewActitvityService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private CommonLimitActivityExMapper commonLimitActivityExMapper;
	
	/**
	 * 判断用户是否购买过特殊商品(拼购商品，三免一商品)
	 */
	@Override
	public Integer OrderContainSpeicleGoods(Long userId, String goodsId) {
		int i = 0;
		//1.判断该商品是否包含了特殊商品
		String[] split = goodsId.split(",");
		for (String string : split) {
			//1.1免邮商品
			if(Arrays.asList(SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE).contains(Long.parseLong(string))){
				Map<String, Integer> feeFerightGodos = chekFeeFerightGodos();
				Integer integer = feeFerightGodos.get(string);
				if(integer>=100){//总共只能买100份
					throw new BusinessException(77,"该商品已告罄");//77.表示限量一百份已经卖完了
				}/*else{//判断一起是否购买过
					String orderId = "0";
					String orderGoodsId = "0";
					ReqData reqData = new ReqData();
					//0.查询用户以前购买的商品id
					reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
					List<Order> list = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
					reqData.clearValue();
					for (Order order : list) {
						orderId += ","+order.getId();
					}
					reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_IN);
					List<OrderGoods> selectByExample = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
					reqData.clearValue();
					for (OrderGoods orderGoods : selectByExample) {
						orderGoodsId += "0"+orderGoods.getGoodsId();
					}
					if(orderGoodsId.contains(string)){
						throw new BusinessException(78,"该用户已经购买了此免邮商品");//78.该用户已经购买了此免邮商品
					}
				}*/
				
			}
			//1.1判断是否包含了拼购商品
			if(string.contains(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.toString())){
				String orderId = "0";
				String orderGoodsId = "0";
				ReqData reqData = new ReqData();
				//查询活动商品的销售总份数
				reqData.putValue("goods_id", SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID, SystemConstant.REQ_PARAMETER_EQ);
				int countByExample = orderGoodsMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				if(countByExample>=300){//300份
					throw new BusinessException(77, "该商品已告罄，欢迎请下次购买！");
				}
				//0.查询用户以前购买的商品id
				reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
				List<Order> list = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				for (Order order : list) {
					orderId += ","+order.getId();
				}
				reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_IN);
				List<OrderGoods> selectByExample = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				for (OrderGoods orderGoods : selectByExample) {
					orderGoodsId += ","+orderGoods.getGoodsId();
				}
				if(orderGoodsId.contains(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.toString())){
					throw new BusinessException(78,"该用户已经购买了拼购商品");//77.表示商品已经购买了拼购商品
				}
			}
			//1.1判断是否包含了三免一商品
			if(Arrays.asList(SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE).contains(Long.parseLong(string))){
				String orderId = "0";
				ReqData reqData = new ReqData();
				//0.查询用户以前购买的商品id
				reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
				List<Order> list = orderMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				for (Order order : list) {
					orderId += ","+order.getId();
				}
				reqData.putValue("order_id", orderId, SystemConstant.REQ_PARAMETER_IN);
				List<OrderGoods> selectByExample = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
				reqData.clearValue();
				for (OrderGoods orderGoods : selectByExample) {
					if(Arrays.asList(SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE).contains(orderGoods.getGoodsId())){
						throw new BusinessException(78,"该用户已经购买了该活动商品");//78.表示已经购买了三免一商品
					}
				}
			}
		}
		return i;
	}
	
	
	@Override
	public void addGoodsToCart(List<ShoppingCart> shoppingCarts,
			List<ShoppingCart> list, Long goodsId,Long userId,Integer goodsNum,Integer actId,Integer points) {
		if(Arrays.asList(SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE).contains(goodsId)){
			for(ShoppingCart cart:shoppingCarts){
				if(Arrays.asList(SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE).contains(cart.getGoodsId())){
					throw new BusinessException(54,"亲~您已经购买过或购物车已包含该活动商品，不能重复购买哦！");
				}
			}
			if (goodsNum >1 ) {
				throw new BusinessException(51,"该活动商品单个会员只能限购一份");
			}
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setCreateTime(new Date());
			shoppingCart.setGoodsId(goodsId);
			shoppingCart.setNum(1);//三免一商品也只能购买一件(前台也需要验证)
			shoppingCart.setUserId(userId);
			shoppingCart.setActivityPoints(0);
			shoppingCartMapper.insert(shoppingCart);
			//免邮 2018年1月25日15:34:05
			/*if(shoppingCarts.size()>0){
				throw new BusinessException(52,"免邮商品只能单独购买，请先结算购物车其他商品后继续购买。");
			} if (goodsNum >1 ) {
				throw new BusinessException(50,"免邮活动商品单个会员只能限购一份");
			} else{
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setCreateTime(new Date());
				shoppingCart.setGoodsId(goodsId);
				shoppingCart.setNum(1);//免邮商品只能购买一件（前台也应该要验证）
				shoppingCart.setUserId(userId);
				shoppingCart.setActivityPoints(0);
				shoppingCartMapper.insert(shoppingCart);
			}*/
		}else if(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.equals(goodsId)){//1.如果该商品是拼购抽奖
			if(shoppingCarts.size()>0){
				throw new BusinessException(52,"拼购商品只能单独购买，请先结算购物车其他商品后继续购买。");
			} if (goodsNum >1 ) {
				throw new BusinessException(50,"拼购活动商品单个会员只能限购一份");
			} else{
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setCreateTime(new Date());
				shoppingCart.setGoodsId(goodsId);
				shoppingCart.setNum(1);//拼购商品只能购买一件（前台也应该要验证）
				shoppingCart.setUserId(userId);
				shoppingCart.setActivityPoints(0);
				shoppingCartMapper.insert(shoppingCart);
			}
		}else{
			for(ShoppingCart cart:shoppingCarts){
				if(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.equals(cart.getGoodsId())){
					throw new BusinessException(53,"购物车中包含免邮商品，请先结算购物车商品后再购买。");
				}
			}
			if ( list.size() > 0) {  //如果购物车已存在  只叠加商品数量
				ShoppingCart shoppingCart = list.get(0);
				shoppingCart.setNum(shoppingCart.getNum() + goodsNum);
				shoppingCartMapper.updateByPrimaryKey(shoppingCart);
			} else {
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setCreateTime(new Date());
				shoppingCart.setGoodsId(goodsId);
				shoppingCart.setNum(goodsNum);
				shoppingCart.setUserId(userId);
				shoppingCart.setActivityPoints(0);
				if(ObjectUtil.isNotNull(actId)){//如果该商品是积分活动商品
					shoppingCart.setActivityPoints(points);
					shoppingCart.setActId(actId);
				}
				shoppingCartMapper.insert(shoppingCart);
			}
		}
		/*if(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.equals(goodsId)){//1.如果该商品是拼购抽奖
		if(shoppingCarts.size()>0){
			throw new BusinessException(52,"拼购商品只能单独购买，请先结算购物车其他商品后继续购买。");
		} if (goodsNum >1 ) {
			throw new BusinessException(50,"拼购活动商品单个会员只能限购一份");
		} else{
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setCreateTime(new Date());
			shoppingCart.setGoodsId(goodsId);
			shoppingCart.setNum(1);//拼购商品只能购买一件（前台也应该要验证）
			shoppingCart.setUserId(userId);
			shoppingCart.setActivityPoints(0);
			shoppingCartMapper.insert(shoppingCart);
		}
	}*/
		/*else if(!Arrays.asList(SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE).contains(goodsId)){//2.如果该商品不是拼购抽奖,也不是买三免一
			for(ShoppingCart cart:shoppingCarts){
				if(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.equals(cart.getGoodsId())){
					throw new BusinessException(53,"购物车中包含拼购商品，请先结算购物车商品后再购买。");
				}
			}
			if ( list.size() > 0) {  //如果购物车已存在  只叠加商品数量
				ShoppingCart shoppingCart = list.get(0);
				shoppingCart.setNum(shoppingCart.getNum() + goodsNum);
				shoppingCartMapper.updateByPrimaryKey(shoppingCart);
			} else {
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setCreateTime(new Date());
				shoppingCart.setGoodsId(goodsId);
				shoppingCart.setNum(goodsNum);
				shoppingCart.setUserId(userId);
				shoppingCart.setActivityPoints(0);
				if(ObjectUtil.isNotNull(actId)){//如果该商品是积分活动商品
					shoppingCart.setActivityPoints(points);
					shoppingCart.setActId(actId);
				}
				shoppingCartMapper.insert(shoppingCart);
			}
		}else{//3.如果该商品是买三免一
			for(ShoppingCart cart:shoppingCarts){
				if(Arrays.asList(SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE).contains(cart.getGoodsId())){
					throw new BusinessException(54,"亲~您已经购买过或购物车已包含买三免一商品，不能重复购买哦！");
				}
				if(SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID.equals(cart.getGoodsId())){
					throw new BusinessException(53,"购物车中包含拼购商品，请先结算购物车商品后再购买。");
				}
			}
			if (goodsNum >1 ) {
				throw new BusinessException(51,"三免一活动商品单个会员只能限购一份");
			}
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setCreateTime(new Date());
			shoppingCart.setGoodsId(goodsId);
			shoppingCart.setNum(1);//三免一商品也只能购买一件(前台也需要验证)
			shoppingCart.setUserId(userId);
			shoppingCart.setActivityPoints(0);
			shoppingCartMapper.insert(shoppingCart);
		}*/
	}

	/**
	 *  判断用户添加活动（拼购抽，三免一，免邮商品）指定商品到购物车
	 * @param goodsId 商品id
	 * @param userId 用户id   
	 * @return void    
	 * Date:2018年1月3日 下午2:16:14 
	 * @author hexw
	 */
	@Override
	public void checkAssignActivityGoodsToShoppingCart(ShoppingCart shoppingCart){
		Long userId = shoppingCart.getUserId();
		Long goodsId = shoppingCart.getGoodsId();
		if (ObjectUtil.isNull(userId)) throw new BusinessException("亲，您未登录香满圆，请登陆后进行购买");
		if (ObjectUtil.isNull(goodsId)) throw new BusinessException("添加商品不能为空");
		List<ShoppingCart> shoppingCarts = findShoppingCartByUserId(userId);
		// 1. 判断添加的商品是拼购抽活动商品还是三免一活动商品
		Long pgcGoods = SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID ; //拼购凑商品
		Long[] smyGoods = SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE ; //三免一商品 
		Long[] feeFreigthGoods = SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS;//免邮商品
		if (Arrays.asList(pgcGoods).contains(goodsId)) { //拼购抽活动商品
			if (shoppingCart.getNum() > 1) throw new BusinessException("拼购凑商品商品单个会员每个商品只能限购一份");
			if (shoppingCarts.size() > 0) throw new BusinessException("此商品限单独购买。您的购物车有其他商品，请先结算后再购买活动商品！");
			if (checkBuyGoods(userId,goodsId)) throw new BusinessException("您已经购买过此商品，请购买其他商品！");
		}
		if (Arrays.asList(feeFreigthGoods).contains(goodsId)) { //免邮商品
			if (shoppingCart.getNum() > 1) throw new BusinessException("免邮商品单个会员每个商品只能限购一份");
			if (shoppingCarts.size() > 0) throw new BusinessException("此商品限单独购买。您的购物车有其他商品，请先结算后再购买活动商品！");
			if (checkBuyGoods(userId,goodsId)) throw new BusinessException("您已经购买过此商品，请购买其他商品！");
			if (getGoodsSales(goodsId) >= 100) throw new BusinessException("该商品已告罄,欢迎下次购买");
		}
		if (Arrays.asList(smyGoods).contains(goodsId)) { //三免一活动商品
			if (shoppingCart.getNum() > 1) throw new BusinessException("该商品单个会员只能限购一份");
			if (checkBuySmyOrderGoods(userId)) throw new BusinessException("亲~您已经购买过或购物车已包含该类型活动商品，不能重复购买哦！");
			if (getGoodsSales(goodsId) >= 100) throw new BusinessException("该商品已告罄,欢迎下次购买");
		}  
		checkBuyCouponGoods(shoppingCart);  //判断是否是抵用券商品
		if (checkBuyAlone(userId)) throw new BusinessException("购物车有需单独购买的商品，请先结算后再购买！");
	}
	
	@Override
	public void checkBuyCouponGoods(ShoppingCart shoppingCart){
		Long userId = shoppingCart.getUserId();
		Long goodsId = shoppingCart.getGoodsId();
		if (ObjectUtil.isNull(userId)) throw new BusinessException("亲，您未登录香满圆，请登陆后进行购买");
		if (ObjectUtil.isNull(goodsId)) throw new BusinessException("添加商品不能为空");
		Long couponGoods = SystemConstant.NEWACIVITY.COUPON_GOODS  ; //抵用券商品
		List<ShoppingCart> shoppingCarts = findShoppingCartByUserId(userId); //查询用户购物车
		//1. 判断是否是抵用券商品
		if (couponGoods.equals(goodsId)) {
			if (shoppingCart.getNum() > 1) throw new BusinessException("抵用券商品单个会员每个商品只能限购一份");
			if (shoppingCarts.size() > 0) throw new BusinessException("此商品限单独购买。您的购物车有其他商品，请先结算后再购买活动商品！");
			if (checkBuyGoods(userId,goodsId)) throw new BusinessException("您已经购买过此商品，请购买其他商品！");
			if (getGoodsSales(goodsId) >= 300) throw new BusinessException("该商品已告罄,欢迎下次购买");
		}
	}
	
	public List<ShoppingCart> findShoppingCartByUserId(Long userId) {
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		return shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	
	/**
	 * 检查购物车是否含有拼购抽商品
	 * @param userId
	 * @return    
	 * @return boolean    
	 * Date:2018年1月3日 下午2:43:30 
	 * @author hexw
	 */
	public boolean checkShoppingCartPgcGoods(Long userId){
		boolean flag = false ;
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		/*reqData.putValue("goods_id", SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID, SystemConstant.REQ_PARAMETER_EQ);*/
		List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (ShoppingCart shoppingCart : shoppingCarts) {
			if(Arrays.asList(SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS).contains(shoppingCart.getGoodsId())){
				flag = true ;
			}
		}
		/*if (shoppingCarts.size() > 0) {
			flag = true ;
		}*/
		return flag;
	}
	
	/**
	 * 判断购物车是否含有单独购买的商品
	 * @param userId
	 * @return    
	 * @return boolean    
	 * Date:2018年3月5日 下午2:34:32 
	 * @author hexw
	 */
	@Override
	public boolean checkBuyAlone(Long userId){
		boolean flag = false ;
		ReqData reqData = new ReqData();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		/*reqData.putValue("goods_id", SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID, SystemConstant.REQ_PARAMETER_EQ);*/
		List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (ShoppingCart shoppingCart : shoppingCarts) {
			Long[] feeReigthGoods =  SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS;
			Long[] couponGoods = {SystemConstant.NEWACIVITY.COUPON_GOODS};
			Long[] aloneGoods = ArrayUtil.addAll(feeReigthGoods,couponGoods);
			if(Arrays.asList(aloneGoods).contains(shoppingCart.getGoodsId())){
				flag = true ;
			}
		}
		return flag ;
	}

	/**
	 * 判断用户是否已购买过该商品
	 * @param userId
	 * @param goodsId
	 * @return    
	 * @return boolean    
	 * Date:2018年1月3日 下午3:42:17 
	 * @author hexw
	 */
	public boolean checkBuyGoods(Long userId,Long goodsId) {
		boolean flag = false ;
		ReqData reqData = new ReqData();
		reqData.putValue("a.user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("b.goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		Integer count = commonLimitActivityExMapper.countOrderGoods(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (count > 0 ) {
			flag = true ;
		}
		return flag;
	}
	
	/**
	 * 判断用户是否购买或已添加三免一活动商品
	 * @param userId
	 * @return    
	 * @return boolean    
	 * Date:2018年1月3日 下午3:41:16 
	 * @author hexw
	 */
	public boolean checkBuySmyOrderGoods(Long userId){
		boolean flag = false ;
		String id = "0";
		Long[] smyGoods = SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE ; 
		List<Long> list = Arrays.asList(smyGoods);
		for (Long goodsId : list) {
			id = id +"," + goodsId ;
		}
		ReqData reqData = new ReqData();
		reqData.putValue("a.user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("b.goods_id", id, SystemConstant.REQ_PARAMETER_IN);
		Integer count = commonLimitActivityExMapper.countOrderGoods(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (count > 0) {
			flag = true ; 
		}
		reqData.clearValue();
		reqData.putValue("user_id", userId, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("goods_id", id, SystemConstant.REQ_PARAMETER_IN);
		List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		if (shoppingCarts.size() > 0) {
			flag = true ;
		}
		return flag ;
	}
	
	@Override
	public boolean checkPgcSmyGoodsShoppingCart(Long goodsId){
		boolean flag = false ;
		Long pgcGoods = SystemConstant.NEWACIVITY.LOTTERY_GOODS_ID ; 
		Long[] smyGoods = SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE ;
		Long[] feeFreigthGoods = SystemConstant.NEWACIVITY.FEE_FREIGTH_GOODS;
		if (pgcGoods.equals(goodsId) || Arrays.asList(feeFreigthGoods).contains(goodsId) ||  Arrays.asList(smyGoods).contains(goodsId)) {
			flag = true ;
		}
		return flag;
	}



	/**
	 * 查询免邮商品的销售总量 2018年1月25日15:27:27 更改为3免一
	 */
	@Override
	public Map<String, Integer> chekFeeFerightGodos() {
		ReqData reqData = new ReqData();
		Map<String, Integer> map = new HashMap<String, Integer>();
		Long[] feeFreigthGoods = SystemConstant.NEWACIVITY.BUY_THREE_FREE_ONE;
		for (Long long1 : feeFreigthGoods) {
			map.put(long1.toString(), 0);
			reqData.putValue("goods_id", long1, SystemConstant.REQ_PARAMETER_EQ);
			List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (OrderGoods orderGoods : orderGoodsList) {
				Order order = orderMapper.selectByPrimaryKey(orderGoods.getOrderId());
				if(!ObjectUtils.isEmpty(order)){
					if(order.getIsDel()==SystemConstant.ORDER.IS_DEL_NOT_DELETE){
						map.replace(long1.toString(), map.get(long1.toString())+orderGoods.getNum());
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * 查询商品总限量
	 * @param goodsId
	 * @return    
	 * @return Integer    
	 * Date:2018年1月25日 下午2:11:17 
	 * @author 
	 */
	@Override
	public Integer getGoodsSales(Long goodsId){
		Integer totalSale = 0 ;
		ReqData reqData = new ReqData();
		reqData.putValue("goods_id", goodsId, SystemConstant.REQ_PARAMETER_EQ);
		List<OrderGoods> list = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (OrderGoods orderGoods : list) {
			Order order = orderMapper.selectByPrimaryKey(orderGoods.getOrderId());
			if (order.getIsDel()==SystemConstant.ORDER.IS_DEL_NOT_DELETE) {
				totalSale = totalSale +  orderGoods.getNum();
			}
		}
		return totalSale;
	}
	
}
