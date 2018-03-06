package com.zfj.xmy.cms.web.common;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletConfigAware;

import com.aliyun.oss.OSSClient;
import com.zfj.xmy.oss.OSSManager;

public class InitDataListener implements InitializingBean,ServletConfigAware {

	@Autowired
	private OSSManager ossManager ;
	
	@Override
	public void setServletConfig(ServletConfig config) {
		//初始化ossclient对象
		try {
			ossManager.init() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		OSSClient ossClient = ossManager.getOSSClient() ;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

}
