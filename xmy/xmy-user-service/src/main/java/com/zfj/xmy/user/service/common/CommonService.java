package com.zfj.xmy.user.service.common;

import com.zfj.xmy.common.persistence.pojo.TermData;
import com.zfj.xmy.common.persistence.pojo.app.HtmlInDto;
import com.zfj.xmy.common.persistence.pojo.app.HtmlOut;

public interface CommonService {

	/**
	 * 
	 * @Description 根据ID得到关于香满园的H5具体页面信息 
	 * @param id
	 * @return
	 * @Author liuw
	 * @Date 2017年7月21日下午4:32:21
	 */
	TermData getAppAboutXmyH5ByVid(Long id);

	/**
	 * 获取Html地址
	 * @param htmlIn
	 * @return
	 * @Description 
	 * @date 2017年8月3日  下午4:45:30
	 * @author wy
	 * 2017
	 * @return HtmlOut
	 */
	HtmlOut modelDispat(HtmlInDto htmlIn);


}
