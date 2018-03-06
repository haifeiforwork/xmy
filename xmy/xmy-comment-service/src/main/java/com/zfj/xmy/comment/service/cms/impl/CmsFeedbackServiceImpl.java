package com.zfj.xmy.comment.service.cms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.comment.persistence.app.pojo.dao.FeedbackExMapper;
import com.zfj.xmy.comment.persistence.app.pojo.dto.FeedbackDto;
import com.zfj.xmy.comment.service.cms.CmsFeedbackService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.FeedbackMapper;
import com.zfj.xmy.common.persistence.pojo.Feedback;
import com.zfj.xmy.common.persistence.pojo.SysUser;
@Service
public class CmsFeedbackServiceImpl implements CmsFeedbackService{
	
	@Autowired
	private FeedbackExMapper exMapper;
	
	@Autowired
	private FeedbackMapper feedbackMapper;
	
	/**
	 * 分页查建议信息
	 */
	@Override
	public List<FeedbackDto> findAllFeedbackDtoList(ReqData reqData, PageBean pageBean) {
		List<FeedbackDto> list = exMapper.findFeedback(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setTotalCount(exMapper.countFeedback(ReqUtil.reqParameterToCriteriaParameter(reqData)));
		return list;
	}

	@Override
	public void updateFeedBack(Long id,SysUser sysUser) {
		Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
		feedback.setStatus(1);
		feedback.setHandlePersonName(sysUser.getName());
		feedback.setHandleTime(new Date());
		feedbackMapper.updateByPrimaryKey(feedback);
	}

}
