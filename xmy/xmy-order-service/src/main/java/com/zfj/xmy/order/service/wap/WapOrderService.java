package com.zfj.xmy.order.service.wap;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.order.persistence.common.pojo.dto.OrderDto;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;


public interface WapOrderService{

	Integer updateApplyRefund(Order order, String refundMoney, Long userId);

	Integer changeTicketType(Long id, Integer type);

	Integer changeTicketInfo(Long id, String companyName, String content);

	Map<String, Object> getFreight(String province, String area,
			String goodsId, String sumPrice, Long userId);

	List<PcGoodsDto> getShippingCart(Long userId, String goodsId);

	OrderDto findOderyDetailById(Long orderId);
	
	/**
	 * 获取活动商品打折价格
	 * @param request
	 * @param sessionInfo
	 * @param userAddress
	 * @param shoppingCart
	 * @return    
	 * @return BigDecimal    
	 * Date:2017年11月13日 上午6:31:08 
	 * @author cj
	 */
	BigDecimal changeActivityPrice(HttpServletRequest request,UserAddrees userAddress,List<PcGoodsDto> shoppingCart, Long userId);
	
	/**
	 * 根据订单编号获取订单
	 * @param no
	 * @return
	 */
	Order findOrderByNo(String no);
	
	/**
	 * 获取用户余额
	 * @param id
	 * @return
	 */
	BigDecimal getUserBalance(Long id);

	List<OrderDto> findAllOrderByUserId(Integer status, Long id, PageBean pageBean);

	Order findOrderById(Long orderId);

	Order addOrder(HttpServletRequest request, HttpServletResponse response,
			String phone, String address, String conName, String taxperNo,
			String idCard, Long addressId, String dateTime, String invoice,
			String goodsIds, String remark, String conponId,
			String provinceAddress, String countyAddress, UserInfo userInfo,
			String companyName, Integer invoiceType, Long accPoints, String area);

	Order updateOrder(Order order);

	List<OrderDto> getOrderByGoodsId(Long userId, String goodsId);
	
	/**
	 * 拼购抽奖和三免一条件判断
	 * @param sessionInfo
	 * @param shopping
	 */
	RespData judgeNewActivityGoods(Long userId, String goodsId);
}
