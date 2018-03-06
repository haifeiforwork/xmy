package com.zfj.xmy.redis;


import javax.servlet.http.HttpServletRequest;

import org.springframework.util.SerializationUtils;

import com.zfj.base.util.common.StringUtil;

public enum UserSessionManager {

	SESSION;

	private TokenManager tokenManager;

	public TokenManager getTokenManager() {
		return tokenManager;
	}

	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}


	private Object getData(HttpServletRequest request) {
		String token = request.getHeader(ThreadToken.SESSION.HEADER_TOKEN_KEY);
		if (StringUtil.isEmpty(token)) {
			return null;
		}
		return getObject(token);
	}

	private Object getObject(String token) {
		if (token == null)
			return null;
		byte[] data = tokenManager.getKey(token.getBytes());
		if (data == null || data.length == 0) {
			return null;
		}
		return SerializationUtils.deserialize(data);
	}

}
