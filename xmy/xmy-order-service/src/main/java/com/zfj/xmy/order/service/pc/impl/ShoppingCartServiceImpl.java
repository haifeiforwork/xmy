package com.zfj.xmy.order.service.pc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.common.DateUtils;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CouponMapper;
import com.zfj.xmy.common.persistence.dao.GoodsCategoryMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.pojo.Coupon;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.GoodsCategory;
import com.zfj.xmy.common.service.CommonGoodsService;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcCartDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcCouponDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;
import com.zfj.xmy.order.service.pc.PcShoppingCartService;
import com.zfj.xmy.order.service.pc.ShoppingCartService;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CommonGoodsService goodsService;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	
	@Autowired
	private PcShoppingCartService pcShoppingCartService;
	
	/**
	 * 添加到购物车
	 */
	@Override
	public List<Goods> addShoppingCartCookie(Goods goods,List<Goods> list,HttpServletResponse response) {
		CookieUtil.addCookie(response, "jiekdnsakj", "000", 24 * 60 * 60);//保存一天
		return null;
	}
	/**
	 * 保存(修改)用户添加的购物车的商品
	 */
	@Override
	public void addShoppingCart(PcCartDto cartDto, HttpServletResponse response) {
		CookieUtil.addCookie(response, cartDto.getGoodsId().toString(), cartDto.getCartNum().toString(), 24 * 60 * 60);//保存一天
	}
	/**
	 * 删除购物车商品信息
	 */
	@Override
	public void deleteShoppingCart(PcCartDto pcCartDto,HttpServletResponse response) {
		Cookie[] cookies = WebUtil.getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(pcCartDto.getGoodsId().toString())){
				Cookie cook = new Cookie(pcCartDto.getGoodsId().toString(),"");//这边得用"",不能用null  
				cook.setPath("/");//设置成跟写入cookies一样的  
               // cookie.setDomain(".wangwz.com");//设置成跟写入cookies一样的  
				cook.setMaxAge(0);  
                response.addCookie(cook);  
			};
		}
	}
	
	
	
	/**
	 * 查询未登录的购物车信息
	 */
	@Override
	public List<PcGoodsDto> findUnLoadShoppingCart(String goodsId) {
		List<PcGoodsDto> goodsList = new ArrayList<PcGoodsDto>();
		Cookie[] cookies = WebUtil.getRequest().getCookies();
		if(!ObjectUtils.isEmpty(cookies)){
			for (Cookie cookie : cookies) {
				if(isNum(cookie.getName())){
					if(ObjectUtils.isEmpty(goodsId)){
						PcGoodsDto goodsDto = new PcGoodsDto();
						Goods goods = goodsMapper.selectByPrimaryKey(Long.parseLong(cookie.getName()));
						goods.setImgPath(goodsService.getFirstGoodsImg(goods.getId()));
						goodsDto.setId(goods.getId());
						goodsDto.setName(goods.getName());
						goodsDto.setCode(goods.getCode());
						goodsDto.setImgPath(goods.getImgPath());
						goodsDto.setIsDelivery(goods.getIsDelivery());
						goodsDto.setPrice(goods.getPrice());
						goodsDto.setPoints(goods.getPoints());
						goodsDto.setCategoryName(goods.getCategoryName());
						goodsDto.setCartNum(Integer.parseInt(cookie.getValue()));
						goodsDto.setWeight(goods.getWeight());
						goodsDto.setActPoints(0);
						goodsDto.setIsPutway(goods.getIsPutway());
						goodsDto.setSumPrice(goods.getPrice().multiply(BigDecimal.valueOf(Long.parseLong(cookie.getValue()))));
						goodsDto.setStandard(goods.getStandard());
						goodsList.add(goodsDto);
					}else{
						if(goodsId.contains(cookie.getName())){
							PcGoodsDto goodsDto = new PcGoodsDto();
							Goods goods = goodsMapper.selectByPrimaryKey(Long.parseLong(cookie.getName()));
							goods.setImgPath(goodsService.getFirstGoodsImg(goods.getId()));
							goodsDto.setId(goods.getId());
							goodsDto.setName(goods.getName());
							goodsDto.setCode(goods.getCode());
							goodsDto.setImgPath(goods.getImgPath());
							goodsDto.setIsDelivery(goods.getIsDelivery());
							goodsDto.setPrice(goods.getPrice());
							goodsDto.setPoints(goods.getPoints());
							goodsDto.setCategoryName(goods.getCategoryName());
							goodsDto.setCartNum(Integer.parseInt(cookie.getValue()));
							goodsDto.setWeight(goods.getWeight());
							goodsDto.setActPoints(0);
							goodsDto.setIsPutway(goods.getIsPutway());
							goodsDto.setSumPrice(goods.getPrice().multiply(BigDecimal.valueOf(Long.parseLong(cookie.getValue()))));
							goodsDto.setStandard(goods.getStandard());
							goodsList.add(goodsDto);
						}
					}
				}
			}
		}
		return goodsList;
	}
	/**
	 * 清除全部购物车
	 */
	@Override
	public void deleteAllShoppingCart(HttpServletResponse response,String goodsId) {
		Cookie[] cookies = WebUtil.getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if(ObjectUtils.isEmpty(goodsId)){
				Cookie cook = new Cookie(cookie.getName(),"");//这边得用"",不能用null  
				cook.setPath("/");//设置成跟写入cookies一样的  
				cook.setMaxAge(0);  
				response.addCookie(cook);  
			}else{
				if(goodsId.contains(cookie.getName())){
					Cookie cook = new Cookie(cookie.getName(),"");//这边得用"",不能用null  
					cook.setPath("/");//设置成跟写入cookies一样的  
					cook.setMaxAge(0);  
					response.addCookie(cook); 
				}
			}
		}
	}
	/**
	 * @param str
	 * @return boolean
	 * @author lij
	 * @date 2017年9月6日 上午11:29:04
	 * 判断字符串是否可以转换为数字
	 */
	public static boolean isNum(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	@Override
	public List<PcCouponDto> findCoupon(List<PcGoodsDto> goodsList){
		List<PcCouponDto> result = new ArrayList<PcCouponDto>();  
		//1. 查询有效的优惠券
		ReqData reqData = new  ReqData();
		reqData.putValue("status", SystemConstant.COUPON.COUPON_STATUS_ENABLE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("coupon_count", 0, SystemConstant.REQ_PARAMETER_GT);
		reqData.putValue("show_in_coupon_center", SystemConstant.COUPON.SHOW_IN_COUPON_CENTER_TRUE, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("effective_end_time", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"), SystemConstant.REQ_PARAMETER_GE);  //使用结束时间大于当前时间
		List<Coupon>  list = couponMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		//2. 筛选出优惠券能使用的商品
		for (Coupon coupon : list) {
			PcCouponDto pcCouponDto = new  PcCouponDto();
			BeanUtil.copyProperties(coupon, pcCouponDto);
			switch(pcCouponDto.getUseRange()){
				case SystemConstant.COUPON.ALL_USE:
					//全场通用
					break;
				case SystemConstant.COUPON.CATEGORY_USE:
					//类别使用
					pcCouponDto.setCouponGoods(findCouponGoods(goodsList,coupon.getUseRangeIds()));
					break;
				case SystemConstant.COUPON.LIMIT_USE:
					//限定商品使用
					for (PcGoodsDto pcGoodsDto : goodsList) {
						if (coupon.getUseRangeIds().contains(pcGoodsDto.getId().toString())) {
							List<Goods> couponGoods = new ArrayList<Goods>();
							Goods goods = goodsMapper.selectByPrimaryKey(pcGoodsDto.getId());
							goods.setImgPath(goodsService.getFirstGoodsImg(pcGoodsDto.getId()));
							couponGoods.add(goods);
							pcCouponDto.setCouponGoods(couponGoods);
						}
					}
					break;
				case SystemConstant.COUPON.LINE_UP_USE:
					//排除商品使用
					break;
			}
			result.add(pcCouponDto);
		}
		return result;
	}
	
	/**
	 * 筛选类别使用的优惠券 有哪些商品可用 
	 */
	public List<Goods> findCouponGoods(List<PcGoodsDto> goodsList,String categoryIds){
		List<Goods> couponGoods = new ArrayList<Goods>();
		for (PcGoodsDto pcGoodsDto : goodsList) {
			ReqData reqData = new ReqData();
			reqData.putValue("goods_id", pcGoodsDto.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<GoodsCategory> list = goodsCategoryMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if (list.size() > 0) {
				if (categoryIds.contains(list.get(0).getCid().toString())) {
					Goods goods = goodsMapper.selectByPrimaryKey(pcGoodsDto.getId());
					goods.setImgPath(goodsService.getFirstGoodsImg(pcGoodsDto.getId()));
					couponGoods.add(goods);
				}
			} 
		}
		return couponGoods;
	}
	
	@Override
	public void deletGoods(String goodsIds,Long userId,HttpServletResponse response) {
		String[] idArray = goodsIds.split("\\,");
		for (String goodsId : idArray) {
			PcCartDto pcCartDto = new PcCartDto();
			pcCartDto.setGoodsId(Long.parseLong(goodsId));
			if (ObjectUtil.isNull(userId)) {
				deleteShoppingCart(pcCartDto, response);
			} else{
				pcShoppingCartService.deleteShoppingCartGoods(userId, Long.parseLong(goodsId));
			}
		}
		
	}
	@Override
	public List<PcGoodsDto> findChooseGoods(String goodsId) {
		
		List<PcGoodsDto> goodsList = new ArrayList<PcGoodsDto>();
		Cookie[] cookies = WebUtil.getRequest().getCookies();
		if(!ObjectUtils.isEmpty(cookies)){
			for (Cookie cookie : cookies) {
				if(isNum(cookie.getName())){
					PcGoodsDto goodsDto = new PcGoodsDto();
					String[] goodId = goodsId.split(",");
					for(String id : goodId){
						if(Long.parseLong(id) == Long.parseLong(cookie.getName())){
							Goods goods = goodsMapper.selectByPrimaryKey(Long.parseLong(cookie.getName()));
							goods.setImgPath(goodsService.getFirstGoodsImg(goods.getId()));
							goodsDto.setId(goods.getId());
							goodsDto.setName(goods.getName());
							goodsDto.setCode(goods.getCode());
							goodsDto.setImgPath(goods.getImgPath());
							goodsDto.setIsDelivery(goods.getIsDelivery());
							goodsDto.setPrice(goods.getPrice());
							goodsDto.setPoints(goods.getPoints());
							goodsDto.setCategoryName(goods.getCategoryName());
							goodsDto.setCartNum(Integer.parseInt(cookie.getValue()));
							goodsDto.setWeight(goods.getWeight());
							goodsDto.setActPoints(0);
							goodsDto.setIsPutway(goods.getIsPutway());
							goodsDto.setSumPrice(goods.getPrice().multiply(BigDecimal.valueOf(Long.parseLong(cookie.getValue()))));
							goodsDto.setStandard(goods.getStandard());
							goodsList.add(goodsDto);
						}
					}
				}
			}
		}
		
		return goodsList;
	}
	
}
