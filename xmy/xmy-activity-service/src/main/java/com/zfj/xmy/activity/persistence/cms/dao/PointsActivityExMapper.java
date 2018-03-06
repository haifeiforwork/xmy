package com.zfj.xmy.activity.persistence.cms.dao;  

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PointsActivityDto;

/** 
 * @Title: PointsActivityExMapper.java 
 * @Package com.zfj.xmy.activity.persistence.dao 
 * @Description: 
 * @author zhangh
 * @date 2017年7月12日 下午7:21:31 
 */
public interface PointsActivityExMapper {
	
	List<PointsActivityDto> findList(CriteriaParameter parameter,RowBounds rowBounds);

}
  