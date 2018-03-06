package com.zfj.xmy.comment.persistence.app.pojo.dto;

import com.zfj.xmy.common.persistence.pojo.Feedback;

public class FeedbackDto extends Feedback {
	private String name;//用户名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
