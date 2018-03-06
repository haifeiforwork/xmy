package com.zfj.xmy.common.service;

import java.util.List;

import com.appdev.db.common.CriteriaParameter;
import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.persistence.pojo.JiguangPushLabel;

public interface CommonPushLabelService {

	/**
	 * 获取标签数据
	 * @param para
	 * @param pageBean
	 * @return
	 * @Description 
	 * @date 2018年1月8日  下午2:56:21
	 * @author wy
	 * 2018
	 * @return List<JiguangPushLabel>
	 */

	List<JiguangPushLabel> getJiguangPushLabelList(CriteriaParameter para,
			PageBean pageBean);

	/**
	 * 添加标签
	 * @param labelName
	 * @Description 
	 * @date 2018年1月8日  下午2:56:38
	 * @author wy
	 * 2018
	 * @return void
	 */
	void addLabel(String labelName);

	/**
	 * 修改标签
	 * @param labelName
	 * @Description 
	 * @date 2018年1月8日  下午3:24:38
	 * @author wy
	 * 2018
	 * @return void
	 */

	void modifyLabel(Long id, String labelName);

	/**
	 * 删除标签
	 * @param id
	 * @Description 
	 * @date 2018年1月8日  下午3:47:59
	 * @author wy
	 * 2018
	 * @return void
	 */
	void delLabel(Long id);

	/**
	 * 给用户绑定标签
	 * @param userid
	 * @param labelid
	 * @Description 
	 * @date 2018年1月23日  下午1:47:40
	 * @author wy
	 * 2018
	 * @return void
	 */
	void bindLabelToUser(Long userid, Long labelid);
	/**
	 * 给用户解除绑定标签 操作
	 * @param userid
	 * @param labelid
	 * @Description 
	 * @date 2018年1月26日  上午10:21:14
	 * @author wy
	 * 2018
	 * @return void
	 */
    void unbindLabelToUser(Long userid, Long labelid);
	/**
	 * 获取标签数据 
	 * @param search 搜索关键字
	 * @param pageBean
	 * @return
	 * @Description 
	 * @date 2018年1月24日  下午5:24:32
	 * @author wy
	 * 2018
	 * @return List<JiguangPushLabel>
	 */
	List<JiguangPushLabel> getJiguangPushLabelList(String search,
			PageBean pageBean);

	/**
	 * 获取所有标签数据
	 * @return
	 * @Description 
	 * @date 2018年1月25日  下午1:47:20
	 * @author wy
	 * 2018
	 * @return List<JiguangPushLabel>
	 */
	List<JiguangPushLabel> getAllJiguangPushLabelList();

	
}
