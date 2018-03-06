package com.zfj.xmy.activity.service.cms;

import java.util.List;

import com.zfj.xmy.common.persistence.pojo.Container;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;

public interface IndexSettingService {

	/**
	 * 插入货柜
	 * @Description 
	 * @param container
	 * @Author liuw
	 * @Date 2017年8月11日下午6:03:00
	 */
	void insert(Container container);

	/**
	 * 插入首页配置
	 * @Description 
	 * @param indexConfig
	 * @Author liuw
	 * @Date 2017年8月12日下午2:56:40
	 */
	void insert(IndexConfig indexConfig);

	/**
	 * 获得首页配置
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日上午10:10:26
	 */
	IndexConfig getIndexConfig();

	/**
	 * 根据首页配置获取到id集合
	 * @Description 
	 * @param indexConfig
	 * @return
	 * @Author liuw
	 * @Date 2017年8月14日上午11:36:35
	 */
	List<Object> parseIndexConfigToList(IndexConfig indexConfig);

	/**
	 * 更新首页配置
	 * @Description 
	 * @param indexConfig
	 * @Author liuw
	 * @Date 2017年8月14日下午2:00:25
	 */
	void updateIndexConfig(IndexConfig indexConfig);

	/**
	 * 获取PC首页配置
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月25日下午4:05:22
	 */
	IndexConfig getPCIndexConfig();

	/**
	 * 获取app精选水果配置
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月30日上午10:18:22
	 */
	IndexConfig getChoiceFruitSetting();

	/**
	 * 获取app跨境专区配置
	 * @Description 
	 * @return
	 * @Author liuw
	 * @Date 2017年8月31日上午10:15:20
	 */
	IndexConfig getCrossBorderSetting();

	/**
	 * 根据id查询单条配置
	 * @Description 
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年9月1日下午3:38:08
	 */
	IndexConfig getIndexConfigById(Long id);

}
