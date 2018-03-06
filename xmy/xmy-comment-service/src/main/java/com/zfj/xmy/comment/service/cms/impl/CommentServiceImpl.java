package com.zfj.xmy.comment.service.cms.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.comment.service.cms.CommentService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CommentImageMapper;
import com.zfj.xmy.common.persistence.dao.CommentMapper;
import com.zfj.xmy.common.persistence.dao.GoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.pojo.Comment;
import com.zfj.xmy.common.persistence.pojo.CommentImage;
import com.zfj.xmy.common.persistence.pojo.Goods;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.service.CommonGoodsService;
/**
 * @author lijie
 *
 */
@Service
public class CommentServiceImpl extends BaseService implements CommentService{
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommentImageMapper commentImageMapper;
	@Autowired
	private CommonGoodsService commonGoodsService;
	
	@Override
	public List<Comment> findAllComment(ReqData reqData) {
		List<Comment> selectByExampleAndPage = commentMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), reqData.getRowBounds());
		return selectByExampleAndPage;
	}
	
	@Override
	public int findCommentCount(ReqData reqData) {
		
		return commentMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
	}
	
	/**
	 * 修改评论状态
	 */
	@Override
	public void updateCommentById(Integer commentId,Integer status) {
		
		Comment oldComent = commentMapper.selectByPrimaryKey((long)commentId);
		oldComent.setCommentStatus(status);
		oldComent.setAuditTime(new Date());
		commentMapper.updateByPrimaryKey(oldComent);
	}
	/**
	 * 批量修改评论状态
	 */
	@Override
	public void updateCommentsByIds(String commentIds, Integer status) {
		//1.查询出未更新的多个评论
		ReqData reqData = new ReqData();
		reqData.putValue("id", commentIds, SystemConstant.REQ_PARAMETER_IN);
		List<Comment> oldComment = commentMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		for (Comment comment : oldComment) {
			//2.修改评论的状态并保存
			comment.setCommentStatus(status);
			comment.setAuditTime(new Date());
			commentMapper.updateByPrimaryKey(comment);
			if(comment.getCommentType() == SystemConstant.Comment.COMMENT_GOODS){
				commonGoodsService.updateGoodsSumCommentByGoodsId(comment.getGoodsId());
			}
		}
	}
	/**
	 * 查询详细的评论内容
	 */
	@Override
	public Map<String, Object> getCommentDetail(Integer commentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		//1.查询出评论内容详情
		Comment comment = commentMapper.selectByPrimaryKey((long)commentId);
		map.put("comment", comment);
		//2.根据评论的用户id查询用户的信息
		User user = userMapper.selectByPrimaryKey(comment.getUserId());
		map.put("user", user);
		//3.根据类型查询对应的商品或者订单
		if(comment.getCommentType() == SystemConstant.Comment.COMMENT_ORDER){
			//3.1订单的信息
			Order order = orderMapper.selectByPrimaryKey(comment.getOrderId());
			map.put("order", order);
		}else if(comment.getCommentType() == SystemConstant.Comment.COMMENT_GOODS){
			//3.2商品信息
			Goods goods = goodsMapper.selectByPrimaryKey(comment.getGoodsId());
			map.put("goods", goods);
		}
		//4.查询对应图片
		ReqData reqData = new ReqData();
		reqData.putValue("comment_id", commentId, SystemConstant.REQ_PARAMETER_EQ);
		List<CommentImage> imageCount = commentImageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		map.put("imageList", imageCount);
		return map;
	}
 
}
