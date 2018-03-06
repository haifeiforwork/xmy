package com.zfj.xmy.common.persistence.pojo;

import javax.persistence.Column;
public class GoodsWithBLOBs extends Goods {

    /**
     * goods.pc_introduced (pc详细介绍)
     * @ibatorgenerated 2017-08-22 09:57:46
     */
    @Column(name = "pc_introduced")
    private String pcIntroduced;

    /**
     * 新增加的收藏夹id，返回收藏夹商品的时候有用，liuw 
     */
    private Long collectionId;
    
    //活动名字
    private String activityName;
    //限量多少个
    private Integer limitTotalNum;
    
    private Integer activityType;//活动类型
	private Long activityId;//活动id
    
    public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getLimitTotalNum() {
		return limitTotalNum;
	}

	public void setLimitTotalNum(Integer limitTotalNum) {
		this.limitTotalNum = limitTotalNum;
	}

	public Long getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}

	/**
     * goods.ph_introduced (手机详细介绍)
     * @ibatorgenerated 2017-08-22 09:57:46
     */
    @Column(name = "ph_introduced")
    private String phIntroduced;

    public String getPcIntroduced() {
        return pcIntroduced;
    }

    public void setPcIntroduced(String pcIntroduced) {
        this.pcIntroduced = pcIntroduced;
    }

    public String getPhIntroduced() {
        return phIntroduced;
    }

    public void setPhIntroduced(String phIntroduced) {
        this.phIntroduced = phIntroduced;
    }
}