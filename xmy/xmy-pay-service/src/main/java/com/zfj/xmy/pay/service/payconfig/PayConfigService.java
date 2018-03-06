package com.zfj.xmy.pay.service.payconfig;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.PayConfig;
import com.zfj.xmy.common.persistence.pojo.TradeChannels;
import com.zfj.xmy.quartz.dto.PushReturnDto;

public interface PayConfigService {

	
	/**
	 * 根据 支付渠道(支付类型) 查询相关配置
	 * @param payType
	 * @return
	 * @Description 
	 * @date 2017年8月9日  下午5:40:33
	 * @author wy
	 * 2017
	 * @return List<PayConfig>
	 */
	List<PayConfig> findPayConfigByPayType(Integer payType);

	/**
	 * 根据 支付渠道(支付类型) 查询相关配置
	 * @param payType
	 * @return JsonStr
	 * @Description 
	 * @date 2017年8月9日  下午5:45:41
	 * @author wy
	 * 2017
	 * @return String
	 */
	String findPayConfigByPayTypeToJsonStr(Integer payType);

	/**
	 * 重新加载配置
	 * @param payType
	 * @Description 
	 * @date 2017年8月10日  上午9:23:58
	 * @author wy
	 * 2017
	 * @return void
	 */
	String reloadPayConfig(Integer payType);
	/**
	 * 获取支付配置
	 * @param payType
	 * @return
	 * @Description 
	 * @date 2017年8月10日  上午9:33:37
	 * @author wy
	 * 2017
	 * @return String
	 */
	String getPayConfig(Integer payType);

	/**
	 * 根据商户类型 获取支付配置
	 * @param merchantType
	 * @return
	 * @Description 
	 * @date 2017年8月22日  上午10:49:26
	 * @author wy
	 * 2017
	 * @return String
	 */
	String getPayConfigByMerchantType(int merchantType);

	/**
	 * 查询所有 支付配置
	 * @return
	 * @Description 
	 * @date 2017年12月22日  上午9:54:10
	 * @author wy
	 * 2017
	 * @return List<PayConfig>
	 */
	List<PayConfig> findAllPayConfig();

	/**
	 * 查询所有 支付渠道
	 * @return
	 * @Description 
	 * @date 2017年12月22日  上午10:28:06
	 * @author wy
	 * 2017
	 * @return List<TradeChannels>
	 */
	List<TradeChannels> findAllTradeChannelsMapper();

	/**
	 * 修改支付配置
	 * @param form
	 * @Description 
	 * @date 2017年12月22日  下午4:28:29
	 * @author wy
	 * 2017
	 * @param paytype 
	 * @return void
	 */
	 void modify(String form, Integer paytype);
	
	
	
}
