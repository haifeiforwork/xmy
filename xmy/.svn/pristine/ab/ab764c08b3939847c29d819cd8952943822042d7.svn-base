package com.zfj.xmy.app.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.app.web.controller.BaseController;
import com.zfj.xmy.common.service.CommonVersionService;

/**
 * app版本控制
 * @author wy
 *
 */
@RestController
@RequestMapping(value="/version")
public class VersionController  extends BaseController{

	
	@Autowired
	private CommonVersionService commonVersionService;
	
	
	
	/**
	 * 获取app版本更新配置
	 * @return
	 * @Description 
	 * @date 2018年1月9日  上午11:37:56
	 * @author wy
	 * 2018
	 * @return ResponseEntity<AppResp>
	 */
	@RequestMapping("/get")
	@Exclusion
	 public ResponseEntity<AppResp> get(){		
		return AppResp.get(commonVersionService.get());
	 }
}
