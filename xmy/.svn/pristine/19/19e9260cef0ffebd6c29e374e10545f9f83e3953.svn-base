package com.zfj.xmy.comment.service.cms;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.comment.persistence.app.pojo.dto.FeedbackDto;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.SysUser;

public interface CmsFeedbackService {
	/**
	 * 分页查询建议信息
	 * @param reqData
	 * @param pageBean
	 * @return List<FeedbackDto>
	 * @author lij
	 * @date 2017年10月31日 下午9:11:56
	 */
	List<FeedbackDto> findAllFeedbackDtoList(ReqData reqData,PageBean pageBean);
	/**
	 * 修改已处理建议投诉
	 * @param id void
	 * @author lij
	 * @date 2017年12月14日 下午4:21:18
	 */
	void updateFeedBack(Long id,SysUser sysUser);
	
}
