package com.zfj.xmy.comment.service.cms;

import java.util.List;
import java.util.Map;

import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Comment;

public interface CommentService {
	/**
	 * 分页查询全部评论
	 * @param reqData
	 * @return List<Comment>
	 * @author lij
	 * @date 2017年7月21日 上午10:19:53
	 */
	List<Comment> findAllComment(ReqData reqData);
	/**
	 * 查拳评论总条数
	 * @param reqData
	 * @return int
	 * @author lij
	 * @date 2017年7月21日 上午11:21:09
	 */
	int findCommentCount(ReqData reqData);
	/**
	 * @param commentId void
	 * @author lij
	 * @date 2017年7月21日 上午11:35:52
	 * 根据评论id修改评论信息
	 */
	void updateCommentById(Integer commentId,Integer status);
	/**
	 * @param commentIds
	 * @param status void
	 * @author lij
	 * @date 2017年7月21日 下午2:20:14
	 * 根据多个评论id修改评论状态
	 */
	void updateCommentsByIds(String commentIds,Integer status);
	/**
	 * @param commentId
	 * @return Map<String,Object>
	 * @author lij
	 * @date 2017年7月21日 下午2:47:42
	 * 查询评论详细内容
	 */
	Map<String, Object> getCommentDetail(Integer commentId);
}
