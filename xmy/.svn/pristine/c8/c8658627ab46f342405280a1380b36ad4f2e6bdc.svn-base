package com.zfj.xmy.order.service.common;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.PayConfig;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.order.persistence.app.pojo.dto.AppPayInDto;
import com.zfj.xmy.pay.persistence.pojo.vo.LaunchPayGoodsVo;
import com.zfj.xmy.pay.service.ali.AlipayClientSyncDto;
import com.zfj.xmy.pay.service.pay.dto.AvailblePayTypeDto;
import com.zfj.xmy.pay.service.pay.vo.WxPayConfigVo;

public interface PayService {
	
	/**
	 * 支付宝 客户端同步返回验签
	 * @param alipayClientSync
	 * @Description 
	 * @date 2017年8月10日  下午2:46:00
	 * @author wy
	 * 2017
	 * @return void
	 */

	void alipayClientSyncSign(AlipayClientSyncDto alipayClientSync);

	/**
	 *  支付 回调业务
	 * @param request
	 * @param response
	 * @param paytype 支付方式
	 * @Description 
	 * @date 2017年8月11日  下午4:10:37
	 * @author wy
	 * 2017
	 * @return void
	 */
	void payNotify(HttpServletRequest request, HttpServletResponse response,int paytype);
	
	
	
	/**
	 * 购买商品(发起支付)
	 * @param launchPayGoods
	 * @param actual 实际支付金额 
	 * @param payamount 抵扣金额 (不要购物卡时传入 0)
	 * @Description 
	 * @date 2017年8月11日  下午2:19:36
	 * @author wy
	 * 2017
	 * @return void
	 */
	Object buyGoods(LaunchPayGoodsVo launchPayGoods, BigDecimal actual,BigDecimal payamount);
	/**
	 * 根据支付类型 获取 支付成功时的回调地址
	 * @return
	 * @Description 
	 * @date 2017年8月11日  下午2:50:58
	 * @author wy
	 * 2017
	 * @return String
	 */
	String getNotifyUrlByPayType(int paytype);
	
	
	/**
	 * 获取可用的支付方式
	 * @return
	 * @Description 
	 * @date 2017年8月11日  下午3:55:51
	 * @author wy
	 * 2017
	 * @return Object
	 */
	List<AvailblePayTypeDto> getAvailblePayType();

	/**
	 * 获取微信配置 - ios SDK需要
	 * @return
	 * @Description 
	 * @date 2017年8月23日  上午10:25:28
	 * @author wy
	 * 2017
	 * @return WxPayConfigVo
	 */
	WxPayConfigVo getWechatConfig();

	/***
	 * 货到付款
	 * @param launchPayGoods
	 * @return
	 * @Description 
	 * @date 2017年10月24日  下午3:52:14
	 * @author wy
	 * 2017
	 * @return Object
	 */
	void cashondelivery(LaunchPayGoodsVo launchPayGoods);

	/***
	 * 购买商品(发起支付) 处理购物卡
	 * @param launchPayGoods
	 * @param userid 不登录时传空
	 * @return
	 * @Description 
	 * @date 2017年10月24日  下午6:09:05
	 * @author wy
	 * 2017
	 * @return Object
	 */
	Object beforebuyGoods(LaunchPayGoodsVo launchPayGoods, Long userid);

	/**
	 * 购物卡支付
	 * @param inDto void
	 * @author lij
	 * @date 2018年1月24日 下午4:43:49
	 */
	void shoppingcardPay(AppPayInDto inDto,UserInfo user,List<Order> orderList);
}
