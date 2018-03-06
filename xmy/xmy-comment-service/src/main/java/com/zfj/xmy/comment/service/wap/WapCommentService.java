package com.zfj.xmy.comment.service.wap;

import java.util.List;

import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentInDto;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentListOutDto;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentOutDto;

public interface WapCommentService {
	
	Integer addComment(CommentInDto dto);

	List<CommentOutDto> findComment(long id);

	List<CommentOutDto> findCommentByUserId(long id);

	CommentListOutDto findCommentsByGoodsId(Long goodsId);
	
}
