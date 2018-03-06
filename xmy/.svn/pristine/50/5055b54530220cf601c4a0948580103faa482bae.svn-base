package com.zfj.xmy.activity.service.cms.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.db.common.CriteriaParameter;
import com.zfj.xmy.activity.persistence.cms.pojo.dto.PcSecondLevelClassificationDto;
import com.zfj.xmy.activity.service.cms.PcSecondLevelClassificationService;
import com.zfj.xmy.common.persistence.dao.IndexConfigMapper;
import com.zfj.xmy.common.persistence.dao.PcSecondLevelClassificationMapper;
import com.zfj.xmy.common.persistence.pojo.IndexConfig;
import com.zfj.xmy.common.persistence.pojo.PcSecondLevelClassification;

@Service
public class PcSecondLevelClassificationServiceImpl implements 	PcSecondLevelClassificationService {
	@Autowired
	private PcSecondLevelClassificationMapper pcSecondLevelClassificationMapper;
	@Autowired
	private IndexConfigMapper indexConfigMapper;
	@Override
	 public List<PcSecondLevelClassification> getPcSecondLevel(){
		CriteriaParameter param=new CriteriaParameter();
		List<PcSecondLevelClassification> selectByExample = pcSecondLevelClassificationMapper.selectByExample(param);
		return selectByExample;
	}
	
	public List<PcSecondLevelClassificationDto> exchange(List<PcSecondLevelClassification> pcSecondLevel){
		List<PcSecondLevelClassificationDto> result=new ArrayList<PcSecondLevelClassificationDto>();
		
		for (PcSecondLevelClassification pcSecondLevelClassification : pcSecondLevel) {
			PcSecondLevelClassificationDto pcSecondLevelClassificationDto=new PcSecondLevelClassificationDto();
			
			
			pcSecondLevelClassificationDto.setPcSecondLevelClassification(pcSecondLevelClassification);
			
			Long configId = pcSecondLevelClassification.getConfigId();
			IndexConfig indexConfig = indexConfigMapper.selectByPrimaryKey(configId);
			
			
			pcSecondLevelClassificationDto.setIndexConfig(indexConfig);
			
			
		}
		
		return result;
	}
	@Override
	public void insert(PcSecondLevelClassification pcSecondLevelClassification){
		pcSecondLevelClassificationMapper.insertSelective(pcSecondLevelClassification);
	}
}
