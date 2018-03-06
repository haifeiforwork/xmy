package com.zfj.xmy.comment.service.app;

import java.util.List;
import java.util.Map;

import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentInDto;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentOutDto;

/**
 * @author lij
 */
public interface AppCommentService {
	
	/**
	 * @param id
	 * @return List<CommentOutDto>
	 * @author lij
	 * @date 2017年7月24日 下午4:09:23
	 * 根据商品id查询出相关评论
	 */
	List<CommentOutDto> findCommentByGoodsId(long id) ;
	/**
	 * @param userId
	 * @param id
	 * @param type
	 * @param content
	 * @param start
	 * @param imagePath void
	 * @author lij
	 * @date 2017年7月24日 下午5:06:52
	 * 新增评论接口
	 */
	void addComment(CommentInDto commentInDto) ;
	/**
	 * @param id
	 * @return Map<String,Object>
	 * @author lij
	 * @date 2017年7月24日 下午7:48:04
	 * 根据商品id查询商品评论的好评和差评的比例
	 */
	Map<String, Object> findCommentScaleByGoodsId(long id) ;
	/**
	 * @param id
	 * @return List<CommentOutDto>
	 * @author lij
	 * @date 2017年7月24日 下午8:25:35
	 * 根据用户id查询出该用户下的所有评价
	 */
	List<CommentOutDto> findCommentByUserId(long id) ;
	/**
	 * 默认好评
	 * @param commentInDto
	 * @return
	 * @Description 
	 * @date 2017年8月1日  下午7:24:31
	 * @author wy
	 * 2017
	 * @return String
	 */
	void defComment(CommentInDto commentInDto);
}
