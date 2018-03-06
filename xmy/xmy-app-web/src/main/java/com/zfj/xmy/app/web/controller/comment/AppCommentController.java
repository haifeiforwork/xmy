package com.zfj.xmy.app.web.controller.comment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfj.base.commons.mini.annotation.Exclusion;
import com.zfj.xmy.app.web.common.AppLocalInfo;
import com.zfj.xmy.app.web.common.AppResp;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentInDto;
import com.zfj.xmy.comment.persistence.app.pojo.dto.CommentOutDto;
import com.zfj.xmy.comment.service.app.AppCommentService;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.pojo.Comment;

@RestController
@RequestMapping(value="/comment",params=SystemConstant.MOBILE_VERSION_V10)
public class AppCommentController {

	@Autowired
	private AppCommentService appCommentService;
	
	/**
	 * @param goodsId
	 * @return ResponseEntity<List<CommentOutDto>>
	 * @author lij
	 * @date 2017年7月28日 下午4:42:21
	 * 根据商品id查询关于商品的全部评论
	 */
	@RequestMapping(value="/findCommentByGoodsId",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> findCommentByGoodsId(@RequestBody Comment comment){
		
		List<CommentOutDto> findCommentByGoodsId = appCommentService.findCommentByGoodsId(comment.getGoodsId());
		
		return AppResp.get(findCommentByGoodsId);
	}
	/**
	 * @param userId
	 * @return ResponseEntity<List<CommentOutDto>>
	 * @author lij
	 * @date 2017年7月28日 下午5:00:41
	 * 根据用户id查询改用户的所有评论
	 */
	@RequestMapping(value="/findCommentByUserId",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> findCommentByUserId(@RequestBody Comment comment){
		
		List<CommentOutDto> findCommentByUserId = appCommentService.findCommentByUserId(comment.getUserId());
		
		return AppResp.get(findCommentByUserId);
	}
	
	/**
	 * @param goodsId
	 * @return ResponseEntity<Map<String,Object>>
	 * @author lij
	 * @date 2017年7月28日 下午5:14:06
	 * 根据商品id查询商品的好评和差评的数量
	 */
	@RequestMapping(value="/findCommentScaleByGoodsId",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> findCommentScaleByGoodsId(@RequestBody Comment comment){
		
		Map<String, Object> scaleByGoodsId = appCommentService.findCommentScaleByGoodsId(comment.getGoodsId());
		
		return AppResp.get(scaleByGoodsId);
	}
	
	/**
	 * @param commentInDto
	 * @return ResponseEntity
	 * @author lij
	 * @date 2017年7月28日 下午5:23:49
	 * 添加评论内容/评价
	 */
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	@Exclusion
	public ResponseEntity<AppResp> addComment(@RequestBody CommentInDto commentInDto){
		appCommentService.addComment(commentInDto);
		return AppResp.get();
	}
	
	/**
	 * 默认好评
	 * @param commentInDto
	 * @return
	 * @Description 
	 * @date 2017年8月1日  下午5:59:34
	 * @author wy
	 * 2017
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/defComment",method=RequestMethod.POST)
	public ResponseEntity<AppResp> defaultComment(@RequestBody CommentInDto commentInDto){
		Long userid = AppLocalInfo.getUserId();
		commentInDto.setUserId(userid);
		appCommentService.defComment(commentInDto);
		return AppResp.get();
	}
}
