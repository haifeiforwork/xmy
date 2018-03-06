package com.zfj.xmy.activity.service.cms;
import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.dao.BuyAndPresentMapper;
import com.zfj.xmy.common.persistence.pojo.BuyAndPresent;

public interface BuyAndPresentService {

	/**
	 * 
	 * @Description 新增一条买即赠促销 
	 * @param buyAndPresent
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日上午9:38:53
	 */
	int insert(BuyAndPresent buyAndPresent);
	
	/**
	 * 
	 * @Description 查询出符号条件的买即赠记录（分页显示） 
	 * @param reqData
	 * @param pageBean
	 * @Author liuw
	 * @Date 2017年7月12日上午10:12:32
	 */
	void findBuyAndPresentS(ReqData reqData,PageBean pageBean);
	
	/**
	 * 
	 * @Description 根据ID查询单条记录 
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午2:11:42
	 */
	BuyAndPresent findBuyAndPresent(Object id);
	/**
	 * 
	 * @Description  查询出符号条件的买即赠记录（没有分页） 
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午2:58:22
	 */
	List<BuyAndPresent> findBuyAndPresentS(ReqData reqData);
	/**
	 * 
	 * @Description 更新一条买即赠信息
	 * @param buyAndPresent
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午4:31:38
	 */
	int updateBuyAndPresent(BuyAndPresent buyAndPresent);
	
	/**
	 * 
	 * @Description 根据ID删除一条买即赠信息 
	 * @param string
	 * @return
	 * @Author liuw
	 * @Date 2017年7月12日下午7:27:55
	 */
	int deleteByPrimaryKey(Object string);
}
