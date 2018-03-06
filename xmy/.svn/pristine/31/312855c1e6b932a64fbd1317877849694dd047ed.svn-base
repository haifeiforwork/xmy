package com.zfj.xmy.activity.service.wap;

import java.util.List;

import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcDownColumDto;

/**
 * @author lij
 */
public interface WapColumService {
	
	/**
	 * 查询本级分类
	 * @param vid
	 * @return
	 */
	List<PcDownColumDto> findParentColum(int vid);
	
	/**
	 * 查询子分类
	 * @param parentId
	 * @return
	 */
	List<PcDownColumDto> findChildrenColum(String parentId, int vid);

}
