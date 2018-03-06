package com.zfj.xmy.common.service.impl;  

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.UserInfoMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.dao.UserSpendPointsMapper;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.UserInfo;
import com.zfj.xmy.common.persistence.pojo.UserSpendPoints;
import com.zfj.xmy.common.service.CommonUserPointsService;
/** 
 * @Title: CommonUserPointsServiceImpl.java 
 * @Package com.zfj.xmy.common.service.impl 
 * @Description: 
 * @author hexw
 * @date 2017年11月4日 下午2:10:54 
 */
@Service
public class CommonUserPointsServiceImpl extends BaseService implements CommonUserPointsService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserSpendPointsMapper userSpendPointsMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public int insertUserSpendPointsMapper(){
		UserSpendPoints userSpendPoints = new UserSpendPoints();
		
		return 0;
	}
	/**
	 * 确认收货增加积分
	 */
	@Transactional
	@Override
	public void updateUserSpendPoints(Long orderId) {
		//1.查询出订单信息
		Order order = orderMapper.selectByPrimaryKey(orderId);
		//2.为用户添加积分
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(order.getUserId());
		userInfo.setAccPoints(userInfo.getAccPoints()+order.getTotal().intValue());
		userInfo.setUpdateTime(new Date());
		userInfoMapper.updateByPrimaryKey(userInfo);
		//3.添加到用户积分记录表
		UserSpendPoints spendPoints = new UserSpendPoints();
		spendPoints.setUserId(order.getUserId());
		spendPoints.setMoneyPoint(new BigDecimal(order.getTotal().intValue()));
		spendPoints.setCreatTime(new Date());
		spendPoints.setNo(order.getNo());
		spendPoints.setSpendType(1);//存入
		spendPoints.setType(2);//积分
		spendPoints.setSign(1);
		spendPoints.setRemarks("订单完成赠送积分："+order.getTotal().intValue());
		userSpendPointsMapper.insert(spendPoints);
	}
	@Override
	public void updateUserCommnetPoints(Long userId, Long orderId,Integer commentStar) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		Integer addPonits = 0;
		if(commentStar>=5){//大于等于五星
			addPonits=5;
		}else if(commentStar>=3 && commentStar<=4){//3分
			addPonits=3;
		}else{//1分
			addPonits = 1;
		}
		userInfo.setAccPoints(userInfo.getAccPoints()+addPonits);
		userInfo.setUpdateTime(new Date());
		userInfoMapper.updateByPrimaryKey(userInfo);
		if(addPonits>0){
			//1.查询出订单信息
			Order order = orderMapper.selectByPrimaryKey(orderId);
			//3.添加到用户积分记录表
			UserSpendPoints spendPoints = new UserSpendPoints();
			spendPoints.setUserId(userId);
			spendPoints.setMoneyPoint(new BigDecimal(addPonits));
			spendPoints.setCreatTime(new Date());
			spendPoints.setNo(order.getNo());
			spendPoints.setSpendType(1);//存入
			spendPoints.setSign(1);
			spendPoints.setType(2);//积分
			spendPoints.setRemarks("评论订单赠送积分："+addPonits);
			userSpendPointsMapper.insert(spendPoints);
		}
	} 
	
	
}
  