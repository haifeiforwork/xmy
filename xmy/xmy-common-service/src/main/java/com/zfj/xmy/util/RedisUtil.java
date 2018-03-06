package com.zfj.xmy.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.zfj.xmy.redis.RedisTokenManager;

public class RedisUtil {

	@Autowired
	private RedisTokenManager redisTokenManager;
	
	public <T> T get(String key,Class<T> class1){
		String redisJson = redisTokenManager.get(key);
		if (redisJson == null) {
			return null;
		}
		return  JSONObject.parseObject(redisJson, class1);
	}
	
	public void set(String key,Object object){
		if (object == null) {
			return;
		}
		String redisJson = JSONObject.toJSONString(object);
		redisTokenManager.setKey(key, redisJson);
	}
}
