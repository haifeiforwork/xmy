package com.zfj.xmy.activity.persistence.pc.dao;

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;

/**
 * @author lij
 * 查询底下栏目
 */
public interface PcTermDataExMapper {
	/**
	 * @param parameter
	 * @return List<PcDownColumDto>
	 * @author lij
	 * @date 2017年8月10日 下午7:45:28
	 * 查询底部栏目的
	 */
	List<PcDownColumDto> selectDownColum(CriteriaParameter parameter);
	
}
