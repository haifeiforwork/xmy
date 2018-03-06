package com.zfj.xmy.app.web.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSON;
import com.zfj.base.commons.mini.bean.RespData;
import com.zfj.base.encryption.DESUtil;
import com.zfj.base.exception.BusinessException;
/**
 * 移动端返回数据
 * @author dengq
 *
 */
public class AppResp extends RespData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resultMsg ;

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	/**
	 * 加密返回
	 * @param data
	 * @return
	 * @Description 
	 * @date 2017年8月23日  上午10:42:28
	 * @author wy
	 * 2017
	 * @return ResponseEntity<AppResp>
	 */
	public static ResponseEntity<AppResp> getEncr(Object data){
		String encrStr = "";
		try {
			encrStr = DESUtil.encryptDES(JSON.toJSONString(data)) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(BusinessException.EX_CODE_FAILURE,"加密失败") ;
		}
		return get(encrStr) ;
	}
	
	public static ResponseEntity<AppResp> get(Object data){
		return new ResponseEntity<AppResp>(get(data,null),HttpStatus.OK) ;
	}
	public static ResponseEntity<AppResp> get(){
		return AppResp.get(null);
	}
	
	private static AppResp get(Object data,Class<?> clz){
		AppResp appResp = new AppResp() ;
		appResp.setResultCode(RespData.CODE_SUCCESS) ;
//		appResp.setData(null != data ? data : List.class == clz ? new ArrayList<Object>() : new HashMap<String,Object>()) ;
		appResp.setData(data) ;
		appResp.setResultMsg("") ;
		return appResp ;
	}
	
}
