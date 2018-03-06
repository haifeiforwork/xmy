package com.zfj.xmy.order.persistence.common.dao;  

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.order.persistence.common.pojo.dto.AdInfoDto;

/** 
 * @Title: AdInfoExMapper.java 
 * @Package com.zfj.xmy.order.persistence.dao 
 * @Description: 
 * @author hexw
 * @date 2017年7月12日 下午5:59:29 
 */
public interface AdInfoExMapper {
	
	/**
	 * 查询广告集合
	 * @param parameter
	 * @param rowBounds
	 * @return    
	 * @return List<AdInfoDto>    
	 * Date:2017年7月12日 下午8:23:15
	 */
	List<AdInfoDto> findAdInfo(CriteriaParameter parameter,RowBounds rowBounds);
	
	/**
	 * 根据id得到广告
	 * @param parameter
	 * @return    
	 * @return AdInfoDto    
	 * Date:2017年7月12日 下午8:24:00
	 */
	AdInfoDto getAdInfo(CriteriaParameter parameter);
	/**
	 * 
	 * @Description 根据条件取出单条Adinfo和image的扩展信息
	 * @param parameter
	 * @return
	 * @Author liuw
	 * @Date 2017年7月17日下午8:56:49
	 */
	AdInfoDto getAdInfoImage(CriteriaParameter parameter);
}
  