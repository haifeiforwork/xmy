package com.zfj.xmy.goods.persistence.pc.dao;  

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppAdImageDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppEnterpriseDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppGoodsDir;
import com.zfj.xmy.goods.persistence.app.pojo.dto.AppTermDataDir;
import com.zfj.xmy.goods.persistence.common.pojo.dto.GoodsDto;

/** 
 * @Title: AppTermDataExMapper.java 
 * @Package com.zfj.xmy.goods.persistence.app.dao 
 * @Description: 
 * @author lij
 * @date 2017年8月24日 上午10:32:24 
 */
public interface PcWrodExMapper {
	/**
	 * @param parameter
	 * @return List<String>
	 * @author lij
	 * @date 2017年8月24日 下午2:37:14
	 * 筛选条件查询父级名称
	 */
	List<String> findTowName(CriteriaParameter parameter);
}
  