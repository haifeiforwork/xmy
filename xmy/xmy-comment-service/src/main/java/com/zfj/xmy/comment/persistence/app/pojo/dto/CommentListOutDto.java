package com.zfj.xmy.comment.persistence.app.pojo.dto;

import java.util.Date;
import java.util.List;

public class CommentListOutDto {
	
	//商品id
	private Long goodsId;
	
	//user_info表中对应的姓名字段
	private String realName;
	
	//手机
	private String mobilePhone;
	
	//评论内容
	private String commentConten;
	
	//星级
	private Integer commentStar;
	
	//TODO 差评、中评、好评（暂不使用）
	private String commentRank;
	
	//创建时间
	private Date createTime;
	
	//总星级
	private Integer level;
	
	//百分比
	private Integer rank;
	
	private Integer starSum;
	
	//评论们
	private List<CommentListOutDto> comments;
	
	public CommentListOutDto() {}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getCommentStar() {
		return commentStar;
	}

	public void setCommentStar(Integer commentStar) {
		this.commentStar = commentStar;
	}

	public String getCommentRank() {
		return commentRank;
	}

	public void setCommentRank(String commentRank) {
		this.commentRank = commentRank;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCommentConten() {
		return commentConten;
	}

	public void setCommentConten(String commentConten) {
		this.commentConten = commentConten;
	}

	public List<CommentListOutDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentListOutDto> comments) {
		this.comments = comments;
	}

	public Integer getStarSum() {
		return starSum;
	}

	public void setStarSum(Integer starSum) {
		this.starSum = starSum;
	}
	
	
}