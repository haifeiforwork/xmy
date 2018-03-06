package com.zfj.xmy.comment.service.app.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.stereotype.Service;

import com.zfj.base.commons.mini.BaseService;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentInDto;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentOutDto;
import com.zfj.xmy.comment.service.app.AppCommentService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.CommentImageMapper;
import com.zfj.xmy.common.persistence.dao.CommentMapper;
import com.zfj.xmy.common.persistence.dao.OrderGoodsMapper;
import com.zfj.xmy.common.persistence.dao.OrderMapper;
import com.zfj.xmy.common.persistence.dao.UserMapper;
import com.zfj.xmy.common.persistence.pojo.Comment;
import com.zfj.xmy.common.persistence.pojo.CommentImage;
import com.zfj.xmy.common.persistence.pojo.Order;
import com.zfj.xmy.common.persistence.pojo.OrderGoods;
import com.zfj.xmy.common.persistence.pojo.User;
import com.zfj.xmy.common.service.CommonUserPointsService;
/**
 * @author lij
 */
@Service
public class AppCommentServiceImpl extends BaseService implements AppCommentService{
	
	@Autowired
	private CommentMapper commentMapper ;
	
	@Autowired
	private CommentImageMapper imageMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private CommonUserPointsService commonUserPointsService;
	
