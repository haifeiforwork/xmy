package com.zfj.xmy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.xiaoleilu.hutool.util.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.zfj.base.exception.BusinessException;
import com.zfj.xmy.common.persistence.dao.ClientVersionMapper;
import com.zfj.xmy.common.persistence.pojo.ClientVersion;
import com.zfj.xmy.common.service.CommonVersionService;

@Service
public class CommonVersionServiceImpl implements CommonVersionService {

	@Autowired
	private ClientVersionMapper clientVersionMapper;
	
	@Override
	public ClientVersion get(){
		CriteriaParameter parameter = new CriteriaParameter();
		List<ClientVersion> clientVersions = clientVersionMapper.selectByExampleWithBLOBs(parameter);
		if (CollectionUtil.isEmpty(clientVersions)) {
			throw new BusinessException("参数未配置");
		}
		return clientVersions.get(0);
	}

	@Override
	public void update(ClientVersion clientVersion) {
		ClientVersion targetClientVersion = this.get();
		BeanUtil.copyProperties(clientVersion, targetClientVersion, new String[]{"id","addtime"});
		clientVersionMapper.updateByPrimaryKeyWithBLOBs(targetClientVersion);
	}
}
