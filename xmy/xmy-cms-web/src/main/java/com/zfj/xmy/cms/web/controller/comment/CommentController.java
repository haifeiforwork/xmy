package com.zfj.xmy.cms.web.controller.comment;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.zfj.xmy.comment.service.cms.CommentService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.Comment;

/**
 * @author lij
 *
 */
@RequestMapping("/comment")
@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	/**
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月21日 下午1:59:53
	 * 查询评论总页数
	 */
	@RequestMapping("/audit")
	public ModelAndView findCommentSumPage(ReqData reqData,HttpServletRequest request){
		int count = commentService.findCommentCount(reqData);
		int countPage;
		if (count % 10 == 0) {
			countPage = count / 10;
		} else {
			countPage = count / 10 + 1;
		}
		request.setAttribute("countPage", countPage);
		return new ModelAndView("/comment/comment_audit_list");
	}
	/**
	 * @param pageIndex
	 * @param reqData
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月21日 下午2:00:20
	 * 分页查询全部评论
	 */
	@RequestMapping("/list/{pageIndex}")
	public ModelAndView findAllComment(@PathVariable("pageIndex") Integer pageIndex,ReqData reqData,HttpServletRequest request){
		reqData.setPageIndex(pageIndex);
		reqData.setPageSize(10);
		reqData.setSortname("id");
		reqData.setSortorder("desc");
		List<Comment> findAllComment = commentService.findAllComment(reqData);
		request.setAttribute("commentList", findAllComment);
		//return findAllComment;
		return new ModelAndView("/comment/comment_list_page");
	}
	/**
	 * @param id
	 * @param status
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月21日 下午2:00:38
	 * 根据评论id和审核状态修改评论状态
	 */
	@RequestMapping("update/{id}/{status}")
	public ModelAndView updateOneCommentById(@PathVariable("id") Integer id, @PathVariable("status") Integer status){
		commentService.updateCommentById(id, status);
		
		return new ModelAndView(new RedirectView("/comment/audit"));
	}
	/**
	 * @param commentIds
	 * @param status
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月21日 下午2:30:31
	 * 批量修改评论状态
	 */
	@RequestMapping("updateComments/{commentIds}/{status}")
	public ModelAndView updateAllCommentByIds(@PathVariable("commentIds") String commentIds,
			@PathVariable("status") Integer status){
		commentService.updateCommentsByIds(commentIds, status);
		return new ModelAndView(new RedirectView("/comment/audit"));
	}
	/**
	 * @param commentId
	 * @param request
	 * @return ModelAndView
	 * @author lij
	 * @date 2017年7月21日 下午4:11:59
	 * 查询单个评论详细信息
	 */
	@RequestMapping("/detail/{commentId}")
	public ModelAndView queryCommentDetail(@PathVariable("commentId") Integer commentId,HttpServletRequest request){
		Map<String, Object> commentDetail = commentService.getCommentDetail(commentId);
		request.setAttribute("commentMap", commentDetail);
		return new ModelAndView("/comment/comment_detail");
	}
}
