package com.zfj.xmy.pay.service.wechat;

import java.util.SortedMap;
import java.util.TreeMap;
















import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.StrUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.pay.service.pay.PayBase;
import com.zfj.xmy.pay.service.pay.dto.WxPayDto;
import com.zfj.xmy.pay.service.pay.impl.PayBaseImpl;
import com.zfj.xmy.pay.service.pay.vo.WxPayConfigVo;
import com.zfj.xmy.pay.service.payconfig.PayConfigService;



/**
 * @author wangs
 * 
 */
public interface WeChatApi {

	/**
	 * 获取请求预支付id报文
	 * @param tpWxPayDto
	 * @return
	 * @Description 
	 * @date 2017年8月12日  下午4:49:19
	 * @author wy
	 * 2017
	 * @return String
	 */
	String getPackage(WxPayDto tpWxPayDto);
	

}
