package com.zfj.xmy.activity.service.pc;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcLimitActivityDto;
import com.zfj.xmy.common.persistence.pojo.LimitGoods;

public interface PcLimitActivityService {
	/**
	 * @param limitType 限时活动类型
	 * @return PcLimitActivityDto
	 * @author lij
	 * @date 2017年8月14日 下午4:32:36
	 * 根据限时活动类型查询活动信息
	 */
	List<PcLimitActivityDto> findActivityGoods(Integer limitType,PageBean pageBean);
}
