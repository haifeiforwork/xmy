package com.zfj.xmy.wap.web.common;

public class AjaxResult {
	
	//状态
	private int status;
	
	//信息
	private String msg;
	
	//返回对象
	private Object obj;

	public AjaxResult(int status, String msg, Object obj) {
		super();
		this.status = status;
		this.msg = msg;
		this.obj = obj;
	}
	
	public AjaxResult() {}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public static AjaxResult success (String msg, Object o) {
		return new AjaxResult(1, msg, o);
	}
	
	public static AjaxResult faild (String msg) {
		return new AjaxResult(0, msg, null);
	}
}
