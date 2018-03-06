package com.zfj.xmy.comment.service.app;

import java.util.List;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Feedback;

public interface FeedbackService {

	/**
	 * 
	 * @Description 插入单条意见反馈
	 * @param feedback
	 * @Author liuw
	 * @Date 2017年7月25日下午2:55:16
	 */
	void insertFeedback(Feedback feedback);

	/**
	 * 
	 * @Description 根据条件查询所有的反馈记录
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月25日下午3:41:31
	 */
	List<Feedback> findFeedbacks(ReqData reqData);
}
