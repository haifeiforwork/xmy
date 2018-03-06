package com.zfj.xmy.comment.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.zfj.xmy.comment.service.app.FeedbackService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.FeedbackMapper;
import com.zfj.xmy.common.persistence.pojo.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Override
	public void insertFeedback(Feedback feedback){
		Integer insert = feedbackMapper.insert(feedback);
		if(ObjectUtils.isEmpty(insert)||insert<1){
			//throw new XmyException("插入一天反馈信息失败");
		}
	}
	@Override
	public List<Feedback> findFeedbacks(ReqData reqData){
		List<Feedback> selectByExample = feedbackMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return selectByExample;
	}
}
