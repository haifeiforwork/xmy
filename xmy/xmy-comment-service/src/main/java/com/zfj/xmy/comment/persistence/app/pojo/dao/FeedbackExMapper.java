package com.zfj.xmy.comment.persistence.app.pojo.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.comment.persistence.app.pojo.dto.FeedbackDto;

public interface FeedbackExMapper {
	/**
	 * 分页查询商品名称
	 * @param parameter
	 * @param rowBounds
	 * @return List<FeedbackDto>
	 * @author lij
	 * @date 2017年10月31日 下午9:06:52
	 */
	List<FeedbackDto> findFeedback(CriteriaParameter parameter,RowBounds rowBounds);
	/**
	 * 查询总条数
	 * @param parameter
	 * @return Integer
	 * @author lij
	 * @date 2017年10月31日 下午9:07:30
	 */
	Integer countFeedback(CriteriaParameter parameter);
}
