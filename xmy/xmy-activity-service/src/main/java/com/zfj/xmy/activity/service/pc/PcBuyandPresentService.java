package com.zfj.xmy.activity.service.pc;

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.persistence.pc.pojo.dto.PcBuyandPresentDto;

public interface PcBuyandPresentService {
	/**
	 * @param pageBean
	 * @return List<PcBuyandPresentDto>
	 * @author lij
	 * @date 2017年8月19日 上午9:30:50
	 * 分页查询买即赠活动的信息
	 */
	List<PcBuyandPresentDto> findPcBuyandPresentDto(PageBean pageBean);
	
}
