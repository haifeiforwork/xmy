package com.zfj.xmy.wap.web.controller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/v1.1")
public class TestController {

	
	@RequestMapping("/index")
	public boolean index(){
		return true ;
	}
	
}
