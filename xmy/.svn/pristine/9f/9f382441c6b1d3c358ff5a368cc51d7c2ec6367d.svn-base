package com.zfj.xmy.wap.web.controller.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfj.base.util.common.StringUtil;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentInDto;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentListOutDto;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentOutDto;
import com.zfj.xmy.comment.service.app.FeedbackService;
import com.zfj.xmy.comment.service.wap.WapCommentService;
import com.zfj.xmy.common.persistence.pojo.Feedback;
import com.zfj.xmy.order.persistence.wap.pojo.dto.WapCommentInDto;
import com.zfj.xmy.wap.web.common.AjaxResult;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private WapCommentService commentService;
	
	@Autowired
	private FeedbackService feedBackService;
	
	@RequestMapping("/toAddComment")
	public String toComment(WapCommentInDto userIdAndGoodsId, ModelMap model) {
		model.addAttribute("data", userIdAndGoodsId);
		return "mine/comment";
	}
	
	@RequestMapping("/addFeedBack")
	@ResponseBody
	public AjaxResult addFeedBack(Feedback feedBack) {
		if(!StringUtil.isMatch("^1[3|4|5|7|8]\\d{9}$", feedBack.getPhoneNum()))
			return new AjaxResult(0, "手机号格式错误", null);
		feedBackService.insertFeedback(feedBack);
		return new AjaxResult(1, null, null);
	}
	
	@RequestMapping("/addComment")
	@ResponseBody
	public AjaxResult addComment(WapCommentInDto dto) {
		if(dto.getOrderId() == null || dto.getGoodsId() == null || dto.getGoodsId().length == 0)
			return new AjaxResult(0, "服务器繁忙", null);
		ArrayList<CommentInDto> list = new ArrayList<CommentInDto>();
		for(Long goodsId : dto.getGoodsId()) {
			CommentInDto comment = new CommentInDto();
			comment.setCommentType(1);
			comment.setGoodsId(goodsId);
			comment.setCommentStar(dto.getStarLevel() == null ? 5 : dto.getStarLevel());
			comment.setUserId(dto.getUserId());
			comment.setCommentConten(dto.getDetail());
			//因暂无添加图片功能，故设置null
			comment.setImgaePath(null);
			Integer addComment = commentService.addComment(comment);
			switch(addComment) {
			case 0 :
				return new AjaxResult(0, "评论内容不能为空", null);
			case -1 :
				return new AjaxResult(-1, "服务器繁忙", null);
			}
		}
		
		CommentInDto orderComment = new CommentInDto();
		orderComment.setCommentType(0);
		orderComment.setUserId(dto.getUserId());
		orderComment.setCommentStar(dto.getStarLevel() == null ? 5 : dto.getStarLevel());
		orderComment.setOrderId(dto.getOrderId());
		orderComment.setCommentConten(dto.getDetail());
		//因暂无添加图片功能，故设置null
		orderComment.setImgaePath(null);
		Integer addComment = commentService.addComment(orderComment);
		switch(addComment) {
		case 0 :
			return new AjaxResult(0, "评论内容不能为空", null);
		case -1 :
			return new AjaxResult(-1, "服务器繁忙", null);
		}
		
		return new AjaxResult(1, "评论成功", null);
	}
	
	@RequestMapping("/findComment/{goodsId}")
	@ResponseBody
	public AjaxResult findComment(@PathVariable("goodsId") Long goodsId) {
		List<CommentOutDto> findComment = commentService.findComment(goodsId);
		return new AjaxResult(1, null, findComment);
	}
	
	@RequestMapping("/{id}")
	public String comment(@PathVariable("id") Long id, ModelMap model) {
		CommentListOutDto vo = commentService.findCommentsByGoodsId(id);
		model.addAttribute("comments", vo);
		return "goods/commentList";
	}
	
	
	
	
}
