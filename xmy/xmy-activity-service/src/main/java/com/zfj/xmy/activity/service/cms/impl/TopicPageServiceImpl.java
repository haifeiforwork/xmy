package com.zfj.xmy.activity.service.cms.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.xmy.activity.service.cms.TopicPageService;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.persistence.dao.TopicPageMapper;
import com.zfj.xmy.common.persistence.pojo.TopicPage;
import com.zfj.xmy.util.DecompressionUtil;

@Service
public class TopicPageServiceImpl implements TopicPageService {
	@Autowired
	private TopicPageMapper topicPageMapper;
	@Override
	public void saveTopicPage(TopicPage topicPage){
		topicPageMapper.insertSelective(topicPage);
	}
	@Override
	public Map<String,Object> unZipFile(String file, String saveRootDirectory){
		Map<String, Object> zipFileRead = DecompressionUtil.zipFileRead(file, saveRootDirectory);
		return zipFileRead;
	}
	@Override
	public int countTopicPage(ReqData reqData){
		int countByExample = topicPageMapper.countByExample(ReqUtil.reqParameterToCriteriaParameter(reqData));
		return countByExample;
	}
	@Override
	public void findTopicPages(ReqData reqData,PageBean pageBean){
		List<TopicPage> selectByExampleAndPage = topicPageMapper.selectByExampleAndPage(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		pageBean.setData(selectByExampleAndPage);
		pageBean.setTotalCount(this.countTopicPage(reqData));
	}
	@Override
	public void deleteTopicPage(Long id){
		topicPageMapper.deleteByPrimaryKey(id);
	}
}
