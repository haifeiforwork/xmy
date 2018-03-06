package com.zfj.xmy.user.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.appdev.db.common.pojo.PageBean;
import com.zfj.base.exception.BusinessException;
import com.zfj.base.util.common.StringUtil;
import com.zfj.base.util.mail.model.JMail;
import com.zfj.xmy.common.ReqData;
import com.zfj.xmy.common.ReqUtil;
import com.zfj.xmy.common.SystemConstant;
import com.zfj.xmy.common.persistence.dao.TermDataMapper;
import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.app.HtmlInDto;
import com.zfj.xmy.common.persistence.pojo.app.HtmlOut;
import com.zfj.xmy.user.service.common.CommonService;
/**
 * 
 * @Description 某些通用的service业务
 * @Author liuw
 * @Date 2017年7月21日下午4:24:56
 */
@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private TermDataMapper termDataMapper;
	
	@Override
	public TermData getAppAboutXmyH5ByVid(Long vid){
		TermData termData=null;
		ReqData reqData=new ReqData();
		reqData.putValue(SystemConstant.TERMDATA.VID, vid, SystemConstant.REQ_PARAMETER_EQ);
		PageBean pageBean=new PageBean();
		 List<TermData> selectByExampleWithBLOBs = termDataMapper.selectByExampleWithBLOBs(ReqUtil.reqParameterToCriteriaParameter(reqData), pageBean.getRowBounds());
		if(!ObjectUtils.isEmpty(selectByExampleWithBLOBs)||selectByExampleWithBLOBs.size()>=1){
			termData=selectByExampleWithBLOBs.get(0);//应该只会有一个关于H5 的页面。直接取第一个
		}
		 return termData;
	}

	@Override
	public HtmlOut modelDispat(HtmlInDto inDto) {
		String key = inDto.getKey();
		
		if (StringUtil.isEmpty(key)) {
			throw new BusinessException("请填写KEY值");
		}
		HtmlOut htmlOut = new HtmlOut();
		switch (key) {
		case SystemConstant.HTML_KEY.PROTOCOL:
			htmlOut = getprotocolurl();
			break;
		default:
			throw new BusinessException("请传入正确的KEY值");
		}
		return htmlOut;
	}

	/**
	 * 获取协议页面地址
	 * @return
	 * @Description 
	 * @date 2017年8月3日  下午4:58:35
	 * @author wy
	 * 2017
	 * @return HtmlOut
	 */
	private HtmlOut getprotocolurl() {
		return new HtmlOut("/common/protocol");
	}
	
	
}
