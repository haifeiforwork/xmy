package com.zfj.xmy.activity.service.cms;

import java.util.Map;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.persistence.pojo.TopicPage;

public interface TopicPageService {

	/**
	 * 保存专题页面
	 * @Description 
	 * @param topicPage
	 * @Author liuw
	 * @Date 2017年8月28日下午4:17:37
	 */
	void saveTopicPage(TopicPage topicPage);

	/**
	 * 专题页面解压
	 * @Description 
	 * @param file
	 * @param saveRootDirectory
	 * @return
	 * @Author liuw
	 * @Date 2017年8月28日下午4:25:13
	 */
	Map<String, Object> unZipFile(String file, String saveRootDirectory);

	/**
	 * 根据条件查询记录数
	 * @Description 
	 * @param reqData
	 * @return
	 * @Author liuw
	 * @Date 2017年8月29日上午11:32:23
	 */
	int countTopicPage(ReqData reqData);

	/**
	 * 查询专题页面记录（分页显示）
	 * @Description 
	 * @param reqData
	 * @param pageBean
	 * @Author liuw
	 * @Date 2017年8月29日上午11:33:34
	 */
	void findTopicPages(ReqData reqData, PageBean pageBean);

	/**
	 * 删除单条专题记录
	 * @Description 
	 * @param id
	 * @Author liuw
	 * @Date 2017年8月29日下午3:17:25
	 */
	void deleteTopicPage(Long id);
}
