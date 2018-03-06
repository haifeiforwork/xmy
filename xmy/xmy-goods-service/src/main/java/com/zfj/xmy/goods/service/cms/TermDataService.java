package com.zfj.xmy.goods.service.cms; 

import java.util.List;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.TermData;

/** 
 * @Title: TermDataService.java 
 * @Package com.zfj.xmy.goods.service 
 * @Description:  
 * @author hexw
 * @date 2017年6月23日 上午9:52:16 
 */
public interface TermDataService {
	
	/**
	 * 插入记录
	 * @param termData    
	 * @return void    
	 * Date:2017年6月23日 上午10:01:40
	 */
	void insertTermData(TermData termData);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return    
	 * @return TermData    
	 * Date:2017年6月23日 上午10:02:01
	 */
	TermData getTermData(long id);
	
	/**
	 * 查询列表
	 * @param reqData
	 * @param vid
	 * @return    
	 * @return List<TermData>    
	 * Date:2017年6月23日 上午10:02:31
	 */
	void selectTermDataList(ReqData reqData, long vid ,PageBean pageBean);
	
	/**
	 * 批量修改状态
	 * @param idArry
	 * @param status
	 * @return    
	 * @return int    
	 * Date:2017年6月23日 上午10:02:48
	 */
	int updateStatus(String[] idArry, String status);
	
	/**
	 * 修改实体
	 * @param termData    
	 * @return void    
	 * Date:2017年6月23日 上午10:03:08
	 */
	void updateTermData(TermData termData);
	
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return    
	 * @return int    
	 * Date:2017年6月23日 上午10:14:09
	 */
	int updateStatus(long id, String status);
	
	/**
	 * 获得总的记录数
	 * @param reqData
	 * @return    
	 * @return int    
	 * Date:2017年6月23日 下午3:52:25
	 */
	int getCountTermData(ReqData reqData);

	/**
	 * 根据vid查询
	 * @param vid
	 * @return    
	 * @return List<TermData>    
	 * Date:2017年6月23日 下午5:07:08
	 */
	List<TermData> selectTermDataByVid(ReqData reqData);
	
	
	/**
	 * 分页查询
	 * @param reqData
	 * @param pageBean    
	 * @return void    
	 * Date:2017年6月26日 上午10:49:21
	 */
	void findTerm(ReqData reqData,PageBean pageBean);
	
	/**
	 * 
	 * 根据主键删除（物理删除）
	 * @param string
	 * @return    
	 * @return int    
	 * Date:2017年6月26日 上午10:50:42
	 */
	int deleteByPrimaryKey(Object string); 
	/**
	 * 
	 * @Description  查询包含text类型的总记录数
	 * @param reqData
	 * @param pageBean
	 * @Author liuw
	 * @Date 2017年6月26日下午3:52:21
	 */
	void findTermWithBlob(ReqData reqData,PageBean pageBean);

}
  