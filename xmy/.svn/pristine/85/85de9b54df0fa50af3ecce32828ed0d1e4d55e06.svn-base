package com.zfj.xmy.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfj.xmy.common.persistence.dao.LogMapper;
import com.zfj.xmy.common.persistence.pojo.Log;
import com.zfj.xmy.common.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logMapper;

	@Override
	public void createLog(Log log) {
		// TODO Auto-generated method stub
		logMapper.insertSelective(log);
	}

	@Override
	public void updateLog(Log log) {
		// TODO Auto-generated method stub
		logMapper.updateByPrimaryKeySelective(log);
	}
}
