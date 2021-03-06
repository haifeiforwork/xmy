package com.zfj.xmy.common.service.impl;

import org.springframework.stereotype.Service;

import com.xiaoleilu.hutool.http.HttpBase;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpResponse;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.http.Method;
import com.zfj.base.enu.BaseProp;
import com.zfj.xmy.common.service.BaseCommonService;

@Service
public class BaseCommonServiceImpl implements BaseCommonService {

	@Override
	public void refreshIndexHomePage(){
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String appIndexUrl = BaseProp.BASE.getValue("APP_SERVER_PATH")+"/common/refreshIndex?v=1.0";
				String pcIndexUrl = BaseProp.BASE.getValue("PC_SERVER_PATH")+"/refreshIndex";
				String wapIndexUrl = BaseProp.BASE.getValue("WAP_SERVER_PATH")+"/home/refreshIndex";
				
				System.out.println("更新首页redis");
				
				String pcres = HttpUtil.get(pcIndexUrl);
				System.out.println(pcres);
				//HttpUtil.get(appIndexUrl);
				HttpRequest httpRequest = new HttpRequest(appIndexUrl);
				httpRequest.header("Content-Type", "application/json");
				httpRequest.method(Method.POST);
				HttpResponse httpResponse = httpRequest.execute();
				System.out.println(httpResponse.body());
				
				
				String wapres = HttpUtil.get(wapIndexUrl);
				System.out.println(wapres);
			}
		}).start();
	}
}
