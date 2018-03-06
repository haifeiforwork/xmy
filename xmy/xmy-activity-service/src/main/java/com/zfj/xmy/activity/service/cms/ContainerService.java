package com.zfj.xmy.activity.service.cms;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.AdImage;
import com.zfj.xmy.common.persistence.pojo.Container;

public interface ContainerService {

	/**
	 * 根据货柜id获取到所有货柜
	 * @Description 
	 * @param ids
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日上午11:15:22
	 */
	List<Container> findsContainersWithContainerIds(List<Object> ids);

	/**
	 * 根据货柜id取得单条货柜信息
	 * @Description 
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日下午2:56:07
	 */
	Container getContainersById(Long id);

	/**
	 * 根据主键有条件性的更新货柜
	 * @Description 
	 * @param container
	 * @Author liuw
	 * @Date 2017年8月14日下午3:08:21
	 */
	void updateContainer(Container container);

	/**
	 * 根据id字符串，或得到所有的货柜
	 * @Description 
	 * @param containerIds id字符串集合，以逗号隔开，<br>
	 * 		eg.	containerIds="20,10,40,"
	 * @return
	 * @Author liuw
	 * @Date 2017年8月15日下午2:25:55
	 */
	List<Container> findsContainerWithContainerIdString(String containerIds);



}
