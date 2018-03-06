package com.zfj.xmy.cms.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

import com.zfj.base.util.collection.CollectionExtUtils;
import com.zfj.base.util.web.WebUtil;
import com.zfj.xmy.common.ReqData;


@RestController
public class BaseController {

	/**
	 * 参数转换
	 * @author dengq
	 * @param reqData
	 * @param ignoreFields
	 */
	protected void reqData2Params(ReqData reqData,String... ignoreFields){
		HttpServletRequest request = WebUtil.getRequest() ;
		if (null != reqData && !CollectionExtUtils.isEmpty(reqData.getRule())) {
			List<Map<String,Object>> rules = reqData.getRule() ;
			Map<String,Object> params = new HashMap<>() ;
			List<String> ignoreList = null ;
			if(null != ignoreFields && 0 != ignoreFields.length){
				ignoreList = Arrays.asList(ignoreFields) ;
			}
			for(Map<String,Object> rule : rules){
				String field = (String)rule.get(ReqData.FIELD) ;
				if (!CollectionExtUtils.isEmpty(ignoreList) && ignoreList.contains(field)) {
					continue ;
				}
				if(-1 != field.indexOf("_")){
					String[] fieldArr = field.split("_") ;
					StringBuilder fieldBuilder = new StringBuilder() ;
					for(int i = 0 ;i < fieldArr.length ;i++){
						if (0 == i) {
							fieldBuilder.append(fieldArr[i]) ;
							continue ;
						}
						fieldBuilder.append(fieldArr[i].substring(0,1).toUpperCase()).append(fieldArr[i].substring(1,fieldArr[i].length())) ;
					}
					field = fieldBuilder.toString() ;
				}
				params.put(field,rule.get(ReqData.VALUE)) ;
			}
			request.setAttribute("params",params) ;
		}
	}
	/**
	 * 参数转换升级版
	 * @param reqData
	 * @param ignoreFields void
	 * @author lij
	 * @date 2017年11月18日 下午2:44:02
	 */
	protected void reqData3Params(ReqData reqData,String... ignoreFields){
		HttpServletRequest request = WebUtil.getRequest() ;
		if (null != reqData && !CollectionExtUtils.isEmpty(reqData.getRule())) {
			List<Map<String,Object>> rules = reqData.getRule() ;
			Map<String,Object> params = new HashMap<>() ;
			List<String> ignoreList = null ;
			if(null != ignoreFields && 0 != ignoreFields.length){
				ignoreList = Arrays.asList(ignoreFields) ;
			}
			for(Map<String,Object> rule : rules){
				String field = (String)rule.get(ReqData.FIELD) ;
				if (!CollectionExtUtils.isEmpty(ignoreList) && ignoreList.contains(field)) {
					continue ;
				}
				if(-1 != field.indexOf("_")){
					String[] fieldArr = field.split("_") ;
					StringBuilder fieldBuilder = new StringBuilder() ;
					for(int i = 0 ;i < fieldArr.length ;i++){
						if (0 == i) {
							fieldBuilder.append(fieldArr[i]) ;
							continue ;
						}
						fieldBuilder.append(fieldArr[i].substring(0,1).toUpperCase()).append(fieldArr[i].substring(1,fieldArr[i].length())) ;
					}
					field = fieldBuilder.toString() ;
				}
				params.put(field+rule.get(ReqData.OP).toString(),rule.get(ReqData.VALUE)) ;
			}
			request.setAttribute("params",params) ;
		}
	}
	
	
	
}
