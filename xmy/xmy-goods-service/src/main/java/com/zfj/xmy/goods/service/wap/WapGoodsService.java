package com.zfj.xmy.goods.service.wap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;
import com.zfj.xmy.common.persistence.pojo.UserAddrees;
import com.zfj.xmy.goods.persistence.pc.pojo.dto.PcGoodsDir;
import com.zfj.xmy.order.persistence.pc.pojo.dto.PcGoodsDto;

public interface WapGoodsService {
	
	/**
	 * 获取活动商品限量
	 * @param activityId
	 * @param goodsId
	 * @return
	 */
	LimitGoods getActivityNum(Long activityId, Long goodsId);
	
	/**
	 * 企业定制获取商品
	 * @param name
	 * @return
	 */
	List<PcGoodsDir> findBorderGoods(String name);
	
	/**
	 * 判断商品是否下架
	 * @Description 
	 * @param goodsId
	 * @return
	 * @Author cj
	 * @Date 2017年11月6日下午10:50:38
	 */
	Boolean isPutaway(Long goodsId);
	
	/**
	 * 随机获取商品
	 * @param date
	 * @param num
	 * @return
	 */
	List<Goods> roundGoods(Integer num,Long goodsId);
	
}