	@Autowired
	private OrderGoodsMapper goodsMapper;
	
	
	/**
	 * 根据商品id查询关于该商品的全部评论
	 */
	@Override
	public List<CommentOutDto> findCommentByGoodsId(long id) {
		List<CommentOutDto> commentOut = new ArrayList<>();
		ReqData reqData = new ReqData();
		//1.根据商品ID查询全部评论
		reqData.putValue("goods_id", id, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("comment_status", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<Comment> comment = commentMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (Comment comment2 : comment) {
			//1.1为扩展实体类赋值
			CommentOutDto commentDto = new CommentOutDto() ;
			commentDto.setAuditTime(comment2.getAuditTime());
			commentDto.setCommentConten(comment2.getCommentConten());
			commentDto.setCommentStar(comment2.getCommentStar());
			commentDto.setUserId(comment2.getUserId());
			commentDto.setGoodsId(comment2.getGoodsId());
			commentDto.setId(comment2.getId());
			commentDto.setCommentStatus(comment2.getCommentStatus());
			//2.根据评论的用户id查询用户信息取出用户名
			User user = userMapper.selectByPrimaryKey(comment2.getUserId());
			commentDto.setUserName(user.getName());
			//3.查询对应的图片路径
			reqData.putValue("comment_id", comment2.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<CommentImage> image = imageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if(!CollectionUtils.isEmpty(image)){
				List<String> imageUrl = new ArrayList<String>();
				for (CommentImage cimage : image) {
					imageUrl.add(cimage.getImagePath());
				}
				commentDto.setCommentImage(imageUrl);
			}
			//4.添加到扩展评论表
			commentOut.add(commentDto);
		}
		//5.返回数据
		return commentOut;
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addComment(CommentInDto commentInDto) {
		Comment comment = new Comment();
		ReqData reqData = new ReqData();
		Date date = new Date();
		//1.判断是什么的评论
		Integer commentType = commentInDto.getCommentType();
		Long orderid = commentInDto.getOrderId();
		if (ObjectUtils.isEmpty(commentType)) {
			throw new BusinessException("评论类型不能为空");
		}
		if(commentType.equals(SystemConstant.Comment.COMMENT_GOODS)){//如果是商品评论
			comment.setCommentType(SystemConstant.Comment.COMMENT_GOODS);
			comment.setGoodsId(commentInDto.getGoodsId());
		}
		if(commentType.equals(SystemConstant.Comment.COMMENT_ORDER)){//如果是订单评论
			Order order = orderMapper.selectByPrimaryKey(orderid);
			if (ObjectUtils.isEmpty(order)) {
				throw new BusinessException("订单不存在");
			}
			if (!SystemConstant.ORDER.STATUS_WAITTING_COMMENT.equals(order.getStatus()) ) {
				throw new BusinessException("該订单当前阶段无法评价或者已评价");
			}
			order.setStatus(SystemConstant.ORDER.STATUS_FINISH);
			orderMapper.updateByPrimaryKeySelective(order);
			comment.setCommentType(SystemConstant.Comment.COMMENT_ORDER);
			comment.setOrderId(orderid);
			
			//为该订单每个商品添加评论
			reqData.putValue("order_id", order.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<OrderGoods> orderGoodslist = goodsMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			reqData.clearValue();
			for (OrderGoods orderGoods : orderGoodslist) {
				Comment mment = new Comment();
				mment.setCommentStar(commentInDto.getCommentStar());
				mment.setCommentStatus(SystemConstant.Comment.COMMENT_AUDIT_WAIT);//待审核
				mment.setUserId(commentInDto.getUserId());
				mment.setCommentConten(commentInDto.getCommentConten());
				mment.setCreateTime(date);
				mment.setCommentType(SystemConstant.Comment.COMMENT_GOODS);
				mment.setGoodsId(orderGoods.getGoodsId());
				commentMapper.insertSelective(mment);
			}
		}
		comment.setUserId(commentInDto.getUserId());
		comment.setCommentConten(commentInDto.getCommentConten());
		comment.setCommentStar(commentInDto.getCommentStar());
		comment.setCreateTime(date);
		comment.setCommentStatus(SystemConstant.Comment.COMMENT_AUDIT_WAIT);//新增评论的审核状态为待审核
		commentMapper.insertSelective(comment);

		//2.添加到评论图片表
		String imageStr = commentInDto.getImgaePath();
		if (!StringUtil.isEmpty(imageStr)) {
			String[] image = imageStr.split(",");
			for (String path : image) {
				CommentImage commentImage = new CommentImage();
				commentImage.setCommentId(comment.getId());
				commentImage.setImagePath(path.trim());//去除空格后添加
				imageMapper.insertSelective(commentImage);
			}
		}
		//添加评论积分
		if(!ObjectUtils.isEmpty(commentInDto.getUserId())){
			if(commentInDto.getUserId()!=0){
				commonUserPointsService.updateUserCommnetPoints(commentInDto.getUserId(), commentInDto.getOrderId(), commentInDto.getCommentStar());
			}
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void defComment(CommentInDto commentInDto) {
		commentInDto.setCommentType(SystemConstant.Comment.COMMENT_ORDER);
		commentInDto.setCommentConten("");
		commentInDto.setCommentStar(5);//5星
		this.addComment(commentInDto);
	}
	@Override
	public Map<String, Object> findCommentScaleByGoodsId(long id) {
		ReqData reqData = new ReqData();
		Map<String, Object>  map = new HashMap<String, Object>();
		Integer good = 0 ;
		Integer bad = 0 ; 
		//1.查询该商品的全部评论
		reqData.putValue("goods_id", id, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("comment_status", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<Comment> comments = commentMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		//2.计算好评数和差评数，4星或4星以上为好评，3星或3星一下为差评
		for (Comment comment : comments) {
			if(comment.getCommentStar() >=4){
				good += 1;
			}else{
				bad += 1;
			}
		}
		
		map.put("goodComment", good);
		map.put("badComment", bad);
		
		return map;
	}
	@Override
	public List<CommentOutDto> findCommentByUserId(long id) {
		List<CommentOutDto> commentOut = new ArrayList<>();
		ReqData reqData = new ReqData();
		//1.根据用户ID查询全部评论
		reqData.putValue("user_id", id, SystemConstant.REQ_PARAMETER_EQ);
		reqData.putValue("comment_status", 0, SystemConstant.REQ_PARAMETER_EQ);
		List<Comment> comment = commentMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		reqData.clearValue();
		for (Comment comment2 : comment) {
			//1.1为扩展实体类赋值
			CommentOutDto commentDto = new CommentOutDto() ;
			commentDto.setAuditTime(comment2.getAuditTime());
			commentDto.setCommentConten(comment2.getCommentConten());
			commentDto.setCommentStar(comment2.getCommentStar());
			commentDto.setUserId(comment2.getUserId());
			commentDto.setGoodsId(comment2.getGoodsId());
			commentDto.setId(comment2.getId());
			commentDto.setCommentStatus(comment2.getCommentStatus());
			//2.根据评论的用户id查询用户信息取出用户名
			User user = userMapper.selectByPrimaryKey(comment2.getUserId());
			commentDto.setUserName(user.getName());
			//3.查询对应的图片路径
			reqData.putValue("comment_id", comment2.getId(), SystemConstant.REQ_PARAMETER_EQ);
			List<CommentImage> image = imageMapper.selectByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
			if(!CollectionUtils.isEmpty(image)){
				List<String> imageUrl = new ArrayList<String>();
				for (CommentImage cimage : image) {
					imageUrl.add(cimage.getImagePath());
				}
				commentDto.setCommentImage(imageUrl);
			}
			//4.添加到扩展评论表
			commentOut.add(commentDto);
		}
		//5.返回数据
		return commentOut;
		
	}
	
}