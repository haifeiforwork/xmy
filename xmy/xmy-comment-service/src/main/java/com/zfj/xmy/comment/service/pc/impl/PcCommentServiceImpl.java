package com.zfj.xmy.comment.service.pc.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.zfj.base.commons.mini.BaseService;
import com.zfj.xmy.comment.service.pc.PcCommentService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CommentMapper;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.pojo.Comment;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;

@Service
public class PcCommentServiceImpl extends BaseService implements PcCommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	//差评
	private final static int BAD=2;
	//中评
	private final static int MEDIUM=3;
	//好评
	private final static int GOOD=4;
	
	@Override
	public void findCommentByPage(Long uid, PageBean pageBean,int status) {
		ReqData reqData=new ReqData();
		reqData.putValue("user_id", uid,SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("comment_type", SystemConstant.Comment.COMMENT_ORDER,SystemConstant.REQ_PARAMETER_EQ);
		if(status==0||status==1){
			reqData.putValue("comment_status", status,SystemConstant.REQ_PARAMETER_EQ);
		}
		if(status==BAD){
			reqData.putValue("comment_rank", 1,SystemConstant.REQ_PARAMETER_EQ);
		}
		if(status==MEDIUM){
			reqData.putValue("comment_rank", 2,SystemConstant.REQ_PARAMETER_EQ);
		}
		if(status==GOOD){
			reqData.putValue("comment_rank", 3,SystemConstant.REQ_PARAMETER_EQ);
		}
		List<Comment> list=commentMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(list);
		int count=commentMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		pageBean.setTotalCount(count);
	}
	
	@Override
	public int insertComment(Comment comment){
		int result  = 0;
		if (ObjectUtil.isNotNull(comment.getOrderId())) {
			//1. 插入订单评论
			comment.setCommentType(SystemConstant.Comment.COMMENT_ORDER);
			comment.setCreateTime(new Date());
			comment.setUpdateTime(new Date());
			commentMapper.insertSelective(comment);
			// 1.1 插入订单商品评价
			ReqData reqData = new ReqData();
			reqData.putValue("order_id", comment.getOrderId(), SystemConstant.REQ_PARAMETER_EQ);
			List<OrderGoods> list = orderGoodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			for (OrderGoods orderGoods : list) {
				Comment goodsComment = new Comment();
				goodsComment.setCommentConten(comment.getCommentConten());
				goodsComment.setCommentStar(comment.getCommentStar());
				goodsComment.setUserId(comment.getUserId());
				goodsComment.setGoodsId(orderGoods.getGoodsId());
				goodsComment.setCommentType(SystemConstant.Comment.COMMENT_GOODS);
				goodsComment.setCreateTime(new Date());
				goodsComment.setUpdateTime(new Date());
				commentMapper.insertSelective(goodsComment);
			}
			//2. 修改订单状态
			Order order = orderMapper.selectByPrimaryKey(comment.getOrderId());
			order.setStatus(SystemConstant.ORDER.STATUS_FINISH);
			result = orderMapper.updateByPrimaryKey(order);
		}
		return result;
	}
	
}
