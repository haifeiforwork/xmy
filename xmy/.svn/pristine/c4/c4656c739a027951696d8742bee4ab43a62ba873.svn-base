package com.zfj.xmy.redis;

public enum ThreadToken {
  SESSION;
  public final String HEADER_TOKEN_KEY = "token";
  public final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";
//  public static final ThreadLocal<HttpServletRequest> localhostRequest =
//      new InheritableThreadLocal<>(); // 保存request的
//  public static final ThreadLocal<HttpServletResponse> localhostResponse =
//      new InheritableThreadLocal<>();// response

//  public HttpServletRequest getRequest() {
//    return localhostTestRequest.get();
//  }

//  public HttpServletResponse getResponse() {
//    return localhostResponse.get();
//  }

  public String getToken() {
    return localhostRequestToken.get() == null ? null
                                               : localhostRequestToken.get();
  }

  public static final ThreadLocal<String> localhostRequestToken =
      new InheritableThreadLocal<>(); // 保存request的
}
